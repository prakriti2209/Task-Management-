Task Management System

A Spring Boot REST API for managing personal daily tasks with secure user authentication.

This application supports two roles — ADMIN and USER. Admin can view all users, promote or demote users to admin role. Each user can create, view, update and delete only their own tasks. User profile management is also available — users can view, update and delete their own profile.

Authentication is done using Spring Security with HTTP Basic Auth. All task and profile endpoints are secured and require login.

Tech Stack: Java, Spring Boot, Spring Security, MySQL, Spring Data JPA, Hibernate, Postman

How to run:
Clone the repository, create a MySQL database named todo, update your credentials in application.properties, and run using mvn spring-boot:run.

API endpoints:

Public:
Register — POST /public

User:
Get my profile — GET /profile
Update my profile — PUT /profile
Delete my profile — DELETE /profile
Create task — POST /todos
Get all my tasks — GET /todos
Update task — PUT /todos/{id}
Delete task — DELETE /todos/{id}

Admin:
Get all users — GET /admin
Make user admin — GET /admin/make-admin/{id}
Remove admin role — GET /admin/remove-admin/{id}
