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

            switch((tokens[0]).toLowerCase())
            {
                case "help":
                    if(tokens.length == 1)
                    {
                        DBMSUtils.displayHelp();
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
                    if((tokens.length == 8) && (tokens[1].equalsIgnoreCase("into")) && (tokens[2].equalsIgnoreCase("Employee")) && (tokens[3].equalsIgnoreCase("Values")))
                    {
                        if(DBMSUtils.validateValue(new String[] {"empname","empage","empaddress","empsalary"},new String[] {tokens[4],tokens[5],tokens[6],tokens[7]}))
                        {
                            int age = Integer.parseInt(tokens[5]);
                            int salary = Integer.parseInt(tokens[7]);
                            if(age < 15)
                            {
                                System.out.println("Invalid EmpAge. Age must be 15 or above.\n");
                            }
                            else
                            {
                                eobj.insertQuery(tokens[4],age,tokens[6],salary);
                            }
                        }
                    }
                    else
                    {
                        DBMSUtils.invalidCommand();
                    }
                    break;

                case "select":
                    int fromIndex = 0;
                    int i = 0;

                    for(i = 2;i < (tokens.length-1);i++)
                    {
                        if(tokens[i].equalsIgnoreCase("from"))
                        {
                            fromIndex = i;
                            break;
                        }
                    }

                    if((fromIndex != 0) && (tokens.length > fromIndex+1) && (tokens[fromIndex+1].equalsIgnoreCase("Employee")))
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
                                    eobj.selectRecords(allFields);
                                }
                                else if(DBMSUtils.isValidFields(fields))
                                {
                                    eobj.selectRecords(fields);
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
                                    eobj.selectRecords(fields);
                                }
                                else
                                {
                                    DBMSUtils.invalidCommand();
                                }
                            }
                        }
                        else if((fromIndex == (tokens.length-6)) && (tokens[fromIndex+2].equalsIgnoreCase("where")))
                        {
                            if(DBMSUtils.isValidFields(new String[] {tokens[fromIndex+3].toLowerCase()}))
                            {
                                if((fields.length == 1) && (fields[0].equals("*")))
                                {
                                    String allFields[] = {"empid","empname","empage","empaddress","empsalary"};
                                    fields = Arrays.copyOfRange(allFields, 0, allFields.length);
                                }

                                if(DBMSUtils.checkRelationalOperator(tokens[fromIndex+3],tokens[fromIndex+4]) && (DBMSUtils.isValidFields(fields)))
                                {
                                    if(DBMSUtils.validateValue(new String[] {tokens[fromIndex+3]},new String[] {tokens[fromIndex+5]}))
                                    {
                                        eobj.selectSpecificRecords(fields,tokens[fromIndex+3],tokens[fromIndex+4],(tokens[fromIndex+5]));
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
                    }
                    else
                    {
                        DBMSUtils.invalidCommand();
                    }
                    break;

                case "update":
                    if(tokens.length >= 6)
                    {
                        if((tokens[1].equalsIgnoreCase("Employee")) && (tokens[2].equalsIgnoreCase("set")))
                        {
                            int j = 0;
                            int whereIndex = -1;
                            int countEqual = 0;
                            String fields[];
                            String fValues[];

                            for(i = 3;i < tokens.length;i++)
                            {
                                if(tokens[i].equals("="))
                                {
                                    countEqual++;
                                }
                                else if(tokens[i].equalsIgnoreCase("where"))
                                {
                                    whereIndex = i;
                                    break;
                                }
                            }

                            fields = new String[countEqual];
                            fValues = new String[countEqual];

                            for(i = 3,j = 0;(i < tokens.length-1) && (i != whereIndex) && (j < countEqual);i++)
                            {
                                if(tokens[i].equals("="))
                                {
                                    fields[j] = tokens[i-1].toLowerCase();
                                    fValues[j] = tokens[i+1];
                                    j++;
                                }
                            }

                            if(Arrays.asList(fields).contains("empid"))
                            {
                                System.out.println("Employee ID is unique for each record and cannot be updated.\n");
                                if(fields.length == 1)
                                {
                                    break;
                                }
                            }

                            if(DBMSUtils.isValidFields(fields))
                            {
                                if(DBMSUtils.validateValue(fields,fValues))
                                {
                                    if(whereIndex == -1)
                                    {
                                        eobj.updateRecords(fields,fValues);
                                    }
                                    else if((whereIndex == tokens.length-4) && (DBMSUtils.checkRelationalOperator(tokens[whereIndex+1],tokens[whereIndex+2])))
                                    {
                                        if(DBMSUtils.validateValue(new String[] {tokens[whereIndex+1]},new String[] {tokens[whereIndex+3]}))
                                        {
                                            eobj.updateSpecificRecords(fields,fValues,tokens[whereIndex+1],tokens[whereIndex+2],tokens[whereIndex+3]);
                                        }
                                    }
                                    else
                                    {
                                        DBMSUtils.invalidCommand();
                                    }
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

                case "exit":
                    if((tokens.length == 1) || ((tokens.length == 2) && (tokens[1].equalsIgnoreCase("Employee"))))
                    {
                        System.out.println("----------------------------------------------------------------------");
                        System.out.println("---------- Thank You for using Customised DBMS Application -----------");
                        System.out.println("----------------------------------------------------------------------");
                        sobj.close();
                        isRunning = false;
                    }
                    else
                    {
                        DBMSUtils.invalidCommand();
                    }
                    break;

                default :
                    System.out.println("Please give valid input.\n");
            }   // End of switch case

        }   // End of while loop

    }   // End of main method

} // End of main class