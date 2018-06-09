package com.diningphilospher;

import java.util.Random;

public class Philospher implements Runnable {

	private int id;
	private Chopsticks leftChopsticks;
	private Chopsticks rightChopsticks;
	private Random random;
	private int eatingcounter;
	private volatile boolean isFull = false;

	public Philospher(int id, Chopsticks leftChop, Chopsticks rightChop) {
		this.id = id;
		this.leftChopsticks = leftChop;
		this.rightChopsticks = rightChop;
		this.random = new Random();
	}

	@Override
	public void run() {
		try {
			while (!isFull) {
				think();
				if (leftChopsticks.pickUp(this, State.LEFT)) {
					if (rightChopsticks.pickUp(this, State.RIGHT)) {
						eat();
						rightChopsticks.putDown(this, State.RIGHT);
					}
					leftChopsticks.putDown(this, State.LEFT);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void think() throws InterruptedException {
		System.out.println(this + " is thinking...");
		Thread.sleep(random.nextInt(1000));
	}

	private void eat() throws InterruptedException {
		System.out.println(this + " is eating...");
		this.eatingcounter++;
		Thread.sleep(random.nextInt(1000));
	}

	public int getCounter() {
		return this.eatingcounter;
	}

	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}

	@Override
	public String toString() {

		return "Philospher " + id;
	}
}
