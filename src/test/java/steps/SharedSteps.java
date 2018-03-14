package steps;

import common.DriverSetup;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.de.Gegebensei;
import org.apache.log4j.BasicConfigurator;
import pages.HomePage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Objects;

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
    public void setup() throws IOException, InterruptedException {
        BasicConfigurator.configure();
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
        if (scenario.isFailed() || Objects.equals(scenario.getStatus(), "undefined"))
            try {
                screenshotHinzufuegen(scenario);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        DriverSetup.close();
    }
}
