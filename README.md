# Emotional Songs Backend

The Emotional Songs Backend is a Spring application that provides APIs to manage a collection of songs and their associated emotions. It is part of a university project along with the [Emotional Songs Frontend](https://github.com/NNazem/EmotionalSongs-front).

## Key Features

- Retrieve songs from a database
- Creating playlists
- Assign emotions, and their associated rate, to songs
- Search songs by title or author and year
- User account creation and authentication

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- Lombok
- PostgreSQL
- Maven

## Getting Started

1. Clone this repository: `git clone https://github.com/yourusername/emotionalSongsBackend.git`
2. Ensure you have Java and Maven installed
3. Set up the database credentials in `src/main/resources/application.properties`
4. Run the application: `mvn spring-boot:run`
5. The application will be available at `http://localhost:8080`

## Database Configuration

To run the Emotional Songs Backend, you need to configure the database connection in the `application.properties` file located at:
EmotionalSongs-server/src/main/resources/application.properties

Update the following properties with your database credentials:
spring.datasource.url=your_database
spring.datasource.username=your_username
spring.datasource.password=your_password

## License

This project is released under the MIT License. See the [LICENSE](LICENSE) file for details.
