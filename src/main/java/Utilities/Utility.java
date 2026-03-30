package Utilities;

import Setup.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Utility extends BaseTest {


    public static void getUrl(WebDriver driver, String url) {
        driver.get(url);
    }

    public static String takeScreenShot(){
        TakesScreenshot ts = (TakesScreenshot) getDriver();
        File screenshot = ts.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
        try {
            FileUtils.copyFile(screenshot, new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path;
    }



}
