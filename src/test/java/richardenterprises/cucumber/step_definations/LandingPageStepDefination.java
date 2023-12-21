package richardenterprises.cucumber.step_definations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import richardenterprises.cucumber.StepDefinationContextSession;
import richardenterprises.page_objects.LandingPage;
import java.time.Duration;


public class LandingPageStepDefination {

    public StepDefinationContextSession SDSess;

    public LandingPageStepDefination( StepDefinationContextSession stepDefinationContextSession ) {
        this.SDSess = stepDefinationContextSession;
    }

    @Given("Come to sites landing page")
    public void come_to_sites_landing_page() {
        this.SDSess.baseTest.driver.get("https://www.makemytrip.com/");
    }

    @Given("^Fill flight travel ralated details (.+) (.+) and date details$")
    public void fill_flight_travel_ralated_details_x_y_and_date_details(String placeFrom, String placeTo ) throws InterruptedException {

        LandingPage landingPage = new LandingPage( this.SDSess.baseTest.driver );
        landingPage.fillSeachDeatisforFilghtsToday( placeFrom, placeTo );
        landingPage.clickSearchButton();

        //long wait ~10 -12 seconds for page load. //wiat until the fights listings is complete.
        //Thread.sleep(15000); //old approach
        WebDriverWait wdw = new WebDriverWait( this.SDSess.baseTest.driver, Duration.ofSeconds(15) );
        wdw.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='listing-id']")));
    }


    @Given("Fill flight travel related details and date details")
    public void fill_flight_travel_related_details_and_date_details() throws InterruptedException {
        LandingPage landingPage = new LandingPage( this.SDSess.baseTest.driver );
        landingPage.fillSeachDeatisforFilghtsToday( "goa", "pune" );
        Assert.assertTrue( false );
    }

    @Then("Final confirmation page displays before it proceeds to payment")
    public void final_confirmation_page_displays_before_it_proceeds_to_payment() {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue( true );
    }





}
