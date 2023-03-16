# Bank_Account_Management
#I am learning this from Professor Mohamed Lyoussfi. 
This Java project is for learning and practicing Spring Data JPA and the MVC architecture.

We want to create an application that manages bank accounts.
Each account is defined by a code, a balance, and a creation date.
A current account is an account that also has an overdraft.
A savings account is an account that also has an interest rate.
Each account belongs to a customer.
Each customer is defined by their code and name.
Each account can undergo several operations.
There are two types of operations: Deposit and Withdrawal. 
An operation is defined by a number, a date, and an amount.


Functional requirements
The application must allow:

Managing customers:
Adding a customer
Consult all customers
Consult customers whose name contains a keyword.
Managing accounts:
Add an account
Consult an account
Managing operations:
Perform a deposit of an amount in an account
Perform a withdrawal of an amount in an account
Perform a transfer of an amount from one account to another
Consult the operations of an account page by page
Operations require an authentication operation.



Technical requirements

The data is stored in a MySQL database
The application consists of three layers:

The DAO layer which is based on Spring Data, JPA, Hibernate, and JDBC.
The Business layer
The Web layer based on MVC on the server side using Thymeleaf.
Security is based on Spring Security
