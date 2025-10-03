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
   +---- Shows the Detailed Profile
```

## Key Features

- **Two-Step Search:**
    1. Search by name to list authors and their IDs.
    2. Search by ID to get a detailed profile.
- **REST API Consumption:** Makes GET requests to the SerpApi to retrieve data from Google Scholar.
- **JSON Mapping:** Uses the Gson library to automatically convert JSON responses from the API into Java objects.
- **Error Handling:** Validates API responses and handles potential connection or API errors.
- **Console Interface:** An interactive application that guides the user through the search process.

## Technology Stack

- **Language:** Java 17
- **Dependency Manager:** Apache Maven
- **Core Libraries:**
    - **Apache HttpClient:** for making GET requests to the API.
    - **Google Gson:** for JSON serialization and deserialization.
- **External API:** SerpApi (Google Scholar API)
- **IDE:** IntelliJ IDEA

## How to Run the Project

### Prerequisites

- Java JDK 17 or higher installed.
- Apache Maven installed.
- An API key from [SerpApi](https://serpapi.com/).

### Steps

1. **Clone the repository:**
   ```bash
   git clone [YOUR-REPOSITORY-URL]
   cd [PROJECT-NAME]
   ```

2. **Install dependencies:**
   Maven will automatically download the necessary libraries.

3. **Configure the API Key:**
   Ensure your SerpApi key is set in the `apiKey` variable inside the `AuthorController.java` file.

4. **Compile and Run:**
   From the project's root directory, run the `Main` class:
   ```bash
   mvn compile
   mvn exec:java -Dexec.mainClass="com.scholarservice.Main"
   ```

## Usage Example

The console will prompt you to enter an author's name.

```
Enter the author's name to search: yoshua bengio
```

It will then display a list of authors found with their IDs.

```
--- The following authors were found ---
Please copy and paste the ID of the author you are interested in:
ID: vsj2slIAAAAJ | Name: Yoshua Bengio
ID: HFtCH-AAAAAJ | Name: Samy Bengio
-------------------------------------------
```

Finally, you enter the desired ID to see the full profile.

```
Enter the ID of the author to see their details: vsj2slIAAAAJ

--- Author's Detailed Profile ---
Name: Yoshua Bengio
Affiliation: Mila, U. de Montréal, IVADO, CIFAR, GenAI, Scale AI, Recursion
Email: Verified email at umontreal.ca
Total Citations: 805341
-----------------------------------
```