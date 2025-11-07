package com.customiseddbms.utility;

import com.customiseddbms.model.Employee;
import com.customiseddbms.dbms.EmployeeDBMS;

////////////////////////////////////////////////////////////////////////////////////////////////////
//
//	Class Name			:	DBMSUtils
//	Description			:	This class provide utility methods to EmployeeDBMS and DBMSConsoleApp class.
//
////////////////////////////////////////////////////////////////////////////////////////////////////
public class DBMSUtils
{
    ///////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	DBMSUtils
    //	Description             :	This is a private constructor of class DBMSUtils 
    //                              which prevents instantiation of this utility class.
    //                              It throws an AssertionError if object creation is attempted.
    //	Parameters				:	NONE
    //	Returns					:	NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////
    private DBMSUtils()
    {
        throw new AssertionError("Cannot instantiate utility class");
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	isValidFields
    //	Description             :   This method checks whether the fields is valid or not.
    //	Parameters				:   String[](fName[])
    //	Returns					:   boolean
    //
    ///////////////////////////////////////////////////////////////////////////////////////////
    public static boolean isValidFields(
                                            String fName[]      // Array of field name
                                        )
    {
        boolean isValid = true;
            
        for(String field : fName)
        {
            switch(field)
            {
                case "empid","empname","empage","empaddress","empsalary":
                    break;
                default:
                    isValid = false;
                    break;
            }
            if(!isValid)
            {
                break;
            }
        }

        return isValid;
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	isAggregateFunction
    //	Description             :   This method checks whether the aggregate function is valid or not
    //                              and also call related methods.
    //	Parameters				:   String(funcName), String(fName), EmployeeDBMS(eobj)
    //	Returns					:   boolean
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////
    public static boolean isAggregateFunction(
                                                  String funcName ,     // Aggregate function name
                                                  String fName,         // field name
                                                  EmployeeDBMS eobj     // object of EmployeeDBMS class
                                             )
    {
        boolean isValid = false;
        switch(funcName)
        {
            case "max","min":
                isValid = true;
                if((fName.equals("empage")) || (fName.equals("empsalary")))
                {
                    if(funcName.equals("max"))
                    {
                        eobj.selectMaximumRecord(fName);
                    }
                    else
                    {
                        eobj.selectMinimumRecord(fName);
                    }
                }
                else
                {
                    invalidCommand();
                }
                break;

            case "sum","avg":
                isValid = true;
                if(fName.equals("empsalary"))
                {
                    eobj.calculateSumAndAverage(funcName);
                }
                else
                {
                    invalidCommand();
                }
                break;

            case "count":
                isValid = true;
                if(isValidFields(new String[] {fName}))
                {
                    eobj.countRecords();
                }
                else
                {
                    invalidCommand();
                }
                break;
        }

        return isValid;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	checkRelationalOperator
    //	Description             :   This method checks whether the operator is valid or not and also
    //                              call related methods.
    //	Parameters				:   String[](fNames[]), String(fName), String(operator), T(value),
    //                              EmployeeDBMS(eobj)
    //	Returns					:   void
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////
    public static <T> void checkRelationalOperator(
                                                        String fNames[],        // Array of field name
                                                        String fName,           // Field name
                                                        String operator,        // Define operator
                                                        T value,                // Value of field
                                                        EmployeeDBMS eobj       // object of EmployeeDBMS class
                                                    )
    {
        switch(fName)
        {
            case "empid","empage","empsalary":
                switch(operator)
                {
                    case "=","!=",">","<",">=","<=":
                        eobj.selectSpecificIntegerRecords(fNames,fName,operator,(Integer) value);
                        break;
                    default:
                        invalidCommand();
                        break;
                }
                break;

            case "empname","empaddress":
                if(operator.equals("="))
                {
                    eobj.selectSpecificStringRecords(fNames,fName,operator,(String) value);
                }
                else
                {
                    invalidCommand();
                }
                break;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	extractIntField
    //	Description             :   This method extracts an integer field value from employee database.
    //	Parameters				:   String(fName), Employee(eref)
    //	Returns					:   int - value of integer field
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    public static int extractIntField(
                                        String fName,       // Field name
                                        Employee eref       // object of Employee class
                                    )
    {
        switch(fName)
        {
            case "empid":
                return eref.getEmpId();
            case "empage":
                return eref.getEmpAge();
            case "empsalary":
                return eref.getEmpSalary();
        }

        return 0;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	evaluateCondition
    //	Description             :   This method evaluates a relational condition for integer comparison.
    //	Parameters				:   int(fieldValue), String(operator), int(value)
    //	Returns					:   boolean
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public static boolean evaluateCondition(
                                                int fieldValue,     // Value of field
                                                String operator,    // Define operator
                                                int value           // Accepted value
                                            ) 
    {
        switch (operator) 
        {
            case "=":  
                return fieldValue == value;
            case "!=": 
                return fieldValue != value;
            case ">":  
                return fieldValue > value;
            case "<":  
                return fieldValue < value;
            case ">=": 
                return fieldValue >= value;
            case "<=": 
                return fieldValue <= value;
        }

        return false;
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	printSchema
    //	Description             :   This method prints the employee database schema.
    //	Parameters				:   NONE
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public static void printSchema()
    {
        System.out.println("+---------------+");
        System.out.println("| Schema\t|");
        System.out.println("+---------------+");
        System.out.println("| empId \t|");
        System.out.println("| empName\t|");
        System.out.println("| empAge\t|");
        System.out.println("| empAddress\t|");
        System.out.println("| empSalary\t|");
        System.out.println("+---------------+");
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	printEmployeeTableHeader
    //	Description             :   This method prints the employee database table header.
    //	Parameters				:   String[](fName[])
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public static void printEmployeeTableHeader(
                                                    String fName[]    // Array of field name
                                                )
    {
        String headers[] = new String[fName.length];
        int widths[] = new int[fName.length];

        for(int i = 0; i < fName.length; i++)
        {
            switch(fName[i])
            {
                case "empid":
                    headers[i] = "ID";
                    widths[i] = 6;
                    break;
                case "empname":
                    headers[i] = "Name";
                    widths[i] = 12;
                    break;
                case "empage":
                    headers[i] = "Age";
                    widths[i] = 6;
                    break;
                case "empaddress":
                    headers[i] = "Address";
                    widths[i] = 12;
                    break;
                case "empsalary":
                    headers[i] = "Salary";
                    widths[i] = 10;
                    break;
                case "avg":
                    headers[i] = "Average Salary";
                    widths[i] = 14;
                    break;
                case "sum":
                    headers[i] = "Total Salary";
                    widths[i] = 12;
                    break;
                case "count":
                    headers[i] = "Count";
                    widths[i] = 10;
                    break;
            }
        }

        printCustomTableHeader(headers, widths);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	printCustomTableHeader
    //	Description             :   This method prints the employee database table header with the 
    //                              specified column headers and widths.
    //	Parameters				:   String[](headers[]), int[](widths[])
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////
    private static void printCustomTableHeader(
                                                    String headers[],   // Array of header(field) name
                                                    int widths[]        // Array of widths
                                                )
    {
        StringBuilder border = new StringBuilder("+");
        for (int width : widths) 
        {
            border.append("-".repeat(width + 2)).append("+");
        }
        System.out.println(border);
        System.out.print("|");
        for (int i = 0; i < headers.length; i++) 
        {
            System.out.printf(" %-" + widths[i] + "s |", headers[i]);
        }
        System.out.println();
        System.out.println(border);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	printEmployeeRecord
    //	Description             :   This method display single employee record from database.
    //	Parameters				:   Employee(eref), String[](fName[])
    //	Returns					:   NONE
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////
    public static void printEmployeeRecord(
                                                Employee eref,      // Employee class reference
                                                String fName[]      // Array of field name
                                            )
    {
        String headers[] = new String[fName.length];
        int widths[] = new int[fName.length];
        char cValues[] = new char[fName.length];

        for(int i = 0; i < fName.length; i++)
        {
            switch(fName[i])
            {
                case "empid":
                    headers[i] = "empid";
                    widths[i] = 6;
                    cValues[i] = 'd';
                    break;
                case "empname":
                    headers[i] = "empname";
                    widths[i] = 12;
                    cValues[i] = 's';
                    break;
                case "empage":
                    headers[i] = "empage";
                    widths[i] = 6;
                    cValues[i] = 'd';
                    break;
                case "empaddress":
                    headers[i] = "empaddress";
                    widths[i] = 12;
                    cValues[i] = 's';
                    break;
                case "empsalary":
                    headers[i] = "empsalary";
                    widths[i] = 10;
                    cValues[i] = 'd';
                    break;
            }
        }

        printCustomRecord(eref,headers,widths,cValues);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	printCustomRecord
    //	Description             :   This method display single employee record from database with the 
    //                              specified column width and argument type.
    //	Parameters				:   Employee(eref), String[](headers[]), int[](widths[]), char[](cValues[])
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    private static void printCustomRecord(
                                            Employee eref,      // Employee class reference
                                            String headers[],   // Array of header(field) name
                                            int widths[],       // Array of widths
                                            char cValues[]      // Define argument type
                                        )
    {
        
        System.out.print("|");
        for (int i = 0; i < headers.length; i++) 
        {
            switch(headers[i])
            {
                case "empid":
                    System.out.printf(" %-" + widths[i] + cValues[i] + " |", eref.getEmpId());
                    break;
                case "empname":
                    System.out.printf(" %-" + widths[i] + cValues[i] + " |", eref.getEmpName());
                    break;
                case "empage":
                    System.out.printf(" %-" + widths[i] + cValues[i] + " |", eref.getEmpAge());
                    break;
                case "empaddress":
                    System.out.printf(" %-" + widths[i] + cValues[i] + " |", eref.getEmpAddress());
                    break;
                case "empsalary":
                    System.out.printf(" %-" + widths[i] + cValues[i] + " |", eref.getEmpSalary());
                    break;
            }
        }
        System.out.println();

        StringBuilder border = new StringBuilder("+");
        for (int width : widths) 
        {
            border.append("-".repeat(width + 2)).append("+");
        }
        System.out.println(border);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	printRecord
    //	Description             :   This method display single record from database with the 
    //                              specified value(generic), width and argument type.
    //	Parameters				:   T(value), int(width), char(args)
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public static <T> void printRecord(
                                            T value,        // Value of record
                                            int width,      // Width of record
                                            char args       // Define argument type
                                        )
    {
        if(args == 'f')
        {
            System.out.printf("| %-" + width + ".2f |%n", value);
        }
        else
        {
            System.out.printf("| %-" + width + args + " |%n", value);
        }
        
        StringBuilder border = new StringBuilder("+");

        border.append("-".repeat(width + 2)).append("+");

        System.out.println(border);
    }
 
    ///////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	invalidCommand
    //	Description             :   This method display error message if command mismatched.
    //	Parameters				:   NONE
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////
    public static void invalidCommand()
    {
        System.out.println("Command not found");
        System.out.println("Please give valid command.");
    }
}