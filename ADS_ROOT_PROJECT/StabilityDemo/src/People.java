// CS 0445 Fall 2021
// Demonstration of polymorphism in Java
import java.util.Scanner;
public class People implements Comparable<People>
{
    private String name;
    private int age;

    public void readData(Scanner inScan)
    {
        System.out.print("Name? ");
        name = inScan.nextLine();
        System.out.print("Age? ");
        age = Integer.parseInt(inScan.nextLine());
    }

    public String toString()
    {
        return ("Name: " + name + " Age: " + age);
    }

    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }

    public int compareTo(People rhs)
    {
        return name.compareTo(rhs.name);
    }
}