package testplan;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Verantwortlich für die Ausführung der Cucumber Testfälle
 * Und für die Erstellung von json Report
 * @author YouRad
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"steps"},                   // Ort wo die Steps Definitions sich befinden -->
        features = "src/test/features/",    // Ort wo die fachliche Spezifikation sich befindet (Features--> Szenarios --> Steps)
        tags = {"@Integrationstest"},  // Welche Tests sollen selektiv ausgeführt werden
        format = {"pretty","json:build/reports/features/json/integrationstest_cucumber_report.json"}) // Report Format
public class IntegrationstestStarten {
}
