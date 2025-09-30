package com.customiseddbms.dbms;

import com.customiseddbms.model.Employee;
import com.customiseddbms.utility.DBMSUtils;

import java.util.*;

///////////////////////////////////////////////////////////////////////////////////////////
//
//	Class Name			:	EmployeeDBMS
//	Description			:	This class handles core functionalities (CRUD operations).
//
///////////////////////////////////////////////////////////////////////////////////////////
public class EmployeeDBMS
{
    public LinkedList <Employee> Table;

    ///////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	EmployeeDBMS
    //	Description             :   This is default constructor of class EmployeeDBMS
    //                              which is used to allocate dynamic memory for LinkedList.
    //	Parameters				:   NONE
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////
    public EmployeeDBMS()
    {
        Table = new LinkedList <Employee> ();
        System.out.println("----------------------------------------------------------------------");
        System.out.println("---------- Customised DBMS Application started succesfully. ----------");
    }


    ///////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	InsertQuery
    //	Description             :   This method creates a new employee record in the database.
    //	Parameters				:   String(name), int(age), String(address), int(salary)
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////
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


    ///////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	SelectAll
    //	Description             :   This method display all employee records from the database.
    //	Parameters				:   NONE
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////
    public void SelectAll()
    {
        DBMSUtils.TableHeader();

        for(Employee eref : Table)
        {
            DBMSUtils.DisplayAllFields(eref);
        }
    }
}