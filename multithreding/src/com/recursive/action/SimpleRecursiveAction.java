package com.recursive.action;

import java.util.concurrent.RecursiveAction;

//RecursiveTask return value but RecursiveAction doesn't
public class SimpleRecursiveAction extends RecursiveAction{

	private int work;
	public SimpleRecursiveAction(int work) {

		this.work = work;
	}
	
	@Override
	protected void compute() {
	
		if(work>100){
			System.out.println("Parallel execution wit work "+work);
			SimpleRecursiveAction s1 = new SimpleRecursiveAction(work/2);
			SimpleRecursiveAction s2 = new SimpleRecursiveAction(work/2);
			s1.fork();
			s2.fork();
		} else 
			System.out.println("No need for parallel: "+ work);
		
	}

}
