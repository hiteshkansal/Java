package com.learning;

import java.util.concurrent.Exchanger;

// You can exchange values using exchangers between different threads.

class FirstThread implements Runnable{

	private int counter;
	private Exchanger<Integer> exchanger;
	
	public FirstThread(Exchanger<Integer> e) {
		this.exchanger = e;
	}
	@Override
	public void run() {

		while(true){
			counter = counter+1;;
			System.out.println("First thread increment "+ counter);
			try {
				counter = exchanger.exchange(counter);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class SecondThread implements Runnable{

	private int counter;
	private Exchanger<Integer> exchanger;
	
	public SecondThread(Exchanger<Integer> e) {
		this.exchanger = e;
	}
	@Override
	public void run() {

		while(true){
			counter = counter-1;;
			System.out.println("First thread decrement "+ counter);
			try {
				counter = exchanger.exchange(counter);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
}
public class ExchangerApp {

	public static void main(String[] args) {

		Exchanger<Integer> e = new Exchanger<>();
		new Thread(new FirstThread(e)).start();
		new Thread(new SecondThread(e)).start();
		
	}

}
