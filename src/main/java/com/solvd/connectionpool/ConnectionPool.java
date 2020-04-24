package com.solvd.connectionpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ConnectionPool {
	private final static Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
	private BlockingQueue <String> connectionPoolQueue;
	private static ConnectionPool cp;
	private static final int MAX_SIZE = 5;
	
	private ConnectionPool() {
		connectionPoolQueue = new LinkedBlockingQueue<>(MAX_SIZE);
	}
	
	public void init() throws InterruptedException{
		for (int i = 0; i < MAX_SIZE; i++) {
			connectionPoolQueue.put("Connection"+i);
		}
	}
	
	public String getConnection() throws InterruptedException {
		return connectionPoolQueue.poll(3, TimeUnit.SECONDS); //a thread will wait 3 seconds for a connection to become available 
	}
	
	public void releaseConnection(String string) throws InterruptedException{
		if (string != null) {
			connectionPoolQueue.offer(string, 3, TimeUnit.SECONDS); //a string will wait 3 seconds for a space in the blockingqueue to become available
			LOGGER.info(string+" has been released");
		}
	}
	
	/* not sure if this is a good practice, but i've read it's a way to implement  'double checked locking' principle, which
	 * provides thread-safety without the need to make the getInstance() method synchronized*/
	public static ConnectionPool getInstance() {
		if (cp == null){
			synchronized(ConnectionPool.class) { 
				if (cp == null) {
					cp = new ConnectionPool();
				}
			}
		}
		return cp;
	}
	
	public int getMAX_SIZE(){
		return ConnectionPool.MAX_SIZE;
	}
	
	@Override
	public String toString() {
		return connectionPoolQueue.toString();
	}
}
