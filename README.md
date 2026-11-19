
# Lost and Found Application

 1. Project Overview

The Lost and Found Application is a RESTful web application developed for an educational institute.

The system helps students and staff report lost and found items, view reported items, and submit requests to claim found belongings.

The application provides secure user authentication and role-based access control.

2. Technologies Used

- Java 21
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT Authentication
- MySQL
- Maven
- REST API

3. Main Features

User Management
- User registration
- User login
- Password encryption using BCrypt
- View user details
- Update user details
- Delete users

Item Management
- Create lost or found item reports
- View all reported items
- View an item by ID
- Update item details
- Delete items
- Search items by title
- Filter items by status
- Filter items by category

Request Management
- Submit item claim requests
- View all requests
- View a request by ID
- Update requests
- Delete requests
- Approve or reject requests
- Filter requests by status
- Filter requests by user ID
- Filter requests by item ID

Security
- JWT-based authentication
- BCrypt password encryption
- Role-based access control
- Stateless session management

4. User Roles

| Role | Description |
| ADMIN | Manages users and application resources |
| STAFF | Manages items and claim requests |
| USER | Registers, logs in, reports items, and submits requests |


5. Project Status

Core application development includes user authentication, item management, request management, role-based access control, and exception handling.

