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
    private LinkedList <Employee> table;
    private static final String FILE_PATH = "data" + File.separator + "backups" + File.separator;

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
        table = new LinkedList <Employee> ();
        System.out.println("----------------------------------------------------------------------");
        System.out.println("---------- Customised DBMS Application started successfully ----------");
    }

    /////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	getTable
    //	Description             :   This method returns the employee table.
    //	Parameters				:   NONE
    //	Returns					:   LinkedList<Employee>(table)
    //
    /////////////////////////////////////////////////////////////////////////////
    public LinkedList <Employee> getTable()
    {
        return table;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	insertQuery
    //	Description             :   This method creates a new employee record in the employee database.
    //	Parameters				:   String(name), int(age), String(address), int(salary)
    //	Returns					:   NONE
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public void insertQuery(
                                String name,        //  Employee name
                                int age,            //  Employee age
                                String address,     //  Employee address
                                int salary          //  Employee salary
                            )
    {
        Employee eobj = new Employee(name,age,address,salary);

        table.add(eobj);

        System.out.println("New Record inserted successfully");
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	selectQuery
    //	Description             :   This method display employee records from the database.
    //	Parameters				:   String[] (fNames[])
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////
    public void selectQuery(
                                String fNames[]    // Array of field name
                            )
    {
        DBMSUtils.printEmployeeTableHeader(fNames);

        for(Employee eref : table)
        {
            DBMSUtils.printEmployeeRecord(eref,fNames);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	selectSpecificIntegerRecords
    //	Description             :   This method display specific integer employee records from the database.
    //	Parameters				:   String[](fNames[]), String(fName), String(operator), int(Value)
    //	Returns					:   NONE
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void selectSpecificIntegerRecords(
                                                String fNames[],        // Array of field name
                                                String fName,           // Field name
                                                String operator,        // Define operator
                                                int value               // Value of field
                                            )
    {
        DBMSUtils.printEmployeeTableHeader(fNames);
        boolean bFound = false;

        for (Employee eref : table) 
        {
            int fieldValue = DBMSUtils.extractIntField(fName, eref);
            if(DBMSUtils.evaluateCondition(fieldValue, operator, value)) 
            {
                bFound = true;
                DBMSUtils.printEmployeeRecord(eref, fNames);
            }
        }

        if(!bFound)
        {
            System.out.println("Record not found");
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	selectSpecificStringRecords
    //	Description             :   This method display specific string employee records from the database.
    //	Parameters				:   String[](fNames[]), String(fName), String(operator), String(Value)
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public void selectSpecificStringRecords(
                                                String fNames[],        // Array of field name
                                                String fName,           // Field name
                                                String operator,        // Define operator
                                                String value            // Value of field
                                            )
    {
        DBMSUtils.printEmployeeTableHeader(fNames);
        boolean bFound = false;

        switch(fName)
        {
            case "empname":
                for(Employee eref : table)
                {
                    if(eref.getEmpName().equalsIgnoreCase(value))
                    {
                        bFound = true;
                        DBMSUtils.printEmployeeRecord(eref,fNames);
                    }
                }
                break;

            case "empaddress":
                for(Employee eref : table)
                {
                    if(eref.getEmpAddress().equalsIgnoreCase(value))
                    {
                        bFound = true;
                        DBMSUtils.printEmployeeRecord(eref,fNames);
                    }
                }
                break;
        }

        if(!bFound)
        {
            System.out.println("Record not found");
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	selectMaximumRecord
    //	Description             :   This method find and display maximum employee record for specific field.
    //	Parameters				:   String(field)
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void selectMaximumRecord(
                                        String field    // Define the field
                                    )
    {
        DBMSUtils.printEmployeeTableHeader(new String[] {field});

        int iMax = 0;
        boolean bFound = false;

        switch(field)
        {
            case "empage":
                for(Employee eref : table)
                {
                    if(iMax < eref.getEmpAge())
                    {
                        bFound = true;
                        iMax = eref.getEmpAge();
                    }
                }
                if(bFound)
                {
                    DBMSUtils.printRecord(iMax,6,'d');
                }
                else
                {
                    System.out.println("Record not found");
                }
                break;
            
            case "empsalary":
                for(Employee eref : table)
                {
                    if(iMax < eref.getEmpSalary())
                    {
                        bFound = true;
                        iMax = eref.getEmpSalary();
                    }      
                }
                if(bFound)
                {
                    DBMSUtils.printRecord(iMax,10,'d');
                }
                else
                {
                    System.out.println("Record not found");
                }
                break;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	selectMinimumRecord
    //	Description             :   This method find and display minimum employee record for specific field.
    //	Parameters				:   String(field)
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void selectMinimumRecord(
                                        String field    // Define the field
                                    )
    {
        DBMSUtils.printEmployeeTableHeader(new String[] {field});

        int iMin = Integer.MAX_VALUE;
        boolean bFound = false;

        switch(field)
        {
            case "empage":
                for(Employee eref : table)
                {
                    if(iMin > eref.getEmpAge())
                    {
                        bFound = true;
                        iMin = eref.getEmpAge();
                    }
                }
                if(bFound)
                {
                    DBMSUtils.printRecord(iMin,6,'d');
                }
                else
                {
                    System.out.println("Record not found");
                }
                break;
            
            case "empsalary":
                for(Employee eref : table)
                {
                    if(iMin > eref.getEmpSalary())
                    {
                        bFound = true;
                        iMin = eref.getEmpSalary();
                    }
                }
                if(bFound)
                {
                    DBMSUtils.printRecord(iMin,10,'d');
                }
                else
                {
                    System.out.println("Record not found");
                }
                break;
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	calculateSumAndAverage
    //	Description             :   This method apply summation or average operation on employee record.
    //	Parameters				:   String(fName)
    //	Returns					:   NONE
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    public void calculateSumAndAverage(
                                            String fName    // Define the function
                                        )
    {
        long lSum = 0L;

        for(Employee eref : table)
        {
            lSum += eref.getEmpSalary();
        }

        if((fName.equals("avg")) && (table.size() != 0))
        {
            double dAvg = (double)lSum/table.size();

            DBMSUtils.printEmployeeTableHeader(new String[] {"avg"});
            DBMSUtils.printRecord(dAvg,14,'f');
        }
        else if(fName.equals("sum"))
        {
            DBMSUtils.printEmployeeTableHeader(new String[] {"sum"});
            DBMSUtils.printRecord(lSum,12,'d');
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	countRecords
    //	Description             :   This method Count employee database records.
    //	Parameters				:   NONE
    //	Returns					:   NONE
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    public void countRecords()
    {
        DBMSUtils.printEmployeeTableHeader(new String[] {"count"});
        DBMSUtils.printRecord(table.size(),10,'d');
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	takeBackup
    //	Description             :   This method stores/write an object of the EmployeeDBMS class in a file.
    //	Parameters				:   String(fName)
    //	Returns					:   NONE
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    public void takeBackup(
                                String fName    // File name
                            )
    {
        try
        {
            File dir = new File(FILE_PATH);
            if(!dir.exists())
            {
                dir.mkdirs();
            }

            String fPath = FILE_PATH+fName+".ser";
            
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
    //	Method Name			    :	restoreBackup
    //	Description             :   This method reads an object of the EmployeeDBMS class from the file.
    //	Parameters				:   String(fName)
    //	Returns					:   EmployeeDBMS(object)
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    public static EmployeeDBMS restoreBackup(
                                                String fName    // File name
                                            )
    {
        try
        {
            String fPath = FILE_PATH+fName+".ser";
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
        catch(FileNotFoundException eobj1)
        {
            System.out.printf("Backup file (%s) not found. \n",fName);
            return null;
        }
        catch(Exception eobj2)
        {
            System.out.println("Failed to restore backup from "+fName);
            System.out.println("error : "+eobj2.getMessage());
            return null;
        }
    }
}