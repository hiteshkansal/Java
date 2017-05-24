package com.learning;

public class LowLevelSynchronizedApp {

	public static void main(String[] args) throws InterruptedException {

		final LowLevelSynchronized wn = new LowLevelSynchronized();

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					wn.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					wn.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();
	}
}
