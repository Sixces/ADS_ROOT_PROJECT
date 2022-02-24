import java.util.Random;
import java.util.Scanner;

// CS 0445 Fall 2021// Recitation Exercise 7 Solutionimport java.util.*;public
class Rec7{ public static void main(String [] args)
{
    Scanner S = new Scanner(System.in);
    System.out.print("Array size > ");
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

    SimpleSorts.selectionSort(A, A.length);
    long SSrand = SimpleSorts.comps;
    if (A.length <= 100)
    {
        for (int i = 0; i < A.length; i++)
        {
            System.out.print(A[i] + " ");
            System.out.println();
        }
    }
    SimpleSorts.insertionSort(B, B.length);
    long ISrand = SimpleSorts.comps;
    if (B.length <= 100) {
        for (int i = 0; i < B.length; i++)
        {
            System.out.print(B[i] + " ");
            System.out.println();
        }
    }
    SimpleSorts.selectionSort(A, A.length);
    long SSsort = SimpleSorts.comps;
    SimpleSorts.insertionSort(B, B.length);
    long ISsort = SimpleSorts.comps;
    A = reverse(A);
    B = reverse(B);
    SimpleSorts.selectionSort(A, A.length);
    long SSrev = SimpleSorts.comps;
    SimpleSorts.insertionSort(B, B.length);
    long ISrev = SimpleSorts.comps;
    System.out.println("SelectionSort random: " + SSrand);
    System.out.println("SelectionSort sorted: " + SSsort);
    System.out.println("SelectionSort reversed: " + SSrev);
    System.out.println();
    System.out.println("InsertionSort random: " + ISrand);
    System.out.println("InsertionSort sorted: " + ISsort);
    System.out.println("InsertionSort reversed: " + SSrev);
}

public static Integer [] reverse(Integer [] A)
{
    Integer [] temp = new Integer[A.length];
    for (int i = 0; i < A.length; i++)
        temp[i] = A[A.length-1-i];return temp;} }