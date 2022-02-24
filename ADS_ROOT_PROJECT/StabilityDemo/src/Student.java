// Subclass of People
import java.util.Scanner;
public class Student extends People
{
    private String major;
    private double gpa;

    public void readData(Scanner inScan)
    {
        super.readData(inScan);
        System.out.print("Major? ");
        major = inScan.nextLine();
        System.out.print("GPA? ");
        gpa = Double.parseDouble(inScan.nextLine());
    }

    public String toString()
    {
        String firstpart = super.toString();
        return (firstpart + " Major: " + major + " GPA: " + gpa);
    }

    public String getMajor()
    {
        return major;
    }

    public double getGpa()
    {
        return gpa;
    }
}