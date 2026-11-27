# 🐷 Piggery Inventory System – Feeds Management

<p align="center">
  A Java-based console application for managing pig batches, monitoring feeds inventory, tracking expenses, and computing piggery operational costs.
</p>

---

## 📌 About the Project

The **Piggery Inventory System – Feeds Management** is a menu-driven application developed using Java and Object-Oriented Programming (OOP).

The system is designed to help hog raisers organize their piggery operations by providing features for user authentication, pig batch management, feeds inventory monitoring, inventory dashboards, activity tracking, and batch expense computation.

It allows users to record pig batches, associate feeds purchases with specific batches, monitor feed quantities, and calculate the total expenses involved in raising pigs.

This project demonstrates the practical implementation of Java classes, encapsulation, inheritance, abstraction, polymorphism, collections, and exception handling.

---

## ✨ Key Features

### 🔐 User Authentication
- User registration and login.
- Username validation to prevent duplicate accounts.
- Password authentication.
- Maximum of three login attempts.
- Default administrator account.

### 🐖 Pig Batch Management
- Add one or multiple pig batches.
- Automatically generate batch names when no name is provided.
- Record pig count and price per pig.
- Record batch financer.
- Automatically calculate total pig costs.
- View all batches associated with the logged-in account.
- Display the three most recently added batches.

### 🌽 Feeds Inventory Management
- Add feeds to an existing pig batch.
- Select a batch before recording feeds.
- Record feed name or brand.
- Record feed variety, price, quantity, and purchase date.
- Choose from predefined feed varieties:
  - Pre-Starter
  - Starter
  - Grower
  - Finisher
  - Breeder
- Add custom feed varieties.
- View feeds from all batches or a selected batch.

### 📊 Feeds Inventory Dashboard
- Display the logged-in user's name.
- Display total batches and pigs.
- Show total feed entries.
- Calculate total inventory quantities.
- Calculate estimated inventory value.
- Display feed quantities by variety.
- Compare selected feed quantities against predefined stock goals.
- Display the three most recently added feeds.

### 📈 Batch Dashboard
- Display total pig batches.
- Display total pig population.
- Calculate total pig expenses.
- Compute average pigs per batch.
- Display the number of pigs by financer.
- Display the three most recently added batches.

### 📋 Inventory History
- Record newly added pig batches.
- Record newly added feeds.
- Display inventory activities.
- Include activity details such as batch name, quantity, date, and user.

### 🧮 Batch Expense Computation
- Select a specific pig batch for computation.
- Calculate total pig expenses.
- Calculate total feeds used.
- Calculate feed expenses by variety.
- Compare feed quantities against predefined goals.
- Calculate total batch expenses.
- Calculate feed cost per pig.
- Calculate total cost per pig.

---

## 📐 Feed Quantity Computation

The application includes predefined feed quantity goals for monitoring inventory.

| Feed Variety | Quantity Goal |
|---|---|
| Pre-Starter | 1 sack per 10 pigs |
| Starter | 1 feed per pig |
| Grower | 3 feeds per pig |
| Finisher | No predefined goal |
| Breeder | No predefined goal |

**Computation formulas:**

**Total Pig Cost**
```text
Pig Count × Price per Pig
```

**Total Feed Cost**
```text
Feed Price × Feed Quantity
```

**Total Batch Expenses**
```text
Total Pig Cost + Total Feed Cost
```

**Feed Cost per Pig**
```text
Total Feed Cost ÷ Number of Pigs
```

**Total Cost per Pig**
```text
Total Batch Expenses ÷ Number of Pigs
```

---

## 🛠️ Technologies and Java Libraries

| Technology / Library | Implementation |
|---|---|
| Java | Main programming language |
| Scanner | Accepts user input |
| ArrayList | Stores accounts, batches, feeds, and history |
| LinkedHashMap | Organizes feed and financer summaries |
| LocalDate | Handles inventory dates |
| DateTimeFormatter | Formats dates |
| Exception Handling | Handles invalid inputs and runtime errors |
| Custom Exception | Implements `InvalidInputException` |

---

## 🧩 Object-Oriented Programming Concepts

The system implements the four fundamental principles of Object-Oriented Programming.

| OOP Principle | Implementation in the System |
|---|---|
| Encapsulation | Private attributes with public getter and setter methods |
| Abstraction | Abstract `Person` class with an abstract `display()` method |
| Inheritance | `HogRaiser` extends the `Person` class |
| Polymorphism | Overriding the `display()` method and accessing it through a `Person` reference |

### Class Implementation

| Class | Responsibility |
|---|---|
| `Main` | Handles application execution, authentication, menus, dashboards, and system operations |
| `Person` | Abstract parent class containing common person information |
| `HogRaiser` | Manages hog raiser information and login authentication |
| `Batch` | Stores pig batch information and calculates pig costs |
| `FeedsInventory` | Stores feeds inventory information and handles feed records |
| `InvalidInputException` | Custom exception for input validation |

---

## 📁 Project Structure

The current project is organized in a single Java source file.

```text
Piggery-Inventory-System/
│
├── Main.java
│   ├── InvalidInputException
│   ├── Person
│   ├── Batch
│   ├── HogRaiser
│   ├── FeedsInventory
│   │
│   └── Main
│       ├── User Authentication
│       ├── Batch Management
│       ├── Feeds Management
│       ├── Inventory Dashboard
│       ├── Batch Dashboard
│       ├── Inventory History
│       └── Batch Computation
│
└── README.md
```

---

## ⚙️ Installation and Setup

### Prerequisites

Make sure you have the following installed:

- Java Development Kit (JDK)
- Visual Studio Code or another Java-compatible IDE
- Java Extension Pack for Visual Studio Code (recommended)

### 1. Clone the Repository

```bash
git clone <repository-url>
```

### 2. Open the Project

Open the downloaded project folder using Visual Studio Code.

### 3. Open the Terminal

Navigate to the directory containing `Main.java`.

### 4. Compile the Program

```bash
javac Main.java
```

### 5. Run the Application

```bash
java Main
```

---

## 🔑 Default Login Credentials

The application automatically creates a default administrator account when it starts.

| Account Information | Value |
|---|---|
| Name | System Admin |
| Username | `admin` |
| Password | `1234` |

Users can also register their own accounts through the registration menu.

---

## 🖥️ System Screenshots

Add screenshots of the actual application to showcase its functionalities.

### 1. Welcome Menu

![Welcome Menu](screenshots/welcome-menu.png)

### 2. User Registration and Login

![User Authentication](screenshots/user-authentication.png)

### 3. Feeds Inventory Dashboard

![Feeds Dashboard](screenshots/feeds-dashboard.png)

### 4. Batch Dashboard

![Batch Dashboard](screenshots/batch-dashboard.png)

### 5. Batch Management

![Batch Management](screenshots/batch-management.png)

### 6. Feeds Inventory

![Feeds Inventory](screenshots/feeds-inventory.png)

### 7. Inventory History

![Inventory History](screenshots/inventory-history.png)

### 8. Batch Computation

![Batch Computation](screenshots/batch-computation.png)

> Replace the sample image paths with your actual application screenshots.

---

## ⚠️ Current Limitations

- The application uses a console-based interface.
- Data is stored temporarily in `ArrayList` collections.
- No database integration is implemented.
- No permanent file storage is implemented.
- Existing batch and feeds records cannot be edited or deleted.
- Inventory history is not permanently saved.
- Records are lost when the application terminates.

---

## 🚀 Future Improvements

Potential enhancements for future versions include:

- Database integration using MySQL.
- Permanent storage of user accounts and inventory records.
- Edit and delete functionality for batches and feeds.
- Improved graphical user interface.
- Inventory reports and printable summaries.
- Additional inventory monitoring and expense tracking features.

---

## 🎯 Project Objectives

The project aims to:

1. Develop a Java-based inventory management application for piggery operations.
2. Provide an organized way to record and monitor pig batches.
3. Monitor feeds inventory and quantities by variety.
4. Calculate pig expenses, feed expenses, and total batch costs.
5. Apply Object-Oriented Programming principles in a practical application.
6. Implement exception handling to improve input validation and program reliability.

---

## 👨‍💻 Contributors

| Developer | GitHub Account |
|---|---|
| Malabanan, Richmond | Add GitHub username |
| Marquez, Kym Azenith B. | Add GitHub username |
| Pagkalinawan, Kerix | Add GitHub username |
| Roculan, Ebenezer | Add GitHub username |

---

## 📌 Project Information

| Category | Details |
|---|---|
| Project | Piggery Inventory System – Feeds Management |
| Language | Java |
| Application Type | Console-Based |
| Architecture | Object-Oriented Programming |
| Data Structure | ArrayList and LinkedHashMap |
| Development Purpose | Academic Project |

---

<p align="center">
  <b>Piggery Inventory System – Feeds Management</b><br>
  Developed with Java ❤️
</p>
