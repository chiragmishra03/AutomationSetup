package Setup;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private ConfigReader configReader;
    private DriverFactory driverFactory;
    private ExtentReports extent;
    private ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();



    public void setDriver(WebDriver driverInstance){
        driver.set(driverInstance);
    }

    public static WebDriver getDriver(){
        return driver.get();
    }

    @BeforeMethod
    public void setup(){
        extentTest.set(extent.createTest("Test Execution"));
        configReader = new ConfigReader();
        driverFactory = new DriverFactory();

        String browser = configReader.getProperty("browser");
        String url = configReader.getProperty("url");

        WebDriver webDriver = driverFactory.createDriver(browser);
        setDriver(webDriver);
        getDriver().get(url);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());
            String screenshotPath = Utilities.Utility.takeScreenShot();
            extentTest.get().addScreenCaptureFromPath(screenshotPath);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.get().log(Status.PASS, "Test Passed");
        }
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
        }
        this.driver.remove();
    }

    @BeforeSuite
    public void setUpReport() {
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/Report"+System.currentTimeMillis()+".html");
        extent.attachReporter(spark);
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
    }

    public void logStep(String message) {
        extentTest.get().log(Status.INFO, message);
    }
}
