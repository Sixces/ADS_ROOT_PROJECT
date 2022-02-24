import java.io.*;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Assig4 {

    public static Integer [] storeA;
    public static double bill = 1000000000;
    public static double trialsF;
    public static void fillRandArray(int size)
    {
        storeA = new Integer [size];
        Random F = new Random();
        int SEED = F.nextInt();
        Random R = new Random(SEED);
        for (int i = 0; i < size; i++)
        {
            int val = Math.abs(R.nextInt()) % size;
            Integer X = Integer.valueOf(val);
            storeA[i] = X;
        }
    }

    public static void fillSortedArray(int size)
    {
        storeA = new Integer [size];
        for (int i = 0, j = 1; i < size; i++, j++)
        {
            storeA[i] = j;
        }
    }

    public static void fillRevSortedArray(int size)
    {
        storeA = new Integer [size];
        for (int i = 0, j = size; i < size; i++, j--)
        {
            storeA[i] = j;
        }
    }

    public static void copyArray(Integer [] newA)
    {
        for (int i = 0; i < storeA.length; i++)
            newA[i] = storeA[i];
    }

    public static void showArray(Integer [] Z)
    {
        for (int i = 0; i < Z.length; i++)
        {
            System.out.print(Z[i] + " ");
        }
        System.out.println("\n");
    }

    public static void showArray(double [] Z)
    {
        for (int i = 0; i < Z.length; i++)
        {
            System.out.print(Z[i] + " ");
        }
        System.out.println("\n");
    }

    public static void copyArray(Integer [] newA, Integer [] A)
    {
        for (int i = 0; i < A.length; i++)
            newA[i] = A[i];
    }

    public static double [] doTrials(int size, int MIN_SIZE, int trials, String setup) {

        Integer [] A = new Integer[storeA.length], B = new Integer[storeA.length],
                C = new Integer[storeA.length], D = new Integer[storeA.length];
        double [] TIMES = new double [4];

        //QuickSort trials;
        if (size < 100000)
        {
            copyArray(A);
            if (size <= 20)
            {
                System.out.println("Algo: Simple Quicksort | Base: " + MIN_SIZE + " | Setup: " + setup + " | before: ");
                showArray(A);
            }
            double quickBefore = System.nanoTime();
            SortAlgorithms.simpQuickSort(A, A.length);
            double quickAfter = System.nanoTime();
            if (size <= 20)
            {
                System.out.println("Algo: Simple Quicksort | Base: " + MIN_SIZE + " | Setup: " + setup + " | after: ");
                showArray(A);
            }
            double runTimeQ = (quickAfter - quickBefore);
            TIMES[0] = runTimeQ;
        }

        //Med3QuickSort trials
        copyArray(B);
        if (size <= 20)
        {
            System.out.println("Algo: Median of Three | Base: " + MIN_SIZE + " | Setup: " + setup + " | before: ");
            showArray(B);
        }
        double medianBefore = System.nanoTime();
        SortAlgorithms.medQuickSort(B, B.length);
        double medianAfter = System.nanoTime();
        if (size <= 20)
        {
            System.out.println("Algo: Median of Three | Base: " + MIN_SIZE + " | Setup: " + setup + " | after: ");
            showArray(B);
        }
        double runTime3 = (medianAfter - medianBefore);
        TIMES[1] = runTime3;


        //RandQuickSort trials
        copyArray(C);
        if (size <= 20)
        {
            System.out.println("Algo: Random Pivot | Base: " + MIN_SIZE + " | Setup: " + setup + " | before: ");
            showArray(C);
        }
        double randomBefore = System.nanoTime();
        SortAlgorithms.randQuickSort(C, C.length);
        double randomAfter = System.nanoTime();
        if (size <= 20)
        {
            System.out.println("Algo: Random Pivot | Base: " + MIN_SIZE + " | Setup: " + setup + " | after: ");
            showArray(C);
        }
        double runTimeR = (randomAfter - randomBefore);
        TIMES[2] = runTimeR;


        //MergeSort Trials
        copyArray(D);
        if (size <= 20)
        {
            System.out.println("Algo: MergeSort | Base: " + MIN_SIZE + " | Setup: " + setup + " | before: ");
            showArray(D);
        }
        double mergeBefore = System.nanoTime();
        SortAlgorithms.mergeSort(D, D.length);
        double mergeAfter = System.nanoTime();
        if (size <= 20)
        {
            System.out.println("Algo: MergeSort | Base: " + MIN_SIZE + " | Setup: " + setup + " | after: ");
            showArray(D);
        }
        double runTimeM = (mergeAfter - mergeBefore);
        TIMES[3] = runTimeM;

        return TIMES;
    }

    public static void runTest(int size, int trials, double trialsF, int MIN_SIZE, BufferedWriter bw) throws IOException {
        SortAlgorithms.setMinSize(MIN_SIZE);

        String SQ = "Simple QuickSort",
                MT = "Median of Three",
                RP = "Random Pivot",
                MS = "MergeSort";
        double aSQ, aMT, aRP, aMS;

        double [] TIMESOLD = new double[4];
        double [] TIMESNEW = new double[4];

        Arrays.fill(TIMESOLD, 0);

        for (int i = 0; i < trials; i++)
        {
            fillRandArray(size);
            TIMESNEW = doTrials(size, MIN_SIZE, trials, "Random");

            for (int j = 0; j < 4; j++)
            {
                TIMESOLD[j] += TIMESNEW[j];
            }
/*
            System.out.println("TIMESOLD: ");
            showArray(TIMESOLD);
            System.out.println("TIMESNEW: ");
            showArray(TIMESNEW);

 */
        }



        aSQ = (TIMESOLD[0] / trialsF) / bill;
        aMT = (TIMESOLD[1] / trialsF) / bill;
        aRP = (TIMESOLD[2] / trialsF) / bill;
        aMS = (TIMESOLD[3] / trialsF) / bill;

        writeToFile(bw, size, trials, MIN_SIZE, "Random", SQ, MT, RP, MS, aSQ, aMT, aRP, aMS);

        Arrays.fill(TIMESOLD, 0);

        for (int i = 0; i < trials; i++)
        {
            fillSortedArray(size);
            TIMESNEW = doTrials(size, MIN_SIZE, trials, "Sorted");

            for (int j = 0; j < 4; j++)
            {
                TIMESOLD[j] += TIMESNEW[j];
            }
        }

        aSQ = (TIMESOLD[0] / trialsF) / bill;
        aMT = (TIMESOLD[1] / trialsF) / bill;
        aRP = (TIMESOLD[2] / trialsF) / bill;
        aMS = (TIMESOLD[3] / trialsF) / bill;

        writeToFile(bw, size, trials, MIN_SIZE, "Sorted", SQ, MT, RP, MS, aSQ, aMT, aRP, aMS);

        Arrays.fill(TIMESOLD, 0);

        for (int i = 0; i < trials; i++)
        {
            fillRevSortedArray(size);
            TIMESNEW = doTrials(size, MIN_SIZE, trials, "Reverse");

            for (int j = 0; j < 4; j++)
            {
                TIMESOLD[j] += TIMESNEW[j];
            }
        }

        aSQ = (TIMESOLD[0] / trialsF) / bill;
        aMT = (TIMESOLD[1] / trialsF) / bill;
        aRP = (TIMESOLD[2] / trialsF) / bill;
        aMS = (TIMESOLD[3] / trialsF) / bill;

        writeToFile(bw, size, trials, MIN_SIZE, "Reverse", SQ, MT, RP, MS, aSQ, aMT, aRP, aMS);
    }


    public static void writeToFile(BufferedWriter bw, int size, int trials, int MIN_SIZE, String setup,
                                   String SQ, String MT, String RP, String MS, double aSQ, double aMT,
                                   double aRP, double aMS) throws IOException {
        if (size < 100000)
        {
            bw.write("Algorithm: " + SQ +
                    "\n Array Size: " + size +
                    "\n Base Case Less Than: " + MIN_SIZE +
                    "\n Data Setup: " + setup +
                    "\n Number of trials: " + trials +
                    "\n Average Time per trial: " + aSQ + " sec." +
                    "\n");
            bw.newLine();
        }

        bw.write("Algorithm: " + MT +
                "\n Array Size: " + size +
                "\n Base Case Less Than: " + MIN_SIZE +
                "\n Data Setup: " + setup +
                "\n Number of trials: " + trials +
                "\n Average Time per trial: " + aMT + " sec." +
                "\n");
        bw.newLine();

        bw.write("Algorithm: " + RP +
                "\n Array Size: " + size +
                "\n Base Case Less Than: " + MIN_SIZE +
                "\n Data Setup: " + setup +
                "\n Number of trials: " + trials +
                "\n Average Time per trial: " + aRP + " sec." +
                "\n");
        bw.newLine();

        bw.write("Algorithm: " + MS +
                "\n Array Size: " + size +
                "\n Base Case Less Than: " + MIN_SIZE +
                "\n Data Setup: " + setup +
                "\n Number of trials: " + trials +
                "\n Average Time per trial: " + aMS + " sec." +
                "\n");
        bw.newLine();
    }

    public static void main(String[] args) throws IOException {

        Scanner S = new Scanner(System.in);
        System.out.println("Array size > ");
        int size = S.nextInt();
        System.out.println("Number of trials > ");
        int trials = S.nextInt();
        trialsF = trials;
        System.out.println("Output file name > ");
        String fString = S.next();
        File fName = new File(fString);

        BufferedWriter bw = new BufferedWriter(new FileWriter(fName));

        ///int size = 10;
        //int trials = 2;

        runTest(size, trials, trialsF, 3, bw);
        runTest(size, trials, trialsF, 15, bw);
        runTest(size, trials, trialsF, 25, bw);
        runTest(size, trials, trialsF, 50, bw);
        runTest(size, trials, trialsF, 100, bw);
        runTest(size, trials, trialsF, 500, bw);

        System.out.println("Run completed");

        bw.close();

    }
}
