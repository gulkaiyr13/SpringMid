Store Project
A Java Spring Boot project that implements an application for managing orders, users, and products. This README provides an overview of the project structure and usage instructions.

-!VIDEO https://youtu.be/3X29qzSddb0 я удалила проект после того как загрузила в гит, потом вы сказали, что надо видео записать, я скачала и там некоторые файлы горят красным, так как нет в гиде, хотя я не связывала, в общем автоматически так получилось.

Project Structure
The project is structured as follows:

entities: Contains the entity classes representing the database tables.

Order.java: Entity class representing an order.
Product.java: Entity class representing a product.
User.java: Entity class representing a user.
dtos: Contains Data Transfer Object (DTO) classes.

Includes request and response DTOs used for communication between the client and server.
mappers: Contains classes responsible for mapping between entities and DTOs.

OrderMapper.java: Mapper interface for mapping order entities to DTOs and vice versa.
repositories: Contains repository interfaces for interacting with the database.

Repository interfaces for entities, such as OrderRepository, ProductRepository, and UserRepository.
controllers: Contains REST controllers for handling HTTP requests.

Controllers for orders, products, and users.
services: Contains service classes for business logic.

OrderService.java: Service interface for managing orders.
Implementation of OrderService, ProductService, and UserService.
tests: Contains unit tests for testing various components of the application.

Test classes for entities, controllers, services, and mappers.
Usage
To use the Store project, follow these steps:

1) Clone the repository to your local machine:

git clone https://github.com/gulkaiyr13/Store.git

2)Set up your database configuration in application.properties.

3)Build the project using Maven:

mvn clean package

4) Run the application:

java -jar target/Store.jar

5) Access the application through the provided endpoints.

Dependencies
The project uses the following dependencies:

Spring Boot
Spring Data JPA
Lombok
Ensure that these dependencies are installed and configured properly in your environment.

Contributing
Contributions to the Store project are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request on GitHub.
