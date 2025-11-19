# Customised DBMS Application

A command-line interface (CLI) based database management system designed for managing employee records.   
This application allows users to perform CRUD operations, apply conditional queries, take backups, restore database state, and interact through a simple text-based parser-driven interface.
---

## Overview

CustomisedDBMS allows users to manage Employee records with the ability to:  
- Insert new employee records  
- Display all or specific fields
- Execute conditional queries using operators (=, !=, <, >, <=, >=)
- Use aggregate functions (min, max, sum, avg, count)
- Update all or specific records
- Delete all or specific records
- Drop the Employee table
- Take database backup
- Restore the database from backup
- View database schema and list of all supported DBMS commands.

The application uses a parser-like console interface for efficient command-based operations.

---

## 📂 Project Structure
```bash
src/ 
└── com/customiseddbms/ 
|            ├── model/Employee.java          # Employee entity class 
|            ├── dbms/EmployeeDBMS.java       # Core DBMS logic (CRUD operations + backup/restore) 
|            ├── utility/DBMSUtils.java       # Utility/helper methods 
|            └── app/DBMSConsoleApp.java      # Entry point (console interface)
|
└── data/backups/
             └── employeeDB.ser               # Default backup file
```

---

## 🚀 Features

- **Insert Records:**
  - Add new employee records

- **Select Records:**
  - Retrieve fields or full records
- **Conditional Select:**
  - WHERE clause with relational operators

- **Aggregate Functions:**
  - min, max (on age/salary)
  - sum, avg (on salary)
  - count (on any field)

- **Update Records:**
  - Update all records
  - Update specific records with WHERE clause

- **Delete Records:**
  - Delete all records
  - Delete specific records using WHERE clause

- **Drop Table:**
  - Completely remove Employee table and reset EmpId sequence

- **Backup & Restore:**
  - Save database state
  - Auto-restore on next run

- **Table Schema View:**
  - Display structure of Employee table

- **Enhanced Console Output:**
  - Dynamic column width for cleaner tabular display

- **Centralized Validation:**
  - All commands, fields, values, and operators validated in DBMSUtils

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
java com/customiseddbms/app/DBMSConsoleApp [backupFileName]
```
Default backup file: data/backups/employeeDB if no filename is provided.

---

## Usage & Commands

Describe Commands:
```bash
help
```

Describe Employee Database Schema:
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
Select Specific Field(s):
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
Select with Aggregate Function:
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

Update All Records:
```bash
update Employee set empSalary = 50000
```
Update Specific Records:
```bash
update Employee set empAddress = Mumbai where empAge > 30
```

Delete All Records:
```bash
delete from Employee
```
Delete Specific Records:
```bash
delete from Employee where empName != Radha
```

Drop Table:
```bash
drop table Employee
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

## 📖 License

This project is licensed under the MIT License – see the [LICENSE](LICENSE) file for details.
