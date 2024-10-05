### Java Connectivity

This project demonstrates basic CRUD (Create, Read, Update, Delete) operations using JDBC 
(Java Database Connectivity) with a MySQL database.

## Prerequisites :
Java Development Kit (JDK) installed
MySQL Server installed
MySQL Workbench installed
MySQL JDBC Driver (Connector/J)
Setting Up MySQL Database

## Steps :
Step 1: Create a Database
Open MySQL Workbench.
Connect to your MySQL Server.
Run the following SQL command to create a new database:
CREATE DATABASE jdbc_name;

Step 2: Create a Table
Switch to the new database:
USE jdbc_name;
Create a table named Carholder to store employee information:
CREATE TABLE Carholder (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    address VARCHAR(255),
    salary INT
);

## Configuring the Java Project

Step 1: Set Up JDBC Driver

Download the MySQL JDBC Driver (Connector/J) from MySQL's official site.
Add the JDBC driver JAR file to your project's build path.

Step 2: Update Database Connection Details
In the crudOperation class, update the database connection details:

Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_name", "DB_Username", "yourDB_UsernamePassword");

Replace DB_Username with your MySQL username.

Replace yourDB_UsernamePassword with your MySQL password.

## Running the Project

Compile and run the crudOperation class.
Follow the on-screen instructions to interact with the database.

# Features
Insert Data: Allows the user to add employee records.
Update Data: Modify existing employee details.
Read Data: Retrieve and display all employee records.
Delete Data: Remove an employee record by ID.

The code uses a Scanner for user input.
JDBC is used for database operations.
Each operation (insert, read, update, delete) is encapsulated in separate methods.
