# Selenium TestNG Framework

A UI test automation framework built with **Selenium WebDriver** and **TestNG**, targeting the [TutorialsNinja](https://tutorialsninja.com/demo/) e-commerce demo application. The framework follows the **Page Object Model (POM)** design pattern and supports **parallel execution**, **data-driven testing**, and **automated HTML reporting via ExtentReports**.

---

## Tech Stack

| Tool / Library       | Purpose                                              |
|----------------------|------------------------------------------------------|
| Java                 | Core programming language                            |
| Selenium WebDriver   | Browser automation (Chrome & Firefox)                |
| TestNG               | Test runner, parameterization, and parallel execution|
| Maven                | Build tool and dependency management                 |
| ExtentReports (Spark)| HTML test execution report with screenshots          |
| Java Faker           | Random test data generation (thread-safe)            |
| Jackson              | JSON deserialization into POJO                       |
| Apache POI           | Excel read/write for test data                       |
| OpenCSV              | CSV test data reading                                |
| Lombok               | Boilerplate reduction on POJO                        |

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

### Thread-Safe Parallel Execution
`BaseTest` stores the `WebDriver` instance in a `ThreadLocal`, and `TestDataHelper` stores `Faker` in a separate `ThreadLocal`. This allows TestNG to run tests in parallel without any shared state between threads.

### Data-Driven Testing
Three data sources are supported interchangeably:

| Source      | Reader Class    | Usage in Tests                        |
|-------------|-----------------|---------------------------------------|
| JSON        | `JsonUtils`     | `LoginTests` reads credentials at index 0 |
| Excel       | `ExcelUtils`    | `RegisterTests` writes each new registration back to the sheet |
| CSV         | `CsvUtils`      | Available for use; reads rows by index |

### Random Data Generation
`TestDataHelper` wraps Java Faker in a `ThreadLocal` and exposes named generators: `generateRandomFirstName()`, `generateRandomEmail()`, `generateRandomPassword()`, etc. `TestDataGenerator` composes these into a ready-to-use `RegistrationForm` object.

### ExtentReports with Screenshots
`MyListeners` implements `ITestListener` and hooks into the full TestNG lifecycle:
- **onStart** — initialises `ExtentSparkReporter` and sets the report title
- **onTestStart** — creates a new `ExtentTest` node
- **onTestSuccess** — logs a PASS status
- **onTestFailure** — captures a screenshot via `ScreenshotHelper`, attaches it to the report, and logs the exception
- **onTestSkipped** — logs a SKIP with the throwable
- **onFinish** — flushes the report and automatically opens it in the default browser

Report output: `test-output/ExtentReports/extentReport.html`
Screenshots output: `screenshots/`

### Cross-Browser Support
`BaseTest.startDriver()` accepts a `browser` parameter from `testng.xml`. Supported values: `chrome` (default), `firefox`.

---

## Prerequisites

- **Java JDK 11+**
- **Maven 3.6+**
- **Google Chrome** and/or **Mozilla Firefox** installed
- No manual driver setup needed if **WebDriverManager** is configured in `pom.xml`; otherwise place the matching driver on your system `PATH`

---

## Setup

```bash
git clone https://github.com/your-username/Selenium_TestNG_Framework.git
cd Selenium_TestNG_Framework
mvn clean install -DskipTests
```

---

## Running Tests

### Run all tests (default browser: Chrome)
```bash
mvn test
```

### Run with a specific browser
```bash
mvn test -Dbrowser=firefox
```

### Run via TestNG suite file
```bash
mvn test -DsuiteXmlFile=testng.xml
```

### Run a specific test class
```bash
mvn test -Dtest=LoginTests
mvn test -Dtest=RegisterTests
```

---

## Test Data Setup

Before running `LoginTests`, make sure `UserData.json` contains at least one entry at index `0` with valid credentials for the TutorialsNinja demo:

```json
[
  {
    "FirstName": "John",
    "LastName": "Doe",
    "Email": "john.doe@example.com",
    "Telephone": "0123456789",
    "Password": "Test@1234"
  }
]
```

`RegisterTests` uses the `validRegistrationData` DataProvider which generates fully random data via Faker — no manual setup required. After each successful registration, the user's data is appended to `UserData.xlsx` automatically.

---

## Key Design Decisions

**`writeToExcel` is `synchronized`** — because `RegisterTests` runs in parallel (via `parallel = true` on the DataProvider), concurrent writes to the same `.xlsx` file are serialised at the method level to prevent file corruption.

**`ThreadLocal<Faker>` in `TestDataHelper`** — parallel test threads each get their own `Faker` instance. The `unload()` method is available to remove the instance after a thread is done, preventing memory leaks in long-running test suites.

**`@Optional("chrome")` on `startDriver`** — the browser parameter is optional, so tests can run without any `testng.xml` configuration by defaulting to Chrome.
