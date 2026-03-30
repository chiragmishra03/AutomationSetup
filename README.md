# Selenium TestNG Parallel Automation Setup

This is a simple Selenium WebDriver setup using TestNG for parallel test execution. It's designed for easy understanding and interview preparation. The setup uses ThreadLocal for thread-safe browser management.

## Prerequisites
- Java 17 or higher (set in pom.xml as 25, but adjust if needed)
- Maven 3.x
- Chrome browser installed
- IDE like IntelliJ IDEA or Eclipse

## Project Structure
```
AutomationSetupPOM/
├── pom.xml (Maven dependencies and plugins)
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── Setup/
│   │   │       ├── BaseTest.java (Base class with ThreadLocal driver)
│   │   │       ├── ConfigReader.java (Reads config.properties)
│   │   │       └── DriverFactory.java (Creates WebDriver instances)
│   │   └── resources/
│   │       └── Configuration.properties (Browser and URL settings)
│   └── test/
│       └── java/
│           └── TestClass.java (Sample test class)
```

## What to Create
1. **pom.xml**: Add dependencies for Selenium, WebDriverManager, TestNG, and SLF4J. Include Maven Compiler and Surefire plugins.
2. **BaseTest.java**: Base class with ThreadLocal<WebDriver> for thread safety. Includes setup() and tearDown() methods.
3. **DriverFactory.java**: Factory to create WebDriver for Chrome, Firefox, or Edge.
4. **ConfigReader.java**: Reads properties from Configuration.properties.
5. **Configuration.properties**: Set browser (e.g., chrome) and URL (e.g., https://www.google.com).
6. **TestClass.java**: Extends BaseTest, has parallel test methods using @Test(invocationCount=3, threadPoolSize=3).

## How to Set Up
1. Create a new Maven project: `mvn archetype:generate -DgroupId=org.example -DartifactId=AutomationSetupPOM -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false`
2. Add the folders: src/main/java/Setup, src/main/resources, src/test/java
3. Copy the code for each class as above.
4. Update pom.xml with dependencies and plugins.
5. Create Configuration.properties in src/main/resources.

## Key Components Explanation
- **ThreadLocal<WebDriver>**: Each thread gets its own driver instance, avoiding conflicts in parallel runs.
- **@BeforeMethod setup()**: Initializes driver and navigates to URL before each test.
- **@AfterMethod tearDown()**: Quits driver and removes from ThreadLocal after each test.
- **Parallel Execution**: TestNG runs multiple invocations in parallel threads.
- **WebDriverManager**: Automatically manages driver binaries.

## How to Run Tests
1. Open terminal in project root.
2. Run `mvn clean install` (downloads dependencies and runs tests).
3. Or `mvn test` for just tests.
4. Tests will open browsers in parallel and navigate to the URL.

## Parallel Execution Details
- Uses TestNG's threadPoolSize and invocationCount for parallel runs.
- ThreadLocal ensures no driver sharing between threads.
- Suitable for CI/CD pipelines like Jenkins or GitHub Actions.
- For distributed (multi-machine), integrate Selenium Grid.

## Interview Preparation Points
- Explain ThreadLocal: "ThreadLocal stores data per thread, so each parallel test has its own WebDriver."
- Why parallel: "Speeds up test execution, simulates real users."
- Setup flow: "BaseTest sets up driver, TestClass runs tests, tearDown cleans up."
- Challenges: "Handle thread safety, browser compatibility, CI integration."
- Improvements: "Add headless mode, reporting (ExtentReports), data providers."

This setup is minimal, easy to explain, and demonstrates key Selenium concepts. Practice running it and explaining each part!</content>
<parameter name="filePath">/Users/chiragmishra/Desktop/AutomationSetupPOM/README.md
