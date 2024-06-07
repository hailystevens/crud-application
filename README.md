<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CRUD Application</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            margin: 0;
            padding: 20px;
            background-color: #f4f4f4;
        }
        h1, h2, h3 {
            color: #333;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .btn {
            display: inline-block;
            margin: 10px 0;
            padding: 10px 20px;
            font-size: 16px;
            color: #fff;
            background: #007bff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
        }
        .btn:hover {
            background: #0056b3;
        }
        pre {
            background: #333;
            color: #fff;
            padding: 10px;
            border-radius: 4px;
            overflow-x: auto;
        }
        code {
            background: #eee;
            padding: 2px 4px;
            border-radius: 4px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>CRUD Application</h1>
        <p>This CRUD (Create, Read, Update, Delete) application is designed to manage various entities within a database using Hibernate for ORM (Object-Relational Mapping) and Java for the application logic. The application interacts with a MySQL database and provides a simple interface for performing CRUD operations on the following entities:</p>
        <ul>
            <li>Customers</li>
            <li>Employees</li>
            <li>Orders</li>
            <li>OrderDetails</li>
            <li>Payments</li>
            <li>ProductLines</li>
            <li>Products</li>
            <li>UserRoles</li>
            <li>Users</li>
        </ul>

        <h2>Features</h2>
        <ul>
            <li>Entity Management: Manage multiple entities such as Customers, Employees, Orders, Payments, ProductLines, Products, UserRoles, and Users.</li>
            <li>CRUD Operations: Perform Create, Read, Update, and Delete operations on the entities.</li>
            <li>User-Friendly Interface: Command-line interface for interacting with the application.</li>
            <li>Validation: Input validation to ensure data integrity.</li>
            <li>Hibernate ORM: Utilizes Hibernate for seamless database interaction.</li>
            <li>Spring Boot Integration (optional): Easy integration with Spring Boot for web-based interface and REST APIs.</li>
        </ul>

        <h2>Technologies Used</h2>
        <ul>
            <li>Java: Core programming language.</li>
            <li>Hibernate: ORM framework for database interaction.</li>
            <li>MySQL: Database for storing application data.</li>
            <li>Maven/Gradle: Build and dependency management tools.</li>
            <li>Lombok: Simplifies Java code by using annotations.</li>
            <li>Spring Boot (optional): For web-based interface and REST APIs.</li>
        </ul>

        <h2>Getting Started</h2>
        <h3>Prerequisites</h3>
        <ul>
            <li>Java JDK 8 or higher</li>
            <li>MySQL Database</li>
            <li>Maven or Gradle</li>
            <li>Git</li>
        </ul>

        <h3>Setup</h3>
        <ol>
            <li><strong>Clone the Repository:</strong></li>
            <pre><code>git clone https://github.com/your-username/my-crud-application.git
cd my-crud-application
            </code></pre>

            <li><strong>Configure Database:</strong></li>
            <p>Update the <code>hibernate.cfg.xml</code> file with your MySQL database connection details.</p>

            <li><strong>Build the Project:</strong></li>
            <p>If using Maven:</p>
            <pre><code>mvn clean install</code></pre>
            <p>If using Gradle:</p>
            <pre><code>gradle build</code></pre>

            <li><strong>Run the Application:</strong></li>
            <p>Use the main classes provided for each entity (e.g., <code>CustomerMain</code>, <code>EmployeeMain</code>) to perform CRUD operations.</p>
        </ol>

        <h2>Project Structure</h2>
        <pre><code>my-crud-application/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/
│   │   │       └── example/
│   │   │           ├── database/
│   │   │           │   ├── dao/
│   │   │           │   │   ├── CustomerDAO.java
│   │   │           │   │   ├── EmployeeDAO.java
│   │   │           │   │   ├── OrderDAO.java
│   │   │           │   │   ├── PaymentDAO.java
│   │   │           │   │   ├── ProductDAO.java
│   │   │           │   │   ├── ProductLineDAO.java
│   │   │           │   │   ├── UserDAO.java
│   │   │           │   │   └── UserRoleDAO.java
│   │   │           │   ├── entity/
│   │   │           │   │   ├── Customer.java
│   │   │           │   │   ├── Employee.java
│   │   │           │   │   ├── Order.java
│   │   │           │   │   ├── OrderDetails.java
│   │   │           │   │   ├── Payment.java
│   │   │           │   │   ├── Product.java
│   │   │           │   │   ├── ProductLine.java
│   │   │           │   │   ├── User.java
│   │   │           │   │   └── UserRole.java
│   │   │           │   ├── service/
│   │   │           │   ├── controller/
│   │   │           │   └── util/
│   │   ├── resources/
│   │   │   ├── application.properties
│   │   │   └── hibernate.cfg.xml
│   └── test/
│       └── java/
├── .gitignore
├── README.md
└── pom.xml (or build.gradle)
        </code></pre>

        <h2>Usage</h2>
        <ul>
            <li><code>CustomerMain</code>: Manage customer details.</li>
            <li><code>EmployeeMain</code>: Manage employee details.</li>
            <li><code>OrderMain</code>: Manage orders.</li>
            <li><code>OrderDetailsMain</code>: Manage order details.</li>
            <li><code>PaymentMain</code>: Manage payments.</li>
            <li><code>ProductLineMain</code>: Manage product lines.</li>
            <li><code>ProductMain</code>: Manage products.</li>
            <li><code>UserRoleMain</code>: Manage user roles.</li>
            <li><code>UserMain</code>: Manage user details.</li>
        </ul>

        <h2>Contributions</h2>
        <p>Contributions are welcome! Please fork the repository and submit pull requests for any improvements or bug fixes.</p>

        <h2>License</h2>
        <p>This project is licensed under the MIT License - see the <a href="LICENSE">LICENSE</a> file for details.</p>
    </div>
</body>
</html>
