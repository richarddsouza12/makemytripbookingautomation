package richardenterprises.cucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features ="src/test/java/richardenterprises/cucumber/features",
        glue = "richardenterprises.cucumber.step_definations",
        tags = "@special",
        monochrome = true,
        plugin = {
                "html:cucumber_reports/default_cucumber_report/cucumber.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
})
public class TestNgTestRunnerTagedOne extends AbstractTestNGCucumberTests {

}
