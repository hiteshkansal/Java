package com.learning;

public class ReentrantApp {

	public static void main(String[] args) throws InterruptedException {

		final ReentrantRunner runner = new ReentrantRunner();
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					runner.firstThread();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					runner.secondThread();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();
		
		runner.finished();
	}

}
