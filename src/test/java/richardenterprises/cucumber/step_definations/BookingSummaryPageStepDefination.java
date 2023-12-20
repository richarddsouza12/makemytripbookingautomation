package richardenterprises.cucumber.step_definations;

import io.cucumber.java.en.Then;
import org.testng.Assert;
import richardenterprises.cucumber.StepDefinationContextSession;
import richardenterprises.page_objects.BookingSummaryPage;

public class BookingSummaryPageStepDefination {

    public StepDefinationContextSession SDSess;

    public BookingSummaryPageStepDefination( StepDefinationContextSession stepDefinationContextSession ) {
        this.SDSess = stepDefinationContextSession;
    }

    @Then("Final confirmation page displays with proceed to pay button")
    public void final_confirmation_page_displays() {

        BookingSummaryPage bookingSummaryPage = new BookingSummaryPage( this.SDSess.baseTest.driver );
        Assert.assertTrue( bookingSummaryPage.checkIfProceedToPayButtonIsPresent() );

    }

}
