# Velocity_template
# Dynamic Page Generator (Java, Velocity, XML)

This project dynamically generates HTML pages based on an XML configuration using Java and Apache Velocity templates.

## Features

- Reads form field definitions from `application.xml`
- Supports text fields, radio buttons, and message displays
- Renders each form step as an HTML file using Velocity templates
- Collects user input via the console

## Technologies

- Java
- Apache Velocity
- Gradle
- XML

## Getting Started

### Prerequisites

- Java 11+
- Gradle

### Build & Run

1. Clone the repository:
    ```
    git clone <your-repo-url>
    cd <project-directory>
    ```

2. Build the project:
    ```
    ./gradlew build
    ```

3. Run the application:
    ```
    ./gradlew run
    ```

### Project Structure

- `src/main/java/org/example/Main.java` - Main application logic
- `src/main/resources/application.xml` - Form configuration
- `src/main/resources/templates/form_step.vm` - Velocity template for form steps
