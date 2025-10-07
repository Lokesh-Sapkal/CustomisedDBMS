package com.customiseddbms.app;

import com.customiseddbms.model.Employee;
import com.customiseddbms.utility.DBMSUtils;
import com.customiseddbms.dbms.EmployeeDBMS;

import java.util.*;

///////////////////////////////////////////////////////////////////////////////////////////
//
//	Class Name			:	DBMSConsoleApp
//	Description			:	This class is an entry point of the application.
//
///////////////////////////////////////////////////////////////////////////////////////////
public class DBMSConsoleApp
{
    ///////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	main
    //	Description             :   This is an entry point method that provides a parser-driven 
    //                              console interface for interacting with the database.
    //	Parameters				:   String[0](FileName)
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String A[])
    {
        String str = null;
        boolean exitloop = true;
        String FileName = "employeeDB.ser";

        if(A.length != 0)
        {
            FileName = A[0];
        }

        EmployeeDBMS eobj = EmployeeDBMS.RestoreBackup(FileName);
        
        if(eobj == null)
        {
            eobj = new EmployeeDBMS();
        }
        else
        {
            int iMax = 0;

            for(Employee eref : eobj.Table)
            {
                if(iMax < eref.EmpID)
                {
                    iMax = eref.EmpID;
                }

                Employee.iCounter = ++iMax;
            }
        }

        Scanner sobj = new Scanner(System.in);

        System.out.println("----------------------------------------------------------------------");
        System.out.println("--------------- Welcome to Customised DBMS Application ---------------");
        System.out.println("----------------------------------------------------------------------");

        while(exitloop)
        {
            System.out.print("Employee DB > ");
            str = sobj.nextLine();

            str = str.trim();
            str = str.replaceAll("\\s+"," ");
            String tokens[] = str.split(" ");

            switch((tokens[0]).toLowerCase())
            {
                case "exit":
                    if((tokens.length == 2) && (tokens[1].equalsIgnoreCase("Employee")))
                    {
                        System.out.println("----------------------------------------------------------------------");
                        System.out.println("---------- Thank You for using Customised DBMS Application -----------");
                        System.out.println("----------------------------------------------------------------------");
                        eobj = null;
                        System.gc();
                        exitloop = false;
                        break;
                    }
                    else
                    {
                        DBMSUtils.InvalidCommand();
                    }
                    break;

                case "describe":
                    if((tokens.length == 2) && (tokens[1].equalsIgnoreCase("Employee")))
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
                    else
                    {
                        DBMSUtils.InvalidCommand();
                    }
                    break;

                case "insert":
                    try
                    {
                        if((tokens.length == 8) && (tokens[1].equalsIgnoreCase("into")) && (tokens[2].equalsIgnoreCase("Employee")) && (tokens[3].equalsIgnoreCase("Values")))
                        {
                            eobj.InsertQuery(tokens[4],Integer.parseInt(tokens[5]),tokens[6],Integer.parseInt(tokens[7]));
                        }
                        else
                        {
                            DBMSUtils.InvalidCommand();
                        }
                    }
                    catch(NumberFormatException nfeobj)
                    {
                        System.out.println("Exception occured.. : "+nfeobj);
                    }
                    break;

                case "select":
                    if((tokens.length == 4) && (tokens[2].equalsIgnoreCase("from")) && (tokens[3].equalsIgnoreCase("Employee")))
                    {
                        String Field = (tokens[1]).toLowerCase();

                        if(Field.equals("*"))
                        {
                            eobj.SelectAllFields();
                        }
                        else if(DBMSUtils.IsValidField(Field))
                        {
                            
                            eobj.SelectSpecificField(Field);
                        }
                        else
                        {
                            DBMSUtils.InvalidCommand();
                        }
                    }
                    else
                    {
                        DBMSUtils.InvalidCommand();
                    }
                    break;

                case "takebackup":
                    if((tokens.length == 2) && (tokens[1].equalsIgnoreCase("Employee")))
                    {
                        eobj.TakeBackup(FileName);
                    }
                    else
                    {
                        DBMSUtils.InvalidCommand();
                    }
                    break;

                default :
                    System.out.println("Please give valid input.");

            }   // End of switch case

        }   // End of while loop
    }   // End of main method
} // End of main class