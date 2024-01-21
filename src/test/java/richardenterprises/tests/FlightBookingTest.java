package richardenterprises.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import richardenterprises.page_objects.*;
import richardenterprises.test_components.BaseTest;

import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class FlightBookingTest extends BaseTest {

    /* Base Test
          public WebDriver driver;
     */

    public FlightBookingTest() throws IOException {
        super.initializeWebDriver();
    }

    /**
     * Test booking of a one way form Goa to Pune.
     * @throws Exception
     */
    @Test
    public void testBookGoaToPuneOneWayFlight() throws Exception {

        this.driver.get("https://www.makemytrip.com/");

        String username = "richarddsouza12@gmail.com";
        String password = "$hiphop123$";
        String fromPlace = "goa";
        String toPlace = "pune";
        String flightname = "Air India";

        LandingPage landingPage = new LandingPage( this.driver );
        //landingPage.login( username, password );
        landingPage.fillSeachDeatisforFilghtsToday( fromPlace, toPlace );
        landingPage.clickSearchButton();

        //long wait ~10 -12 seconds for page load. //wiat until the fights listings is complete.
        //Thread.sleep(15000); //old approach
        WebDriverWait wdw = new WebDriverWait( this.driver, Duration.ofSeconds(15) );
        wdw.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='listing-id']")));

        FlightSelectionPage flightSelectionPage = new FlightSelectionPage( this.driver );
        flightSelectionPage.selectCheapestFlightByName( flightname );

        //flow dosent open up new tab now as earlier.
        this.switchHandleToTravelersDetailsPage();

        TravellersDetailsPage travellersDetailsPage = new TravellersDetailsPage( this.driver );
        travellersDetailsPage.fillTravellersDetails();

        //page load issues are taken care of by default in implicit wait.
        SeatSelectionPage seatSelectionPage = new SeatSelectionPage( this.driver );
        seatSelectionPage.performSeatSelection();

        BookingSummaryPage bookingSummaryPage = new BookingSummaryPage( this.driver );
        Assert.assertTrue( bookingSummaryPage.checkIfProceedToPayButtonIsPresent() );

    }

    @Test
    public void testEnterFlightSearchDetailsAndForcefullyFail() throws Exception {

        String fromPlace = "goa";
        String toPlace = "pune";

        this.driver.get("https://www.makemytrip.com/");
        LandingPage landingPage = new LandingPage( this.driver );
        landingPage.fillSeachDeatisforFilghtsToday( fromPlace, toPlace );
        Assert.assertTrue(false );

    }


    private void switchHandleToTravelersDetailsPage() {

        Set<String> windowHandles = this.driver.getWindowHandles();
        Iterator<String> iteratorSetWindowHandles = windowHandles.iterator();
        String parentWindowId = iteratorSetWindowHandles.next();
        String childWindowId = iteratorSetWindowHandles.next();
        driver.switchTo().window( childWindowId );

    }

    //xx

}
