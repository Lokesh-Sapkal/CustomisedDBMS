package utility;

import model.Employee;
import dbms.EmployeeDBMS;

public class DBMSUtils
{
    public static void TableHeader()
    {
        System.out.println("+--------+--------------+--------+--------------+------------+");
        System.out.printf("| %-6s | %-12s | %-6s | %-12s | %-10s |%n","ID","Name","Age","Address","Salary");
        System.out.println("+--------+--------------+--------+--------------+------------+");
    }

    public static void DisplayAllFields(Employee eref)
    {
        System.out.printf("| %-6d | %-12s | %-6d | %-12s | %-10d |%n",eref.EmpID,eref.EmpName,eref.EmpAge,eref.EmpAddress,eref.EmpSalary);
        System.out.println("+--------+--------------+--------+--------------+------------+");
    }

    public static int IsNumeric(String sValue)
    {
        int iNo = 0;

        try
        {
            iNo = Integer.parseInt(sValue);
            return iNo;
        }
        catch(NumberFormatException eobj)
        {
            System.out.println("Exception occured.. : "+eobj);
            return iNo;
        }
    }

    public static void InvalidCommand()
    {
        System.out.println("Command not found");
        System.out.println("Please give valid command.");
    }
}