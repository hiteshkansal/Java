package com.learning;

class Runner1 implements Runnable{

	@Override
	public void run() {

		for(int i=0;i<10;i++){
			System.out.println("Thread 1: "+i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}

class Runner2 implements Runnable{

	@Override
	public void run() {

		for(int i=0;i<50;i++){
			System.out.println("Thread 2: "+i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}

public class JoinMethod {

	public static void main(String[] args) throws InterruptedException {

		Thread t1 = new Thread(new Runner1());
		Thread t2 = new Thread(new Runner2());
		t1.start();
		t2.start();
		
		t1.join();
		//t2.join();
		System.out.println("Finished Task..");
	}

}
