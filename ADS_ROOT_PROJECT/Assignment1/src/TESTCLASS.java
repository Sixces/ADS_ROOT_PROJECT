public class TESTCLASS {

    public static void main(String[] args) {

        DequeInterface<Integer> D1 = new MyDeque<Integer>(4);
        DequeInterface<Integer> D2 = new MyDeque<Integer>(6);

        // Testing addToBack, resizing and toString
        for (int i = 0; i < 10; i++)
        {
            Integer newItem = Integer.valueOf(2 * i);
            D1.addToBack(newItem);
            D2.addToBack(newItem);
            System.out.println(newItem + " added to Deque");
        }
        System.out.println();
        System.out.println("D1: " + D1.toString());
        System.out.println("D1 size: " + D1.size() + " and capacity: " + D1.capacity());
        System.out.println("D2: " + D2.toString());
        System.out.println("D2 size: " + D2.size() + " and capacity: " + D2.capacity());

        System.out.println();

        /*
        // Testing array management and addToFront
        int count = 1;
        DequeInterface<String> D3 = new MyDeque<String>(5);
        String theItem = new String("Item " + count);
        System.out.println("Adding " + theItem);
        D3.addToFront(theItem);
        for (int i = 0; i < 8; i++)
        {
            count++;
            theItem = new String("Item " + count);
            System.out.println("Adding " + theItem);
            D3.addToFront(theItem);
            theItem = D3.removeBack();
            System.out.println("Removing " + theItem);
        }
        int sz = D3.size();
        System.out.println("There are " + sz + " items in the deque");
        System.out.println("The capacity is " + D3.capacity());
        System.out.println();
        */
    }
}
