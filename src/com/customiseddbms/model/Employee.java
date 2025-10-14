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
    private static final long serialVersionUID = 1L;    // version control ID

    private int empId;
    private String empName;
    private int empAge;
    private String empAddress;
    private int empSalary;

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
        this.empId = iCounter++;
        this.empName = name;
        this.empAge = age;
        this.empAddress = address;
        this.empSalary = salary;
    }

    /////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	getEmpId
    //	Description             :   This method returns the employee id.
    //	Parameters				:   NONE
    //	Returns					:   int(empId)
    //
    /////////////////////////////////////////////////////////////////////////////
    public int getEmpId()
    {
        return empId;
    }

    /////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	setEmpName
    //	Description             :   This method sets the employee name.
    //	Parameters				:   String(name)
    //	Returns					:   NONE
    //
    /////////////////////////////////////////////////////////////////////////////
    public void setEmpName(String name) 
    {
        this.empName = name;
    }

    /////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	getEmpName
    //	Description             :   This method returns the employee name.
    //	Parameters				:   NONE
    //	Returns					:   String(empName)
    //
    /////////////////////////////////////////////////////////////////////////////
    public String getEmpName() 
    {
        return empName;
    }

    /////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	setEmpAge
    //	Description             :   This method sets the employee age.
    //	Parameters				:   int(age)
    //	Returns					:   NONE
    //
    /////////////////////////////////////////////////////////////////////////////
    public void setEmpAge(int age) 
    {
        this.empAge = age;
    }

    /////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	getEmpAge
    //	Description             :   This method returns the employee age.
    //	Parameters				:   NONE
    //	Returns					:   int(empAge)
    //
    /////////////////////////////////////////////////////////////////////////////
    public int getEmpAge() 
    {
        return empAge;
    }

    /////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	setEmpAddress
    //	Description             :   This method sets the employee address.
    //	Parameters				:   String(address)
    //	Returns					:   NONE
    //
    /////////////////////////////////////////////////////////////////////////////
    public void setEmpAddress(String address) 
    {
        this.empAddress = address;
    }

    /////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	getEmpAddress
    //	Description             :   This method returns the employee address.
    //	Parameters				:   NONE
    //	Returns					:   String(empAddress)
    //
    /////////////////////////////////////////////////////////////////////////////
    public String getEmpAddress() 
    {
        return empAddress;
    }

    /////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	setEmpSalary
    //	Description             :   This method sets the employee salary.
    //	Parameters				:   int(salary)
    //	Returns					:   NONE
    //
    /////////////////////////////////////////////////////////////////////////////
    public void setEmpSalary(int salary) 
    {
        this.empSalary = salary;
    }

    /////////////////////////////////////////////////////////////////////////////
    //
    //	Method Name			    :	getEmpSalary
    //	Description             :   This method returns the employee salary.
    //	Parameters				:   NONE
    //	Returns					:   int(empSalary)
    //
    /////////////////////////////////////////////////////////////////////////////
    public int getEmpSalary() 
    {
        return empSalary;
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
        return "ID : "+this.empId+" Name : "+this.empName+" Age : "+this.empAge+" Address : "+this.empAddress+" Salary : "+this.empSalary;
    }
}