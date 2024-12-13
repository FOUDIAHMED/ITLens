# SurveyIT - IT Survey Management API

## Table of Contents
1. [Description](#description)
2. [Features](#features)
3. [Technologies Used](#technologies-used)
4. [Project Structure](#project-structure)
5. [Setup and Installation](#setup-and-installation)
6. [API Endpoints](#api-endpoints)
7. [Data Model](#data-model)
8. [Configuration](#configuration)
9. [Testing](#testing)
10. [Documentation](#documentation)
11. [Deployment](#deployment)
12. [Contributing](#contributing)
13. [License](#license)

## Description
SurveyIT is a Spring Boot-based RESTful API application designed for creating and managing IT-focused surveys. The application allows for the creation of structured surveys with chapters and sub-chapters, collection of user responses, and generation of statistical results.

## Features
1. Survey Creation and Management
   - Create surveys with multiple chapters and sub-chapters
   - Add various question types (single choice, multiple choice)
2. Survey Participation
   - Allow users to participate in open surveys
   - Collect and store user responses
3. Results Analysis
   - Generate statistics and analysis of survey results
   - Provide results in the form of numbers and percentages

## Technologies Used
- Spring Boot
- Spring Data JPA
- Spring Web
- PostgreSQL
- Lombok
- MapStruct
- JUnit 5 and Mockito
- Swagger for API documentation

## Setup and Installation
1. Clone the repository
2. Ensure you have Java 11+ and Maven installed
3. Set up PostgreSQL database
4. Configure the application.yml file with your database credentials
5. Run `mvn clean install` to build the project
6. Run `mvn spring-boot:run` to start the application

## API Endpoints
- `POST /surveys`: Create a new survey
- `POST /surveys/{surveyId}/chapters`: Add a chapter to a survey
- `POST /chapters/{chapterId}/subchapters`: Add a sub-chapter to a chapter
- `POST /subchapters/{subChapterId}/questions`: Add a question to a sub-chapter
- `POST /surveys/{surveyId}/participate`: Record user responses to a survey
- `GET /surveys/{surveyId}/results`: Get survey results

For a complete list of endpoints and request/response formats, please refer to the Swagger documentation.

## Data Model
- Survey
- SurveyEdition
- Chapter
- SubChapter
- Question
- Answer
- Owner

For detailed entity relationships and attributes, please refer to the entity classes in the source code.

## Configuration
The application uses YAML configuration file:
- `application.yml`: Contains database configuration, server port, and other application-specific settings

## Testing
- Unit tests: `mvn test`
- Integration tests: Implement using @SpringBootTest annotation

## Documentation
API documentation is automatically generated using Swagger. When the application is running, you can access the Swagger UI at `http://localhost:8080/swagger-ui.html`.

## Deployment
1. Build the application: `mvn clean package`
2. The resulting JAR file can be found in the `target/` directory
3. Run the JAR file: `java -jar target/surveyit-0.0.1-SNAPSHOT.jar`


