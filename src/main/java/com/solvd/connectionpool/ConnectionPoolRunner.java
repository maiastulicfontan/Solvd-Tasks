package com.solvd.connectionpool;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionPoolRunner {
	private final static Logger LOGGER =  LogManager.getLogger(ConnectionPoolRunner.class);

	public static void main(String[] args) {
		ConnectionPool pool = ConnectionPool.getInstance();
		List <MyThread> threads = new ArrayList<MyThread>();
		
		for (int i = 0; i <= pool.getMAX_SIZE(); i++) {
			threads.add(new MyThread("Thread"+i, pool));
		}
		
		
		try {
			pool.init();
		} catch (InterruptedException e) {
			LOGGER.error(e);
		}
		
		
		threads.forEach(thread -> thread.run());
		
		
		LOGGER.info("Connections in connection pool:" +pool);
		
		
		threads.get(5).run();
		
		

		
	}

}
