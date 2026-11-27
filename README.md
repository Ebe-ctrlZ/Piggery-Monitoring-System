# 🐷 Piggery Pro – Farm & Feeds Management System

A modern Java desktop application designed to help hog raisers manage pig batches, monitor feeds inventory, track farm expenses, and generate farm reports.

Built using **Java Swing**, Piggery Pro features a graphical user interface with a green-themed design, interactive dashboard, inventory tables, search and filtering tools, and animated interface components.

---

## 📌 Project Overview

**Piggery Pro – Farm & Feeds Management System** is a desktop-based inventory management application developed using Java.

The application provides tools for organizing pig batches, recording feeds inventory, monitoring farm expenses, and viewing important farm information through a centralized dashboard.

It also includes account registration, user authentication, activity history, batch computation, and CSV report export.

The system demonstrates the practical application of Object-Oriented Programming (OOP), Java Swing, exception handling, and Java Collections.

---

## ✨ Features

### 🔐 1. User Authentication
- User registration and login.
- Username validation to prevent duplicate accounts.
- Password validation.
- Account roles and administrator privileges.
- Account-specific inventory management.

### 📊 2. Dashboard
- Displays important farm statistics.
- Shows total pig batches and pig population.
- Displays feeds inventory quantities and expenses.
- Provides visual summaries of farm information.
- Includes animated dashboard cards and visual indicators.

### 🐖 3. Pig Batch Management
- Add new pig batches.
- Record batch name, pig count, price per pig, and financer.
- Automatically calculate total pig costs.
- Assign batch statuses, such as Active, Growing, Finishing, and Completed.
- Search and filter batch records.
- Edit existing batch information.
- Delete selected batches.

### 🌾 4. Feeds Inventory Management
- Add feeds to an existing pig batch.
- Record feed name, variety, price, quantity, and date.
- Choose from standard feed varieties:
  - Pre-Starter
  - Starter
  - Grower
  - Finisher
  - Breeder
  - Custom variety
- Search and filter feeds records.
- View feeds according to selected batches.
- Automatically calculate feed subtotals.

### ⚠️ 5. Low-Stock Monitoring
- Monitor feeds inventory quantities.
- Identify feeds with low stock.
- Display alerts when recorded stock reaches the low-stock threshold of 5 units or below.

### 🧮 6. Batch Computation
- Calculate total pig expenses.
- Calculate total feeds expenses.
- Display feeds usage by variety.
- Compute total batch expenses.
- Calculate feed cost per pig.
- Calculate overall cost per pig.

### 💰 7. Expenses Management
- Display pig stock expenses.
- Display feeds inventory expenses.
- Calculate combined farm expenses.
- Show average cost per pig.
- Search expense records.

### 📑 8. Reports and Analytics
- Generate an executive farm and financial report.
- Display population and inventory summaries.
- Show financial and expense information.
- Summarize feeds consumption by variety.
- Display batch information and financers.
- Visualize selected report values using progress bars.
- Export reports as CSV files.

### 👥 9. Users and Activity History
- Display registered user accounts.
- Show usernames and account roles.
- Display the currently signed-in user.
- Record account-related activities.
- View activity history associated with the account.

---

## 🖥️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Main programming language |
| Java Swing | Graphical user interface |
| AWT | Graphics, colors, fonts, and interface components |
| ArrayList | Stores accounts, batches, inventory, and history |
| LinkedHashMap | Organizes inventory summaries |
| Java Time API | Handles dates using `LocalDate` and `DateTimeFormatter` |
| File I/O | Exports reports into CSV files |
| Exception Handling | Handles invalid inputs and application errors |

---

## 🧠 Object-Oriented Programming Concepts

The application demonstrates the four fundamental principles of OOP.

| OOP Concept | Implementation |
|---|---|
| Encapsulation | Private attributes and public getter/setter methods |
| Abstraction | Abstract `Person` class |
| Inheritance | `HogRaiser` extends `Person` |
| Polymorphism | Method overriding through `summaryText()` |

### Main Classes

| Class | Description |
|---|---|
| `PiggeryProApp` | Application entry point |
| `Person` | Abstract class containing common person information |
| `HogRaiser` | Handles user information, roles, and authentication |
| `Batch` | Stores pig batch information and calculates pig costs |
| `FeedsInventory` | Stores feeds inventory records |
| `FarmService` | Handles farm operations, validation, computations, and reports |
| `InvalidInputException` | Custom exception for invalid input |
| `AppTheme` | Defines application colors, fonts, and styles |
| `FancyButton` | Custom button with hover animation |
| `KpiCard` | Displays animated dashboard statistics |
| `RingGaugePanel` | Displays animated progress indicators |
| `ExpenseTrendPanel` | Displays visual expense information |

---

## 📸 Application Screenshots

Screenshots of the actual application can be added below. Save your screenshots inside the `images` folder in your GitHub repository.

### 1. Login Screen

![Login Screen](images/login.png)

### 2. Main Dashboard

![Dashboard](images/dashboard.png)

### 3. Pig Batch Management

![Pig Batch Management](images/batch-management.png)

### 4. Feeds Inventory

![Feeds Inventory](images/feeds-inventory.png)

### 5. Expenses Management

![Expenses Management](images/expenses.png)

### 6. Reports and Analytics

![Reports and Analytics](images/reports.png)

### 7. Users and Activity History

![Users and Activity History](images/users-history.png)

> Replace the sample image paths with your actual screenshot filenames.

---

## ⚙️ Installation and Setup

### Prerequisites

- Java Development Kit (JDK)
- Visual Studio Code or another Java-compatible IDE
- Java Extension Pack (recommended for VS Code)

### Steps

**1. Clone the repository**

```bash
git clone https://github.com/your-username/piggery-pro.git
```

**2. Open the project folder**

Open the downloaded folder using Visual Studio Code.

**3. Compile the application**

Open the terminal and execute:

```bash
javac PiggeryProApp.java
```

**4. Run the application**

```bash
java PiggeryProApp
```

---

## 🔑 Default Login Credentials

The application includes a predefined administrator account.

| Field | Credentials |
|---|---|
| Full Name | System Admin |
| Username | `admin` |
| Password | `1234` |
| Role | Farm Administrator |

Users can also register their own accounts through the registration interface.

---

## 📁 Project Structure

```text
Piggery-Pro/
│
├── PiggeryProApp.java
├── README.md
│
└── images/
    ├── login.png
    ├── dashboard.png
    ├── batch-management.png
    ├── feeds-inventory.png
    ├── expenses.png
    ├── reports.png
    └── users-history.png
```

---

## ⚠️ Current Limitations

- Application records are stored temporarily in memory using Java collections.
- Data is not automatically saved to a database or permanent storage.
- Exporting reports to CSV is supported, but this does not provide full application data persistence.
- Account and inventory information may be lost when the application closes.
- Passwords are stored as plain text in the current implementation.

---

## 🚀 Future Improvements

- Integrate MySQL or another database.
- Implement permanent data storage.
- Improve account and password security.
- Add more detailed inventory monitoring.
- Add additional financial charts and analytics.
- Implement backup and restore functionality.
- Improve report generation and printing options.

---

## 🎯 Project Purpose

This project was developed as an academic application of Java programming and Object-Oriented Programming principles. It aims to demonstrate how a desktop-based inventory management system can help organize piggery operations, manage feeds inventory, monitor expenses, and generate useful farm reports.

---

## 👨‍💻 Contributors

| Member | GitHub Account |
|---|---|
| Malabanan, Richmond | `@github-username` |
| Marquez, Kym Azenith B. | `@github-username` |
| Pagkalinawan, Kerix | `@github-username` |
| Roculan, Ebenezer | `@github-username` |

---

<p align="center">
  <strong>PIGGERY PRO – FARM & FEEDS MANAGEMENT SYSTEM</strong><br>
  Developed using Java Swing
</p>
