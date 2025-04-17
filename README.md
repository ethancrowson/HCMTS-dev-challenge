# Task Manager API

A simple REST API built with Java Spring Boot for managing tasks for caseworkers at HMCTS.

## 📚 Features

- Create a task with:
  - Title
  - Optional Description
  - Status (Pending, In Progress, Completed, On Hold, Cancelled)
  - Due date/time
- Retrieve a task by ID
- Retrieve all tasks
- Update the status of a task
- Delete a task
- Input validation and error handling
- Data stored in a PostgreSQL database

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven
- PostgreSQL

### Setup Instructions

1. Clone this repository:
```
git clone https://github.com/yourusername/task-manager-backend.git
cd task-manager-backend
```
   
2. Configure your PostgreSQL database in src/main/resources/application.properties:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/taskdb
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
```

3. Run the application:
```
mvnw spring-boot:run
```

The API will be available at http://localhost:8080/tasks


## API Documentation
### Base URL
```
http://localhost:8080/api/tasks
```

 ### Endpoints
1. Create a Task
  URL: /
  Method: POST
  Request Body:
```
{
    "title": "Task Title",
    "description": "Task Description",
    "status": "PENDING",
    "dueDate": "2025-05-01T12:00:00"
}
```
  Response:
```
{
    "id": 1,
    "title": "Task Title",
    "description": "Task Description",
    "status": "PENDING",
    "dueDate": "2025-05-01T12:00:00"
}
```
2. Get All Tasks
  URL: /
  Method: GET
  Response:
```
[
    {
        "id": 1,
        "title": "Task 1",
        "status": "PENDING",
        "dueDate": "2025-05-01T12:00:00"
    },
    {
        "id": 2,
        "title": "Task 2",
        "status": "IN_PROGRESS",
        "dueDate": "2025-05-02T12:00:00"
    }
]
```
3. Get Task by ID
  URL: /id
  Method: GET
  Response:
```
{
    "id": 1,
    "title": "Task 1",
    "status": "PENDING",
    "dueDate": "2025-05-01T12:00:00"
}
```
4. Update Task Status
  URL: /id/status
  Method: PUT
  Request Body:
```
{
    "status": "COMPLETED"
}
```
5. Delete a Task
  URL: /id
  Method: DELETE
  Response:
    No content response (204)

## Running the Application
### Prerequisites:
- Java 17+
- Maven
- PostgreSQL

### Running the Server
1. Clone the repository:
```
git clone https://github.com/your-username/task-manager-backend.git
```
2. Configure your database connection in src/main/resources/application.properties.

3. Run the application:
```
mvn spring-boot:run
```
The application will start at http://localhost:8080.

### Unit Tests
The backend contains unit tests for the API endpoints and the service layer using JUnit. You can run the tests using the following command:
```
mvn test
```
