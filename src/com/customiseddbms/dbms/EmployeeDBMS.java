package com.customiseddbms.dbms;

import com.customiseddbms.model.Employee;
import com.customiseddbms.utility.DBMSUtils;

import java.util.*;
import java.io.*;

///////////////////////////////////////////////////////////////////////////////////////////
//
//	Class Name			:	EmployeeDBMS
//	Description			:	This class handles core functionalities (CRUD operations) and 
//                          implements the Serializable interface.
//
///////////////////////////////////////////////////////////////////////////////////////////
public class EmployeeDBMS implements Serializable
{
    private static final long serialVersionUID = 1L;    // version control ID
    public LinkedList <Employee> Table;
    private static final String FilePath = "data/backups/";

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
        System.out.println("---------- Customised DBMS Application started successfully. ----------");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	InsertQuery
    //	Description             :   This method creates a new employee record in the employee database.
    //	Parameters				:   String(name), int(age), String(address), int(salary)
    //	Returns					:   NONE
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public void InsertQuery(
                                String name,        //  Employee name
                                int age,            //  Employee age
                                String address,     //  Employee address
                                int salary          //  Employee salary
                            )
    {
        Employee eobj = new Employee(name,age,address,salary);

        Table.add(eobj);

        System.out.println("New Record inserted successfully");
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	SelectQuery
    //	Description             :   This method display employee records from the database.
    //	Parameters				:   String[] (fNames[])
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////
    public void SelectQuery(
                                String fNames[]    // Array of field name
                            )
    {
        DBMSUtils.PrintEmployeeTableHeader(fNames);

        for(Employee eref : Table)
        {
            DBMSUtils.PrintEmployeeRecord(eref,fNames);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	SelectMaximumRecord
    //	Description             :   This method find and display maximum employee record for specific field.
    //	Parameters				:   String(field)
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void SelectMaximumRecord(
                                        String field    // Define the field
                                    )
    {
        DBMSUtils.PrintEmployeeTableHeader(new String[] {field});

        int iMax = 0;

        switch(field)
        {
            case "empage":
                for(Employee eref : Table)
                {
                    if(iMax < eref.EmpAge)
                    {
                        iMax = eref.EmpAge;
                    }
                }
                DBMSUtils.PrintRecord(iMax,6,'d');
                break;
            
            case "empsalary":
                for(Employee eref : Table)
                {
                    if(iMax < eref.EmpSalary)
                    {
                        iMax = eref.EmpSalary;
                    }      
                }
                DBMSUtils.PrintRecord(iMax,10,'d');
                break;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	SelectMinimumRecord
    //	Description             :   This method find and display minimum employee record for specific field.
    //	Parameters				:   String(field)
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void SelectMinimumRecord(
                                        String field    // Define the field
                                    )
    {
        DBMSUtils.PrintEmployeeTableHeader(new String[] {field});

        int iMin = Integer.MAX_VALUE;

        switch(field)
        {
            case "empage":
                for(Employee eref : Table)
                {
                    if(iMin > eref.EmpAge)
                    {
                        iMin = eref.EmpAge;
                    }
                }
                DBMSUtils.PrintRecord(iMin,6,'d');
                break;
            
            case "empsalary":
                for(Employee eref : Table)
                {
                    if(iMin > eref.EmpSalary)
                    {
                        iMin = eref.EmpSalary;
                    }
                }
                DBMSUtils.PrintRecord(iMin,10,'d');
                break;
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	CalculateSumAndAverage
    //	Description             :   This method apply summation or average operation on employee record.
    //	Parameters				:   String(fName)
    //	Returns					:   NONE
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    public void CalculateSumAndAverage(
                                            String fName    // Define the function
                                        )
    {
        long lSum = 0L;

        for(Employee eref : Table)
        {
            lSum += eref.EmpSalary;
        }

        if((fName.equals("avg")) && (Table.size() != 0))
        {
            double dAvg = (double)lSum/Table.size();

            DBMSUtils.PrintEmployeeTableHeader(new String[] {"avg"});
            DBMSUtils.PrintRecord(dAvg,14,'f');
        }
        else if(fName.equals("sum"))
        {
            DBMSUtils.PrintEmployeeTableHeader(new String[] {"sum"});
            DBMSUtils.PrintRecord(lSum,12,'d');
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	CountRecords
    //	Description             :   This method Count employee database records.
    //	Parameters				:   NONE
    //	Returns					:   NONE
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    public void CountRecords()
    {
        DBMSUtils.PrintEmployeeTableHeader(new String[] {"count"});
        DBMSUtils.PrintRecord(Table.size(),10,'d');
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	TakeBackup
    //	Description             :   This method stores/write an object of the EmployeeDBMS class in a file.
    //	Parameters				:   String(fName)
    //	Returns					:   NONE
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    public void TakeBackup(
                                String fName    // File name
                            )
    {
        try
        {
            File dir = new File(FilePath);
            if(!dir.exists())
            {
                dir.mkdirs();
            }

            String fPath = FilePath+fName;
            
            FileOutputStream fos = new FileOutputStream(fPath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(this);

            oos.close();
            fos.close();
            
            System.out.println("The class object could be written to a file.");
        }
        catch(Exception eobj)
        {
            System.out.println("The class object could not be written to the file.");
            System.out.println("Error : "+eobj);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	RestoreBackup
    //	Description             :   This method reads an object of the EmployeeDBMS class from the file.
    //	Parameters				:   String(fName)
    //	Returns					:   EmployeeDBMS(object)
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    public static EmployeeDBMS RestoreBackup(
                                                String fName    // File name
                                            )
    {
        try
        {
            String fPath = FilePath+fName;
            EmployeeDBMS empobj = null;
            
            FileInputStream fis = new FileInputStream(fPath);
            ObjectInputStream ois = new ObjectInputStream(fis);

            empobj = (EmployeeDBMS) ois.readObject();
            
            System.out.println("----------------------------------------------------------------------");
            System.out.printf("--- Database restored successfully from %s file. ---\n",fName);

            ois.close();
            fis.close();
            
            return empobj;
        }
        catch(Exception eobj)
        {
            System.out.println("Failed to restore backup from "+fName);
            return null;
        }
    }
}