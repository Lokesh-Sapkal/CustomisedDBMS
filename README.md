# Customised DBMS Application

A command-line interface (CLI) based database management system designed for managing employee records.   
This application allows users to perform CRUD operations, apply conditional queries, take backups, restore database state, and interact through a simple text-based interface.
---

## Overview

CustomisedDBMS allows users to manage Employee records with the ability to:  
- Insert new employee records  
- Display all or specific fields
- Execute conditional queries using operators (=, !=, <, >, <=, >=)
- Perform aggregate functions (min, max, sum, avg, count)
- Update all or specific records
- Backup current DB state
- Restore data from previous backups
- View database schema and list of all supported DBMS commands.

The application uses a parser-like console interface for fast command-based operations.

---

## 📂 Project Structure
```bash
src/ 
└── com/customiseddbms/ 
|            ├── model/Employee.java          # Employee entity class 
|            ├── dbms/EmployeeDBMS.java       # Core DBMS logic (CRUD operations + backup/restore) 
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
- **Aggregate Functions :** min, max, sum, avg, count supported on numeric fields.
- **Update Records :** Update all employee records
- **Update with Conditions :** Update only specific records matching a WHERE condition
- **Enhanced Console Output :** Well-formatted table view with dynamic column widths for better readability.
- **Centralized Validation :** All fields, operators, commands, and values are validated in one place (DBMSUtils).
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

Update all records:
```bash
update Employee set empSalary = 50000
```
Update with condition:
```bash
update Employee set empAddress = Mumbai where empAge > 30
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

- Add delete operations

---

## 📖 License

This project is licensed under the MIT License – see the [LICENSE](LICENSE) file for details.
