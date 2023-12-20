package richardenterprises.cucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features ="src/test/java/richardenterprises/cucumber/features",
        glue = "richardenterprises.cucumber.step_definations",
        monochrome = true,
        plugin = {
                "html:cucumber_reports/default_cucumber_report/cucumber.html"
})
public class TestNgTestRunner extends AbstractTestNGCucumberTests {

}
