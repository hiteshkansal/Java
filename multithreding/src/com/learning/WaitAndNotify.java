package com.learning;

import java.util.Scanner;

public class WaitAndNotify {

	public void produce() throws InterruptedException {
		synchronized (this) {
			System.out.println("Producer thread...");
			//Thread.sleep(10000);
			wait();
			System.out.println("resumed..");
		}
	}
	
	public void consume() throws InterruptedException {
		
		Scanner scanner = new Scanner(System.in);
		Thread.sleep(2000);
		
		synchronized (this) {//used same lock
			/*System.out.println("Waiting for key...");
			scanner.nextLine();*/
			System.out.println("Consumer: Return key pressed");
			notify();
			Thread.sleep(5000);
			System.out.println("Consumer:1");
			System.out.println("Consumer:2");
			
			
			for(int i=0;i<10;i++){
				System.out.print(i+" ");
			}
		}
		System.out.println("Consumer:3");
	}
	
}
