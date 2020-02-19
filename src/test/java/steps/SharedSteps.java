package steps;

import common.DriverSetup;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.de.Gegebensei;
import pages.HomePage;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static common.DriverSetup.setDriver;
import static common.DriverSetup.startApp;
import static steps.Util.screenshotHinzufuegen;

/**
 * Enthält Steps Definitions die für mehrere Tests wiederverwendbar sind
 * Wie @Before und @After Methoden
 * @author YouRad
 */

public class SharedSteps {
    /**
     * setup() hat die Annotation @Before und wird immer vor jeder Szenario-Ausführung aufgerufen
     * Damit werden technische Vorbedingungen erfühlt
     */

    @Before
    public void setup() {
        setDriver();
        startApp();
    }

    @Gegebensei("^die Startseite (.+) ist geladen$")
    public void checkStartseiteIstAngezeigt(String homeSeitentitel) {
        assertEquals(homeSeitentitel , new HomePage(DriverSetup.getDriver()).getTitle());
    }

    /**
     * cleanup() hat die Annotation @After und wird immer nach jeder Szenario-Ausführung aufgerufen
     * Damit werden technische Nachbedingungen erfühlt
     * @param scenario wenn die Szenario-Ausführung fehlschlägt wird ein Screenshot in scenario integriert
     */
    @After
    public void cleanup(Scenario scenario) {
        if (scenario.isFailed() || scenario.getStatus().toString().equals("undefined"))
            try {
                screenshotHinzufuegen(scenario);
            } catch (Exception e) {
                e.printStackTrace();
            }
        DriverSetup.close();
    }
}
