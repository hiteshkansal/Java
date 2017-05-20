package com.learning;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processrs implements Runnable{

	private CountDownLatch latch;
	
	public Processrs(CountDownLatch latch){
		this.latch  =latch;
	}
	@Override
	public void run() {
		System.out.println("Starting. ");
		
		try {
			Thread.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		latch.countDown();//value of count is decrement by 1 by this call
	}
	
}

public class CountdownLatches {

	public static void main(String[] args) {

		CountDownLatch latch = new CountDownLatch(3);
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		for (int i=0;i<30;i++){
			executor.submit(new Processrs(latch));
		}
		
		try {
			latch.await();	//wait until count becomes 0
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Completed");
	}

}
