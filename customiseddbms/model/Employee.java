package com.customiseddbms.model;

import java.io.*;

///////////////////////////////////////////////////////////////////////////////////////////
//
//	Class Name			:	Employee
//	Description			:	This class represents an employee entity and implements the
//                          Serializable interface.
//
///////////////////////////////////////////////////////////////////////////////////////////
public class Employee implements Serializable
{
    public int EmpID;
    public String EmpName;
    public int EmpAge;
    public String EmpAddress;
    public int EmpSalary;

    public static int iCounter;

    static
    {
        iCounter = 1;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	Employee
    //	Description             :   This is parameterized constructor of class Employee
    //                              which is used to initializ characteristics.
    //	Parameters				:   String(name), int(age), String(address), int(salary)
    //	Returns					:   NONE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////
    public Employee(
                        String name,        //  Employee name
                        int age,            //  Employee age
                        String address,     //  Employee address
                        int salary          //  Employee salary
                    )
    {
        this.EmpID = iCounter++;
        this.EmpName = name;
        this.EmpAge = age;
        this.EmpAddress = address;
        this.EmpSalary = salary;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	toString
    //	Description             :   This is the toString method that overrides from the Object
    //                              class used to display characteristics in string format.
    //	Parameters				:   NONE
    //	Returns					:   String
    //
    ///////////////////////////////////////////////////////////////////////////////////////////
    public String toString()
    {
        return "ID : "+this.EmpID+" Name : "+this.EmpName+" Age : "+this.EmpAge+" Address : "+this.EmpAddress+" Salary : "+this.EmpSalary;
    }
}