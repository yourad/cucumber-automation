package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class HomePageObjects {

    @CacheLookup
    @FindBy(id = "login-button")
    public WebElement login_button;

    @CacheLookup
    @FindBy(id = "username")
    public WebElement benutzername;

    @CacheLookup
    @FindBy(id = "password")
    public WebElement passwort;

    @CacheLookup
    @FindBy(id = "locS0")
    public WebElement startbahnhof;

    @CacheLookup
    @FindBy(id = "locZ0")
    public WebElement zielbahnhof;

    @CacheLookup
    @FindBy(id = "searchConnectionButton")
    public WebElement suche_button;
}
