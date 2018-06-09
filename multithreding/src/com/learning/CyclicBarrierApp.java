package com.learning;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Worker1 implements Runnable{

	private int id;
	private Random random;
	private CyclicBarrier cyclicBarrier;
	
	Worker1(int id, CyclicBarrier cyclicBarrier){
		this.id = id;
		this.cyclicBarrier = cyclicBarrier;
		this.random= new Random();
	}
	
	@Override
	public void run() {
		doWork();
	}

	private void doWork() {
		System.out.println("Thread "+id+" started");
		try {
			Thread.sleep(random.nextInt(3000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread "+id+" Finished");
		try {
			cyclicBarrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}// will wait here until all thread finished
		System.out.println("After Await..");
	}
	
	public String toString(){
		return ""+this.id;
	}
	
}

public class CyclicBarrierApp {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newFixedThreadPool(5);
		CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() { //5 task are created and this run will be called when all thread finishes or waiting at awaits()
			
			@Override
			public void run() {
				System.out.println("All Finished");
			}
		});
		for(int i=0;i<5;i++)
			executorService.execute(new Worker1(i+1, barrier));
		executorService.shutdown();
	}

}
