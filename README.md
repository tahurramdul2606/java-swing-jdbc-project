# 📚 Library Management System (Java Swing + MySQL)

A desktop-based Library Management System built using **Java Swing (GUI)** and **MySQL database**.  
The system supports **Admin and User roles**, allowing book management, issuing, returns, and fine handling.

---

## 🚀 Features

### 👨‍🎓 User Features
- Secure Login
- View personal dashboard
- View issued books
- View fines
- Pay fine using UPI QR simulation
- Logout system

### 🧑‍💼 Admin Features
- Add new books
- Manage books
- Search books
- Issue books to users
- View issued books
- Fine management system
- View all students data
- Edit student records
- Logout system

---

## 🏗️ Project Structure
ui/
├── LoginPage.java
├── RegistrationPage.java
├── UserDashboard.java
├── AdminDashboard.java
├── AddBookPanel.java
├── IssueBookPanel.java

database/
├── DatabaseUtil.java


---

## 🧾 Database Tables

### 👤 users
```sql
id (PK)
name
email
username
password
role
phone
department
enrollment_no
fine
books_taken
books_due
```

### 📚 books
```
book_id (PK)
title
author
category
quantity
available_quantity
shelf_number
```
---
### 📖 issued_books
```
issue_id (PK)
user_id (FK)
book_id (FK)
issue_date
return_date
status
```
---
### ⚙️ Technologies Used
```
Java (JDK 8+)
Java Swing (GUI)
JDBC
MySQL Database
```
---

### 🔌 Database Connection

Update your database credentials in:
```
DatabaseUtil.java
```
Example:
```
Connection con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/library_db",
    "root",
    "password"
);
```
---
### ▶️ How to Run
```
1. Install Java JDK (8 or above)
2. Install MySQL and create database
3. Import required tables
4. Configure DatabaseUtil.java
5. Run LoginPage.java
```
---
### 📸 Screens Overview
```
- Login Page (User/Admin)
- Registration Page
- User Dashboard
- Admin Dashboard
- Add Book Panel
- Issue Book Panel
```
---
### 📌 Future Improvements
```
- Password encryption (BCrypt)
- Book return module
- Fine auto-calculation system
- Barcode/QR based book issue
- Advanced search filters
- Email notifications
```
---
