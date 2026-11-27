# 🐷 Piggery Pro – Commercial Farm & Feeds Management System

<p align="center">
  A Java Swing desktop application with custom animations, vector visuals, and OOP architecture for managing pig batches, feeds inventory, operational expenses, and batch cost analytics.
</p>

---

## 📌 About the Project

**Piggery Pro** is a desktop GUI application built in Java (`PiggeryProApp.java`) using Object-Oriented Programming (OOP) principles and custom Java 2D/Swing components.

The system helps hog raisers manage piggery operations through an animated authentication portal, account-isolated workspaces, batch CRUD management, feeds inventory tracking, real-time cost previews, feed goal computations, expense breakdowns, activity logs, and CSV report exports.

---

## ✨ Key Features

### 🔐 Animated Authentication Portal & Account Isolation
- Compact `360 × 395` Sign In and Create Account card alongside an animated ambient showcase panel (`AnimatedShowcasePanel`).
- Password show/hide toggle, focus-ring highlighting, inline validation feedback, and horizontal card shake animation (`ShakeableCardPanel`) on invalid login/registration attempts.
- Username duplicate checks (case-insensitive), space validation, and a minimum 4-character password rule.
- Maximum of 3 login attempts per session before lockout.
- Account-isolated batches, feed records, and activity history per logged-in `HogRaiser`.

### 🐖 Pig & Batch Management
- Create, edit (via form or dialog), and delete pig batches.
- Auto-generates batch names (`Batch 1`, `Batch 2`, ...) when left blank and prevents duplicate batch names within the same account.
- Tracks batch name, pig population, price per pig, total pig cost, financer (defaults to `Self-Financed`), and status (`Active`, `Growing`, `Finishing`, `Completed`).
- Live estimated pig cost preview while typing, live search filtering, status filtering, and sortable table columns.
- Cascading batch name updates to matching feed records, and cascading deletion of associated feed entries when a batch is removed.

### 🌽 Feeds Inventory Management
- Add feed records linked to an existing user-owned batch.
- Tracks feed name/brand, variety, price per unit, quantity, subtotal, and purchase date (`MM/dd/yyyy`).
- Supports predefined varieties (`Pre-Starter`, `Starter`, `Grower`, `Finisher`, `Breeder`) plus a `Custom Variety` option.
- Live subtotal preview (`price × quantity`), batch filtering (`All Batches` or a specific batch), live search filtering, sortable columns, and variety-level unit/cost summaries.
- Automated low-stock alerts when aggregated stock for a feed/variety/batch is at or below `5` units (`FarmService.LOW_STOCK_THRESHOLD`).

### 📊 Dashboard & Animated Visual Analytics
- Animated KPI cards (`KpiCard`) for **Total Pig Population**, **Total Batches**, **Total Feed Units**, and **Total Expenses**, complete with custom vector badges (`VectorBadge`) and hover elevation.
- Quick feed & expense summary banner, recent feed records table, recent batches table, and notifications/low-stock alert panel.
- Animated custom charts:
  - `RingGaugePanel`: Starter feed goal progress ring.
  - `ExpenseTrendPanel`: Batch cost trajectory curve.
  - `BatchColumnChartPanel`: Batch cost comparison bars.
- Smooth page transitions (`AnimatedPageHost`), animated sidebar navigation (`SidebarNavButton`), custom initials avatar (`AvatarBadge`), and global header search/status banner.

### 🧮 Feed Computation & Cost Formulas
- Computes per-batch reports (`FarmService.computeBatchReport`) with pig details, variety usage vs. targets, feed cost per pig, and total cost per pig.

| Feed Variety | Predefined Goal |
|---|---|
| Pre-Starter | `0.1` units per pig (`pigs / 10.0`) |
| Starter | `1` unit per pig (`pigs * 1`) |
| Grower | `3` units per pig (`pigs * 3`) |
| Finisher | No predefined goal |
| Breeder | No predefined goal |

**Computation formulas:**

- **Total Pig Cost:** `Pig Count × Price per Pig`
- **Feed Subtotal / Total Feed Cost:** `Price per Unit × Quantity`
- **Total Batch / Farm Expenses:** `Total Pig Cost + Total Feed Cost`
- **Feed Cost per Pig:** `Total Feed Cost ÷ Pig Count`
- **Total Cost per Pig:** `Total Expenses ÷ Pig Count`

### 💸 Expenses, Reports & CSV Export
- Unified **Expenses** view combining pig stock costs and feed subtotals with live search and summary totals (`Pig Stock Expenses`, `Feed Stock Expenses`, `Total Expenses`, and `Average Cost / Pig`).
- **Reports & Charts** view with visual progress bars (`Pig Cost Share`, `Feed Cost Share`, `Feed Units vs Pigs`), detailed executive report text, and **CSV export** (`piggery_pro_<username>_report.csv`).

### 👥 User Accounts & Activity History
- View current session details, register new Hog Raiser accounts, and inspect all registered accounts.
- Date-stamped **Activity History** filtered to the signed-in user's actions (sign-in/sign-out, account registration, batch add/update/delete, and feed additions).

---

## 🛠️ Technologies and Java Libraries

| Technology / Library | Implementation |
|---|---|
| Java & Swing (`javax.swing`) | Desktop UI, cards, tables, dialogs, timers, and file chooser |
| Java 2D (`java.awt`, `java.awt.geom`) | Custom painting, gradients, animations, charts, and vector badges |
| `ArrayList` & `LinkedHashMap` | In-memory storage and ordered variety/stock summaries |
| `TableRowSorter` & `RowFilter` | Table sorting and live search/status filtering |
| `LocalDate` & `DateTimeFormatter` | Strict `MM/dd/yyyy` date parsing, formatting, and timestamps |
| `PrintWriter` & `FileWriter` | CSV report export |
| Exception Handling | `InvalidInputException` and `IllegalArgumentException` validation |

---

## 🧩 Object-Oriented Programming Concepts

| OOP Principle | Implementation in the System |
|---|---|
| Encapsulation | Private fields across domain classes with validated constructors and getter/update methods |
| Abstraction | Abstract `Person` class declaring abstract `summaryText()` |
| Inheritance | `HogRaiser` extends `Person` |
| Polymorphism | `HogRaiser` implements `summaryText()` (`getName() + " • " + role`) |

### Class Implementation

| Class | Responsibility |
|---|---|
| `PiggeryProApp` | Application entry point and Swing UI initialization |
| `PiggeryGuiFrame` | Main `JFrame` managing authentication, navigation, pages, forms, tables, and view refreshes |
| `FarmService` | Core business logic, strict parsing, authentication, CRUD operations, alerts, reports, and CSV export |
| `Person` | Abstract base class for person entities |
| `HogRaiser` | User account model with role, credentials, admin flag, and authentication logic |
| `Batch` | Pig batch entity with pig count, pricing, total cost calculation, financer, owner, and status |
| `FeedsInventory` | Feed record entity with variety, unit price, quantity, subtotal, and date |
| `InvalidInputException` | Checked exception for user input and validation errors |
| `AppTheme` & Custom Components | Styling constants and animated/custom-painted widgets (`PremiumTextField`, `PremiumPasswordField`, `FancyButton`, `AnimatedShowcasePanel`, `ShakeableCardPanel`, `KpiCard`, `RingGaugePanel`, `ExpenseTrendPanel`, `BatchColumnChartPanel`, `AnimatedPageHost`, `SidebarNavButton`, `AvatarBadge`, `VectorBadge`) |

---

## 📁 Project Structure

The application is currently contained in a single Java source file.

```text
Piggery-Inventory-System/
│
├── PiggeryProApp.java
│   ├── Domain & OOP Classes (InvalidInputException, Person, HogRaiser, Batch, FeedsInventory)
│   ├── FarmService
│   ├── Theme, Motion & Custom Vector Components
│   ├── PiggeryGuiFrame (Auth Portal, Sidebar, Header, 8 Workspace Views)
│   └── PiggeryProApp (main)
│
└── README.md
