# Spring Boot Default Template Project

>This is a basic Spring Boot template project that you can use as a starting point for your own application.

## Getting Started
1. To get started with this project, follow these steps:
git clone
```bash
https://github.com/your-username/spring-boot-default-template.git
```
2. Open the project in your favorite IDE.
3. Build and run the project using the IDE or the following command:
```
./gradlew clean
```
```
./gradlew bootRun --stacktrace
```
4. Open your web browser and navigate to ```http://localhost:8080/swagger-ui/index.html```

## Project Structure
>This project follows the Domain Driven Design structure:
```struct
    └── andrew.backend.app
        ├── domain
        │   ├── admin
        │   │   ├── blame
        │   │   ├── onboarding
        │   │   └── term        
        │   ├── common
        │   │   └── file
        │   └── main
        │       └── account
        └── global
            ├── configuration
            ├── entity
            ├── exceiption
            ├── response
            ├── security
            └── util

```
    
## The Domain package has the following sub-packages
```
controller: controller package
model.dto: DTO model package
model.entity: entity model package
model.enums: Enumeration Model Package
model.repository: reference package
service: service package
service.impl: service implementation package
```
