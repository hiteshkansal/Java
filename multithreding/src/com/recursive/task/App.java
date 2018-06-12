package com.recursive.task;

import java.util.concurrent.ForkJoinPool;

public class App {

	public static void main(String[] args) {

		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		SimpleRecursiveTask s = new SimpleRecursiveTask(150);
		System.out.println(pool.invoke(s));
	}

}
