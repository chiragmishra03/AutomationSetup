import Setup.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static Utilities.Utility.getUrl;

public class TestClass extends BaseTest {


    @Test(invocationCount = 3, threadPoolSize = 3)
    public void testParallelExecution() {
        logStep("Navigating to Google");
        getUrl(getDriver(), "https://www.google.com");
        logStep("Page loaded successfully");
        System.out.println("TestClass testParallelExecution");

    }
}
