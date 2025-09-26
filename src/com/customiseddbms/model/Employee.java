package model;

public class Employee
{
    public int EmpID;
    public String EmpName;
    public int EmpAge;
    public String EmpAddress;
    public int EmpSalary;

    public static int iCounter;

    static
    {
        iCounter = 1;
    }

    public Employee(String B, int C, String D, int E)
    {
        this.EmpID = iCounter++;
        this.EmpName = B;
        this.EmpAge = C;
        this.EmpAddress = D;
        this.EmpSalary = E;
    }

    public String toString()
    {
        return "ID : "+this.EmpID+" Name : "+this.EmpName+" Age : "+this.EmpAge+" Address : "+this.EmpAddress+" Salary : "+this.EmpSalary;
    }
}