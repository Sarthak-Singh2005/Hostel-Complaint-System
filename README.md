# 🏨 Hostel Complaint Management System

A Java-based desktop application that digitizes hostel complaint management, improving communication and efficiency between students and administrators.

---

## 📌 Project Description

The **Hostel Complaint Management System** is built using **Java Swing** and **MySQL**, allowing students to submit complaints related to hostel facilities while enabling administrators to manage and resolve them through a centralized system.

This application simplifies handling issues such as:

* Water supply problems
* Electricity issues
* Cleanliness concerns
* Internet connectivity issues

---

## 🎯 Objective

To streamline hostel issue reporting and create a transparent system for tracking and resolving complaints efficiently.

---

## ✨ Features

### 👨‍🎓 Student

* Register and login
* Submit complaints
* View complaint status (*Pending, In Progress, Resolved*)

### 👨‍💼 Admin

* Secure login access
* View all complaints
* Update complaint status
* Efficiently manage student complaints

---

## 🛠️ Technologies Used

* **Java** (Swing for GUI)
* **MySQL** (Database)
* **JDBC** (Database Connectivity)

---

## 📦 Dependencies

The project uses a MySQL JDBC driver to connect Java with the database.

* **MySQL Connector JAR**: `mysql-connector-j-9.6.0.jar`
* Located in the `lib/` folder

### 🔗 Purpose

This library enables communication between the Java application and the MySQL database.

---

## ⚙️ Prerequisites

Make sure you have the following installed:

* Java JDK 8 or above
* MySQL Server
* Any Java IDE (IntelliJ IDEA / Eclipse / VS Code)

---

## 📁 Project Structure

```
Hostel-Complaint-System/
│
├── src/
│   ├── Main.java
│   ├── LoginPage.java
│   ├── RegisterPage.java
│   ├── AdminDashboard.java
│   ├── StudentDashboard.java
│   ├── Complaint.java
│   ├── User.java
│   ├── ComplaintService.java
│   ├── UserService.java
│   ├── DBConnection.java
│   └── Session.java
│
├── lib/
│   └── mysql-connector-j-9.6.0.jar
│
└── README.md
```

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/Sarthak-Singh2005/Hostel-Complaint-System.git
cd Hostel-Complaint-System
```

---

### 2️⃣ Setup MySQL Database

Run the following SQL commands:

```sql
CREATE DATABASE hostel;
USE hostel;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100),
    role VARCHAR(20)
);

CREATE TABLE complaints (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    category VARCHAR(100),
    description TEXT,
    status VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
```

---

### 3️⃣ Configure Database Connection

Open `DBConnection.java` and update:

```java
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

---

### 4️⃣ Compile and Run the Project

#### ▶️ For Windows

```bash
javac -cp "lib/mysql-connector-j-9.6.0.jar;src" src/*.java
java -cp "lib/mysql-connector-j-9.6.0.jar;src" Main
```

#### ▶️ For Mac/Linux

```bash
javac -cp "lib/mysql-connector-j-9.6.0.jar:src" src/*.java
java -cp "lib/mysql-connector-j-9.6.0.jar:src" Main
```

---

## 🔐 Admin Access

Admin accounts are managed at the database level to ensure controlled access.

### ➤ Create Admin User

```sql
INSERT INTO users(name, email, password, role)
VALUES ('Admin', 'admin@gmail.com', 'admin123', 'admin');
```

### ➤ Admin Login Credentials

* Email: [admin@gmail.com]
* Password: admin123

---

## 🔄 How the System Works

1. Student registers and logs in
2. Student submits a complaint
3. Complaint is stored in the database
4. Admin logs in
5. Admin views all complaints
6. Admin updates complaint status

---



## 👨‍💻 Author

**Sarthak Singh**
GitHub: https://github.com/Sarthak-Singh2005

---

## 📄 License

This project is intended for educational and learning purposes.

---
