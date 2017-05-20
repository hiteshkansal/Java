package com.learning;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processors implements Runnable{

	private int id;
	
	public Processors(int id){
		this.id=id;
	}
	@Override
	public void run() {
		System.out.println("Starting: "+id);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Completed: "+id);
	}
	
}
public class ThreadPools {

	public static void main(String[] args) {

		ExecutorService executer = Executors.newFixedThreadPool(2);	//"2" no of threads
		
		for(int i=0;i<5;i++){
			executer.submit(new Processors(i));
		}
		
		executer.shutdown();
		
		System.out.println("All tasks submitted");
		
		try {
			executer.awaitTermination(1, TimeUnit.DAYS);	//Code waits here
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("All tasks completed");
	}

}
