# Selenium TestNG Parallel Automation Setup

This is a simple Selenium WebDriver setup using TestNG for parallel test execution. It's designed for easy understanding and interview preparation. The setup uses ThreadLocal for thread-safe browser management, screenshot utility, and Extent Reports for better results.

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
│   │   │   ├── Setup/
│   │   │   │   ├── BaseTest.java (Base class with ThreadLocal driver and Extent Reports)
│   │   │   │   ├── ConfigReader.java (Reads config.properties)
│   │   │   │   └── DriverFactory.java (Creates WebDriver instances)
│   │   │   └── Utilities/
│   │   │       └── Utility.java (Screenshot utility)
│   │   └── resources/
│   │       └── Configuration.properties (Browser and URL settings)
│   └── test/
│       └── java/
│           └── TestClass.java (Sample test class)
├── screenshots/ (Auto-created for failure screenshots)
├── target/
│   └── ExtentReport.html (Generated test report)
```

## What to Create
1. **pom.xml**: Add dependencies for Selenium, WebDriverManager, TestNG, SLF4J, ExtentReports. Include Maven Compiler and Surefire plugins.
2. **BaseTest.java**: Base class with ThreadLocal<WebDriver> for thread safety, Extent Reports setup/teardown, and logging.
3. **DriverFactory.java**: Factory to create WebDriver for Chrome, Firefox, or Edge.
4. **ConfigReader.java**: Reads properties from Configuration.properties.
5. **Configuration.properties**: Set browser (e.g., chrome) and URL (e.g., https://www.google.com).
6. **Utility.java**: Static methods for screenshots and URL navigation.
7. **TestClass.java**: Extends BaseTest, has parallel test methods.

## Key Components Explanation
- **ThreadLocal<WebDriver>**: Each thread gets its own driver instance, avoiding conflicts in parallel runs.
- **@BeforeMethod setup()**: Initializes driver, ExtentTest, and navigates to URL.
- **@AfterMethod tearDown()**: Logs pass/fail, attaches screenshots on failure, quits driver, removes from ThreadLocal.
- **Extent Reports**: Generates HTML report in reports/ExtentReport.html with test results, steps, and screenshots.
- **Screenshot Utility**: Takes screenshot on failure and saves to screenshots/ folder.
- **Logging Steps**: Use `logStep("message")` in test methods to log info in the report.
- **Parallel Execution**: TestNG runs multiple invocations in parallel threads.

## How to Run Tests
1. Open terminal in project root.
2. Run `mvn clean install` (downloads dependencies and runs tests).
3. Or `mvn test` for just tests.
4. Tests will open browsers in parallel and generate ExtentReport.html.
5. Check screenshots/ for failure images.

## Parallel Execution Details
- Uses TestNG's threadPoolSize and invocationCount for parallel runs.
- ThreadLocal ensures no driver sharing between threads.
- Extent Reports handle thread-safe logging.
- Suitable for CI/CD pipelines like Jenkins or GitHub Actions.

## Interview Preparation Points
- Explain ThreadLocal: "ThreadLocal stores data per thread, so each parallel test has its own WebDriver."
- Why parallel: "Speeds up test execution, simulates real users."
- Extent Reports: "Generates detailed HTML reports with screenshots for better debugging."
- Screenshot on failure: "Helps identify issues quickly in CI."
- Setup flow: "BaseTest sets up driver and report, TestClass runs tests, tearDown logs and cleans up."
- Challenges: "Handle thread safety, browser compatibility, CI integration."

This setup is minimal, easy to explain, and demonstrates key Selenium concepts with reporting. Practice running it and explaining each part!
