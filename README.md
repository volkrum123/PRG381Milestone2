# BC Student Wellness Management System – Desktop Application
### Project Milestone 2 Overview
This milestone focuses on building a Java Swing-based desktop application to manage wellness services at Belgium Campus. The application leverages Core Java, OOP principles, MVC architecture, and JavaDB (embedded database) to manage appointments, counselors, and student feedback effectively.

### ✨ Features
Dashboard with Tab/Navigation Panel for:

Appointment Management

Counselor Management

Feedback Management

CRUD Operations for:

Appointments: Book, update, cancel, view.

Counselors: Add, edit, view, remove.

Feedback: Submit, edit, view, delete.

Input Validation, Error Handling, and Confirmation Dialogs for user-friendly interaction.

### 🛠️ Technologies Used
Technology	Purpose
Java (Core)	Core language for application logic
Java Swing	GUI framework for desktop interface
JavaDB (Derby)	Embedded relational database
MVC Architecture	Design pattern for separation of concerns
Collections API	Manage in-memory appointment and feedback data
Exception Handling	Catch and manage runtime and DB issues
GitHub	Version control and team collaboration

### 🧱 System Architecture
The application follows the Model-View-Controller (MVC) pattern:

Model: Business logic and database models for appointments, counselors, and feedback.

View: Java Swing forms and panels (tabs/menus) for GUI interaction.

Controller: Handles logic between the model and the GUI, including CRUD operations and validations.

### 📦 Database Schema (JavaDB)
sql
Copy
Edit
-- Appointments Table
CREATE TABLE Appointments (
  appointment_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  student_name VARCHAR(100),
  counselor_name VARCHAR(100),
  date DATE,
  time TIME,
  status VARCHAR(20)
);

-- Counselors Table
CREATE TABLE Counselors (
  counselor_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  name VARCHAR(100),
  specialization VARCHAR(100),
  availability VARCHAR(50)
);

-- Feedback Table
CREATE TABLE Feedback (
  feedback_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  student_name VARCHAR(100),
  rating INT CHECK (rating >= 1 AND rating <= 5),
  comments VARCHAR(255)
);
### 💡 Functional Modules
#### 📅 Appointments
Book new appointments (choose counselor, date, time)

View upcoming appointments in a list or table

Reschedule or update existing appointments

Cancel/delete appointments with confirmation

#### 👩‍⚕️ Counselors
Add new counselor (name, specialization, availability)

View and update counselor information

Remove counselors from the system

#### 🗣️ Feedback
Submit feedback (rating 1–5 and comments)

View feedback history

Edit or delete existing feedback

### 🖥️ GUI Expectations
Organized interface using JTabbedPane or JMenuBar

Form validation to ensure required fields are not empty

Use of JOptionPane for confirmation and alerts

Catch and display database errors with meaningful messages

### 🔄 Application Flow
Launch dashboard with tabs or menus.

Navigate to a desired module (Appointment, Counselor, Feedback).

Perform CRUD operations via Swing forms.

Data is saved to or retrieved from JavaDB.

Display operation result or errors using popups.

### 🧪 How to Run the Application
Prerequisites
Java JDK 8+

NetBeans IDE (preferred for JavaDB integration)

JavaDB/Apache Derby enabled in NetBeans

Git installed

Setup Instructions
Clone the Repository

bash
Copy
Edit
git clone https://github.com/yourusername/bc-wellness-desktop.git
Open in NetBeans

Import the project folder.

Ensure JavaDB (Derby) is enabled and running.

Create Database

Use NetBeans services tab to create the JavaDB schema and tables.

Execute SQL scripts if provided (schema.sql).

Run the Application

Locate and run the Main.java class.

Navigate through the GUI to manage appointments, counselors, and feedback.

### 🧑‍💻 Team Collaboration
All source code is managed on GitHub for version control and backup.

Team members contribute via branches and pull requests.

Commit messages follow a clear and descriptive format for tracking progress.
