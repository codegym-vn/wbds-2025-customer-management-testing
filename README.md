# Customer Management System 2025

A modern Customer Management System built with the latest Spring Framework 6.x, Hibernate 6.x, and Java 17+ features.

## 🚀 Technology Stack

### Backend
- **Java 17** - Latest LTS version with modern language features
- **Spring Framework 6.1.3** - Latest Spring Web MVC
- **Spring Data JPA 3.2.1** - Modern data access layer
- **Hibernate 6.4.1** - Latest ORM with Jakarta EE support
- **Thymeleaf 3.1.2** - Server-side templating

### Database
- **MySQL 8.0.33** - Latest MySQL with performance improvements
- **HikariCP 5.1.0** - High-performance JDBC connection pooling

### Testing
- **JUnit 5.10.2** - Modern testing framework
- **Mockito 5.8.0** - Latest mocking framework
- **Spring Test 6.1.3** - Spring testing support

### Build & Development
- **Gradle 8.x** - Modern build automation
- **Jakarta EE** - Migration from javax to jakarta namespace

## 📋 Features

- ✅ **CRUD Operations** - Complete customer management
- ✅ **Search & Pagination** - Advanced search with pagination support
- ✅ **REST API** - RESTful web services
- ✅ **Validation** - Jakarta Bean Validation
- ✅ **Exception Handling** - Global exception handler
- ✅ **DTO Pattern** - Data Transfer Objects for API layer
- ✅ **Modern Testing** - Comprehensive unit testing
- ✅ **Connection Pooling** - HikariCP for database connections

## 🛠️ Setup & Installation

### Prerequisites
- Java 17 or higher
- MySQL 8.0+
- Gradle 8.x

### Database Setup
```sql
CREATE DATABASE cms;
CREATE USER 'cms_user'@'localhost' IDENTIFIED BY '123456';
GRANT ALL PRIVILEGES ON cms.* TO 'cms_user'@'localhost';
FLUSH PRIVILEGES;
```

### Build & Run
```bash
# Clone the repository
git clone <repository-url>
cd customer-management-system

# Build the project
./gradlew build

# Run tests
./gradlew test

# Deploy to server (creates WAR file)
./gradlew war
```

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/codegym/
│   │   ├── controller/          # REST controllers
│   │   ├── model/              # JPA entities
│   │   ├── repository/         # Data access layer
│   │   ├── service/            # Business logic
│   │   ├── dto/               # Data Transfer Objects
│   │   ├── exception/         # Exception handling
│   │   ├── AppConfig.java     # Spring configuration
│   │   └── AppInit.java       # Web initializer
│   ├── resources/
│   │   └── application.properties
│   └── webapp/
│       └── WEB-INF/templates/  # Thymeleaf templates
└── test/
    └── java/com/codegym/       # Unit tests
```

## 🔧 Configuration

### Database Configuration
Update `src/main/resources/application.properties`:

```properties
jpa.datasource.url=jdbc:mysql://localhost:3306/cms
jpa.datasource.username=your_username
jpa.datasource.password=your_password
```

### Application Properties
- **Connection Pooling**: HikariCP with optimized settings
- **Hibernate**: Configured for MySQL with proper dialect
- **Validation**: Jakarta Bean Validation enabled

## 🧪 Testing

### Run All Tests
```bash
./gradlew test
```

### Test Coverage
The project includes:
- Unit tests for controllers
- Service layer testing
- Repository integration tests
- Mock-based testing with Mockito

## 📊 API Endpoints

### Customer Management
- `GET /customers` - List all customers (with pagination)
- `GET /customers/{id}` - Get customer by ID
- `POST /customers` - Create new customer
- `PUT /customers/{id}` - Update customer
- `DELETE /customers/{id}` - Delete customer

### JSON API
All endpoints support JSON format with proper content negotiation.

## 🔄 Migration Notes from 2023 to 2025

### Major Updates Applied:

1. **Java Version**: Upgraded from Java 8 to Java 17
2. **Spring Framework**: Upgraded from 5.x to 6.1.3
3. **Jakarta EE**: Migrated from javax to jakarta namespace
4. **Hibernate**: Upgraded from 5.x to 6.4.1
5. **Testing**: Modernized with JUnit 5 and latest Mockito
6. **Database**: Added HikariCP connection pooling
7. **Architecture**: Added DTO pattern and global exception handling
8. **Dependencies**: Updated all dependencies to latest stable versions

### Breaking Changes Addressed:
- ✅ javax.* → jakarta.* namespace migration
- ✅ Thymeleaf spring5 → spring6 migration
- ✅ Repository interfaces updated to JpaRepository
- ✅ Test framework modernization
- ✅ Validation annotations updated

## 📚 Best Practices Implemented

- **Dependency Injection**: Constructor-based DI
- **Transaction Management**: Proper @Transactional usage
- **Exception Handling**: Global exception handler with proper error responses
- **Testing**: Modern testing practices with JUnit 5
- **Code Organization**: Clean architecture with proper separation of concerns
- **Performance**: Connection pooling and optimized database queries

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

---

**Note**: This project has been modernized to follow 2025 best practices and uses the latest stable versions of all frameworks and libraries.
