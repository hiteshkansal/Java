package com.recursive.action;

import java.util.concurrent.ForkJoinPool;

public class App {

	public static void main(String[] args) {

		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		SimpleRecursiveAction s = new SimpleRecursiveAction(150);
		pool.invoke(s);
	}

}
