# CRUD Application

This CRUD (Create, Read, Update, Delete) application is designed to manage various entities within a database using Hibernate for ORM (Object-Relational Mapping) and Java for the application logic. The application interacts with a MySQL database and provides a simple interface for performing CRUD operations on the following entities:

- Customers
- Employees
- Orders
- OrderDetails
- Payments
- ProductLines
- Products
- UserRoles
- Users

## Features

- **Entity Management**: Manage multiple entities such as Customers, Employees, Orders, Payments, ProductLines, Products, UserRoles, and Users.
- **CRUD Operations**: Perform Create, Read, Update, and Delete operations on the entities.
- **User-Friendly Interface**: Command-line interface for interacting with the application.
- **Validation**: Input validation to ensure data integrity.
- **Hibernate ORM**: Utilizes Hibernate for seamless database interaction.
- **Spring Boot Integration** (optional): Easy integration with Spring Boot for web-based interface and REST APIs.

## Technologies Used

- **Java**: Core programming language.
- **Hibernate**: ORM framework for database interaction.
- **MySQL**: Database for storing application data.
- **Maven/Gradle**: Build and dependency management tools.
- **Lombok**: Simplifies Java code by using annotations.
- **Spring Boot** (optional): For web-based interface and REST APIs.

## Getting Started

### Prerequisites

- Java JDK 8 or higher
- MySQL Database
- Maven or Gradle
- Git

### Setup

1. **Clone the Repository:**
   ```sh
   git clone https://github.com/your-username/my-crud-application.git
   cd my-crud-application

   Important Security Notice
Use Dummy Passwords:

When uploading configuration files, such as hibernate.cfg.xml or application.properties, to GitHub, ensure you use dummy passwords. Do not upload your actual database credentials to a public repository.

Example:
<property name="hibernate.connection.username">dummy_username</property>
<property name="hibernate.connection.password">dummy_password</property>
