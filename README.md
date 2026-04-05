# Spring Boot REST API Exercise

This is an **Orphan Exercise branch** from the [Java Fullstack Course - EBAC](https://ebaconline.com.br/full-stack-java), focused on Spring Boot fundamentals and REST API development.

## 🛠️ Technology Stack

- **Java Version:** 17
- **Spring Boot:** 3.5.10 (latest stable)
- **Build Tool:** Maven
- **Database:** PostgreSQL
- **Persistence:** Spring Data JPA (Hibernate ORM)
- **Utilities:** Project Lombok
- **Monitoring:** Spring Boot Actuator
- **Development:** Spring Boot DevTools

## 📦 Project Structure

```
SpringProject/
├── src/
│   ├── main/
│   │   ├── java/                    # Java source code
│   │   │   └── br/com/j_fborges/   # Base package
│   │   └── resources/               # Configuration files (application.properties, etc.)
│   └── test/
│       └── java/                    # Unit and integration tests
├── pom.xml                          # Maven configuration
├── mvnw / mvnw.cmd                  # Maven wrapper scripts (cross-platform)
├── .mvn/                            # Maven wrapper configuration
├── .gitignore                       # Git ignore rules
└── .gitattributes                   # Git attribute configuration
```

## 🚀 Quick Start

### Prerequisites
- Java 17 or higher
- Maven 3.6+ (or use the included `mvnw` wrapper)
- PostgreSQL database running

### Setup Instructions

1. **Clone the branch:**
   ```bash
   git clone https://github.com/j-fborges/java_fullstack_course_ebac.git
   cd java_fullstack_course_ebac
   git checkout java_springboot
   ```

2. **Configure database connection:**
   - Edit `src/main/resources/application.properties` (or create it if it doesn't exist)
   - Add PostgreSQL configuration:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   ```

3. **Build the project:**
   ```bash
   ./mvnw clean install
   ```
   Or on Windows:
   ```bash
   mvnw.cmd clean install
   ```

4. **Run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```

5. **Access the application:**
   - Application runs on: `http://localhost:8080`
   - Health check: `http://localhost:8080/actuator/health`
   - Metrics: `http://localhost:8080/actuator/metrics`
