// CS 0445 Fall 2021
// I have taken the author's selectionSort method and created a second
// version of it that uses the "old style" Java generics.  I have removed
// the author's original comments to save space.  See SortArray.java for
// the original comments.

public class SortArrayVersions
{
    // Carrano original generic selectionSort
    public static <T extends Comparable<? super T>> void selectionSort(T[] a, int n)
    {
        for (int index = 0; index < n - 1; index++)
        {
            int indexOfNextSmallest = getIndexOfSmallest(a, index, n - 1);
            swap(a, index, indexOfNextSmallest);
        }
    }

    private static <T extends Comparable<? super T>>
    int getIndexOfSmallest(T[] a, int first, int last)
    {
        T min = a[first];
        int indexOfMin = first;
        for (int index = first + 1; index <= last; index++)
        {
            if (a[index].compareTo(min) < 0)
            {
                min = a[index];
                indexOfMin = index;
            }

        }
        return indexOfMin;
    }

    // Version of Carrano selectionSort using "old style" Java generics.  Note
    // how the syntax is much simpler.  However, this version will give a warning
    // when compiled because there is no way to check at compile time whether the
    // array contains consistent data.
    public static void selectionSortB(Comparable [] a, int n)
    {
        for (int index = 0; index < n - 1; index++)
        {
            int indexOfNextSmallest = getIndexOfSmallestB(a, index, n - 1);
            swap(a, index, indexOfNextSmallest);

        }
    }

    private static int getIndexOfSmallestB(Comparable [] a, int first, int last)
    {
        Comparable min = a[first];
        int indexOfMin = first;
        for (int index = first + 1; index <= last; index++)
        {
            // The call of compareTo below generates a warning since it is using the old
            // style Comparable interace and not checking the actual types of the objects
            // being compared.
            if (a[index].compareTo(min) < 0)
            {
                min = a[index];
                indexOfMin = index;
            }

        }
        return indexOfMin;
    }

    private static void swap(Object[] a, int i, int j)
    {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    } // end swap
} // end SortArray