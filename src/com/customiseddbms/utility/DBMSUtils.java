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
            switch(field.toLowerCase())
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

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	validateValue
    //	Description             :   This method validates the given input value based on the field.
    //	Parameters				:   String[](field[]), String[](value[])
    //	Returns					:   boolean
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static boolean validateValue(
                                            String field[],       // field name
                                            String value[]        // Accepted value
                                        )
    {
        boolean bAns = true;

        for(int i = 0;i < field.length;i++)
        {
            switch(field[i].toLowerCase())
            {
                case "empid","empage","empsalary":
                    bAns = value[i].matches("\\d+");
                    break;
                case "empname","empaddress":
                    bAns = value[i].matches("[a-zA-Z._-]+");
                    break;
            }
            if(!bAns)
            {
                System.out.println("Error: Invalid value provided for field.");
                System.out.println("Accepted formats:");
                System.out.println("- EmpId, EmpAge, EmpSalary   : Only positive integers (digits 0-9)");
                System.out.println("- EmpName, EmpAddress : Only alphabetic characters, dots(.), underscores(_), and hyphens(-)");
                System.out.println("Please check your input and try again.\n");
                break;
            }
        }
        return bAns;
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
                if((fName.equalsIgnoreCase("empage")) || (fName.equalsIgnoreCase("empsalary")))
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
                if(fName.equalsIgnoreCase("empsalary"))
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
    //	Description             :   This method checks whether the operator is valid or not.
    //	Parameters				:   String(fName), String(operator)
    //	Returns					:   boolean
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////
    public static boolean checkRelationalOperator(
                                                    String fName,           // Field name
                                                    String operator         // Define operator
                                                 )
    {
        boolean isValid = false;

        switch(fName.toLowerCase())
        {
            case "empid","empage","empsalary":
                switch(operator)
                {
                    case "=","!=",">","<",">=","<=":
                        isValid = true;
                        break;
                }
                break;

            case "empname","empaddress":
                switch(operator)
                {
                    case "=","!=":
                        isValid = true;
                        break;
                }
                break;
        }

        return isValid;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	extractField
    //	Description             :   This method extracts field value.
    //	Parameters				:   String(fName), Employee(eref)
    //	Returns					:   Object
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public static Object extractField(
                                        String fName,       // Field name
                                        Employee eref       // object of Employee class
                                    )
    {
        switch(fName.toLowerCase())
        {
            case "empid":
                return eref.getEmpId();
            case "empname":
                return eref.getEmpName();
            case "empage":
                return eref.getEmpAge();
            case "empaddress":
                return eref.getEmpAddress();
            case "empsalary":
                return eref.getEmpSalary();
            default:
                System.out.println("Invalid field access attempted : "+fName);
                return null;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	evaluateCondition
    //	Description             :   This method evaluates a relational condition.
    //	Parameters				:   String(fName), Object(fieldValue), String(operator), Object(value)
    //	Returns					:   boolean
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public static boolean evaluateCondition(
                                                String fName,           // Field name
                                                Object fieldValue,      // Value of field
                                                String operator,        // Define operator
                                                Object value            // Accepted value
                                            ) 
    {
        switch(fName.toLowerCase())
        {
            case "empid","empage","empsalary":
                int extractedValue = Integer.parseInt(fieldValue.toString());
                int acceptedValue = Integer.parseInt(value.toString());
                switch (operator) 
                {
                    case "=":
                        return extractedValue == acceptedValue;
                    case "!=": 
                        return extractedValue != acceptedValue;
                    case ">":  
                        return extractedValue > acceptedValue;
                    case "<":  
                        return extractedValue < acceptedValue;
                    case ">=": 
                        return extractedValue >= acceptedValue;
                    case "<=": 
                        return extractedValue <= acceptedValue;
                }
                break;
                
            case "empname","empaddress":
                String extValue = fieldValue.toString();
                String accValue = value.toString();
                switch (operator) 
                {
                    case "=":
                        return extValue.equalsIgnoreCase(accValue);
                    case "!=": 
                        return !(extValue.equalsIgnoreCase(accValue));
                }
                break;
        }
        return false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	updateRecord
    //	Description             :   This method update employee records.
    //	Parameters				:   Employee(eref), String[](fields[]), String[](values[])
    //	Returns					:   NONE
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void updateRecord(
                                        Employee eref,
                                        String fields[],
                                        String values[]                                
                                    )
    {
        int i = 0;

        for(i = 0;i < fields.length;i++)
        {
            switch(fields[i])
            {
                case "empname":
                    eref.setEmpName(values[i].toString());
                    System.out.printf("Updated EmpName -> %s%n",values[i]);
                    break;
                case "empage":
                    eref.setEmpAge(Integer.parseInt(values[i].toString()));
                    System.out.printf("Updated EmpAge -> %s%n",values[i]);
                    break;
                case "empaddress":
                    eref.setEmpAddress(values[i].toString());
                    System.out.printf("Updated EmpAddress -> %s%n",values[i]);
                    break;
                case "empsalary":
                    eref.setEmpSalary(Integer.parseInt(values[i].toString()));
                    System.out.printf("Updated EmpSalary -> %s%n",values[i]);
                    break;
            }
        }
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
        System.out.println("+---------------+---------------+");
        System.out.println("| Schema\t| Datatype\t|");
        System.out.println("+---------------+---------------+");
        System.out.println("| empId \t| INT   \t|");
        System.out.println("| empName\t| STRING\t|");
        System.out.println("| empAge\t| INT   \t|");
        System.out.println("| empAddress\t| STRING\t|");
        System.out.println("| empSalary\t| INT   \t|");
        System.out.println("+---------------+---------------+");
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
        System.out.println(createBorder(widths));
        System.out.print("|");
        for (int i = 0; i < headers.length; i++) 
        {
            System.out.printf(" %-" + widths[i] + "s |", headers[i]);
        }
        System.out.println();
        System.out.println(createBorder(widths));
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

        System.out.println(createBorder(widths));
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

        System.out.println(createBorder(new int[] {width}));
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	createBorder
    //	Description             :   This method creates employee table border.
    //	Parameters				:   int[](width[])
    //	Returns					:   StringBuilder(border)
    //
    ///////////////////////////////////////////////////////////////////////////////////////////
    public static StringBuilder createBorder(
                                                int widths[]       // Array of width of record                                 
                                            )
    {
        StringBuilder border = new StringBuilder("+");
        for (int width : widths) 
        {
            border.append("-".repeat(width + 2)).append("+");
        }

        return border;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	displayHelp
    //	Description             :   This method displays the list of all supported DBMS commands.
    //	Parameters				:   NONE
    //	Returns					:   NONE
    //
    /////////////////////////////////////////////////////////////////////////////////////////////
    public static void displayHelp()
    {
        String line = "-".repeat(80);
        System.out.println(line);
        System.out.println("                         Customised DBMS - HELP");
        System.out.println(line);

        System.out.println("\n1) Describe schema");
        System.out.println("   ----------------");
        System.out.println("   describe Employee\n");

        System.out.println("2) Insert record");
        System.out.println("   -------------");
        System.out.println("   insert into Employee values <EmpName> <EmpAge> <EmpAddress> <EmpSalary>");
        System.out.println("   (EmpId is auto-incremented and unique)\n");

        System.out.println("3) Select records");
        System.out.println("   --------------");
        System.out.println("   - All fields:");
        System.out.println("       select * from Employee\n");
        System.out.println("   - Specific fields:");
        System.out.println("       select empname empsalary from Employee\n");
        System.out.println("   - With WHERE clause:");
        System.out.println("       select * from Employee where <field> <operator> <value>");
        System.out.println("       select empname empsalary from Employee where empsalary >= 40000");
        System.out.println("     Supported operators:");
        System.out.println("       For numeric fields (empid, empage, empsalary): =, !=, <, >, <=, >=");
        System.out.println("       For string fields (empname, empaddress): =, !=\n");

        System.out.println("4) Aggregate functions");
        System.out.println("   -------------------");
        System.out.println("   select max empsalary from Employee");
        System.out.println("   select min empage from Employee");
        System.out.println("   select sum empsalary from Employee");
        System.out.println("   select avg empsalary from Employee");
        System.out.println("   select count empid from Employee\n");

        System.out.println("5) Update records");
        System.out.println("   --------------");
        System.out.println("   - Update all records:");
        System.out.println("       update Employee set empaddress = Mumbai\n");
        System.out.println("   - Update with WHERE clause:");
        System.out.println("       update Employee set empsalary = 70000 where empname = Radha");
        System.out.println("   Note: empid cannot be updated.\n");

        System.out.println("6) Delete records (not implemented)");
        System.out.println("   -------------------------------");
        System.out.println("   (Planned: delete from Employee where <field> <operator> <value>)\n");

        System.out.println("7) Backup / Restore");
        System.out.println("   -----------------");
        System.out.println("   takebackup Employee    (creates backup file: data/backups/<name>.ser)");
        System.out.println("   (Restore is automatic at program start if the .ser file exists)\n");

        System.out.println("8) Exit");
        System.out.println("   ----");
        System.out.println("   exit");
        System.out.println("   exit Employee\n");

        System.out.println("9) Help");
        System.out.println("   ----");
        System.out.println("   help\n");

        System.out.println(line);
    }
 
    ///////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	invalidCommand
    //	Description             :   Displays error message and points user to help.
    //	Parameters				:   NONE
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////
    public static void invalidCommand()
    {
        System.out.println("Command not found.");
        System.out.println("Type 'help' to see the list of available commands.");
        System.out.println("Common commands: describe, insert, select, update, takebackup, exit, help\n");
    }
}