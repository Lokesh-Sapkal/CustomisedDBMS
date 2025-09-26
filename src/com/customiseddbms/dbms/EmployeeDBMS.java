package dbms;

import model.Employee;
import utility.DBMSUtils;

import java.util.*;

public class EmployeeDBMS
{
    public LinkedList <Employee> Table;

    public EmployeeDBMS()
    {
        Table = new LinkedList <Employee> ();
        System.out.println("----------------------------------------------------------------------");
        System.out.println("---------- Customised DBMS Application started succesfully. ----------");
    }

    public void InsertQuery(
                                String name,        // Name of employee
                                int age,            // Age of employee
                                String address,     // Address of employee
                                int salary          // Salary of employee
                            )
    {
        Employee eobj = new Employee(name,age,address,salary);

        Table.add(eobj);

        System.out.println("New Record inserted succesfully");
    }

    public void SelectAll()
    {
        DBMSUtils.TableHeader();

        for(Employee eref : Table)
        {
            DBMSUtils.DisplayAllFields(eref);
        }
    }
}