package com.parallel.mergesort;

import java.util.Random;

public class App {

	public static Random random = new Random();
	
	public static void main(String[] args) {

		int numofThreads = Runtime.getRuntime().availableProcessors();

		System.out.println("Number of processor: "+numofThreads);
		int [] numbers = createRandomArray();
		MergeSort mergeSort = new MergeSort(numbers);
		int [] parlArray = numbers;
		
		long startTime = System.currentTimeMillis();
		mergeSort.parallelMergeSort(0, numbers.length-1, numofThreads);
		long endTime = System.currentTimeMillis();
		
		System.out.printf("Time for Sequential sort: %d", endTime-startTime);
		
		startTime = System.currentTimeMillis();
		mergeSort.mergeSort(0, parlArray.length-1);
		endTime = System.currentTimeMillis();
		
		System.out.println();
		System.out.printf("Time for parallel sort: %d", endTime-startTime);
		//mergeSort.showResults();
	}

	public static int[] createRandomArray(){
		int []s = new int[100000000];
		for(int i=0;i<s.length;i++){
			s[i] = random.nextInt(20000);
		}
		return s;
	}
}
