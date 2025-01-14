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

### Kubernetes Deployment

The project includes Kubernetes configurations to deploy the application and MySQL database. Kubernetes YAML files are stored in the `k8s` directory.

#### Deployment Files

- **Application Deployment**: Handles the deployment of the Spring Boot application.
  ```yaml
  apiVersion: apps/v1
  kind: Deployment
  metadata:
    name: app-deployment
  spec:
    replicas: 2
    selector:
      matchLabels:
        app: gestionlibrary
    template:
      metadata:
        labels:
          app: gestionlibrary
      spec:
        containers:
        - name: gestionlibrary-app
          image: your-dockerhub-repo/gestionlibrary:latest
          ports:
          - containerPort: 8080
  ```

- **MySQL Deployment**: Configures the MySQL database.
  ```yaml
  apiVersion: apps/v1
  kind: Deployment
  metadata:
    name: mysql-deployment
  spec:
    replicas: 1
    selector:
      matchLabels:
        app: mysql
    template:
      metadata:
        labels:
          app: mysql
      spec:
        containers:
        - name: mysql
          image: mysql:8.0
          env:
          - name: MYSQL_ROOT_PASSWORD
            value: yourpassword
          - name: MYSQL_DATABASE
            value: gestionlibraries
          ports:
          - containerPort: 3306
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
![dockerHub](https://github.com/user-attachments/assets/6975669b-de86-4c1a-b6ff-e4e95d04311e)

### Dockerfile Configuration in IDE
![dockerFile](https://github.com/user-attachments/assets/e5080ddd-f560-4dea-9054-aa006deadb65)

### Docker-Compose Configuration in IDE
![docker-compose](https://github.com/user-attachments/assets/dd51354c-64b4-4fe0-8119-03285200709e)

### Web Application Interface
![capture de inteface ](https://github.com/user-attachments/assets/1be94230-514d-445c-999f-3dd0ff46bba3)

---

## Error Handling

### kubectl get pods Error

While running the command:
```bash
kubectl get pods
```

The following error was encountered:
![kubectl_error](https://github.com/user-attachments/assets/da5e17bc-b857-4252-a482-53cb23b68fbb)

#### Solution
- Ensure the Kubernetes cluster is properly configured.
- Verify the context and namespace with:
  ```bash
  kubectl config get-contexts
  kubectl config set-context <context-name>
  ```
- Check pod statuses with:
  ```bash
  kubectl describe pod <pod-name>
  ```

### Docker Hub Images
Below is the Docker Hub repository used for the project:
![Capture d'écran 2025-01-14 222050](https://github.com/user-attachments/assets/038ef6b1-5130-4fe8-a4bb-a7e21bba3e94)

---

## Next Steps

1. **Jenkins Integration**
   - Automate the build and deployment pipeline using Jenkins.
   - Below is a sample configuration for Jenkins setup:
![Capture d'écran 2025-01-14 223328](https://github.com/user-attachments/assets/1d62e50e-472f-40a5-be30-8aa356ce3a00)


---

## Contribution

Feel free to fork this repository and submit pull requests. Contributions are welcome!

---

This project is licensed under the [MIT License](LICENSE).
