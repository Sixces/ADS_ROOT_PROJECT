public class Exam1<T> {

    private T [] bag;

    private int numberOfEntries;

    private int getIndexOf(T anEntry)
    {
        int check = -1;
        boolean found = false;

        for (int index = 0; !found && (index < numberOfEntries); index++)
        {
            if (anEntry.equals(bag[index]))
            {
                found = true;
                check = index;
            }
        }
        return check;
    }

    private Node firstNode;

    private class Node
    {
        private T data;
        private Node next;
    }

    private Node getReferenceTo(T anEntry)
    {
        Node currNode = firstNode;
        boolean found = false;

        while(!found && currNode.next != null)
        {
            if(anEntry.equals(currNode.data))
            {
                found = true;
            }
            else
            {
                currNode = currNode.next;
            }
        }
        return currNode;
    }
}
