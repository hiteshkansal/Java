package com.learning;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class FirstWorker implements Runnable{

	private BlockingQueue<Person> blockingqueue;
	
	public FirstWorker(BlockingQueue<Person> blockingqueue) {
		this.blockingqueue = blockingqueue;
	}
	@Override
	public void run() {

		try {
			blockingqueue.put(new Person("Hitesh", 28));
			Thread.sleep(1000);
			blockingqueue.put(new Person("Pooja", 25));
			Thread.sleep(1000);
			blockingqueue.put(new Person("Sunny", 30));
			blockingqueue.put(new Person("Budda", 90));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class SecondWorker implements Runnable{

	private BlockingQueue<Person> blockingqueue;
	
	public SecondWorker(BlockingQueue<Person> blockingqueue) {
		this.blockingqueue = blockingqueue;
	}
	@Override
	public void run() {

		try {
			Thread.sleep(5000);
			System.out.println(blockingqueue.take());
			System.out.println(blockingqueue.take());
			Thread.sleep(1000);
			System.out.println(blockingqueue.take());
			System.out.println(blockingqueue.take());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Person implements Comparable<Person>{
	private String name;
	private int age;
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	@Override
	public int compareTo(Person person) {
		return name.compareTo(person.getName());
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return name+"-"+age;
	}
}
public class PriorityQueueApp {

	public static void main(String[] args) {

		BlockingQueue<Person> queue = new PriorityBlockingQueue<>();
		new Thread(new FirstWorker(queue)).start();;
		new Thread(new SecondWorker(queue)).start();;
	}

}
