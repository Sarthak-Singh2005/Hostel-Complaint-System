# 🏨 Hostel Complaint Management System

## 📌 Project Description

The **Hostel Complaint Management System** is a Java Swing-based desktop application that allows students to submit complaints related to hostel facilities and enables administrators to manage and resolve them efficiently.

This system provides a simple interface for handling complaints like water issues, electricity problems, cleanliness, internet issues, etc.

---

## ✨ Features

### 👨‍🎓 Student

* Register and login
* Submit complaints
* View complaint status (Pending, In Progress, Resolved)

### 👨‍💼 Admin

* Login as admin
* View all complaints
* Update complaint status
* Manage student complaints efficiently

---

## 🛠️ Technologies Used

* Java (Swing for GUI)
* MySQL (Database)
* JDBC (Database Connectivity)

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

### 4️⃣ Run the Project

```bash
javac src/*.java
java -cp src Main
```

---

## 🔐 Admin Access

Admin accounts are not created through the registration page.

By default, all users who register are assigned the role of **student**.

To access the admin dashboard, you must manually create an admin account in the database.

### ➤ Create Admin User

```sql
INSERT INTO users(name, email, password, role)
VALUES ('Admin', 'admin@gmail.com', 'admin123', 'admin');
```

### ➤ Admin Login Credentials

* Email: [admin@gmail.com](mailto:admin@gmail.com)
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

## ⚠️ Limitations

* No email/SMS notifications
* Passwords are stored in plain text
* Admin must be created manually
* Desktop-based application

---

## 🚀 Future Improvements

* Add email notifications
* Implement password encryption
* Improve UI design
* Convert to web application

---

## 👨‍💻 Author

Sarthak Singh

---

## 📄 License

This project is for educational purposes only.
