package testData;

import common.DriverSetup;

import java.io.IOException;
import java.util.Properties;
/**
 * Liest die Testdaten von test.properties.
 * Und bereitet sie für weitere verwendung vor
 * @author YouRad
 */
public class TestData {
    private Properties properties;

    public TestData() {
        properties = new Properties();
        try {
            this.properties.load(DriverSetup.class.getClassLoader().getResourceAsStream("test.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getBrowser() {
        return properties.getProperty("browser", "Chrome");
    }

    public String getAppURL() {
        return properties.getProperty("appURL", "https://www.wikipedia.de/");
    }
}
