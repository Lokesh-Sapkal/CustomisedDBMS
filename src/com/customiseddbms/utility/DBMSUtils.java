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
    //	Method Name			    :	IsValidField
    //	Description             :   This method checks whether the field is valid or not.
    //	Parameters				:   String(fName)
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////
    public static boolean IsValidField(
                                            String fName    // Field name
                                        )
    {
        boolean bAns = false;

        switch(fName)
        {
            case "empid":
                bAns = true;
                break;

            case "empname":
                bAns = true;
                break;
                
            case "empage":
                bAns = true;
                break;
                
            case "empaddress":
                bAns = true;
                break;

            case "empsalary":
                bAns = true;
                break;
        }

        return bAns;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	DisplayTableHeaderAll
    //	Description             :   This method display employee database header for all fields.
    //	Parameters				:   NONE
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////
    public static void DisplayAllTableHeaders()
    {
        System.out.println("+--------+--------------+--------+--------------+------------+");
        System.out.printf("| %-6s | %-12s | %-6s | %-12s | %-10s |%n","ID","Name","Age","Address","Salary");
        System.out.println("+--------+--------------+--------+--------------+------------+");
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	DisplaySpecificTableHeader
    //	Description             :   This method display employee database header for specific field.
    //	Parameters				:   String(displayMode)
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public static void DisplaySpecificTableHeader(
                                                    String displayMode    // Define the field to print
                                                )
    {
        switch(displayMode)
        {
            case "empid":
                System.out.println("+--------+");
                System.out.printf("| %-6s |%n","ID");
                System.out.println("+--------+");
                break;

            case "empname":
                System.out.println("+--------------+");
                System.out.printf("| %-12s |%n","Name");
                System.out.println("+--------------+");
                break;
                
            case "empage":
                System.out.println("+--------+");
                System.out.printf("| %-6s |%n","Age");
                System.out.println("+--------+");
                break;
                
            case "empaddress":
                System.out.println("+--------------+");
                System.out.printf("| %-12s |%n","Address");
                System.out.println("+--------------+");
                break;

            case "empsalary":
                System.out.println("+------------+");
                System.out.printf("| %-10s |%n","Salary");
                System.out.println("+------------+");
                break;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	DisplayAllFields
    //	Description             :   This method display all fields of single employee record from database.
    //	Parameters				:   Employee(eref)
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void DisplayAllFields(
                                            Employee eref            // Employee class reference
                                        )
    {
        System.out.printf("| %-6d | %-12s | %-6d | %-12s | %-10d |%n",eref.EmpID,eref.EmpName,eref.EmpAge,eref.EmpAddress,eref.EmpSalary);
        System.out.println("+--------+--------------+--------+--------------+------------+");
    }
 
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	DisplaySpecificField
    //	Description             :   This method display single field of single employee record from database.
    //	Parameters				:   Employee(eref), String(displayMode)
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void DisplaySpecificField(
                                                Employee eref,          // Employee class reference
                                                String displayMode      // Define the field to print
                                            )
    {

        switch(displayMode)
        {
            case "empid":
                System.out.printf("| %-6d |%n",eref.EmpID);
                System.out.println("+--------+");
                break;

            case "empname":
                System.out.printf("| %-12s |%n",eref.EmpName);
                System.out.println("+--------------+");
                break;
                
            case "empage":
                System.out.printf("| %-6d |%n",eref.EmpAge);
                System.out.println("+--------+");
                break;
                
            case "empaddress":
                System.out.printf("| %-12s |%n",eref.EmpAddress);
                System.out.println("+--------------+");
                break;

            case "empsalary":
                System.out.printf("| %-10d |%n",eref.EmpSalary);
                System.out.println("+------------+");
                break;
        }
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