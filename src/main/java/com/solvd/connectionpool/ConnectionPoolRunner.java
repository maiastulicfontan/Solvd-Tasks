package com.solvd.connectionpool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionPoolRunner {
	private final static Logger LOGGER =  LogManager.getLogger(ConnectionPoolRunner.class);
		public static final int THREAD_POOL_SIZE = 10;

	public static void main(String[] args) {
		ConnectionPool pool = ConnectionPool.getInstance();
		
		ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
		
		for (int i = 0; i < THREAD_POOL_SIZE; i++) {
			executor.execute(new MyThread("Thread"+i, pool));
		}
		
		try {
			executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			LOGGER.error(e);
		}
		
		
		

		
		
		
		
		

		
	}

}
