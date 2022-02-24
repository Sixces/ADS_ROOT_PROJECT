// CS 0445 Fall 2021
// Partial implementation of the SimpleDeque class for
// Recitation Exercise 1.  Fill in the missing code for
// the methods shown.  If you wish to add any methods not
// shown (ex: private methods to aid in the implementation)
// feel free to do so.
/*
public class MySimpleDeque<T> implements DequeInterface<T>
{
    private T [] data;
    private int back;  // index of logical back item in deque
    private int size;
    private int loc;

    public MySimpleDeque(int initsize)
    {
        // Note how array is created and cast to T.
        data = (T []) new Object[initsize];
        // Back is initialized to -1 for special case when deque
        // is empty.  Be careful about this special case for both
        // adding and removing.
        back = -1;
        size = 0;
    }

    public String toString()
    {
        if (size > 0)
        {
            // Make and return single String from data stored
            // in Deque.  Proceed from front to back.
            StringBuilder B = new StringBuilder();
            for (int i = 0; i < back; i++)
            {
                B.append(data[i].toString());
                B.append(", ");
            }
            B.append(data[back].toString());
            return B.toString();
        }
        else return new String("Deque is Empty");
    }

    public void addToFront(T newEntry)
    {
        // Add to front, shifting data to make room.
        // If array is full, print out a message and do not
        // add the entry.
        if (size < data.length)
        {
            if (size == 0)
            {
                back = 0;
                data[back] = newEntry;
                size = 1;
            }
            else
            {
                for (int i = loc; i > 0; i--)
                {

                }
                data[0] = newEntry;
                size++;
                back++;
            }
        }
        else
        {
            System.out.println("No room, " + newEntry + " not added");
        }
    }

    public void addToBack(T newEntry)
    {
        // Add to back -- no shifting is required.
        // If array is full, print out a message and do not
        // add the entry.
    }

    public T removeBack()
    {
        // remove and return the item at the back of the deque
        // If deque is empty, return null
    }

    public T getBack()
    {
        // return item at the back of the deque without disturbing
        // it.  If deque is empty, return null
    }

    public T removeFront()
    {
        // remove and return the item at the front of the deque
        // If deque is empty, return null.  You will need to shift
        // the remaining items over in order to keep the front at
        // index 0
    }

    public T getFront()
    {
        // return the item at the front of the deque with disturbing
        // it.  If deque is empty, return null
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public void clear()
    {
        // reset deque to be empty and allow old objects to be
        // garbage collected.
    }
}

 */