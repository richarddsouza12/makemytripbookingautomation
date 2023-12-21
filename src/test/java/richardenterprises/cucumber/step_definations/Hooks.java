package richardenterprises.cucumber.step_definations;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import richardenterprises.cucumber.StepDefinationContextSession;


import java.io.File;
import java.io.IOException;

public class Hooks {

    public StepDefinationContextSession SDSess;

    public Hooks(StepDefinationContextSession stepDefinationContextSession ) {
        this.SDSess = stepDefinationContextSession;
    }

   /*
   Before hooks run before the FIRST_STEP of each scenario.*/


    @After
    public void after() {
        SDSess.baseTest.driver.quit();
    }

    @AfterStep
    public void afterStep(Scenario scenario ) throws IOException {
        if( scenario.isFailed() ) {

            File sourcePath = ( (TakesScreenshot) SDSess.baseTest.driver ).getScreenshotAs(OutputType.FILE);
            byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
            scenario.attach( fileContent, "image/png","image");

        }

    }
}
