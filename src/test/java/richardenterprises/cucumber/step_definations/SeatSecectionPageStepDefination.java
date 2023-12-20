package richardenterprises.cucumber.step_definations;

import io.cucumber.java.en.When;
import richardenterprises.cucumber.StepDefinationContextSession;
import richardenterprises.page_objects.SeatSelectionPage;

public class SeatSecectionPageStepDefination {


    public StepDefinationContextSession SDSess;

    public SeatSecectionPageStepDefination( StepDefinationContextSession stepDefinationContextSession ) {
        this.SDSess = stepDefinationContextSession;

    }


    @When("Select Free or Paid Seats for the flights for the trip")
    public void select_free_or_paid_seats_for_the_flights_for_the_trip() throws Exception {

        //page load issues are taken care of by default in implicit wait.
        SeatSelectionPage seatSelectionPage = new SeatSelectionPage( this.SDSess.baseTest.driver );
        seatSelectionPage.performSeatSelection();
    }

}
