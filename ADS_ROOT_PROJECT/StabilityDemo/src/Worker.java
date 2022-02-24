// Another subclass of People.  Note that this class is abstract, so we cannot
// actually make any objects of it
import java.util.Scanner;
public abstract class Worker extends People
{
    protected double salary;

    public String toString()
    {
        String firstpart = super.toString();
        return (firstpart + " Salary: " + salary);
    }

    public double getSalary()
    {
        return salary;
    }
}