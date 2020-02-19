package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import testData.TestData;

import java.util.concurrent.TimeUnit;

/**
 * Startpunkt für die Testausführung
 *
 * @author YouRad
 */

public class DriverSetup {
    private static final int timeoutInSecond = 10;

    private static final TestData testData = new TestData();

    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        String browserType = testData.getBrowser();
        switch (browserType.toLowerCase()) {
            case "firefox":
                System.out.println("Opening firefox driver");

                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "chrome":
                System.out.println("Opening chrome driver");
                ChromeOptions chromeOptions = new ChromeOptions();
//                chromeOptions.addArguments("--start-maximized");
//                chromeOptions.setBinary("/usr/bin/chromium-browser");
//                chromeOptions.addArguments("--headless");
//                chromeOptions.addArguments("--lang=de-DE");
//                chromeOptions.addArguments("--disable-gpu");


                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                capabilities.setBrowserName("chrome");
//                capabilities.setPlatform(Platform.MAC);
//                capabilities.setVersion("80.0.3987.106");


                String pathToChromeDriver = ".//selenium//chromedriver";
                System.setProperty("webdriver.chrome.driver", pathToChromeDriver);
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                driver = new ChromeDriver();
                break;
            case "IE":
                System.out.println("Opening IE driver");
                capabilities.setBrowserName("ie");
                //   driver = new InternetExplorerDriver(capabilities);
                break;
        }
   //     driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        driver.manage().timeouts().pageLoadTimeout(timeoutInSecond, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(timeoutInSecond, TimeUnit.SECONDS);
    }

    public static void startApp() {

        driver.navigate().to(testData.getAppURL());

        // Waiting Driver
        byte defaultWaitingTimeInSeconds = 3;
        new WebDriverWait(driver, defaultWaitingTimeInSeconds);
    }

    public static void close() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception ignored) {
            } finally {
                driver = null;
            }
        }
    }
}
