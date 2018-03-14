package common;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import testData.TestData;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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

    public static void setDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        String browserType = testData.getBrowser();
        switch (browserType.toLowerCase()) {
            case "firefox":
                System.out.println("Opening firefox driver");
                capabilities = DesiredCapabilities.firefox();
                capabilities.setBrowserName("firefox");
                capabilities.setPlatform(Platform.LINUX);
                capabilities.setVersion("58.0.2");
                //  driver = new FirefoxDriver(desiredCapabilities);
                break;
            case "chrome":
                System.out.println("Opening chrome driver");
                ChromeOptions chromeOptions = new ChromeOptions();
//                chromeOptions.addArguments("--start-maximized");
//                chromeOptions.setBinary("/usr/bin/chromium-browser");
                chromeOptions.addArguments("--headless");
//                chromeOptions.addArguments("--lang=de-DE");
//                chromeOptions.addArguments("--disable-gpu");


                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                capabilities.setBrowserName("chrome");
                capabilities.setPlatform(Platform.LINUX);
                capabilities.setVersion("65.0.3325.146");


/*                String pathToChromeDriver = ".//selenium//chromedriver.exe";
                System.setProperty("webdriver.chrome.driver", pathToChromeDriver);
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                driver = new ChromeDriver(capabilities);*/
                break;
            case "IE":
                System.out.println("Opening IE driver");
                capabilities.setBrowserName("ie");
                //   driver = new InternetExplorerDriver(capabilities);
                break;
            default:
                System.out.println("browser : " + browserType + " is invalid, Launching Firefox as default browser");
                capabilities.setBrowserName("firefox");
                //   driver = new FirefoxDriver(capabilities);
                break;
        }
        driver = new RemoteWebDriver(new URL("http://172.24.93.161:4444/wd/hub"), capabilities); // für die
        driver.manage().timeouts().pageLoadTimeout(timeoutInSecond, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(timeoutInSecond, TimeUnit.SECONDS);
    }

    public static void startApp() throws IOException, InterruptedException {

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
