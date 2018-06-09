package com.diningphilospher;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopsticks {

	private int id;
	private Lock lock;
	
	public Chopsticks(int id) {
		this.id = id;
		this.lock = new ReentrantLock();	//we can lock in one method and unlock in other method which can not be done using synchronized blocks
	}
	
	public boolean pickUp(Philospher p, State s) throws InterruptedException{
		
		if(lock.tryLock(10, TimeUnit.MILLISECONDS)){
			System.out.println(p + " picked up "+s.toString()+" "+ this);
			return true;
		}
		return false;
	}
	public void putDown(Philospher p, State s) throws InterruptedException{
		lock.unlock();
		System.out.println(p + " put down "+s.toString()+" "+ this);
	}
	
	@Override
	public String toString() {
		return "Chopstick "+id;
	}
	
}
