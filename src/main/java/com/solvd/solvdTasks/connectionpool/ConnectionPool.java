package com.solvd.solvdTasks.connectionpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ConnectionPool {
	private final static Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
	private BlockingQueue <String> connectionPoolQueue;
	private static ConnectionPool cp;
	private static AtomicInteger currentConnections = new AtomicInteger();
	private static final int MAX_SIZE = 5;
	
	private ConnectionPool() {
		connectionPoolQueue = new LinkedBlockingQueue<>(MAX_SIZE);
	}
	
	public void init() throws InterruptedException{
		connectionPoolQueue.put("Connection "+currentConnections.get());
	}
	
	public String getConnection() throws InterruptedException {
		if (connectionPoolQueue.peek() == null && currentConnections.get() < MAX_SIZE) {
				synchronized (ConnectionPool.class) {
					if (connectionPoolQueue.peek() == null && currentConnections.get() < MAX_SIZE) {
						cp.init();
						currentConnections.getAndIncrement();
					}
				}		
		}
		return connectionPoolQueue.poll(3, TimeUnit.SECONDS); //a thread will wait 3 seconds for a string (connection) to become available 
	}
	
	public void releaseConnection(String string) throws InterruptedException{
		if (string != null) {
			connectionPoolQueue.offer(string, 3, TimeUnit.SECONDS); //a string will wait 3 seconds for a space in the blocking queue to become available
			LOGGER.info(string+" has been released");
		}
	}
	
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
	
	
	@Override
	public String toString() {
		return connectionPoolQueue.toString();
	}
}
