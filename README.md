# Customised DBMS Application

A command-line interface (CLI) based database management system designed for managing employee records.   
This application allows users to perform CRUD operations, backup and restore data and interact with the database through a simple text-based interface.

---

## Overview

CustomisedDBMS allows users to manage Employee records with the ability to:  
- Insert new employee records  
- Display all or specific fields and records using SELECT and where queries  
- Backup current database state to a file
- Restore database from a previous backup    

It is fully console-driven, using a parser-like command interface.

---

## 📂 Project Structure
```bash
src/ 
└── com/customiseddbms/ 
|            ├── model/Employee.java          # Employee entity class 
|            ├── dbms/EmployeeDBMS.java       # Core DBMS logic (CRUD operations) 
|            ├── utility/DBMSUtils.java       # Utility methods (helper functions) 
|            └── app/DBMSConsoleApp.java      # Entry point (console interface)
|
└── data/backups/
             └── employeeDB.ser               # Backup file location
```

---

## 🚀 Features

- **Insert Records :** Add new employee records to the database.
- **Select Records :** Retrieve employee information based on specified fields.
- **Select with Conditions :** Retrieve specific employee records using WHERE clause with operators like =, !=, <, >, <=, >=.
- **Enhanced Console Output :** Well-formatted table view with dynamic column widths for better readability.
- **Centralized Validation :** Commands, fields, and aggregate functions are now validated through a unified utility class (DBMSUtils).
- **Backup & Restore :** Save and load employee data to/from a file.
- **Schema Description :** View the structure of the employee table.

---

## 🛠 How to Run

1. Navigate to the project folder:
```bash
cd CustomisedDBMS\src
```
2. Compile the project:
```bash
javac com/customiseddbms/app/DBMSConsoleApp.java
```
3. Run the application:
```bash
java com/customiseddbms/app/DBMSConsoleApp [backupFileName.ser]
```
Default backup file: data/backups/employeeDB.ser if no filename is provided.

---

## Usage & Commands

Describe Schema:
```bash
describe Employee
```

Insert Employee:
```bash
insert into Employee Values <Name> <Age> <Address> <Salary>
```

Select All Fields:
```bash
select * from Employee
```
Select Specific Field(up to 4 fields):
```bash
select empId|empName|empAge|empAddress|empSalary from Employee
```
```bash
select empId empName from Employee
```
```bash
select empId empName empAge from Employee
```
```bash
select empId empName empAddress empSalary from Employee
```
Select with aggregate function:
```bash
select min|max empAge|empSalary from Employee
```
```bash
select sum|avg empSalary from Employee
```
```bash
select count empId|empName|empAge|empAddress|empSalary from Employee
```
Select with WHERE clause:
```bash
select * from Employee where empId > 3
```
```bash
select empName empSalary from Employee where empSalary <= 40000
```
```bash
select empId from Employee where empName = John
```
```bash
select empSalary empName empAddress from Employee where empAddress = Pune
```
```bash
select empName empAddress empSalary empAge empId from Employee where empAge != 21
```
Backup Database:
```bash
takebackup Employee
```

Exit Application:
```bash
exit
```
```bash
exit Employee
```

---

## 📌 Future Enhancements

- Add update and delete operations

---

## 📖 License

This project is licensed under the MIT License – see the [LICENSE](LICENSE) file for details.
