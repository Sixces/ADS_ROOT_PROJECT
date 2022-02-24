// CS 0445 Fall 2021
// Solution to Recitation Exercise 1

public class SimpleDequeS<T> implements DequeInterface<T>
{
    private T [] data;
    private int back;
    private int size;

    public SimpleDeque(int initsize)
    {
        data = (T []) new Object[initsize];
        back = -1;
        size = 0
    }
}
