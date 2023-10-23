// S20200010176
// Time complexity : O(n^2)
// Space complexity : O(1)

import java.util.*; 

class Node{
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data=data;
        left=null;
        right=null;
    }
}

public class OddEven{
	public static void main(String[] args)
	{
        int arr[]=new int[50];
		System.out.println("\n----------------------------------------------------------------------------------------------------------------------------\n");
        System.out.print("Enter n (processing entities) : ");
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        System.out.println("Entities Before Applying Sorting : ");
		Random rand = new Random(); 
        for(int i=0;i<n;i++){
			arr[i]=rand.nextInt(500); 
			System.out.print(arr[i] + " ");
		}
        System.out.println();
		boolean isSorted = false; 
        System.out.println("\nParity selection: \nFor '<=' enter 0\nFor '>='  enter 1: ");
        int parity = scn.nextInt();
		int count = 0;
		System.out.println("\n----------------------------------------------------------------------------------------------------------------------------");
        if(parity == 0){
			long st = System.currentTimeMillis(); 
            while (!isSorted) {
				System.out.println("For Iteration " + (count) + " : ");
			    isSorted = true;
			    int temp = 0;
			    for (int i = 1; i <= n - 2; i = i + 2) {
				    if (arr[i] > arr[i + 1]) {
					    temp = arr[i];
					    arr[i] = arr[i + 1];
					    arr[i + 1] = temp;
					    isSorted = false;
				    }
			    }
			    for (int i = 0; i <= n - 2; i = i + 2) {
				    if (arr[i] > arr[i + 1]) {
					    temp = arr[i];
					    arr[i] = arr[i + 1];
					    arr[i + 1] = temp;
					    isSorted = false;
				    }
			    }
                for (int i = 0; i < n; i++){
                    System.out.print( arr[i] + " ");
                }
				count++;
                System.out.println("\n----------------------------------------------------------------------------------------------------------------------------\n");
		    }
            long et = System.currentTimeMillis();
            long tt = et - st; 
            System.out.println("\nTotal execution time: " + tt + " milliseconds");
        }

        if(parity == 1){
			long st = System.currentTimeMillis(); 
            while (!isSorted) {
				System.out.println("For Iteration " + (count) + " : ");
			    isSorted = true;
			    int temp = 0;
                for (int i = 0; i < n; i++){
                    System.out.print( arr[i] + " ");
                }
				count++;
                System.out.println("\n----------------------------------------------------------------------------------------------------------------------------\n");
			    for (int i = 1; i <= n - 2; i = i + 2) {
				    if (arr[i] < arr[i + 1]) {
					    temp = arr[i];
					    arr[i] = arr[i + 1];
					    arr[i + 1] = temp;
					    isSorted = false;
				    }
			    }

			    for (int i = 0; i <= n - 2; i = i + 2) {
				    if (arr[i] < arr[i + 1]) {
					    temp = arr[i];
					    arr[i] = arr[i + 1];
					    arr[i + 1] = temp;
					    isSorted = false;
				    }
			    }
		    }
			long et = System.currentTimeMillis();
            long tt = et - st; 
            System.out.println("\nTotal execution time: " + tt + " milliseconds");
        }
		scn.close();
		System.out.println("\nEntities After Applying Sorting : ");

		for (int i = 0; i < n; i++)
			System.out.print( arr[i] + " ");

		System.out.println("\n");

	}
}