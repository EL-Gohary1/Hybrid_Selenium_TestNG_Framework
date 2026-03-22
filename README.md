# Selenium TestNG Framework

A scalable UI test automation framework built with **Selenium WebDriver** and **TestNG**, following the **Page Object Model (POM)** design pattern. The framework targets the [TutorialsNinja](https://tutorialsninja.com/demo/) e-commerce demo application and supports multiple data-driven testing strategies.

---

## Table of Contents

- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Setup & Installation](#setup--installation)
- [Running Tests](#running-tests)
- [Test Data](#test-data)
- [Framework Components](#framework-components)
- [Reporting](#reporting)

---

## Tech Stack

| Tool / Library        | Purpose                                      |
|-----------------------|----------------------------------------------|
| Java                  | Core programming language                    |
| Selenium WebDriver    | Browser automation                           |
| TestNG                | Test runner and assertions                   |
| Maven                 | Build tool and dependency management         |
| Apache POI            | Reading test data from Excel (`.xlsx`)       |
| Jackson               | Reading test data from JSON                  |
| OpenCSV               | Reading test data from CSV                   |
| TestNG DataProvider   | Parameterized / data-driven test execution   |
| TestNG Listeners      | Custom event handling and reporting hooks    |

---

## Project Structure

```
Selenium_TestNG_Framework/
├── pom.xml                                         # Maven build configuration & dependencies
├── testng.xml                                      # TestNG suite configuration
└── src/
    ├── main/
    │   ├── java/com/tutorialsninja/
    │   │   ├── base/
    │   │   │   └── BasePage.java                   # Shared WebDriver utility methods
    │   │   ├── pages/
    │   │   │   ├── HomePage.java                   # Home page object
    │   │   │   ├── LoginPage.java                  # Login page object
    │   │   │   └── RegisterPage.java               # Registration page object
    │   │   ├── pojo/
    │   │   │   └── RegistrationForm.java           # POJO model for registration data
    │   │   └── util/
    │   │       ├── CsvUtils.java                   # CSV data reader
    │   │       ├── ExcelUtils.java                 # Excel (.xlsx) data reader
    │   │       ├── JsonUtils.java                  # JSON data reader
    │   │       ├── ScreenshotHelper.java           # Screenshot capture utility
    │   │       ├── TestDataGenerator.java          # Dynamic test data generation
    │   │       └── TestDataHelper.java             # Unified test data access helper
    │   └── resources/
    └── test/
        ├── java/com/tutorialsninja/
        │   ├── base/
        │   │   └── BaseTest.java                   # WebDriver lifecycle (setup/teardown)
        │   ├── tests/
        │   │   ├── LoginTests.java                 # Login feature test cases
        │   │   └── RegisterTests.java              # Registration feature test cases
        │   ├── dataproviders/
        │   │   └── RegisterDataProvider.java       # TestNG DataProvider for registration
        │   └── listeners/
        │       └── MyListeners.java                # Custom TestNG listener
        └── resources/
            └── testData/
                ├── UserData.csv                    # Test data in CSV format
                ├── UserData.json                   # Test data in JSON format
                └── UserData.xlsx                   # Test data in Excel format
```

---

## Features

- **Page Object Model (POM)** — Clean separation between page interactions and test logic
- **Data-Driven Testing** — Tests consume data from CSV, JSON, and Excel files interchangeably
- **POJO Mapping** — Test data is mapped to Java objects for type-safe access
- **Custom TestNG Listener** — Hooks into test lifecycle events for logging and reporting
- **Screenshot on Failure** — Automatic screenshot capture when a test fails
- **Centralized Driver Management** — `BaseTest.java` handles WebDriver setup and teardown
- **Reusable Page Utilities** — `BasePage.java` exposes common Selenium actions (wait, click, type, etc.)

---

## Prerequisites

- **Java JDK 11+** — [Download](https://adoptium.net/)
- **Maven 3.6+** — [Download](https://maven.apache.org/download.cgi)
- **Google Chrome** (or your target browser) with matching **ChromeDriver**
- An IDE such as **IntelliJ IDEA** or **Eclipse** (optional but recommended)

Verify your setup:

```bash
java -version
mvn -version
```

---

## Setup & Installation

1. **Clone the repository**

   ```bash
   git clone https://github.com/your-username/Selenium_TestNG_Framework.git
   cd Selenium_TestNG_Framework
   ```

2. **Install dependencies**

   ```bash
   mvn clean install -DskipTests
   ```

3. **Configure the browser driver**

   If you are not using WebDriverManager, place the appropriate driver (e.g., `chromedriver`) on your system `PATH`, or update the driver path in `BaseTest.java`.

---

## Running Tests

### Run all tests via Maven

```bash
mvn test
```

### Run a specific TestNG suite

```bash
mvn test -DsuiteXmlFile=testng.xml
```

### Run a specific test class

```bash
mvn test -Dtest=LoginTests
mvn test -Dtest=RegisterTests
```

---

## Test Data

Test data is stored under `src/test/resources/testData/` in three formats. All three files represent the same logical data set, allowing you to switch data sources without changing test logic.

| File              | Format | Reader Class      |
|-------------------|--------|-------------------|
| `UserData.xlsx`   | Excel  | `ExcelUtils.java` |
| `UserData.json`   | JSON   | `JsonUtils.java`  |
| `UserData.csv`    | CSV    | `CsvUtils.java`   |

The `TestDataHelper` class provides a single entry point to load data from any of these sources, and `RegisterDataProvider` feeds the data into TestNG tests via `@DataProvider`.

---

## Framework Components

### `BasePage.java`
Contains reusable Selenium helper methods such as waiting for elements, performing clicks, entering text, and other common interactions shared across all page objects.

### `BaseTest.java`
Manages the WebDriver lifecycle. Annotated `@BeforeMethod` and `@AfterMethod` methods ensure a fresh browser session for every test and proper cleanup afterward.

### `MyListeners.java`
Implements `ITestListener` to hook into TestNG events. On test failure, it triggers the `ScreenshotHelper` to capture and save a screenshot for debugging.

### `RegistrationForm.java`
A POJO that models the registration form fields. Used by the data utilities and data providers to pass strongly-typed test data into tests.

### `TestDataGenerator.java`
Generates dynamic or randomized test data at runtime, useful for tests that require unique values (e.g., unique email addresses during registration).

---

## Reporting

TestNG generates a default HTML report after each test run:

```
target/surefire-reports/index.html
```

Open this file in a browser to view test results, pass/fail counts, and stack traces for failures.

> **Tip:** For richer reports, consider integrating [Allure](https://allurereport.org/) or [ExtentReports](https://www.extentreports.com/) by adding the relevant listener to `testng.xml`.
