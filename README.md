# CustomisedDBMS

A Java-based console application simulating a simple Database Management System (DBMS) using LinkedList as the storage.  
Supports employee record management,  Backup & Restore, and selective SELECT queries.

---

## Overview

CustomisedDBMS allows users to manage Employee records with the ability to:  
- Insert new employee records  
- Display all or specific fields using SELECT queries  
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

- Insert new employee records  
- SELECT * or SELECT <field> queries (empid, empname, empage, empaddress, empsalary)  
- Backup database to file  
- Restore database from backup  
- Describe schema command (describe Employee)  
- Console-based table output with field validation  

---

## 🛠 How to Run

1. Navigate to the project folder:
```bash
cd src
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

Select Specific Field:
```bash
select <empid|empname|empage|empaddress|empsalary> from Employee
```

Backup Database:
```bash
takebackup Employee
```

Exit Application:
```bash
exit Employee
```

---

## 📌 Future Enhancements

- Add update and delete operations
- Extend parser for SQL-like commands

---

## 📖 License

This project is licensed under the MIT License – see the LICENSE file for details.
