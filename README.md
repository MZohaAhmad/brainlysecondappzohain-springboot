# Second Brain Spring Boot Server

This is a Spring Boot conversion of the Node.js/Express server for the Second Brain application.

## Features

- User authentication (registration/login) with JWT tokens
- Content management (create, read, delete)
- MongoDB integration
- CORS configuration for frontend integration
- Spring Security with stateless JWT authentication

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- MongoDB running on localhost:27017

## API Endpoints

### Authentication
- `POST /api/v1/auth/signup` - User registration
- `POST /api/v1/auth/signin` - User login

### Content Management
- `GET /api/v1/content` - Get user content (requires token)
- `POST /api/v1/content/addcontent` - Add new content (requires token)
- `DELETE /api/v1/content/delete/{title}` - Delete content (requires token)
- `GET /api/v1/content/share/{userId}` - Get shared content (requires token)

## Running the Application

1. Ensure MongoDB is running on localhost:27017
2. Navigate to the project directory
3. Run: `mvn spring-boot:run`
4. Server will start on http://localhost:3000

## Configuration

The application.properties file contains:
- Server port: 3000
- MongoDB URI: mongodb://localhost:27017/mydatabase
- CORS origin: http://localhost:5173 (frontend)
- JWT secret and expiration settings

## Frontend Integration

Update your frontend API calls to use the new endpoints:
- Authentication: `/api/v1/auth/signup` and `/api/v1/auth/signin`
- Content: `/api/v1/content/*` endpoints


