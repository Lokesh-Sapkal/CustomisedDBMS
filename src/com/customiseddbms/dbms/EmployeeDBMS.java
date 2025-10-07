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

    ///////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	InsertQuery
    //	Description             :   This method creates a new employee record in the database.
    //	Parameters				:   String(name), int(age), String(address), int(salary)
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////
    public void InsertQuery(
                                String name,        //  Employee name
                                int age,            //  Employee age
                                String address,     //  Employee address
                                int salary          //  Employee salary
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
    public void SelectAllFields()
    {
        DBMSUtils.DisplayAllTableHeaders();

        for(Employee eref : Table)
        {
            DBMSUtils.DisplayAllFields(eref);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	SelectOneField
    //	Description             :   This method display specific employee records from the database.
    //	Parameters				:   String(displayMode)
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public void SelectSpecificField(
                                        String displayMode    // Define the field to print
                                    )
    {
        DBMSUtils.DisplaySpecificTableHeader(displayMode);

        for(Employee eref : Table)
        {
            DBMSUtils.DisplaySpecificField(eref, displayMode);
        }
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