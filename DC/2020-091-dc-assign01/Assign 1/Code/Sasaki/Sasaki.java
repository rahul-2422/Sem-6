// S20200010091
// Data Structure: linked list
// Time complexity of algo : O(n^2)
// Space complexity of algo : O(n)

import java.util.*;


class pair {
    public int first, second, area;
    public int marked1, marked2;
}


class RunImpl extends Thread {
    private int i, j, order;
    public RunImpl(int i, int j, int order) {
        this.i = i;
        this.j = j;
        this.order = order;
    }

    void receive(pair pr) {
        Sasaki.pr[i] = pr;
    }

     
    void localcomputation(pair pr, int j) {
        if (order == 0 && pr.second > Sasaki.pr[j].first) {
            if (pr.marked2 == 1) {
                Sasaki.pr[j].area -= 1;
            }
            if (Sasaki.pr[j].marked1 == 1) {
                Sasaki.pr[j].area += 1;
            }
            int tempval = Sasaki.pr[j].first;
            Sasaki.pr[j].first = pr.second;
            pr.second = tempval;
            int tempmarked = pr.marked2;
            pr.marked2 = Sasaki.pr[j].marked1;
            Sasaki.pr[j].marked1 = tempmarked;
        }
        if (order == 1 && pr.second < Sasaki.pr[j].first) {
            if (pr.marked2 == 1) {
                Sasaki.pr[j].area -= 1;
            }
            if (Sasaki.pr[j].marked1 == 1) {
                Sasaki.pr[j].area += 1;
            }
            int tempval = Sasaki.pr[j].first;
            Sasaki.pr[j].first = pr.second;
            pr.second = tempval;
            int tempmarked = pr.marked2;
            pr.marked2 = Sasaki.pr[j].marked1;
            Sasaki.pr[j].marked1 = tempmarked;
        }
        
        
        receive(pr);
    }
    
    void send(pair pr, int j) {

        localcomputation(pr, j);
    }

    public void run() {
        try {

            send(Sasaki.pr[i], j);
            //Sasaki.flag += 1;
        } catch (Exception e) {
            System.out.println("Exception is caught");
            System.out.println(e);
        }
    }
}

class Sasaki {
    public static int flag = 0;
    public static int n;
    public static int arr[];
    public static pair pr[];
    public static int order;

    public static void main(String[] args) {
        System.out.println("\n=======================================================================n");
        System.out.print("Enter the value of n (number of processing entities to run the algorithm on) :");
        Scanner scn = new Scanner(System.in);
        n = scn.nextInt();
        arr = new int[n];
        pr = new pair[n];

        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.floor(Math.random() * (100 - 1)+1));
            pr[i] = new pair();
            pr[i].first = arr[i];
            pr[i].second = arr[i];
            pr[i].area = 0;
            pr[i].marked1 = 0;
            pr[i].marked2 = 0;
        }
        pr[0].area = -1;
        pr[0].marked2 = 1;
        pr[n - 1].marked1 = 1;
        
        
        System.out.println("The Initialized Processing Entities Before Sorting : ");
        for (int j = 0; j < n; j++) {
            System.out.print(pr[j].first + " ");
        }
        System.out.println();
        
        System.out.println("\nSelect The Partial Order for Sorting: \nFor 'less than partial order (<=)' enter 0\tFor 'greater than partial order (>=)'  enter 1:");
        order = scn.nextInt();
        System.out.println();

        final long startTime = System.currentTimeMillis();

        System.out.println("=======================================================================n");
        System.out.println("For Round number : " + 0);
        for (int j = 0; j < n; j++) {
            String marked1="";
            String marked2="";
            if(pr[j].marked1==1)
                marked1="*";
            if(pr[j].marked2==1)
                marked2="*";
            if (j == 0){
                System.out.print("[" + pr[j].second +marked2 +" (area = " + pr[j].area + ")]  ");
            }
            else if (j == n - 1){
                System.out.println("[" + pr[j].first + marked1+" (area = " + pr[j].area + ")]  ");
                System.out.println("=======================================================================n");
            }
            else{
                System.out.print("[" + pr[j].first + marked1+"," + pr[j].second + marked2+" (area = " + pr[j].area + ")]  ");
            }
        }

        for (int i = 0; i < (n - 1); i++) {
            flag = 0;
            RunImpl run[] = new RunImpl[n];
            for (int j = 0; j < n - 1; j++) {
                run[j] = new RunImpl(j, j + 1, order);
                run[j].start();
            }
            for (int j = 0; j < n - 1; j++) {
                try {
                    run[j].join();
                } catch (Exception e) {
                    System.out.println("Exception caught : " + e);
                }
            }
            
            for (int j = 1; j < n - 1; j++) {
                if (order == 0 && pr[j].first > pr[j].second) {
                    int temp = pr[j].first;
                    pr[j].first = pr[j].second;
                    pr[j].second = temp;
                    temp = pr[j].marked1;
                    pr[j].marked1 = pr[j].marked2;
                    pr[j].marked2 = temp;
                }
                if (order == 1 && pr[j].first < pr[j].second) {
                    int temp = pr[j].first;
                    pr[j].first = pr[j].second;
                    pr[j].second = temp;
                    temp = pr[j].marked1;
                    pr[j].marked1 = pr[j].marked2;
                    pr[j].marked2 = temp;
                }
            }
            
            System.out.println("For iteration number: " + (i + 1));
            for (int j = 0; j < n; j++) {
                String marked1="";
                String marked2="";
                if(pr[j].marked1==1)
                    marked1="*";
                if(pr[j].marked2==1)
                    marked2="*";
                if (j == 0){
                    System.out.print("[" + pr[j].second +marked2 +" (area = " + pr[j].area + ")]  ");
                }
                else if (j == (n - 1)){
                    System.out.println("[" + pr[j].first + marked1+" (area = " + pr[j].area + ")]  ");
                    System.out.println("=======================================================================n");
                }
                else{
                    System.out.print("[" + pr[j].first + marked1+"," + pr[j].second + marked2+" (area = " + pr[j].area + ")]  ");
                }
            }
        }

        final long endTime = System.currentTimeMillis();
        scn.close();
        System.out.println("Processing Entities After Sorting :");
        for (int i = 0; i < n; i++) {
            if (i == 0)
            System.out.print(pr[i].second + " ");
            else if (i == n - 1)
            System.out.println(pr[i].first + "\n");
            else if (pr[i].area < 0)
            System.out.print(pr[i].second + " ");
            else
            System.out.print(pr[i].first + " ");
        }
        System.out.println("Total run time: " + (endTime - startTime)+" ms\n");
    }
}