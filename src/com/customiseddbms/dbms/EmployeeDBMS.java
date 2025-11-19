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

        System.out.println("New Record inserted successfully.\n");
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	selectRecords
    //	Description             :   This method display employee records from the database.
    //	Parameters				:   String[] (fNames[])
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////
    public void selectRecords(
                                String fNames[]    // Array of field name
                             )
    {
        if(table.isEmpty())
        {
            System.out.println("The database is currently empty.\n");
            return;
        }

        DBMSUtils.printEmployeeTableHeader(fNames);

        for(Employee eref : table)
        {
            DBMSUtils.printEmployeeRecord(eref,fNames);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	selectSpecificRecords
    //	Description             :   This method display specific employee records from the database.
    //	Parameters				:   String[](fNames[]), String(fName), String(operator), Object(Value)
    //	Returns					:   NONE
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void selectSpecificRecords(
                                        String fNames[],        // Array of field name
                                        String fName,           // Field name
                                        String operator,        // Define operator
                                        Object value            // Value of field
                                    )
    {
        if(table.isEmpty())
        {
            System.out.println("The database is currently empty.\n");
            return;
        }

        boolean bFound = false;
        DBMSUtils.printEmployeeTableHeader(fNames);

        for (Employee eref : table) 
        {
            Object fieldValue = DBMSUtils.extractField(fName, eref);
            if(DBMSUtils.evaluateCondition(fName, fieldValue, operator, value)) 
            {
                bFound = true;
                DBMSUtils.printEmployeeRecord(eref, fNames);
            }
        }

        if(!bFound)
        {
            System.out.println("Record not found.\n");
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
        if(table.isEmpty())
        {
            System.out.println("The database is currently empty.\n");
            return;
        }

        DBMSUtils.printEmployeeTableHeader(new String[] {field});

        int iMax = Integer.MIN_VALUE;
        int extractedValue = 0;

        for(Employee eref : table)
        {
            extractedValue = Integer.parseInt(DBMSUtils.extractField(field,eref).toString());
            if(iMax < extractedValue)
            {
                iMax = extractedValue;
            }
        }

        switch(field)
        {
            case "empage":
                DBMSUtils.printRecord(iMax,6,'d');
                break;
            case "empsalary":
                DBMSUtils.printRecord(iMax,10,'d');
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
        if(table.isEmpty())
        {
            System.out.println("The database is currently empty.\n");
            return;
        }

        DBMSUtils.printEmployeeTableHeader(new String[] {field});

        int iMin = Integer.MAX_VALUE;
        int extractedValue = 0;

        for(Employee eref : table)
        {
            extractedValue = Integer.parseInt(DBMSUtils.extractField(field,eref).toString());
            if(iMin > extractedValue)
            {
                iMin = extractedValue;
            }
        }

        switch(field)
        {
            case "empage":
                DBMSUtils.printRecord(iMin,6,'d');
                break;
            case "empsalary":
                DBMSUtils.printRecord(iMin,10,'d');
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
        if(table.isEmpty())
        {
            System.out.println("The database is currently empty.\n");
            return;
        }

        long lSum = 0L;

        for(Employee eref : table)
        {
            lSum += eref.getEmpSalary();
        }

        if(fName.equals("avg"))
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
    //	Description             :   This method count employee database records.
    //	Parameters				:   NONE
    //	Returns					:   NONE
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    public void countRecords()
    {
        if(table.isEmpty())
        {
            System.out.println("The database is currently empty.\n");
            return;
        }

        DBMSUtils.printEmployeeTableHeader(new String[] {"count"});
        DBMSUtils.printRecord(table.size(),10,'d');
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	updateRecords
    //	Description             :   This method update employee records from the employee database.
    //	Parameters				:   String[](fNames[]), String[](fValues[])
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////
    public void updateRecords(
                                String fNames[],    // Array of field name
                                String fValues[]    // Array of accepted value
                             )
    {
        if(table.isEmpty())
        {
            System.out.println("The database is currently empty.\n");
            return;
        }

        int countRows = 0;

        for(Employee eref : table)
        {
            countRows++;
            DBMSUtils.updateRecord(eref,fNames,fValues);
        }

        if(countRows != 0)
        {
            System.out.printf("Record updated successfully. %d row(s) affected.\n\n",countRows);   
        }
        else
        {
            System.out.println("Record not found. Update failed.\n");
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	updateSpecificRecords
    //	Description             :   This method update specific employee records from the employee database.
    //	Parameters				:   String[](fNames[]), String[](fValues[]), String(fName), String(operator), 
    //                              Object(value)
    //	Returns					:   NONE
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void updateSpecificRecords(
                                        String fNames[],        // Array of field name
                                        String fValues[],       // Array of accepted value
                                        String fName,           // Field name
                                        String operator,        // Define operator
                                        Object value            // Value of field
                                     )
    {
        if(table.isEmpty())
        {
            System.out.println("The database is currently empty.\n");
            return;
        }

        int countRows = 0;

        for (Employee eref : table) 
        {
            Object fieldValue = DBMSUtils.extractField(fName, eref);
            if(DBMSUtils.evaluateCondition(fName, fieldValue, operator, value)) 
            {
                countRows++;
                DBMSUtils.updateRecord(eref,fNames,fValues);
            }
        }

        if(countRows != 0)
        {
            System.out.printf("Record updated successfully. %d row(s) affected.\n\n",countRows);   
        }
        else
        {
            System.out.println("Record not found. Update failed.\n");
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	deleteRecords
    //	Description             :   This method delete employee records from the employee database.
    //	Parameters				:   NONE
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////
    public void deleteRecords()
    {
        if(table.isEmpty())
        {
            System.out.println("The database is currently empty.\n");
            return;
        }

        int countRows = table.size();
        table.clear();

        System.out.printf("Record deleted successfully. %d row(s) affected.\n\n",countRows);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	deleteSpecificRecords
    //	Description             :   This method delete specific employee records from the employee database.
    //	Parameters				:   String(fName), String(operator), Object(value)
    //	Returns					:   NONE
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void deleteSpecificRecords(
                                        String fName,           // Field name
                                        String operator,        // Define operator
                                        Object value            // Value of field
                                     )
    {
        if(table.isEmpty())
        {
            System.out.println("The database is currently empty.\n");
            return;
        }

        int i = 0;
        int countRows = 0;

        for(i = 0;i < table.size();i++)
        {
            Employee eref = table.get(i);
            Object fieldValue = DBMSUtils.extractField(fName, eref);
            if(DBMSUtils.evaluateCondition(fName, fieldValue, operator, value))
            {
                table.remove(i);
                countRows++;
                i--;
            }
        }

        if(countRows != 0)
        {
            System.out.printf("Record deleted successfully. %d row(s) affected.\n\n",countRows);   
        }
        else
        {
            System.out.println("Record not found. Delete failed.\n");
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	dropTable
    //	Description             :   This method delete the employee database.
    //	Parameters				:   String(fName)
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////
    public void dropTable(
                             String fName    // File name
                         )
    {
        String fPath = FILE_PATH+fName+".ser";

        File f = new File(fPath);

        if(f.delete())
        {
            table.clear();
            Employee.iCounter = 1;
            System.out.printf("%s.ser file deleted successfully\n\n",fName);
        }
        else
        {
            System.out.println("File not found or couldn't be deleted\n");
        }
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
            
            System.out.printf("Backup created successfully in %s.ser file.\n\n",fName);
        }
        catch(Exception eobj)
        {
            System.out.println("The class object could not be written to the file.");
            System.out.println("Error : "+eobj+"\n");
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