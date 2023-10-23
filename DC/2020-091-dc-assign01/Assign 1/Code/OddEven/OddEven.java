// S20200010091
// Data Structure: array
// Time complexity of algo : O(n^2)
// Space complexity of algo : O(n)

import java.util.*;

class Node {
	int data;
	Node left;
	Node right;

	Node(int data) {
		this.data = data;
		left = null;
		right = null;
	}
}

public class OddEven {
	public static void main(String[] args) {
		int array[] = new int[50];
		System.out.println("\n=======================================================================\n");
		System.out.print("Enter the value of n (number of processing entities to run the algorithm on) : ");
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		System.out.println("The Initialized Processing Entities Before Sorting : ");
		Random rand = new Random();
		for (int i = 0; i < n; i++) {
			array[i] = rand.nextInt(500);
			System.out.print(array[i] + " ");
		}
		System.out.println();
		boolean isSorted = false;
		System.out.println(
				"\nSelect The Partial Order for Sorting: \nFor 'less than partial order (<=)' enter 0\tFor 'greater than partial order (>=)'  enter 1: ");
		int order = scn.nextInt();
		int count = 0;
		System.out.println("\n=======================================================================");
		if (order == 0) {
			long st = System.currentTimeMillis();
			while (!isSorted) {
				System.out.println("For Iteration number \t" + (count) + " : ");
				isSorted = true;
				int temp = 0;
				for (int i = 1; i <= n - 2; i = i + 2) {
					if (array[i] > array[i + 1]) {
						temp = array[i];
						array[i] = array[i + 1];
						array[i + 1] = temp;
						isSorted = false;
					}
				}
				for (int i = 0; i <= n - 2; i = i + 2) {
					if (array[i] > array[i + 1]) {
						temp = array[i];
						array[i] = array[i + 1];
						array[i + 1] = temp;
						isSorted = false;
					}
				}
				for (int i = 0; i < n; i++) {
					System.out.print(array[i] + " ");
				}
				count++;
				System.out.println("\n=======================================================================\n");
			}
			long et = System.currentTimeMillis();
			long tt = et - st;
			System.out.println("\nTotal run time: " + tt + " ms");
		}

		if (order == 1) {
			long st = System.currentTimeMillis();
			while (!isSorted) {
				System.out.println("For Iteration number \t" + (count) + " : ");
				isSorted = true;
				int temp = 0;
				for (int i = 0; i < n; i++) {
					System.out.print(array[i] + " ");
				}
				count++;
				System.out.println("\n=======================================================================\n");
				for (int i = 1; i <= n - 2; i = i + 2) {
					if (array[i] < array[i + 1]) {
						temp = array[i];
						array[i] = array[i + 1];
						array[i + 1] = temp;
						isSorted = false;
					}
				}

				for (int i = 0; i <= n - 2; i = i + 2) {
					if (array[i] < array[i + 1]) {
						temp = array[i];
						array[i] = array[i + 1];
						array[i + 1] = temp;
						isSorted = false;
					}
				}
			}
			long et = System.currentTimeMillis();
			long tt = et - st;
			System.out.println("\nTotal run time: " + tt + " ms");
		}
		scn.close();
		System.out.println("\nProcessing Entities After Sorting :");

		for (int i = 0; i < n; i++)
			System.out.print(array[i] + " ");

		System.out.println("\n");

	}
}