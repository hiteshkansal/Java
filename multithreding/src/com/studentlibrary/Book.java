package com.studentlibrary;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Book {
	
	private int id;
	private Lock lock;
	
	public Book(int id) {
		this.id = id;
		this.lock = new ReentrantLock();	//we can lock in one method and unlock in other method which can not be done using synchronized blocks
	}

	public void read(Student s) throws InterruptedException{
		lock.tryLock(1, TimeUnit.MINUTES);
		//lock.lock();
		System.out.println(s+" start reading "+ this);
		Thread.sleep(2000);
		System.out.println(s+" has finished reading "+ this);
		lock.unlock();
		
	}
	
	@Override
	public String toString() {
		return "Book "+id;
	}
}
