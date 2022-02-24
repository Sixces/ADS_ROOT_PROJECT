// CS 0445 Fall 2021
// Read this class and its comments very carefully to make sure you implement
// the class properly.  The data and public methods in this class are identical
// to those in MyStringBuilder, with the exception of the two additional methods
// shown at the end.  You cannot change the data or add any instance
// variables.  However, you may (and will need to) add some private methods.
// No iteration (i.e. no loops) is allowed in this implementation. 

import javax.swing.*;

// For more details on the general functionality of most of these methods,
// see the specifications of the method in the StringBuilder class from Assignment 2.  
public class MyStringBuilder2
{
    // These are the only two instance variables you are allowed to have.
    // See details of CNode class below.  In other words, you MAY NOT add
    // any additional instance variables to this class.  However, you may
    // use any method variables that you need within individual methods.
    // But remember that you may NOT use any variables of any other
    // linked list class or of the predefined StringBuilder or
    // or StringBuffer class in any place in your code.  You may only use the
    // String class where it is an argument or return type in a method.
    private CNode firstC;	// reference to front of list.  This reference is necessary
    // to keep track of the list
    private int length;  	// number of characters in the list

    // You may also add any additional private methods that you need to
    // help with your implementation of the public methods.

    // Create a new MyStringBuilder2 initialized with the chars in String s.
    // This method is provided to you -- see the Assignment 3 document.

    // Constructor to make a new MyStringBuilder2 from a String. The constructor
    // itself is NOT recursive – however, it calls a recursive method to do the
    // actual set up work. This should be your general approach for all of the
    // methods, since the recursive methods typically need extra parameters that
    // are not given in the specification.
    public MyStringBuilder2(String s)
    {
        if (s != null && s.length() > 0)
            makeBuilder(s, 0);
        else // no String so initialize empty MyStringBuilder2
        {
            firstC = null;
            length = 0;
        }
    }

    // Recursive method to set up a new MyStringBuilder2 from a String
    private void makeBuilder(String s, int pos)
    {
        // Recursive case – we have not finished going through the String
        if (pos < s.length()-1)
        {
            // Note how this is done – we make the recursive call FIRST, then
            // add the node before it. In this way EVERY node we add is
            // the front node, and it enables us to avoid having to make a
            // special test for the front node. However, many of your
            // methods will proceed in the normal front to back way.
            makeBuilder(s, pos+1);
            CNode temp = new CNode(s.charAt(pos));
            CNode back = firstC.prev;
            temp.prev = back;
            back.next = temp;
            temp.next = firstC;
            firstC.prev = temp;
            firstC = temp;
            length++;
        }
        else if (pos == s.length()-1) // Special case for last char in String
        { // This is a base case and initializes
            // firstC in a circular way
            firstC = new CNode(s.charAt(pos));
            firstC.next = firstC;
            firstC.prev = firstC;
            length = 1;
        }
        else // This case should never be reached, due to the way the
            // constructor is set up. However, I included it as a
        { // safeguard (in case some other method calls this one)
            length = 0;
            firstC = null;
        }
    }


    // Create a new MyStringBuilder2 initialized with the chars in array s
    public MyStringBuilder2(char [] s)
    {
        if (s != null && s.length > 0)
            makeBuilder(s, 0);
        else
        {
            firstC = null;
            length = 0;
        }
    }

    private void makeBuilder(char [] s, int pos)
    {
        if (pos < s.length-1)
        {
            makeBuilder(s, pos+1);
            CNode temp = new CNode(s[pos]);
            CNode back = firstC.prev;
            temp.prev = back;
            back.next = temp;
            temp.next = firstC;
            firstC.prev = temp;
            firstC = temp;
            length++;
        }
        else if (pos == s.length-1)
        {
            firstC = new CNode(s[pos]);
            firstC.next = firstC;
            firstC.prev = firstC;
            length = 1;
        }
        else
        {
            length = 1;
            firstC = null;
        }
    }

    // Copy constructor -- make a new MyStringBuilder2 from an old one.  Be sure
    // that you make new nodes for the copy.
    public MyStringBuilder2(MyStringBuilder2 old)
    {
        if (old != null && old.length > 0)
        {
            CNode currNodeOld = old.firstC;
            makeBuilder(old, 0, currNodeOld);
        }
        else
        {
            firstC = null;
            length = 0;
        }
    }

    private void makeBuilder(MyStringBuilder2 old, int pos, CNode currNodeOld)
    {
        //currNodeOld = currNodeOld.next;

        if (pos < old.length-1)
        {
            makeBuilder(old, pos+1, currNodeOld.next);
            CNode temp = new CNode(currNodeOld.data);
            CNode back = firstC.prev;
            temp.prev = back;
            back.next = temp;
            temp.next = firstC;
            firstC.prev = temp;
            firstC = temp;
            length++;
        }
        else if (pos == old.length-1)
        {
            firstC = new CNode(currNodeOld.data);
            firstC.next = firstC;
            firstC.prev = firstC;
            length = 1;
        }
        else
        {
            length = 1;
            firstC = null;
        }
    }

    // Create a new empty MyStringBuilder2
    public MyStringBuilder2()
    {
        firstC = null;
        length = 0;
    }

    // Return the entire contents of the current MyStringBuilder2 as a String.
    // This method is provided to you -- see Assignment 3 document.

    // Again note that the specified method is not actually recursive – rather it
    // calls a recursive method to do the work. Note that in this case we also
    // create a char array before the recursive call, then pass it as a
    // parameter, then construct and return a new string from the char array.
    // Carefully think about the parameters you will be passing to your recursive
    // methods. Through them you must be able to move through the list and
    // reduce the "problem size" with each call.
    public String toString()
    {
        char [] c = new char[length];
        getString(c, 0, firstC);
        return new String(c);
    }
    // Here we need the char array to store the characters, the pos value to
    // indicate the current index in the array and the curr node to access
    // the data in the actual MyStringBuilder2. Note that these rec methods
    // are private – the user of the class should not be able to call them.
    private void getString(char [] c, int pos, CNode curr)
    {
        if (pos < length) // Not at end of the list
        {
            c[pos] = curr.data; // put next char into array
            getString(c, pos+1, curr.next); // recurse to next node and
        }                                       // next pos in array
    }


    // Append MyStringBuilder2 b to the end of the current MyStringBuilder2, and
    // return the current MyStringBuilder2.  Be careful for special cases!
    public MyStringBuilder2 append(MyStringBuilder2 b)
    {
        if (isEmpty())
        {
            CNode curr = b.firstC;
            makeBuilder(b, 0, curr);
        }
        else
        {
            CNode curr = firstC.prev;
            appendBuilder(b, 0, curr);
        }
        return this;
    }

    private void appendBuilder(MyStringBuilder2 b, int pos, CNode curr)
    {
        if (pos < b.length)
        {
            CNode newNode = new CNode(b.toString().charAt(pos));
            newNode.prev = curr;
            curr.next = newNode;
            newNode.next = firstC;
            firstC.prev = newNode;
            curr = newNode;
            length++;
            appendBuilder(b, pos+1, curr);
        }
    }

    // Append String s to the end of the current MyStringBuilder2, and return
    // the current MyStringBuilder2.  Be careful for special cases!
    public MyStringBuilder2 append(String s)
    {
        if (isEmpty())
        {
            makeBuilder(s, 0);
        }
        else
        {
            CNode curr = firstC.prev;
            appendBuilder(s, 0, curr);
        }
        return this;
    }

    private void appendBuilder(String s, int pos, CNode curr)
    {
        if (pos < s.length())
        {
            CNode newNode = new CNode(s.charAt(pos));
            newNode.prev = curr;
            curr.next = newNode;
            newNode.next = firstC;
            firstC.prev = newNode;
            curr = newNode;
            length++;
            appendBuilder(s, pos+1, curr);
        }
    }

    // Append char array c to the end of the current MyStringBuilder2, and
    // return the current MyStringBuilder2.  Be careful for special cases!
    public MyStringBuilder2 append(char [] c)
    {
        if (isEmpty())
        {
            makeBuilder(c, 0);
        }
        else
        {
            CNode curr = firstC.prev;
            appendBuilder(c, 0, curr);
        }
        return this;
    }

    private void appendBuilder(char [] c, int pos, CNode curr)
    {
        if (pos < c.length)
        {
            CNode newNode = new CNode(c[pos]);
            newNode.prev = curr;
            curr.next = newNode;
            newNode.next = firstC;
            firstC.prev = newNode;
            curr = newNode;
            length++;
            appendBuilder(c, pos+1, curr);
        }
    }

    // Append char c to the end of the current MyStringBuilder2, and
    // return the current MyStringBuilder2.  Be careful for special cases!
    public MyStringBuilder2 append(char c)
    {
        if (isEmpty())
        {
            firstC = new CNode(c);
            firstC.next = firstC;
            firstC.prev = firstC;
            length = 1;
        }
        else
        {
            CNode curr = firstC.prev;
            CNode newNode = new CNode(c);
            curr.next = newNode;
            newNode.next = firstC;
            firstC.prev = newNode;
            newNode.prev = curr;
            length++;
        }
        return this;
    }

    // Return the character at location "index" in the current MyStringBuilder2.
    // If index is invalid, throw an IndexOutOfBoundsException.

    public char charAt(int index)
    {
        if (index < 0 || index >= length)
        {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        else
        {
            CNode curr = firstC;
            return charAtRec(index, 0, curr);
        }
    }

    private char charAtRec(int index, int pos, CNode curr)
    {
        if (pos == index)
        {
            return curr.data;
        }
        else
        {
            return charAtRec(index, pos+1, curr.next);
        }
    }

    // Delete the characters from index "start" to index "end" - 1 in the
    // current MyStringBuilder2, and return the current MyStringBuilder2.
    // If "start" is invalid or "end" <= "start" do nothing (just return the
    // MyStringBuilder2 as is).  If "end" is past the end of the MyStringBuilder2,
    // only remove up until the end of the MyStringBuilder2. Be careful for
    // special cases!
    public MyStringBuilder2 delete(int start, int end)
    {
        if (start < 0 || start > length || end <= start)
        {
            return this;
        }
        else
        {
            int endHold = end;
            if (end >= length) { endHold = length-1; }
            CNode nodeS = getNode(0, start, firstC);
            CNode nodeB = nodeS.prev;
            CNode nodeA = getNode(start, endHold, nodeS);

            if (start == 0)
            {
                CNode temp = firstC.prev;
                firstC = nodeA;
                temp.next = firstC;
                firstC.prev = temp;
            }
            else
            {
                if (end > length)
                {
                    nodeA = firstC;
                    end = length;
                }

                nodeB.next = nodeA;
                nodeA.prev = nodeB;
            }
        }
        length = length - (end - start);
        return this;
    }

    private CNode getNode(int pos, int loc, CNode curr)
    {
        if (pos == loc)
        {
            return curr;
        }
        else
        {
            return getNode(pos+1, loc, curr.next);
        }
    }

    // Delete the character at location "index" from the current
    // MyStringBuilder2 and return the current MyStringBuilder2.  If "index" is
    // invalid, do nothing (just return the MyStringBuilder2 as is).
    // Be careful for special cases!
    public MyStringBuilder2 deleteCharAt(int index)
    {
        if (index < 0 || index >= length)
        {
            return this;
        }
        else if (index == 0)
        {
            firstC = firstC.next;
            firstC.prev = getNode(0, index, firstC);
            length--;
        }
        else if (index == length-1)
        {
            CNode nodeB = getNode(0, length-1, firstC);
            firstC.prev = nodeB;
            nodeB.next = firstC;
            length--;
        }
        else
        {
            CNode nodeB = getNode(0, index-1, firstC);
            CNode nodeD = nodeB.next;
            CNode nodeA = nodeD.next;
            nodeB.next = nodeA;
            nodeA.prev = nodeB;
            length--;
        }
        return this;
    }

    // Find and return the index within the current MyStringBuilder2 where
    // String str first matches a sequence of characters within the current
    // MyStringBuilder2.  If str does not match any sequence of characters
    // within the current MyStringBuilder2, return -1.  Think carefully about
    // what you need to do for this method before implementing it.
    public int indexOf(String str)
    {
        return getIndexOf(str, 0, length-1, firstC);
    }

    public int getIndexOf(String str, int index, int cap, CNode curr)
    {
        if (index < cap)
        {
            if (str.charAt(0) != curr.data)
                return getIndexOf(str, index+1, length-1, curr.next);
            else
            {
                if (getIndexOf2(str, 1, str.length()-1, curr.next))
                    return index;
                else
                    return getIndexOf(str, index+1, length-1, curr.next);
            }
        }
        else if (index == cap)
        {
            if (str.charAt(0) != curr.data)
                return -1;
            else
                return index;
        }
        else
            return -1;
    }

    public boolean getIndexOf2(String str, int pos, int cap, CNode curr)
    {
        if (pos < cap)
        {
            if (str.charAt(pos) == curr.data)
                return getIndexOf2(str, pos+1, cap, curr.next);
            else
                return false;
        }
        else if (pos == cap)
        {
            if (str.charAt(pos) != curr.data)
                return false;
            else
                return true;
        }
        else
            return false;
    }

    // Insert String str into the current MyStringBuilder2 starting at index
    // "offset" and return the current MyStringBuilder2.  if "offset" ==
    // length, this is the same as append.  If "offset" is invalid
    // do nothing.
    public MyStringBuilder2 insert(int offset, String str)
    {
        if (offset == length)
        {
            this.append(str);
        }
        else if (offset == 0)
        {
             CNode curr = firstC.prev;
             CNode aftNode = curr.next;
             CNode newNode = new CNode(str.charAt(0));
             newNode.next = aftNode;
             newNode.prev = curr;
             firstC.prev = newNode;
             firstC = newNode;
             length++;
             insertRec(offset, str, 1, firstC, aftNode);


        }
        else if (offset > 0 && offset < length)
        {
            CNode curr = getNode(0, offset-1, firstC);
            CNode aftNode = curr.next;

            insertRec(offset, str, 0, curr, aftNode);
        }
        return this;
    }

    public void insertRec(int offset, String str, int pos, CNode curr, CNode aftNode)
    {
        if (offset == 0)
        {
            if (pos < str.length())
            {
                CNode newNode = new CNode(str.charAt(pos));
                newNode.prev = curr;
                curr.next = newNode;
                curr = newNode;
                length++;
                insertRec(offset, str, pos+1, curr, aftNode);
            }
            else if (pos == str.length())
            {
                curr.next = aftNode;
                aftNode.prev = curr;

            }
        }
        else
        {
            if (pos < str.length())
            {
                CNode newNode = new CNode(str.charAt(pos));
                newNode.prev = curr;
                curr.next = newNode;
                curr = newNode;
                length++;
                insertRec(offset, str, pos+1, curr, aftNode);
            }
            else if (pos == str.length())
            {
                curr.next = aftNode;
                aftNode.prev = curr;
            }
        }
    }

    // Insert character c into the current MyStringBuilder2 at index
    // "offset" and return the current MyStringBuilder2.  If "offset" ==
    // length, this is the same as append.  If "offset" is invalid,
    // do nothing.
    public MyStringBuilder2 insert(int offset, char c)
    {
        if (offset >= 0 || offset < length)
        {
            CNode curr;
            if (offset == 0)
            {
                curr = firstC.prev;
            }
            else
            {
                curr = getNode(0, offset-1, firstC);
            }

            CNode newNode = new CNode(c);
            CNode aftNode = curr.next;
            curr.next = newNode;
            newNode.prev = curr;
            newNode.next = aftNode;
            aftNode.prev = newNode;
            length++;
        }
        else if (offset == length)
        {
            this.append(c);
        }
        return this;
    }

    // Return the length of the current MyStringBuilder2
    public int length()
    {
        return length;
    }

    // Delete the substring from "start" to "end" - 1 in the current
    // MyStringBuilder2, then insert String "str" into the current
    // MyStringBuilder2 starting at index "start", then return the current
    // MyStringBuilder2.  If "start" is invalid or "end" <= "start", do nothing.
    // If "end" is past the end of the MyStringBuilder2, only delete until the
    // end of the MyStringBuilder2, then insert.  This method should be done
    // as efficiently as possible.  In particular, you may NOT simply call
    // the delete() method followed by the insert() method, since that will
    // require an extra traversal of the linked list.
    public MyStringBuilder2 replace(int start, int end, String str)
    {
        if (start < 0 || start > length || end <= start)
        {
            return this;
        }
        else
        {
            if (end > length)
                end = length;
            CNode curr = getNode(0, start, firstC);
            CNode nodeBefore = getNode(start, end-1, curr);

            int deLength = end - start;
            recReplace(str, 0, deLength, curr, nodeBefore);
        }
        return this;
    }

    public void recReplace(String str, int pos, int deLength, CNode curr, CNode nodeBefore)
    {
        if (pos < deLength)
        {
            curr.data = str.charAt(pos);
            curr = curr.next;
            recReplace(str, pos+1, deLength, curr, nodeBefore);
        }
        else if (pos < str.length())
        {
            CNode newNode = new CNode(str.charAt(pos));
            nodeBefore.next = newNode;
            curr.prev = newNode;
            newNode.prev = nodeBefore;
            newNode.next = curr;
            nodeBefore = newNode;
            length++;
            recReplace(str, pos+1, deLength, curr, nodeBefore);
        }
    }

    // Return as a String the substring of characters from index "start" to
    // index "end" - 1 within the current MyStringBuilder2
    public String substring(int start, int end)
    {
        int sublength = (end - start);
        char [] c = new char[sublength];
        CNode curr = getNode(0, start, firstC);
        getSubString(c, 0, sublength, curr);
        return new String(c);
    }

    private void getSubString(char [] c, int pos, int sublength, CNode curr)
    {
        if (pos < sublength)
        {
            c[pos] = curr.data;
            getSubString(c, pos+1, sublength, curr.next);
        }
    }

    // Return the entire contents of the current MyStringBuilder2 as a String
    // in the reverse of the order that it is stored.
    public String revString()
    {
        char [] c = new char[length];
        getRevString(c, length-1, firstC);
        return new String(c);
    }

    private void getRevString(char [] c, int pos, CNode curr)
    {
        if (pos >= 0)
        {
            c[pos] = curr.data;
            getRevString(c, pos-1, curr.next);
        }
    }

    // Find and return the index within the current MyStringBuilder2 where
    // String str LAST matches a sequence of characters within the current
    // MyStringBuilder2.  If str does not match any sequence of characters
    // within the current MyStringBuilder2, return -1.  Think carefully about
    // what you need to do for this method before implementing it.  For some
    // help with this see the Assignment 3 specifications.
    public int lastIndexOf(String str)
    {
        return getLastIndexOf(str, length-1, 0, firstC.prev);
    }

    public int getLastIndexOf(String str, int index, int floor, CNode curr)
    {
        if (index > floor)
        {
            if (str.charAt(0) != curr.data)
                return getLastIndexOf(str, index-1, 0, curr.prev);
            else
            {
                if (getLastIndexOf2(str, 1, str.length()-1, curr.next))
                    return index;
                else
                    return getLastIndexOf(str, index-1, 0, curr.prev);
            }
        }
        else if (index == floor)
        {
            if (str.charAt(0) != curr.data)
                return -1;
            else
                return index;
        }
        else
            return -1;
    }

    public boolean getLastIndexOf2(String str, int pos, int cap, CNode curr)
    {
        if (pos < cap)
        {
            if (str.charAt(pos) == curr.data)
                return getLastIndexOf2(str, pos+1, cap, curr.next);
            else
                return false;
        }
        else if (pos == cap)
        {
            if (str.charAt(pos) != curr.data)
                return false;
            else
                return true;
        }
        else
            return false;
    }

    // Find and return an array of MyStringBuilder2, where each MyStringBuilder2
    // in the return array corresponds to a part of the match of the array of
    // patterns in the argument.  If the overall match does not succeed, return
    // null.  For much more detail on the requirements of this method, see the
    // Assignment 3 specifications.
    public MyStringBuilder2 [] regMatch(String [] pats)
    {
        return new MyStringBuilder2[0];
    }

    // You must use this inner class exactly as specified below.  Note that
    // since it is an inner class, the MyStringBuilder2 class MAY access the
    // data and next fields directly.

    private class CNode
    {
        private char data;
        private CNode next, prev;

        public CNode(char c)
        {
            data = c;
            next = null;
            prev = null;
        }

        public CNode(char c, CNode n, CNode p)
        {
            data = c;
            next = n;
            prev = p;
        }
    }

    private boolean isEmpty()
    {
        boolean result;

        if (length == 0)
            result = true;
        else
            result = false;

        return result;
    }
}