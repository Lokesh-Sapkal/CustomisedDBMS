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
    //	Method Name			    :	IsValidFields
    //	Description             :   This method checks whether the fields is valid or not.
    //	Parameters				:   String[](fName[])
    //	Returns					:   boolean
    //
    ///////////////////////////////////////////////////////////////////////////////////////////
    public static boolean IsValidFields(
                                            String fName[]      // Array of field name
                                        )
    {
        boolean bAns = true;
            
        for(String field : fName)
        {
            switch(field)
            {
                case "empid":
                case "empname":
                case "empage":
                case "empaddress":
                case "empsalary":
                    break;
                default:
                    bAns = false;
                    break;
            }
            if(!bAns)
            {
                break;
            }
        }

        return bAns;
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	AggregateFunction
    //	Description             :   This method checks whether the aggregate function is valid or not
    //                              and also call related methods.
    //	Parameters				:   String(fName)
    //	Returns					:   boolean
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////
    public static boolean AggregateFunction(
                                                  String funcName ,     // Aggregate function name
                                                  String fName,         // field name
                                                  EmployeeDBMS eobj     // object of EmployeeDBMS class
                                             )
    {
        switch(funcName)
        {
            case "max","min":
                if((fName.equals("empage")) || (fName.equals("empsalary")))
                {
                    if(funcName.equals("max"))
                    {
                        eobj.SelectMaximumRecord(fName);
                    }
                    else
                    {
                        eobj.SelectMinimumRecord(fName);
                    }
                    return true;
                }
                else
                {
                    InvalidCommand();
                }
                return true;
            case "sum","avg":
                if(fName.equals("empsalary"))
                {
                    eobj.CalculateSumAndAverage(funcName);
                }
                else
                {
                    InvalidCommand();
                }
                return true;
            case "count":
                eobj.CountRecords();
                return true;
            default:
                return false;
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	PrintEmployeeTableHeader
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
        System.out.println("| EmpId \t|");
        System.out.println("| EmpName\t|");
        System.out.println("| EmpAge\t|");
        System.out.println("| EmpAddress\t|");
        System.out.println("| EmpSalary\t|");
        System.out.println("+---------------+");
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	PrintEmployeeTableHeader
    //	Description             :   This method prints the employee database table header.
    //	Parameters				:   String[](fName[])
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public static void PrintEmployeeTableHeader(
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

        PrintCustomTableHeader(headers, widths);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	PrintCustomTableHeader
    //	Description             :   This method prints the employee database table header with the 
    //                              specified column headers and widths.
    //	Parameters				:   String[](headers[]), int[](widths[])
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////
    private static void PrintCustomTableHeader(
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
    //	Method Name			    :	DisplayAllFields
    //	Description             :   This method display single employee record from database.
    //	Parameters				:   Employee(eref), String[](fName[])
    //	Returns					:   NONE
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////
    public static void PrintEmployeeRecord(
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

        PrintCustomRecord(eref,headers,widths,cValues);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	PrintCustomRecord
    //	Description             :   This method display single employee record from database with the 
    //                              specified column width and argument type.
    //	Parameters				:   Employee(eref), String[](headers[]), int[](widths[]), char[](cValues[])
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    private static void PrintCustomRecord(
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
                    System.out.printf(" %-" + widths[i] + cValues[i] + " |", eref.EmpID);
                    break;
                case "empname":
                    System.out.printf(" %-" + widths[i] + cValues[i] + " |", eref.EmpName);
                    break;
                case "empage":
                    System.out.printf(" %-" + widths[i] + cValues[i] + " |", eref.EmpAge);
                    break;
                case "empaddress":
                    System.out.printf(" %-" + widths[i] + cValues[i] + " |", eref.EmpAddress);
                    break;
                case "empsalary":
                    System.out.printf(" %-" + widths[i] + cValues[i] + " |", eref.EmpSalary);
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
    //	Method Name			    :	PrintRecord
    //	Description             :   This method display single record from database with the 
    //                              specified value(generic), width and argument type.
    //	Parameters				:   T(value), int(width), char(args)
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public static <T> void PrintRecord(
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
    //	Method Name			    :	InvalidCommand
    //	Description             :   This method display error message if command mismatched.
    //	Parameters				:   NONE
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////
    public static void InvalidCommand()
    {
        System.out.println("Command not found");
        System.out.println("Please give valid command.");
    }
}