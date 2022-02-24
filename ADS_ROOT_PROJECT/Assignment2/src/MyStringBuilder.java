// CS 0445 Fall 2021
// Read this class and its comments very carefully to make sure you implement
// the class properly.  Note the items that are required and that cannot be
// altered!  Generally speaking you will implement your MyStringBuilder using
// a circular, doubly linked list of nodes.  See more comments below on the
// specific requirements for the class.

// For more details on the general functionality of most of these methods,
// see the specifications of the similar method in the StringBuilder class.  
public class MyStringBuilder
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

    // Create a new MyStringBuilder initialized with the chars in String s
    // Note: This method is implemented for you.  See A2Help.java
    public MyStringBuilder(String s)
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

    // Create a new MyStringBuilder initialized with the chars in array s
    public MyStringBuilder(char [] s)
    {
        if (s == null || s.length == 0)
        {
            firstC = null;
            length = 0;
        }
        else
        {
            firstC = new CNode(s [0]);
            length = 1;
            CNode currNode = firstC;

            for (int i = 1; i < s.length; i++)
            {
                CNode newNode = new CNode(s [i]);
                currNode.next = newNode;
                newNode.prev = currNode;
                currNode = newNode;
                length++;
            }

            currNode.next = firstC;
            firstC.prev = currNode;
        }
    }

    // Copy constructor -- make a new MyStringBuilder from an old one.  Be sure
    // that you make new nodes for the copy.

    public MyStringBuilder(MyStringBuilder old)
    {

        if (old == null || old.length == 0)
        {
            firstC = null;
            length = 0;
        }
        else
        {
            this.firstC = new CNode(old.toString().charAt(0));
            //this.firstC = new CNode(old.firstC.getData());
            length = 1;
            CNode currNodeThis = this.firstC;
            CNode currNodeOld  = old.firstC;


            for (int i = 0; i < old.length; i++)
            {
                CNode newNodeThis = new CNode(currNodeOld.data);

                currNodeThis.next = newNodeThis;
                newNodeThis.prev  = currNodeThis;
                currNodeThis      = newNodeThis;

                currNodeOld = currNodeOld.next;

                length++;
            }

            currNodeThis.next = this.firstC;
            this.firstC.prev = currNodeThis;

            //System.out.println(currNodeOld.data);
        }
    }

    // Create a new empty MyStringBuilder
    public MyStringBuilder()
    {
        firstC = null;
        length = 0;
    }

    // Return the entire contents of the current MyStringBuilder as a String
    // For this method you should do the following:
    // 1) Create a character array of the appropriate length
    // 2) Fill the array with the proper characters from your MyStringBuilder
    // 3) Return a new String using the array as an argument, or
    //    return new String(charArray);
    // Note: This method is implemented for you.  See A2Help.java
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

    // Append MyStringBuilder b to the end of the current MyStringBuilder, and
    // return the current MyStringBuilder.  Be careful for special cases!
    public MyStringBuilder append(MyStringBuilder b)
    {
        if (isEmpty())
        {
            new MyStringBuilder(b);
        }
        else
        {
            CNode currNode = firstC.prev;

            for (int i = 0; i < (b.length); i++)
            {
                CNode newNode = new CNode(b.toString().charAt(i));
                currNode.next = newNode;
                newNode.prev = currNode;
                currNode = newNode;
                length++;
            }

            currNode.next = firstC;
            firstC.prev = currNode;
        }
        return this;
    }

    // Append String s to the end of the current MyStringBuilder, and return
    // the current MyStringBuilder.  Be careful for special cases!
    public MyStringBuilder append(String s)
    {
        if(isEmpty())
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

                for (int i = 1; i < s.length(); i++)
                {
                    CNode newNode = new CNode(s.charAt(i));
                    currNode.next = newNode;
                    newNode.prev = currNode;
                    currNode = newNode;
                    length++;
                }
                currNode.next = firstC;
                firstC.prev = currNode;
            }
        }
        else
        {
            if (s == null || s.length() == 0)
            {
                return this;
            }
            else
            {
                CNode currNode = firstC.prev;

                for (int i = 0; i < s.length(); i++)
                {
                    CNode newNode = new CNode(s.charAt(i));
                    currNode.next = newNode;
                    newNode.prev = currNode;
                    currNode = newNode;
                    length++;
                }
                currNode.next = firstC;
                firstC.prev = currNode;
            }
        }
        return this;
    }

    // Append char array c to the end of the current MyStringBuilder, and
    // return the current MyStringBuilder.  Be careful for special cases!
    public MyStringBuilder append(char [] c)
    {
        if(isEmpty())
        {
            if (c == null || c.length == 0)
            {
                firstC = null;
                length = 0;
            }
            else
            {
                firstC = new CNode(c [0]);
                length = 1;
                CNode currNode = firstC;

                for (int i = 1; i < c.length; i++)
                {
                    CNode newNode = new CNode(c [i]);
                    currNode.next = newNode;
                    newNode.prev = currNode;
                    currNode = newNode;
                    length++;
                }

                currNode.next = firstC;
                firstC.prev = currNode;
            }
        }
        else
        {
            if (c == null || c.length == 0)
            {
                return this;
            }
            else
            {
                CNode currNode = firstC.prev;

                for (int i = 0; i < c.length; i++)
                {
                    CNode newNode = new CNode(c [i]);
                    currNode.next = newNode;
                    newNode.prev = currNode;
                    currNode = newNode;
                    length++;
                }

                currNode.next = firstC;
                firstC.prev = currNode;
            }
        }
        return this;
    }

    // Append char c to the end of the current MyStringBuilder, and
    // return the current MyStringBuilder.  Be careful for special cases!
    public MyStringBuilder append(char c)
    {
        if(isEmpty())
        {
            firstC = new CNode(c);
            length = 1;
            CNode currNode = firstC;

            currNode.next = firstC;
            firstC.prev = currNode;
        }
        else
        {
            CNode currNode = firstC.prev;
            CNode newNode = new CNode(c);
            currNode.next = newNode;
            newNode.prev = currNode;
            currNode = newNode;
            length++;

            currNode.next = firstC;
            firstC.prev = currNode;
        }
        return this;
    }

    // Return the character at location "index" in the current MyStringBuilder.
    // If index is invalid, throw an IndexOutOfBoundsException.
    public char charAt(int index)
    {
        if(index < 0 || index >= length)
        {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        else
        {
            CNode currNode = firstC;

            for (int count = 0; count < index; count++)
            {
                currNode = currNode.next;
            }
            return currNode.data;
        }
    }

    // Delete the characters from index "start" to index "end" - 1 in the
    // current MyStringBuilder, and return the current MyStringBuilder.
    // If "start" is invalid or "end" <= "start" do nothing (just return the
    // MyStringBuilder as is).  If "end" is past the end of the MyStringBuilder,
    // only remove up until the end of the MyStringBuilder. Be careful for
    // special cases!
    public MyStringBuilder delete(int start, int end)
    {
        if (start < 0 || start > length || end <= start)
        {
            return this;
        }
        else
        {
            CNode nodeStart = getNodeAt(start);
            CNode nodeBefore;
            CNode nodeAfter = getNodeAt(end);

            if (start == 0)
            {
                firstC = nodeAfter;
                getNodeAt(length-1).next = firstC;
                firstC.prev = getNodeAt(length-1);
            }
            else
            {
                if(end > length)
                {
                    nodeAfter = firstC;
                    nodeBefore = nodeStart.prev;
                    nodeBefore.next = nodeAfter;
                    nodeAfter.prev = nodeBefore;
                    end = length;
                }

                nodeBefore = nodeStart.prev;
                nodeBefore.next = nodeAfter;
                nodeAfter.prev = nodeBefore;
            }
        }
        length = (length - (end - start));
        return this;
    }

    // Delete the character at location "index" from the current
    // MyStringBuilder and return the current MyStringBuilder.  If "index" is
    // invalid, do nothing (just return the MyStringBuilder as is).  If "index"
    // is the last character in the MyStringBuilder, go backward in the list in
    // order to make this operation faster (since the last character is simply
    // the previous of the first character)
    // Be careful for special cases!
    public MyStringBuilder deleteCharAt(int index)
    {
        if (index < 0 || index >= length)
        {
            return this;
        }
        else if (index == 0)
        {
            firstC = firstC.next;
            firstC.prev = getNodeAt(length);
            length--;
        }
        else if (index == length-1)
        {
            firstC.prev = getNodeAt(length - 1);
            getNodeAt(length - 1).next = firstC;
            length--;
        }
        else
        {
            CNode nodeBefore = getNodeAt(index-1);
            CNode nodeDel = nodeBefore.next;
            CNode nodeAfter = nodeDel.next;
            nodeBefore.next = nodeAfter;
            nodeAfter.prev = nodeBefore;
            length--;
        }
        return this;
    }

    // Find and return the index within the current MyStringBuilder where
    // String str first matches a sequence of characters within the current
    // MyStringBuilder.  If str does not match any sequence of characters
    // within the current MyStringBuilder, return -1.  Think carefully about
    // what you need to do for this method before implementing it.
    public int indexOf(String str)
    {
        CNode currNode = firstC;
        boolean found = false;
        int index = -1;

        for (int i = 0; i < (length); i++)
        {
            if (str.charAt(0) == currNode.getData())
            {
                CNode checkNode = currNode;
                found = true;
                for (int j = 1; j < str.length(); j++)
                {
                    checkNode = checkNode.next;

                    if (str.charAt(j) != checkNode.data)
                    {
                        found = false;
                    }
                }
                if(found)
                {
                    index = i;
                    break;
                }
            }
            currNode = currNode.next;
        }
        if(found)
            return index;
        else
            return -1;
    }

    // Insert String str into the current MyStringBuilder starting at index
    // "offset" and return the current MyStringBuilder.  if "offset" ==
    // length, this is the same as append.  If "offset" is invalid
    // do nothing.
    public MyStringBuilder insert(int offset, String str)
    {
        if(offset == length)
        {
            this.append(str);
        }
        else if (offset == 0)
        {
            CNode currNode = firstC;
            CNode newNode = new CNode(str.charAt(0));
            newNode.prev = currNode.prev;
            firstC.prev = newNode;
            currNode = newNode;
            CNode newFirstC = currNode;
            length++;

            for (int i = 1; i < str.length(); i++) {
                newNode = new CNode(str.charAt(i));
                newNode.prev = currNode;
                currNode.next = newNode;
                currNode = newNode;
                length++;
            }
            currNode.next = firstC;
            firstC.prev = currNode;
            firstC = newFirstC;
        }
        else if(offset >= 0 && offset < length) {
            CNode currNode = getNodeAt(offset - 1);
            CNode aftNode = currNode.next;

            for (int i = 0; i < str.length(); i++) {
                CNode newNode = new CNode(str.charAt(i));

                currNode.next = newNode;
                newNode.prev = currNode;
                currNode = newNode;
                length++;
            }
            currNode.next = aftNode;
            aftNode.prev = currNode;
        }
        return this;
    }

    // Insert character c into the current MyStringBuilder at index
    // "offset" and return the current MyStringBuilder.  If "offset" ==
    // length, this is the same as append.  If "offset" is invalid,
    // do nothing.
    public MyStringBuilder insert(int offset, char c)
    {
        if(offset >= 0 || offset < length)
        {
            CNode currNode = getNodeAt(offset - 1);
            CNode newNode = new CNode(c);
            CNode aftNode = getNodeAt(offset);
            currNode.next = newNode;
            newNode.prev = currNode;
            newNode.next = aftNode;
            aftNode.prev = newNode;
            length++;

        }
        else if(offset == length)
        {
            this.append(c);
        }

        return this;
    }

    // Return the length of the current MyStringBuilder
    public int length()
    {
        return length;
    }

    // Delete the substring from "start" to "end" - 1 in the current
    // MyStringBuilder, then insert String "str" into the current
    // MyStringBuilder starting at index "start", then return the current
    // MyStringBuilder.  If "start" is invalid or "end" <= "start", do nothing.
    // If "end" is past the end of the MyStringBuilder, only delete until the
    // end of the MyStringBuilder, then insert.  This method should be done
    // as efficiently as possible.  In particular, you may NOT simply call
    // the delete() method followed by the insert() method, since that will
    // require an extra traversal of the linked list.
    public MyStringBuilder replace(int start, int end, String str)
    {
        if (start < 0 || start > length || end <= start)
        {
            return this;
        }
        else
        {
            CNode currNode = getNodeAt(start);

            if(end > length)
                end = length;

            int deLength = (end - start);
            int strIndex = 0;

            for (int j = 0; j < deLength; j++)
            {
                currNode.setData(str.charAt(j));
                currNode = currNode.next;
                strIndex++;
            }
            CNode nodeBefore = currNode.prev;

            for (int i = strIndex; i < str.length(); i++)
            {
                CNode newNode = new CNode(str.charAt(i));
                nodeBefore.next = newNode;
                currNode.prev = newNode;
                newNode.prev = nodeBefore;
                newNode.next = currNode;
                nodeBefore = newNode;
                length++;
            }

            if(end == length)
            {
                currNode = firstC;
            }
        }
        return this;
    }

    // Return as a String the substring of characters from index "start" to
    // index "end" - 1 within the current MyStringBuilder.  For this method
    // you should do the following:
    // 1) Create a character array of the appropriate length
    // 2) Fill the array with the proper characters from your MyStringBuilder
    // 3) Return a new String using the array as an argument, or
    //    return new String(charArray);
    public String substring(int start, int end)
    {
        int subLength = (end - start);
        char [] c = new char[subLength];
        int i = 0;
        CNode currNode = getNodeAt(start);
        while (i < subLength)
        {
            c[i] = currNode.data;
            i++;
            currNode = currNode.next;
        }
        return new String(c);
    }

    // Return as a String the reverse of the contents of the MyStringBuilder.  Note
    // that this does NOT change the MyStringBuilder in any way.  See substring()
    // above for the basic approach.
    public String revString()
    {
        char [] c = new char[length];
        int i = 0;
        CNode currNode = getNodeAt(length-1);
        while (i < length)
        {
            c[i] = currNode.data;
            i++;
            currNode = currNode.prev;
        }
        return new String(c);
    }

    // You must use this inner class exactly as specified below.  Note that
    // since it is an inner class, the MyStringBuilder class MAY access the
    // data, next and prev fields directly.
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

        public char getData()
        {
            return data;
        }

        public void setData(char newData)
        {
            data = newData;
        }

        public CNode getNextNode()
        {
            return next;
        }

        public CNode getPrevNode()
        {
            return prev;
        }

        public void setNextNode(CNode nextNode)
        {
            next = nextNode;
        }

        public void setPrevNode(CNode prevNode)
        {
            prev = prevNode;
        }
    }

    private CNode getNodeAt (int index)
    {
        CNode currNode = firstC;

        for (int counter = 0; counter < index; counter++)
        {
            currNode = currNode.getNextNode();
        }

        return currNode;
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