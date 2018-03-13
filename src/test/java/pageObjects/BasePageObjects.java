package pageObjects;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class BasePageObjects {
    @CacheLookup
    @FindBy(id = "ToDo")
    public WebElement seiten_menue;

    @CacheLookup
    @FindBy(id = "ToDo")
    public WebElement scrollable_element;
}
