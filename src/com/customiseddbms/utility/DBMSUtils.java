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
    //	Method Name			    :	TableHeader
    //	Description             :   This method display employee database header.
    //	Parameters				:   NONE
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////
    public static void TableHeader()
    {
        System.out.println("+--------+--------------+--------+--------------+------------+");
        System.out.printf("| %-6s | %-12s | %-6s | %-12s | %-10s |%n","ID","Name","Age","Address","Salary");
        System.out.println("+--------+--------------+--------+--------------+------------+");
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	DisplayAllFields
    //	Description             :   This method display single employee record from database.
    //	Parameters				:   Employee(object).
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////
    public static void DisplayAllFields(
                                            Employee eref   // Employee class reference
                                        )
    {
        System.out.printf("| %-6d | %-12s | %-6d | %-12s | %-10d |%n",eref.EmpID,eref.EmpName,eref.EmpAge,eref.EmpAddress,eref.EmpSalary);
        System.out.println("+--------+--------------+--------+--------------+------------+");
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	InvalidCommand
    //	Description             :   This mathod display error message if command mismatched.
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