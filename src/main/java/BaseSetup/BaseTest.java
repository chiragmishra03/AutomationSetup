package BaseSetup;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private ConfigReader configReader;
    private DriverFactory driverFactory;

    public void setDriver(WebDriver driverInstance){
        driver.set(driverInstance);
    }

    public WebDriver getDriver(){
        return driver.get();
    }

    @BeforeMethod
    public void setup(){
        configReader = new ConfigReader();
        driverFactory = new DriverFactory();

        String browser = configReader.getProperty("browser");
        String url = configReader.getProperty("url");

        WebDriver webDriver = driverFactory.createDriver(browser);
        setDriver(webDriver);
        getDriver().get(url);
    }

    @AfterMethod
    public void teardDown() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
        }
        this.driver.remove();
    }
}
