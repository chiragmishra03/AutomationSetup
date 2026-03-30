package Setup;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private Properties prop;

    public ConfigReader() {
        prop = new Properties();
        try{
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/Configuration.properties");
            prop.load(fis);
        }catch (IOException e){
            System.out.println("Error: File not loaded");
            System.out.println(e.getMessage());
        }
    }

    public String getProperty(String key){
        return prop.getProperty(key);
    }
}
