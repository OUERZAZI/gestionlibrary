# GestionLibrary Project

This repository hosts the **GestionLibrary** project, designed for library management. The project leverages modern DevOps tools to ensure scalability and reliability, with Docker integration as the first step.

## Project Overview

GestionLibrary is a library management system built using Spring Boot, MySQL, and Thymeleaf for frontend rendering. The project integrates DevOps practices for better deployment and scalability.

### Features
- Manage books, categories, managers, and libraries.
- Responsive UI using Thymeleaf and Bootstrap.
- MySQL database for efficient data management.

---

## Current Progress

### Completed: Docker Integration

The first phase of this project involved containerizing the application using Docker. Below are the details of the Docker setup:

### Docker Configuration

#### Dockerfile
The `Dockerfile` contains multi-stage builds for efficient containerization:

```Dockerfile
FROM maven:3.6.3-openjdk-17-slim AS build
WORKDIR /app
COPY . .
RUN mvn package -DskipTests

FROM openjdk:17-jdk-slim AS prod
WORKDIR /app
COPY --from=build /app/target/*.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
```

#### docker-compose.yml
`docker-compose.yml` orchestrates the containers for the application and MySQL database:

```yaml
version: '3.8'
services:
  db:
    image: mysql:8.0
    container_name: mysql-container
    ports:
      - "3306:3306"
    environment:
      MYSQL_ROOT_PASSWORD: yourpassword # Replace with your MySQL root password
      MYSQL_DATABASE: gestionlibraries
    volumes:
      - db_data:/var/lib/mysql

  app:
    depends_on:
      - db
    build:
      context: .
      dockerfile: Dockerfile
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://db:3306/gestionLibraries?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: yourpassword # Replace with your MySQL root password

volumes:
  db_data:
```

---

### Running the Project with Docker

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/gestionlibrary.git
   cd gestionlibrary
   ```

2. Build and run the Docker containers:
   ```bash
   docker-compose up --build
   ```

3. Access the application in your browser at:
   ```
   http://localhost:8080
   ```

4. Use MySQL at:
   ```
   localhost:3306
   ```

---

## Screenshots

### Docker Desktop Configuration
![dockerHub](https://github.com/user-attachments/assets/23aa4f82-d439-4bcb-a73c-cc4f71d35bcb)

### Dockerfile Configuration in IDE
![dockerFile](https://github.com/user-attachments/assets/80375b6b-718a-4a7c-b1df-59bdb13cc8d6)


### Docker-Compose Configuration in IDE
![docker-compose](https://github.com/user-attachments/assets/fb62f4a6-04ff-499d-bc1a-cd7353b70161)


### Web Application Interface

![capture  de inteface ](https://github.com/user-attachments/assets/2ed9bc06-33ea-4d83-9fd5-3356b6461a76)

---

## Next Steps

1. **Jenkins Integration**: Automate the build and deployment pipeline.
2. **Kubernetes Deployment**: Ensure scalability using Kubernetes clusters.
3. **Enhanced Features**: Add advanced book search and filtering capabilities.

---

## Contribution

Feel free to fork this repository and submit pull requests. Contributions are welcome!

---

## License

This project is licensed under the [MIT License](LICENSE).


