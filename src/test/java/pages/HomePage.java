package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageObjects.HomePageObjects;

import java.util.concurrent.TimeUnit;

/**
 * Eine Klasse für die Abstraktion (der funktionalität) der HomePage Seite
 * @author YouRad
 */

public class HomePage extends BasePage  {

    private HomePageObjects homePageObjects = new HomePageObjects();
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        PageFactory.initElements(this.driver, homePageObjects);
    }
}
