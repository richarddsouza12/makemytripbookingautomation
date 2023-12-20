package richardenterprises.cucumber.step_definations;

import io.cucumber.java.en.When;
import richardenterprises.cucumber.StepDefinationContextSession;
import richardenterprises.page_objects.TravellersDetailsPage;

import java.util.List;

public class TravellerDetailsPageStepDefination {

    public StepDefinationContextSession SDSess;

    public TravellerDetailsPageStepDefination( StepDefinationContextSession stepDefinationContextSession ) {
        this.SDSess = stepDefinationContextSession;
    }


    @When("Fill personal details of the traveller")
    public void fill_personal_details_of_the_traveller( List<String> listData ) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        String mobile = listData.get(0);
        String email = listData.get(1);
        String pincode = listData.get(2);
        String state = listData.get(3);
        String address = listData.get(4);

        TravellersDetailsPage travellersDetailsPage = new TravellersDetailsPage( this.SDSess.baseTest.driver );
        travellersDetailsPage.fillTravellersDetails();

    }
}
