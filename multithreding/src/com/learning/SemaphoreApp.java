package com.learning;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SemaphoreApp {

	public static void main(String[] args) throws Exception {
		
		ExecutorService exec = Executors.newCachedThreadPool();
		
		for (int i=0;i<200;i++){
			exec.submit(new Runnable() {
				
				@Override
				public void run() {
					try {
						SemaphoreConnection.getInstance().connect();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		exec.shutdown();
		exec.awaitTermination(1, TimeUnit.DAYS);
	}

}
