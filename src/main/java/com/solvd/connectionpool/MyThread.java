package com.solvd.connectionpool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyThread implements Runnable {
	private static final Logger LOGGER = LogManager.getLogger(MyThread.class);
	private String name;
	private ConnectionPool connectionPool;
	
	public MyThread(String name, ConnectionPool cp) {
		this.name = name;
		this.connectionPool = cp;
	}


	@Override
	public void run() {
		String connection = null;
		try {
			connection = connectionPool.getConnection();
			if (connection == null) {
				LOGGER.info(this.getName()+ " wasn't able to get a connection");
			} else { 
				LOGGER.info(this.getName()+ " has been assigned connection " + connection);
			}
		} catch (InterruptedException e) {
			LOGGER.error(e);
		} 
		/*try {
			connectionPool.releaseConnection(connection);
		} catch (InterruptedException e) {
			LOGGER.error(e);
		}*/
	}
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return name;
	}

}