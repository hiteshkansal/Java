package com.learning;

import java.util.concurrent.Semaphore;

public class SemaphoreConnection {
	
	private static SemaphoreConnection instance = new SemaphoreConnection();
	
	private Semaphore sem = new Semaphore(10, true);
	
	private int connections = 0;
	private SemaphoreConnection(){
		
	}
	public static SemaphoreConnection getInstance(){
		return instance;
	}
	
	public void connect() throws InterruptedException{
		sem.acquire();
		try{
			doConnect();
		}finally{
			sem.release();
		}
	}
	public void doConnect() throws InterruptedException{
		synchronized (this) {
			connections++;
			System.out.println("Current connections: "+ connections);
		}
		Thread.sleep(2000);
		
		synchronized (this) {
			connections--;
		}
	}
}
