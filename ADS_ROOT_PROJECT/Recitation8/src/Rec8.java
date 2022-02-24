import java.util.Random;
import java.util.Scanner;

public class Rec8 {

    public static void main(String[] args)
    {
        Scanner S = new Scanner(System.in);
        System.out.println("Array size > ");
        int size = S.nextInt();
        S.nextLine();
        Integer [] A = new Integer[size];
        Integer [] B = new Integer[size];
        Random R = new Random();

        for (int i = 0; i < A.length; i++)
        {
            int val = R.nextInt();
            Integer X = Integer.valueOf(val);
            Integer Y = Integer.valueOf(val);
            A[i] = X;
            B[i] = Y;
        }

        float timeBefA = System.nanoTime();
        TextMerge.mergeSort(A, A.length);
        if (A.length <= 100)
        {
            for (int i = 0; i < A.length; i++)
            {
                System.out.println(A[i] + " ");
                //System.out.println();
            }
        }
        float timeAftA = System.nanoTime();
        float runTimeA = (timeAftA - timeBefA) / 1000000000;

        float timeBefB = System.nanoTime();
        TextMerge.mergeSortB(B, B.length);
        if (B.length <= 100)
        {
            for (int i = 0; i < B.length; i++)
            {
                System.out.println(B[i] + " ");
                //System.out.println();
            }
        }
        float timeAftB = System.nanoTime();
        float runTimeB = (timeAftB - timeBefB) / 1000000000;

        System.out.println("MergeSort random: " + runTimeA);
        System.out.println();
        System.out.println("MergeSortB random: " + runTimeB);

    }
}
