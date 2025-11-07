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
        String inputCommand = null;
        boolean isRunning = true;
        String backupFile = "employeeDB";

        if(A.length != 0)
        {
            backupFile = A[0];
        }

        EmployeeDBMS eobj = EmployeeDBMS.restoreBackup(backupFile);
        
        if(eobj == null)
        {
            eobj = new EmployeeDBMS();
        }
        else
        {
            int iMax = 0;

            for(Employee eref : eobj.getTable())
            {
                if(iMax < eref.getEmpId())
                {
                    iMax = eref.getEmpId();
                }
            }
            Employee.iCounter = ++iMax;
        }

        Scanner sobj = new Scanner(System.in);

        System.out.println("----------------------------------------------------------------------");
        System.out.println("--------------- Welcome to Customised DBMS Application ---------------");
        System.out.println("----------------------------------------------------------------------");

        while(isRunning)
        {
            System.out.print("Employee DB > ");
            inputCommand = sobj.nextLine();

            inputCommand = inputCommand.trim();
            inputCommand = inputCommand.replaceAll("\\s+"," ");
            String tokens[] = inputCommand.split(" ");

            if(tokens.length == 0)
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
                            isRunning = false;
                        }
                        else
                        {
                            DBMSUtils.invalidCommand();
                        }
                        break;

                    case "describe":
                        if((tokens.length == 2) && (tokens[1].equalsIgnoreCase("Employee")))
                        {
                            DBMSUtils.printSchema();
                        }
                        else
                        {
                            DBMSUtils.invalidCommand();
                        }
                        break;

                    case "insert":
                        try
                        {
                            if((tokens.length == 8) && (tokens[1].equalsIgnoreCase("into")) && (tokens[2].equalsIgnoreCase("Employee")) && (tokens[3].equalsIgnoreCase("Values")))
                            {
                                eobj.insertQuery(tokens[4],Integer.parseInt(tokens[5]),tokens[6],Integer.parseInt(tokens[7]));
                            }
                            else
                            {
                                DBMSUtils.invalidCommand();
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
                        int fromIndex = 0;
                        int i = 0;

                        for(i = 2;i < tokens.length;i++)
                        {
                            if(tokens[i].equalsIgnoreCase("from"))
                            {
                                fromIndex = i;
                                break;
                            }
                        }

                        if((fromIndex != 0) && (tokens.length >= 4) && (tokens[fromIndex+1].equalsIgnoreCase("Employee")))
                        {
                            String fields[] = Arrays.copyOfRange(tokens, 1, fromIndex);

                            for(i = 0;i < fields.length;i++)
                            {
                                fields[i] = fields[i].toLowerCase();
                            }

                            if(fromIndex == (tokens.length-2))
                            {
                                if(fields.length == 1)
                                {
                                    if(fields[0].equals("*"))
                                    {
                                        String allFields[] = {"empid","empname","empage","empaddress","empsalary"};
                                        eobj.selectQuery(allFields);
                                    }
                                    else if(DBMSUtils.isValidFields(fields))
                                    {
                                        eobj.selectQuery(fields);
                                    }
                                    else
                                    {
                                        DBMSUtils.invalidCommand();
                                    }
                                }
                                else if(!(DBMSUtils.isAggregateFunction(fields[0], fields[1], eobj)))
                                {
                                    if(DBMSUtils.isValidFields(fields))
                                    {
                                        eobj.selectQuery(fields);
                                    }
                                    else
                                    {
                                        DBMSUtils.invalidCommand();
                                    }
                                }
                            }
                            else if(tokens[fromIndex+2].equalsIgnoreCase("where") && (fromIndex == (tokens.length-6)))
                            {
                                if(DBMSUtils.isValidFields(new String[] {tokens[fromIndex+3].toLowerCase()}))
                                {
                                    if((fields.length == 1) && (fields[0].equals("*")))
                                    {
                                        String allFields[] = {"empid","empname","empage","empaddress","empsalary"};
                                        fields = Arrays.copyOfRange(allFields, 0, allFields.length);
                                    }

                                    switch(tokens[fromIndex+3].toLowerCase())
                                    {
                                        case "empid":
                                        case "empage":
                                        case "empsalary":
                                            try
                                            {
                                                DBMSUtils.checkRelationalOperator(fields,tokens[fromIndex+3],tokens[fromIndex+4],Integer.parseInt(tokens[fromIndex+5]), eobj);
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
                                        case "empname":
                                        case "empaddress":
                                            DBMSUtils.checkRelationalOperator(fields,tokens[fromIndex+3],tokens[fromIndex+4],tokens[fromIndex+5], eobj);
                                            break;
                                    }
                                }
                                else
                                {
                                    DBMSUtils.invalidCommand();
                                }
                                
                            }
                            else
                            {
                                DBMSUtils.invalidCommand();
                            }
                        }
                        else
                        {
                            DBMSUtils.invalidCommand();
                        }
                        break;

                    // case "update":
                    //     break;

                    // case "delete":
                    //     break;

                    case "takebackup":
                        if((tokens.length == 2) && (tokens[1].equalsIgnoreCase("Employee")))
                        {
                            eobj.takeBackup(backupFile);
                        }
                        else
                        {
                            DBMSUtils.invalidCommand();
                        }
                        break;

                    default :
                        System.out.println("Please give valid input.");
                }   // End of switch case

            }   // End of else

        }   // End of while loop

    }   // End of main method

} // End of main class