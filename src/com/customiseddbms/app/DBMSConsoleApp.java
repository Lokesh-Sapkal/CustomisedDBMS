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
    //	Parameters				:   String[](A[])
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////
    public static void main(
                                String A[]      // File name
                            )
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
            }
            Employee.iCounter = ++iMax;
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

            if((tokens.length < 1) || (tokens.length > 8))
            {
                System.out.println("Please give valid input.");
            }
            else
            {
                switch((tokens[0]).toLowerCase())
                {
                    case "exit":
                        if((tokens.length == 1) || ((tokens.length == 2) && (tokens[1].equalsIgnoreCase("Employee"))))
                        {
                            System.out.println("----------------------------------------------------------------------");
                            System.out.println("---------- Thank You for using Customised DBMS Application -----------");
                            System.out.println("----------------------------------------------------------------------");
                            eobj = null;
                            System.gc();
                            exitloop = false;
                        }
                        else
                        {
                            DBMSUtils.InvalidCommand();
                        }
                        break;

                    case "describe":
                        if((tokens.length == 2) && (tokens[1].equalsIgnoreCase("Employee")))
                        {
                            DBMSUtils.printSchema();
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
                        catch(Exception geobj)
                        {
                            System.out.println("Exception occured.. : "+geobj);
                        }
                        break;

                    case "select":
                        switch(tokens.length)
                        {
                            case 4:
                                if((tokens[2].equalsIgnoreCase("from")) && (tokens[3].equalsIgnoreCase("Employee")))
                                {
                                    String Field[] = new String[1];

                                    Field[0] = (tokens[1]).toLowerCase();

                                    if(Field[0].equals("*"))
                                    {
                                        String Fields[] = {"empid","empname","empage","empaddress","empsalary"};
                                        eobj.SelectQuery(Fields);
                                    }
                                    else if(DBMSUtils.IsValidFields(Field))
                                    {
                                        eobj.SelectQuery(Field);
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

                            case 5:
                                if((tokens[3].equalsIgnoreCase("from")) && (tokens[4].equalsIgnoreCase("Employee")))
                                {
                                    String Fields[] = new String[2];

                                    Fields[0] = (tokens[1]).toLowerCase();
                                    Fields[1] = (tokens[2]).toLowerCase();

                                    if(DBMSUtils.IsValidFields(new String[] {Fields[1]}))
                                    {
                                        if(!(DBMSUtils.AggregateFunction(Fields[0], Fields[1], eobj)))
                                        {
                                            if(DBMSUtils.IsValidFields(Fields))
                                            {
                                                eobj.SelectQuery(Fields);
                                            }
                                            else
                                            {
                                                DBMSUtils.InvalidCommand();
                                            }
                                        }
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

                            case 6:
                                if((tokens[4].equalsIgnoreCase("from")) && (tokens[5].equalsIgnoreCase("Employee")))
                                {
                                    String Fields[] = new String[3];

                                    Fields[0] = (tokens[1]).toLowerCase();
                                    Fields[1] = (tokens[2]).toLowerCase();
                                    Fields[2] = (tokens[3]).toLowerCase();

                                    if(DBMSUtils.IsValidFields(Fields))
                                    {
                                        eobj.SelectQuery(Fields);
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

                            case 7:
                                if((tokens[5].equalsIgnoreCase("from")) && (tokens[6].equalsIgnoreCase("Employee")))
                                {
                                    String Fields[] = new String[4];

                                    Fields[0] = (tokens[1]).toLowerCase();
                                    Fields[1] = (tokens[2]).toLowerCase();
                                    Fields[2] = (tokens[3]).toLowerCase();
                                    Fields[3] = (tokens[4]).toLowerCase();

                                    if(DBMSUtils.IsValidFields(Fields))
                                    {
                                        eobj.SelectQuery(Fields);
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
                            default:
                                DBMSUtils.InvalidCommand();
                        }
                        break;

                    // case "update":
                    //     break;

                    // case "delete":
                    //     break;

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

            }   // End of else

        }   // End of while loop

    }   // End of main method

} // End of main class