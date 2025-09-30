# CustomisedDBMS
A Customised DBMS implemented in Java with serialization support.

# Customised Employee Database Management System (DBMS)

A simple console-based Employee Database Management System built in Java.  
The project demonstrates the basic design of a DBMS-like application using *object-oriented principles* and a clean *modular structure*.

---

## 📂 Project Structur
```bash
src/ 
└── com/customiseddbms/ 
            ├── model/Employee.java          # Employee entity class 
            ├── dbms/EmployeeDBMS.java       # Core DBMS logic (CRUD operations) 
            ├── utility/DBMSUtils.java       # Utility methods (helper functions) 
            └── app/DBMSConsoleApp.java      # Entry point (console interface)
```
---

## 🚀 Features (Initial Version)

- Add employee records  
- Display all employee records  
- Console-driven command interface
- Modular package design (model, dbms, utility, app)

---

## 🖥 Tech Stack

- Java (OOP-based design)  
- Console-based user interface  

---

## 🛠 How to Run

1. Navigate to the project folder:
```bash
cd src
```
2. Compile the project:
```bash
javac com/customiseddbms/app/DBMSConsoleApp.java 
      com/customiseddbms/dbms/EmployeeDBMS.java 
      com/customiseddbms/utility/DBMSUtils.java 
      com/customiseddbms/model/Employee.java
```
3. Run the application:
```bash
java com/customiseddbms/app/DBMSConsoleApp
```
---

## 📌 Future Enhancements

- Implement serialization for persistent storage
- Add search, update, and delete operations
- Extend parser for SQL-like commands

---

## 💻 Sample Usage
```bash
----------------------------------------------------------------------
---------- Customised DBMS Application started succesfully. ----------
----------------------------------------------------------------------
--------------- Welcome to Customised DBMS Application ---------------
----------------------------------------------------------------------
Employee DB > insert into employee values Mohan 25 Pune 50000
Employee DB > select * from employee
+----+-------+-----+----------+--------+
| ID | Name  | Age | Address  | Salary |
+----+-------+-----+----------+--------+
| 1  | John  | 25  | New York | 50000  |
+----+-------+-----+----------+--------+
Employee DB > exit employee
----------------------------------------------------------------------
---------- Thank You for using Customised DBMS Application -----------
----------------------------------------------------------------------
```
---

## 📖 License

This project is licensed under the MIT License – see the LICENSE file for details.
