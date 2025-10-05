# Project: Java Google Scholar API Client

## Project Description

This project is a console application developed in Java that acts as a client for the Google Scholar API (via SerpApi). The application allows users to search for researcher profiles by name, get a list of potential matches with their unique IDs, and then use a specific ID to retrieve detailed profile information, including affiliation, email, and total citation count.

The development follows the **Model-View-Controller (MVC)** design pattern to ensure a clear separation of concerns and maintainable code.

## How It Works (Simple Flow)

The application works in a simple, step-by-step process:

```
[You] -> Enter an Author's Name -> [The Program]
   ^                                     |
   |                                     v
   |      [The Program] <- Shows a List of Authors <- [API]
   |           |
   |           v
   +-- Enter an Author's ID
   |           |
   |           v
   +---- Shows the Detailed Profile & Articles
   |           |
   |           v
   +---- Asks to Save -> [Database]
```

## Key Features

- **Two-Step Search:**
    1. Search by name to list authors and their IDs.
    2. Search by ID to get a detailed profile.
- **REST API Consumption:** Makes GET requests to the SerpApi to retrieve data from Google Scholar.
- **Database Integration:** Saves author articles to a MySQL database with user confirmation.
- **JSON Mapping:** Uses the Gson library to automatically convert JSON responses into Java objects.
- **Error Handling:** Validates API responses and handles potential connection or API errors.

## Technology Stack

- **Language:** Java 17
- **Dependency Manager:** Apache Maven
- **Core Libraries:**
    - **Apache HttpClient:** for making GET requests to the API.
    - **Google Gson:** for JSON serialization and deserialization.
    - **MySQL Connector/J:** for database connectivity.
- **External API:** SerpApi (Google Scholar API)
- **Database:** MySQL 8.0
- **Development Tools:**
    - **IDE:** IntelliJ IDEA
    - **Database Client:** MySQL Workbench

## How to Run the Project

### Prerequisites

- Java JDK 17 or higher installed.
- Apache Maven installed.
- MySQL Server 8.0 running.
- An API key from [SerpApi](https://serpapi.com/).

### Steps

1. **Clone the repository:**
   ```bash
   git clone [YOUR-REPOSITORY-URL]
   cd [PROJECT-NAME]
   ```

2. **Database Setup:**
   Run the SQL script provided in the technical report to create the `scholar_db` database and the `articles` table.

3. **Configure Credentials:**
    - In `AuthorController.java`, set your SerpApi key.
    - In `DatabaseManager.java`, set your MySQL username and password.

4. **Compile and Run:**
   ```bash
   mvn compile
   mvn exec:java -Dexec.mainClass="com.scholarservice.Main"
   ```