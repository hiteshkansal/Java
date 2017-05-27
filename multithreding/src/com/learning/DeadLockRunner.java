package com.learning;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockRunner {

	private DeadlockAccount acc1 = new DeadlockAccount();
	private DeadlockAccount acc2 = new DeadlockAccount();

	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();

	
	private void acquireLocks(Lock first, Lock second) throws InterruptedException{
		while(true){
			//Acquire locks
			
			boolean gotFirstlock = false;
			boolean gotSecondlock = false;
			try{
				gotFirstlock = first.tryLock();
				gotSecondlock = second.tryLock();
			} finally{
				if(gotFirstlock && gotSecondlock)
					return;
				if(gotFirstlock)
					first.unlock();
				if(gotSecondlock)
					second.unlock();
			}
			//Locks not acquired
			Thread.sleep(1);
			
		}
	}
	public void firstThread() throws InterruptedException {
		Random random = new Random();
		for (int i = 0; i < 10000; i++) {
			//lock1.lock();
			//lock2.lock();
			acquireLocks(lock1, lock2);
			try {
				DeadlockAccount.transfer(acc1, acc2, random.nextInt(100));
			} finally {
				lock1.unlock();
				lock2.unlock();
			}

		}
	}

	public void secondThread() throws InterruptedException {
		Random random = new Random();
		for (int i = 0; i < 10000; i++) {
			//lock2.lock();	//by changing order, deadlock occur
			//lock1.lock();
			acquireLocks(lock2, lock1);
			try {
				DeadlockAccount.transfer(acc2, acc1, random.nextInt(100));
			} finally {
				lock1.unlock();
				lock2.unlock();
			}

		}
	}

	public void finished() {
		System.out.println("Acc1 bal: " + acc1.getBalance());
		System.out.println("Acc2 bal: " + acc2.getBalance());
		System.out.println("Total bal: " + (acc1.getBalance() + acc2.getBalance()));
	}
}
