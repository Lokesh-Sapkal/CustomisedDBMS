# CustomisedDBMS
A Customised DBMS implemented in Java with serialization support.

# Customised Employee Database Management System (DBMS)

A simple console-based Employee Database Management System built in Java.  
The project demonstrates the basic design of a DBMS-like application using *object-oriented principles* and a clean *modular structure*.

## 📂 Project Structur

src/ 
└── com/customiseddbms/ 
            ├── model/Employee.java          # Employee entity class 
            ├── dbms/EmployeeDBMS.java       # Core DBMS logic (CRUD operations) 
            ├── utility/DBMSUtils.java       # Utility methods (helper functions) 
            └── app/DBMSConsoleApp.java      # Entry point (console interface)

## 🚀 Features (Initial Version)

- Add employee records  
- Display all employee records  
- Simple console menu with switch-case navigation  
- Modular design (separated packages for model, dbms, utility, app)

## 🛠 How to Run

1. Navigate to the project folder:

cd sre/com/customiseddbms

2. Compile the project:

javac dbms/*.java app/*.java model/*.java utility/*.java

3. Run the application:

java -cp . app.DBMSConsoleApp

## 📌 Future Enhancements

- Implement serialization for persistent storage

- Add search, update, and delete operations

- Build a parser-based interface for SQL-like commands

## 📖 License

This project is for learning purposes and open for extension.
You can fork and enhance it with new features.