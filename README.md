# 🛫 AeroLink Airport Management System

<div align="center">

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Status](https://img.shields.io/badge/Status-Active-brightgreen?style=for-the-badge)

**A comprehensive console-based airport management system built with Java**

---

*Efficiently manage flights, passengers, bookings, and ground services with advanced data structures*

</div>

## 📋 Table of Contents

1. [Overview](#-overview)
2. [Features](#-features)
3. [Architecture](#️-architecture)
4. [Data Structures Used](#-data-structures-used)
5. [How to Run](#-how-to-run)
6. [Usage Guide](#-usage-guide)
7. [Login Credentials](#-login-credentials)
8. [Project Structure](#-project-structure)
9. [Technical Details](#️-technical-details)

## 🎯 Overview

**AeroLink** is a sophisticated airport management system designed to streamline airport operations through efficient management of flights, passengers, bookings, and ground services. Built with Java, it demonstrates the practical implementation of various data structures and algorithms in a real-world scenario.

### 🌟 Key Highlights

- **🔒 Secure Admin Authentication** - Protected access to system functionalities
- **📊 Advanced Data Structures** - Utilizes ArrayList, HashMap, PriorityQueue, Stack, Deque, and Binary Search Trees
- **🎯 Efficient Operations** - Optimized search, sort, and management operations
- **📱 User-Friendly Interface** - Intuitive console-based menu system
- **🔍 Smart Search Capabilities** - Binary search tree implementation for fast lookups

## ✨ Features

### 🛩️ Flight Management
-  **Add New Flights** - Register flights with complete details
-  **Schedule Management** - Handle departure and arrival times
-  **Pricing System** - Manage flight pricing
-  **Flight Cancellation** - Cancel flights with status updates
-  **Real-time Updates** - Modify flight details dynamically
-  **Advanced Search** - Find flights by name, origin, or destination
-  **Delay Prediction** - AI-powered delay forecasting system

### 👥 Passenger Management
-  **Registration System** - Add passenger details with unique passport validation
-  **Profile Updates** - Modify passenger information
-  **Account Deletion** - Remove passenger records
-  **Smart Search** - Binary search tree implementation for quick lookups
-  **Comprehensive Listings** - View all registered passengers

### 🎫 Booking System
-  **Flight Booking** - Book available flights for passengers
-  **Booking History** - Track all booking transactions
-  **Real-time Availability** - Check seat availability instantly
-  **Booking Analytics** - View booking statistics and trends
-  **Duplicate Prevention** - Prevent multiple bookings for same flight

### 🛠️ Ground Services
-  **Priority-based Scheduling** - PriorityQueue implementation for service management
-  **Service Types** - Baggage handling, cleaning, catering, refueling, maintenance
-  **Location Management** - Track service locations (gates/terminals)
-  **Completion Tracking** - Mark services as completed
-  **Service Queue** - View next service to be processed

## 🏗️ Architecture

```
                                                AeroLink System Architecture

                              ┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
                              │   AeroLink.java │────│  FlightManager  │────│   Flight.java   │
                              │   (Main Entry)  │    │                 │    │   (Model)       │
                              └─────────────────┘    └─────────────────┘    └─────────────────┘
                                       │                       │                       │
                                       ├──────────────────────────────────────────────────────────┐
                                       │                       │                       │          │
                              ┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐   │
                              │ PassengerManager│────│ Passenger.java  │    │ BookingManager  │   │
                              │                 │    │   (Model)       │────│                 │   │
                              └─────────────────┘    └─────────────────┘    └─────────────────┘   │
                                       │                       │                       │          │
                                       │                       │              ┌─────────────────┐ │
                                       │                       │              │  Booking.java   │ │
                                       │                       │              │   (Model)       │ │
                                       │                       │              └─────────────────┘ │
                                       │                       │                       │          │
                              ┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐   │
                              │ ServiceManager  │────│  Service.java   │    │ Data Structures │─ ─┘
                              │                 │    │   (Model)       │    │ (Collections)   │
                              └─────────────────┘    └─────────────────┘    └─────────────────┘
```

## 📊 Data Structures Used

| Data Structure | Usage | Purpose |
|---------------|--------|---------|
| **ArrayList** | Primary storage for all entities | Dynamic arrays for flexible storage |
| **HashMap** | Quick lookup by ID | O(1) access time for entity retrieval |
| **PriorityQueue** | Service scheduling & flight sorting | Priority-based processing |
| **Stack** | Recent bookings tracking | LIFO access for booking history |
| **Deque** | Booking request processing | Double-ended queue for efficient processing |
| **Binary Search Tree** | Search operations | Optimized search for flights and passengers |
| **LinkedList** | Search results | Flexible result storage |

## 🚀 How to Run

### Prerequisites

- ☕ **Java Development Kit (JDK) 8 or higher**
- 💻 **Command Line Interface** (Terminal/Command Prompt)
- 📝 **Text Editor or IDE** (Optional: IntelliJ IDEA, Eclipse, VS Code)

### 🔧 Installation Steps

1. **📥 Clone or Download the Project**
   ```bash
   git clone https://github.com/krishna1584/AeroLink
   # OR download and extract the ZIP file
   ```

2. **📂 Navigate to Project Directory**
   ```bash
   cd AeroLink
   ```

3. **⚙️ Compile the Java Files**
   ```bash
   javac *.java
   ```

4. **🎯 Run the Application**
   ```bash
   java AeroLink
   ```

### 🖥️ Alternative IDE Setup

**IntelliJ IDEA:**
1. Open IntelliJ IDEA
2. Click "Open" and select the project folder
3. Right-click on `AeroLink.java` → "Run 'AeroLink.main()'"

**Eclipse:**
1. File → Import → Existing Projects into Workspace
2. Select the project folder
3. Right-click on `AeroLink.java` → "Run As" → "Java Application"

**VS Code:**
1. Open the project folder in VS Code
2. Install Java Extension Pack
3. Press `F5` or use the Run button on `AeroLink.java`

## 📖 Usage Guide

### 🚀 Getting Started

1. **Launch the Application**
   - Run the program using the steps above
   - You'll see the welcome message for AeroLink

2. **🔐 Admin Login**
   - Enter the admin credentials (see below)
   - Access the main menu after successful authentication

3. **📋 Navigate the Menu**
   - Use the numbered menu options (1-16)
   - Follow the on-screen prompts for each operation

### 🔄 Typical Workflow

```
1. Add Flight → 2. Add Passenger → 3. Book Flight → 6. View All Flights
                                                  ↓
4. Request Ground Service ← 8. View All Bookings
```

## 🔐 Login Credentials

| Username | Password |
|----------|----------|
| `admin`  | `admin123` |

> ⚠️ **Note:** For production use, implement secure authentication with encrypted passwords.

## 📁 Project Structure

```
📦 AeroLink Airport Management System
├── 📄 AeroLink.java          # Main application entry point
├── 📄 Flight.java            # Flight model class
├── 📄 FlightManager.java     # Flight operations management
├── 📄 Passenger.java         # Passenger model class
├── 📄 PassengerManager.java  # Passenger operations management
├── 📄 Booking.java           # Booking model class
├── 📄 BookingManager.java    # Booking operations management
├── 📄 Service.java           # Ground service model class
├── 📄 ServiceManager.java    # Ground service operations management
└── 📄 README.md              # Project documentation
```

## 🛠️ Technical Details

### 🔧 Core Components

- **Models**: Flight, Passenger, Booking, Service (Data representation)
- **Managers**: FlightManager, PassengerManager, BookingManager, ServiceManager (Business logic)
- **Main Controller**: AeroLink (Application entry point and menu system)

### 🎯 Key Algorithms

- **Binary Search Tree**: Efficient searching in O(log n) time
- **Priority Queue**: Service scheduling with priority-based processing
- **Hash Table**: O(1) lookup time for entity retrieval
- **Stack/Queue**: LIFO/FIFO data processing for booking management

### 📊 Performance Characteristics

| Operation | Time Complexity | Space Complexity |
|-----------|----------------|------------------|
| Add Entity | O(1) | O(1) |
| Search by ID | O(1) | O(1) |
| Search by Keyword | O(log n) | O(n) |
| Sort Operations | O(n log n) | O(n) |
| Priority Processing | O(log n) | O(n) |

<div align="center">

### 🌟 Made with ❤️

**AeroLink - Connecting Flights, Passengers, and Services Seamlessly**
</div>
