package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.BasePageObjects;

import java.util.concurrent.TimeUnit;

/**
 * Eine basis Klasse für alle page Klassen
 * Enthält Methoden und Eigenschaften die für (alle) anderen page Klassen Relevant sein können
 * @author YouRad
 */

public class BasePage {

    private WebDriver webDriver;

    BasePage(WebDriver driver) {
        webDriver = driver;
        BasePageObjects basePageObjects = new BasePageObjects();
        PageFactory.initElements(webDriver, basePageObjects);
    }

    public String getTitle(){
        return webDriver.getTitle();
    }

    public void scrollToElement(WebElement webElement) {
        if (webDriver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) webDriver)
                    .executeScript("arguments[0].scrollIntoView(true);", webElement);
        }
    }

    public void warteBisFortschrittsanzeigeVerschwunden() {
        webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
        int timeoutInSecond = 20;
        try {
            new WebDriverWait(webDriver, timeoutInSecond).until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));
        } finally {
            webDriver.manage().timeouts().implicitlyWait(timeoutInSecond, TimeUnit.SECONDS);
        }
    }
}
