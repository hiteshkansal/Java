package com.learning;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableAndFuture {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newCachedThreadPool();
		Future<Integer> future = executor.submit(new Callable<Integer>(){

			@Override
			public Integer call() throws Exception {
				Random random = new Random();
				int d = random.nextInt(4000);
				
				if(d>2000){
					throw new IOException("Sleeping for too long");
				}
				System.out.println("Starting....");
				Thread.sleep(d);
				System.out.println("Finished....");
				return d;
			}
			
		});
		executor.shutdown();
		try {
			System.out.println("Result: "+future.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

}
