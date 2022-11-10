# REST API for Acme Fresh website 

This project is about Building REST APIs for Acme Fresh website which can be used by customers for  different services.
Acme Fresh helps us to buy Hydroponically produced vegetable directly from farmers. 
Hydroponics is also known as soil-less farming that requires a lot of technology augmentation like IoT, AI, etc hence also known as precision farming or vertical farming.

 
The company provides different services such as:
* Setting up hydroponic farm infrastructure for customers.
* Selling hydroponic produce obtained from its clients and other firms.
* Automating farms using modern techniques.
* Selling produce directly to customers(B2C) from its website.


<br />

### This is a individual project, completed with 24 hours as part of placement assignment.

- **Services Offered**
1. Farmer and Customer can Login and Signup.
2. Farmer can post their Hydroponically produced vegetable to the website.
3. Customer can view and buy vegetables directly from farmers

<br />

- **Backend**
1. Built authentication and authorization using Spring Security and JWT.
2. Stored the data on MySQL database

<br />

# Installation & Run
 - Before running the API server, you should update the database config inside the application.properties file.
- Update the port number, username and password as per your local database configuration.

```
    server.port=8080

    spring.datasource.url=jdbc:mysql://localhost:3306/walletdb;
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=root
