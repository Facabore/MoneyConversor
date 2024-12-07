package Helpers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadApi
{
    public static String loadProperties(){
        try (InputStream input = LoadApi.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties prop = new Properties();
            if(input == null){
                throw new FileNotFoundException("property file does not exist in the classpath");
            }
            prop.load(input);
            return prop.getProperty("api.exchange");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
