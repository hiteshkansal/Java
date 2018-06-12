package com.parallel.mergesort;

public class MergeSort {

	private int[] nums;
	private int[] tempArrays;

	public MergeSort(int[] nums) {
		this.nums = nums;
		tempArrays = new int[nums.length];
	}

	void parallelMergeSort(int low, int high, int numOfThread) {

		if (numOfThread <= 1) { // assign as many threads as number of
								// processors.
			mergeSort(low, high);
			return;
		}
		int middle = (low + high) / 2;

		Thread leftSorter = mergeSortParallel(low, middle, numOfThread);
		Thread rightSorter = mergeSortParallel(middle + 1, high, numOfThread);

		leftSorter.start();
		rightSorter.start();

		try {
			leftSorter.join();
			rightSorter.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		merge(low, middle, high);
	}

	private Thread mergeSortParallel(int low, int high, int numOfThread) {
		return new Thread() {

			public void run() {
				parallelMergeSort(low, high, numOfThread / 2);
			}

		};
	}

	public void mergeSort(int low, int high) {
		if (low >= high)
			return;

		int middle = (low + high) / 2;
		mergeSort(low, middle);
		mergeSort(middle + 1, high);
		merge(low, middle, high);
	}

	public void showResults() {
		for (int i = 0; i < nums.length; i++)
			System.out.print(nums[i] + "  ");
	}

	private void merge(int low, int middle, int high) {

		for (int i = low; i <= high; i++)
			tempArrays[i] = nums[i];
		int i = low;
		int j = middle + 1;
		int k = low;

		while ((i <= middle) && (j <= high)) {
			if (tempArrays[i] <= tempArrays[j]) {
				nums[k] = tempArrays[i];
				i++;
			} else {
				nums[k] = tempArrays[j];
				j++;
			}
			k++;
		}

		while (i <= middle) {
			nums[k] = tempArrays[i];
			i++;
			k++;
		}

		while (j <= high) {
			nums[k] = tempArrays[j];
			j++;
			k++;
		}
	}

}
