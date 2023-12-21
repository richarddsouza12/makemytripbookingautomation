package richardenterprises.cucumber.step_definations;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import richardenterprises.cucumber.StepDefinationContextSession;
import richardenterprises.page_objects.FlightSelectionPage;

import java.util.Iterator;
import java.util.Set;

public class FlightSelectionPageStepDefination {

    public StepDefinationContextSession SDSess;

    public FlightSelectionPageStepDefination( StepDefinationContextSession stepDefinationContextSession ) {
        this.SDSess = stepDefinationContextSession;
    }

    @When("^Select Flight (.+) and click view prices dropdown and book a comfort flight$")
    public void select_flight_x_and_click_view_prices_dropdown_and_book_a_comfort_flight( String flightname ) throws InterruptedException {

        FlightSelectionPage flightSelectionPage = new FlightSelectionPage( this.SDSess.baseTest.driver );
        flightSelectionPage.selectCheapestFlightByName( flightname );
        this.switchHandleToTravelersDetailsPage();
    }

    @Then("Shows listing of listing of flights available")
    public void shows_listing_of_listing_of_flights_available(){
        //dummy function for readibility
    }

    private void switchHandleToTravelersDetailsPage() {

        Set<String> windowHandles = this.SDSess.baseTest.driver.getWindowHandles();
        Iterator<String> iteratorSetWindowHandles = windowHandles.iterator();
        String parentWindowId = iteratorSetWindowHandles.next();
        String childWindowId = iteratorSetWindowHandles.next();
        this.SDSess.baseTest.driver.switchTo().window( childWindowId );

    }

}
