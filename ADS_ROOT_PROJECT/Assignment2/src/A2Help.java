// CS 445 Fall 2021
// Help with Assignment 2 -- one way of doing one of the
// constructor methods plus the toString() method.  You may copy this code
// into the MyStringBuilder.java file.  Carefully read over this code, since
// it can also give you hints to other methods.

// Create a new MyStringBuilder which contains the contents of the
// String argument.

/*
public class MysStringBuilder(String s)
        {
        if (s == null || s.length() == 0)  // special case for empty String
        {
        firstC = null;
        length = 0;
        }
        else
        {
        firstC = new CNode(s.charAt(0));  // create first node
        length = 1;
        CNode currNode = firstC;
        // Iterate through remainder of the String, creating a new
        // node at the end of the list for each character.  Note
        // how the nodes are being linked and the current reference
        // being moved down the list.
        for (int i = 1; i < s.length(); i++)
        {
        CNode newNode = new CNode(s.charAt(i));
        currNode.next = newNode;
        newNode.prev = currNode;
        currNode = newNode;
        length++;
        }
        // Connect end back to front to make list circular
        currNode.next = firstC;
        firstC.prev = currNode;
        }
        }

// Create and return a String from the characters in the
// MyStringBuilder.  To do this efficiently, we first make
// an array of the appropriate size, fill it with the
// characters, and then construct and return a String from
// that array.  Note that generally you CANNOT use arrays to
// implement your methods but for this method (and substring()
// also) we are using an array just for the purposes of
// storing the characters in order to return a new String.
public String toString()
        {
        char [] c = new char[length];
        int i = 0;
        CNode currNode = firstC;
        while (i < length)
        {
        c[i] = currNode.data;
        i++;
        currNode = currNode.next;
        }
        return new String(c);
        }

 */
