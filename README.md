# Java Coursework & Projects

## Overview
This repository contains a series of Java applications and exercises developed as part of university coursework. It ranges from fundamental Object-Oriented Programming (OOP) assignments to more advanced architectural patterns and data management techniques. 

## Repository Structure

### 🏗️ Core Projects
* **`/LibraryManagement`**
  A console-based application for managing library operations. Includes entity models for `Book` and `Author`, utilizing the `text-table-formatter` library for clean CLI data presentation.
* **`/assignments/HotelManagement`**
  Implementation of a hotel booking and room management system.
* **`/assigments/StudentManagent`**
  A student record management system focusing on OOP principles and encapsulation.

### 🏛️ Architecture & Database
* **`/MVCTest`**
  A complete Employee Management system demonstrating the Model-View-Controller (MVC) design pattern.
  * **Model:** `Employee`, `Connect` (Database Connection), and Data Access Object (DAO) implementation (`EmployeeDAOImpl`).
  * **View:** `EmployeeView` for user interaction.
  * **Controller:** `EmployeeController` routing data between the view and the database. Includes data `Validation` logic.

### 💾 File I/O & Serialization
* **`/testing/test/src`**
  A sandbox directory containing implementations of various Java Input/Output streams and data handling:
  * Text reading/writing (`ReadText.java`, `WriteText.java`)
  * Binary file manipulation (`ReadBinary.java`, `WriteBinary.java`)
  * Object Serialization & Deserialization (`SerializeExample.java`, `DeserializeExample.java`)
  * Random Access Files (`RAFExample.java`)

## Tech Stack & Libraries
* **Language:** Java (JDK 17+)
* **Database:** PostgreSQL (JDBC)
* **Libraries:** `text-table-formatter-1.1.2` (CLI table formatting)
* **IDE:** IntelliJ IDEA

## Author
**Srun Sochettra**
* Information Technology track at National University of Management (NUM)
* GitHub: [SRUN-Sochettra](https://github.com/SRUN-Sochettra)
