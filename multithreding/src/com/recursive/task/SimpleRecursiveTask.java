package com.recursive.task;

import java.util.concurrent.RecursiveTask;

//RecursiveTask return value but RecursiveAction doesn't
public class SimpleRecursiveTask extends RecursiveTask<Integer>{

	private int work;
	public SimpleRecursiveTask(int work) {

		this.work = work;
	}
	
	@Override
	protected Integer compute() {
	
		if(work>100){
			System.out.println("Parallel execution wit work "+work);
			SimpleRecursiveTask s1 = new SimpleRecursiveTask(work/2);
			SimpleRecursiveTask s2 = new SimpleRecursiveTask(work/2);
			s1.fork();
			s2.fork();
			
			int solution=0;
			solution+=s1.join();
			solution+=s2.join();
			return solution;
					
		} else {
			System.out.println("No need for parallel: "+ work);
			return 2*work;
		}
	}

}
