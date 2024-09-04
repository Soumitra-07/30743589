# Customer Loyalty Program

## Overview
The **Customer Loyalty Program** is a Java-based console application designed to manage customers, rewards, and purchases for a retail business. It allows users to add, view, update, and delete customer data, manage rewards, and handle purchases efficiently. The project demonstrates the use of Core Java, JDBC, and MySQL.

## Features
- **Customer Management**:
  - Add a new customer.
  - View customer details.
  - Update customer information.
  - Delete a customer.
  - View all customers.
  
- **Reward Management**:
  - Add a new reward.
  - View reward details.
  - Update reward information.
  - Delete a reward.
  - View all rewards.

- **Purchase Management**:
  - Add a new purchase.
  - View purchase details.
  - Update purchase information.
  - Delete a purchase.
  - View all purchases.

## Project Structure
The project is organized into multiple packages for better code organization and maintainability:

- **model**: Contains the entity classes (`Customer`, `Reward`, `Purchase`) representing the database tables.
- **dao**: Contains the Data Access Object (DAO) interfaces and their implementations for database operations.
- **service**: Contains service interfaces and their implementations to manage business logic.
- **util**: Contains utility classes such as `DBConnectionUtil` for managing the database connection.
- **main**: Contains the `Main` class, the entry point for the application.

## Database Schema
The application interacts with the following MySQL database tables:

### Customer Table
| Column Name    | Data Type   | Constraints      |
| -------------- | ----------- | ---------------- |
| customer_id    | INT         | PRIMARY KEY      |
| name           | VARCHAR(50) | NOT NULL         |
| email          | VARCHAR(50) | UNIQUE, NOT NULL |
| phone          | VARCHAR(15) | NOT NULL         |
| total_points   | INT         | DEFAULT 0        |

### Reward Table
| Column Name     | Data Type   | Constraints      |
| --------------- | ----------- | ---------------- |
| reward_id       | INT         | PRIMARY KEY      |
| name            | VARCHAR(50) | NOT NULL         |
| points_required | INT         | NOT NULL         |
| description     | VARCHAR(255)|                  |

### Purchase Table
| Column Name    | Data Type   | Constraints                              |
| -------------- | ----------- | ---------------------------------------- |
| purchase_id    | INT         | PRIMARY KEY                              |
| customer_id    | INT         | FOREIGN KEY REFERENCES Customer(customer_id) |
| reward_id      | INT         | FOREIGN KEY REFERENCES Reward(reward_id) |
| purchase_date  | DATE        | NOT NULL                                 |
| points_earned  | INT         | NOT NULL                                 |

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or above.
- MySQL Server.
- MySQL JDBC Driver.
- An IDE like IntelliJ IDEA, Eclipse, or NetBeans (optional).

### Setup Instructions
1. **Clone the Repository**:
   ```sh
   git clone https://github.com/yourusername/customer-loyalty-program.git
   cd customer-loyalty-program
