import java.util.Arrays;

public class MyDeque<T> implements DequeInterface<T>
{
    protected T [] data;
    protected int front;
    protected int back;
    protected int size;

    public MyDeque(int initsize)
    {
        data = (T []) new Object[initsize];
        front = -1;
        back = -1;
        size = 0;
    }

    public MyDeque(MyDeque<T> old)
    {
        data = (T []) new Object[old.size];
        if (old.size > 0)
        {
            front = 0;
            back = old.size-1;
            size = old.size;


            for (int i = front; i <= old.size-1; i++)
            {
                data[i] = old.data[i];
            }
            if(back != old.back) {
                back++;
            }
        }
        else
            System.out.println("Requested Deque is empty");
    }


    public String toString()
    {
        if (size > 0)
        {
            StringBuilder B = new StringBuilder();
            for (int i = front; i != back; i++)
            {
                //System.out.println(i);

                B.append(data[i].toString());
                B.append(", ");
                if (i == data.length-1)
                {
                    i = -1;
                }
            }
            B.append(data[back]);
            return B.toString();
        }
        else return new String("Deque is Empty");
    }

    public void addToFront(T newEntry)
    {
        if(isArrayFull())
        {
            doubleCapacity();
        }

        if (size < data.length)
        {
            if (size == 0)
            {
                front = 0;
                back = 0;
                data[front] = newEntry;
                size = 1;
            }
            else
            {
                if (front > 0)
                {
                    front--;
                    data[front] = newEntry;
                    size++;
                }
                else
                {
                    front = (data.length-1);
                    data[front] = newEntry;
                    size++;
                }
            }
        }
    }

    public void addToBack(T newEntry)
    {
        if(isArrayFull())
        {
            doubleCapacity();
        }

        if (size < data.length)
        {
            if (size == 0)
            {
                front = 0;
                back = 0;
                data[back] = newEntry;
                size = 1;
            }
            else
            {
                if(back < data.length-1)
                {
                    back++;
                }
                else
                {
                    back = 0;
                }
                data[back] = newEntry;
                size++;
            }
        }
        else
        {
            System.out.println("Nope");
        }
    }

    public T removeFront()
    {
        if (size > 0)
        {
            T temp = data[front];
            data[front] = null;
            size--;
            if (front == data.length-1)
            {
                front = 0;
            }
            else
            {
                front++;
            }
            return temp;
        }
        else
        {
            return null;
        }
    }

    public T removeBack()
    {
        if (size > 0)
        {
            T temp = data[back];
            data[back] = null;
            size--;
            if (back == 0)
            {
                back = data.length-1;
            }
            else
            {
                back--;
            }
            return temp;
        }
        else
        {
            return null;
        }
    }

    public T getFront()
    {
        return null;
    }

    public T getBack()
    {
        return null;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public void clear()
    {
        data = (T []) new Object[data.length];
        back = -1;
        size = 0;
    }

    public boolean equals(MyDeque<T> rhs)
    {
        int check = 0;

        if (size > 0)
        {

            StringBuilder B = new StringBuilder();
            StringBuilder D = new StringBuilder();

            if (front < back)
            {
                for (int i = front; i <= back; i++)
                {
                    B.append(data[i].toString());
                }
            }
            else
            {
                for (int i = front; i <= data.length-1; i++)
                {
                    B.append(data[i].toString());
                }

                for (int i = 0; i <= back; i++)
                {
                    B.append(data[i].toString());
                }
            }

            if (rhs.front < rhs.back)
            {
                for (int i = rhs.front; i <= rhs.back; i++)
                {
                    D.append(rhs.data[i].toString());
                }
            }
            else
            {
                for (int i = rhs.front; i <= rhs.data.length-1; i++)
                {
                    D.append(rhs.data[i].toString());
                }

                for (int i = 0; i <= rhs.back; i++)
                {
                    D.append(rhs.data[i].toString());
                }

            }
            return B.toString().equals(D.toString());
        }
        else
        {
            return false;
        }
    }

    public int size()
    {
        return size;
    }

    public int capacity()
    {
        return data.length;
    }

    private boolean isArrayFull()
    {
        return size >= data.length;
    }

    private void doubleCapacity()
    {
        T [] dataTemp = Arrays.copyOf(data, data.length);
        int newLength = 2 * data.length;

        data = (T []) new Object[newLength];

        if (front < back)
        {
            for(int i=front; i<=back; i++)
            {
                data [i] = dataTemp [i];
            }
        }
        else
        {

            for(int i=dataTemp.length-1; i>front; i--)
            {
                data [i+(newLength/2)] = dataTemp[i];
            }

            for(int i=0; i<=back; i++)
            {
                data [i] = dataTemp[i];
            }
        }
    }
}
