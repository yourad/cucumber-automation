package steps;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

import static common.DriverSetup.getDriver;

/**
 * Hilfsfunktionen für steps package
 * @author YouRad
 */

class Util {
    /**
     * screenshotHinzufuegen() ist dafür verantwortlich,
     * ein Screenshot in png Format zu erstellen
     * und das Bild in den Report zu integrieren
     * Sollte am besten nur aufgerufen werden wenn ein Szenario fehlschlägt
     * @param scenario Das Szenario in dem das Bild integriert wird
     */
    static void screenshotHinzufuegen(Scenario scenario) {
        try {
            WebDriver driver = getDriver();
            if (driver == null) {
                System.err.println("Driver ist null");
                return;
            }
            byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png", scenario.getName());
        } catch (RuntimeException e) {
            System.err.println("Screenshot konnte nicht geschrieben werden");
            e.printStackTrace();
        }
    }
}