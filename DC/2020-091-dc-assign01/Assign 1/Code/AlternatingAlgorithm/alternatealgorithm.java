// S20200010091
// Data Structure: array
// Time complexity of algo: O(n^2)
// Space complexity of algo : O(n)

import java.util.*;

class G{
    static int order = 0;
}  

class node {
    int data;
    int modvalue;
}

class RunImpl extends Thread {
    int i, j, k;

    public RunImpl(int i, int j, int k) {
        this.i = i;
        this.j = j;
        this.k = k;
    }
    
    void receive(int i, int min) {
        alternatealgorithm.pr[i].data = min;
    }
    
    void receive(int i, int k, int min, int max) {
        alternatealgorithm.pr[i].data = min;
        alternatealgorithm.pr[k].data = max;
    }
    
    void localcomputation(int i, int j) {
        int min = Math.min(alternatealgorithm.pr[i].data, alternatealgorithm.pr[j].data);
        if (alternatealgorithm.pr[i].data > alternatealgorithm.pr[j].data)
            alternatealgorithm.pr[j].data = alternatealgorithm.pr[i].data;
        receive(i, min);
    }
    
    void localcomputation(int i, int j, int k) {
        int min = Math.min(Math.min(alternatealgorithm.pr[i].data, alternatealgorithm.pr[j].data), alternatealgorithm.pr[k].data);
        int max = Math.max(Math.max(alternatealgorithm.pr[i].data, alternatealgorithm.pr[j].data), alternatealgorithm.pr[k].data);
        int med = alternatealgorithm.pr[i].data + alternatealgorithm.pr[j].data + alternatealgorithm.pr[k].data - max - min;
        if(G.order == 0){
            alternatealgorithm.pr[i].data = min;
            alternatealgorithm.pr[j].data = med;
            alternatealgorithm.pr[k].data = max;
        }
        else{
            alternatealgorithm.pr[i].data = max;
            alternatealgorithm.pr[j].data = med;
            alternatealgorithm.pr[k].data = min;
        }
        
    }
    
    void send(int i, int j) {
        if(G.order == 0){
            localcomputation(i, j);
        }
        else{
            if(i>=0&&j>=0)
                localcomputation(i, j);
        }
    }
   
    void send(int i, int j, int k) {
        if(G.order == 0){
            localcomputation(i, j, k);
        }
        else{
            if(i>=0&&j>=0&&k>=0)
              localcomputation(i, j, k);
        }
    }
    
    public void run() {
        if(G.order == 0){
            if (k == -1) {
                send(i, j);
            } else {
                send(i, j, k);
            }
        }
        else{
            if (k == 1) {
                send(i, j);
            } else {
                send(i, j, k);
            }
        }
    }
}

public class alternatealgorithm {

    public static int n ;
    public static node pr[] ;
    public static int flag = 0;
    
    public static void main(String[] args) {
        System.out.println("\n=======================================================================\n");
        System.out.print("Enter the value of n (number of processing entities to run the algorithm on) : ");
        Scanner scn = new Scanner(System.in);
        n = scn.nextInt();
        int arr[] = new int[n];
        pr = new node[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int)(Math.floor(Math.random() * (100 - 1)+1));
            pr[i] = new node();
            pr[i].data = arr[i];
            pr[i].modvalue = i % 3;
        }
        System.out.println();

        System.out.println("The Initialized Processing Entities Before Sorting : ");
        for (int j = 0; j < n; j++) {
                System.out.print(pr[j].data + " ");
        }
        System.out.println();
        System.out.println("\nSelect The Partial Order for Sorting: \nFor 'less than partial order (<=)' enter 0\tFor 'greater than partial order (>=)'  enter 1: ");
        G.order = scn.nextInt();
        System.out.println("\n");

        final long startTime = System.currentTimeMillis();

        System.out.println("=======================================================================\n");
        System.out.println("For Iteration number: " + (0) + "\n");
        for (int j = 0; j < n; j++) {
            System.out.print("[" + pr[j].data + " | mod value = " + pr[j].modvalue + "| ]  ");
        }
        System.out.println();
        System.out.println("=======================================================================\n");
        for (int i = 0; i < n - 1; i++) {
            flag = 0;
            int n_threads = 0;
            RunImpl run[] = new RunImpl[n];
            for (int j = 0; j < n; j++) {
                if (pr[j].modvalue == 1) {
                    if (j == 0 & j + 1 < n) {
                        run[n_threads] = new RunImpl(j, j + 1, -1);
                        run[n_threads].start();
                        n_threads += 1;
                    } else if (j < n - 1) {
                        run[n_threads] = new RunImpl(j - 1, j, j + 1);
                        run[n_threads].start();
                        n_threads += 1;
                    } else {
                        run[n_threads] = new RunImpl(j - 1, j, -1);
                        run[n_threads].start();
                        n_threads += 1;
                    }
                }
            }
            for (int j = 0; j < n_threads; j++) {
                try {
                    run[j].join();
                } catch (Exception e) {
                    System.out.println("Exception Caught");
                }
            }
            for (int j = 0; j < n; j++) {
                pr[j].modvalue = (pr[j].modvalue + 2) % 3;
            }
            
            System.out.println("For Round no: " + (i + 1)+ "\n");
            for (int j = 0; j < n; j++) {
                System.out.print("[" + pr[j].data + " | mod value = " + pr[j].modvalue + "| ]  ");
            }
            System.out.println();
            System.out.println("=======================================================================\n");
        }

        final long endTime = System.currentTimeMillis();
        scn.close();
        System.out.println("Processing Entities After Sorting :");
        for (int i = 0; i < n; i++) {
            System.out.print(pr[i].data + " ");
        }
        System.out.println("\n\nTotal run time: " + (endTime - startTime)+" ms\n");
    }
}