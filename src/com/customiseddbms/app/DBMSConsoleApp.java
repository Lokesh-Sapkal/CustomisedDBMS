package app;

import model.Employee;
import utility.DBMSUtils;
import dbms.EmployeeDBMS;

import java.util.*;

public class DBMSConsoleApp
{
    public static void main(String A[])
    {
        String str = null;
        boolean exitloop = true;

        EmployeeDBMS eobj = new EmployeeDBMS();
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
                    if((tokens[1].equals("*")) && (tokens.length == 4))
                    {
                        eobj.SelectAll();
                    }
                    else
                    {
                        DBMSUtils.InvalidCommand();
                    }
                    break;

                default :
                    System.out.println("Please give valid input.");

            }   // End of switch

        }   // End of while
    }   // End of main method
} // End of main class