// CS 0445 Fall 2021
// Solution to Recitation Exercise 1

public class SimpleDeque<T> implements DequeInterface<T>
{
	private T [] data; 
	private int back;
	private int size;

	public SimpleDeque(int initsize)
	{
		data = (T []) new Object[initsize];
		back = -1;
		size = 0;
	}
	
	// Note that we want commas between items, so we add a comma after
	// each item up until the 2nd last item.  The last item is output
	// without a comma outside of the loop.
	public String toString()
	{
		if (size > 0)
		{
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

	// Note special case for empty Deque. In the normal case
	// we shift over to add the new item at index 0.
	public void addToFront(T newEntry)
	{
		if (size < data.length)
		{
			if (size == 0) // special case for empty Deque
			{
				back = 0;
				data[back] = newEntry;
				size = 1;
			}
			else
			{
				shiftRight(size);
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
	
	// No shifting required for this method, but we still have the
	// special case for the empty Deque.
	public void addToBack(T newEntry)
	{
		if (size < data.length)
		{
			if (size == 0) // special case for empty Deque
			{
				back = 0;
				data[back] = newEntry;
				size = 1;
			}
			else
			{
				back++;
				data[back] = newEntry;
				size++;
			}
		}
		else
		{
			System.out.println("No room, " + newEntry + " not added");
		}
	}
	
	// Removing from the back is simple -- no shifting.
	public T removeBack()
	{
		if (size > 0)
		{
			T temp = data[back];
			data[back] = null;
			size--;
			back--;
			return temp;
		}
		else
			return null;
	}
	
	public T getBack()
	{
		if (size > 0)
			return data[back];
		else
			return null;
	}
	
	// Removing from the front requires shifting to fill in the gap.
	public T removeFront()
	{
		if (size > 0)
		{
			T temp = data[0];
			shiftLeft(back);
			data[back] = null;
			back--;
			size--;
			return temp;
		}
		else
			return null;
	}
	
	public T getFront()
	{
		if (size > 0)
			return data[0];
		else
			return null;
	}		
	
	public boolean isEmpty()
	{
		return size == 0;
	}
	
	// Make a new array to reset Deque.  Note that old array and
	// objects connected to it will be garbage-collected (if
	// necessary).
	public void clear()
	{
		data = (T []) new Object[data.length];
		back = -1;
		size = 0;
	}
		
	// private methods to shift
	private void shiftRight(int loc)
	{
		for (int i = loc; i > 0; i--)
		{
			data[i] = data[i-1];
		}
	}
	
	private void shiftLeft(int loc)
	{
		for (int i = 0; i < loc; i++)
		{
			data[i] = data[i+1];
		}
	}	
	
}