package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    protected static FileInputStream input;
    protected static Properties properties = new Properties();

    public static String getProperty(String key) {
        try {
            input = new FileInputStream("src/test/resources/creds.properties");
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null)
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return properties.getProperty(key);
    }
}