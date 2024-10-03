# Demo4 Application

## Overview

Demo4 Application is a Spring Boot project integrated with Kubernetes, using ConfigMap and custom health checks to manage configurations dynamically. This project includes deployment configurations for Kubernetes and Docker, allowing easy deployment and configuration.

## Project Structure

```text
demo4/
├── .gitignore
├── deployment.yml                  # Kubernetes Deployment configuration
├── Dockerfile                      # Dockerfile for containerizing the application
├── env-configmap.yml               # ConfigMap for storing environment variables
├── HELP.md
├── mvnw, mvnw.cmd                  # Maven wrapper scripts
├── pom.xml                         # Maven project descriptor
├── role-binding.yml                # RoleBinding configuration for Kubernetes RBAC
├── role.yml                        # Role configuration for Kubernetes RBAC
├── service-account.yml             # ServiceAccount configuration for Kubernetes
├── service.yml                     # Kubernetes Service configuration
├── src/
│   ├── main/
│   │   ├── java/com/example/demo/
│   │   │   ├── Demo4Application.java         # Main application class
│   │   │   ├── FileController.java           # REST controller for handling file operations
│   │   │   └── KubernetesClientConfig.java   # Kubernetes client configuration
│   │   └── resources/
│   │       └── application.yml               # Spring Boot configuration file
│   └── test/java/com/example/demo/
│       └── Demo4ApplicationTests.java        # Unit tests for the application
```

## Requirements
- Java 21
- Docker
- Kubernetes
- Maven
- Build and Run
- Using Maven
- Build the Application

Run the following command to build the application:


```
./mvnw clean package
Run the Application
```
To run the application locally:

```
java -jar target/demo4-0.0.1-SNAPSHOT.jar
```

# Using Docker

## Build the Docker Image

Build the Docker image using the provided Dockerfile:

```
docker build -t my-app:9.9.18 .
```

##Run the Docker Container

```
docker run -p 8078:8078 my-app:9.9.18
```

## Kubernetes Deployment
Create ConfigMap, Roles, and Service Account

Apply the following Kubernetes YAML files to set up ConfigMap, RBAC, and ServiceAccount:

```
kubectl apply -f env-configmap.yml
kubectl apply -f role.yml
kubectl apply -f role-binding.yml
kubectl apply -f service-account.yml
```
## Deploy the Application

Deploy the application using the deployment.yml:

```
kubectl apply -f deployment.yml
```
Service Setup

Set up the Kubernetes service to expose the application:

```
kubectl apply -f service.yml
```

## Key Components
### Kubernetes Integration
ConfigMap (env-configmap.yml): Used to manage environment variables dynamically.
Role and RoleBinding: Configured to allow the application to access and update the ConfigMap.
ServiceAccount (service-account.yml): Provides the necessary permissions for the application to interact with the Kubernetes API.
### Health Check and Auto-Restart
The deployment includes health checks using livenessProbe and readinessProbe to ensure that the application is always healthy. If it fails, Kubernetes will automatically restart the pod.
### REST API
FileController (FileController.java): Provides endpoints to manage file uploads and configurations.
KubernetesClientConfig (KubernetesClientConfig.java): Handles the configuration for Kubernetes API access.
### Configuration
application.yml: Configures the application, including Spring Boot settings, database connections, and other environment variables.
Environment Variables: Can be updated dynamically using Kubernetes ConfigMap.
# Usage
The application exposes an endpoint /api/update-config for updating ConfigMap values. This can be used to dynamically adjust configuration at runtime.
Health checks are available at /actuator/health to monitor the application's status.

# License

This project is licensed under the MIT License.