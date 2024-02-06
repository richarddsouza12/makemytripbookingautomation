package richardenterprises.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import richardenterprises.recources.Utils;

import java.time.Duration;
import java.util.List;

public class SeatSelectionPage {

    public WebDriver driver;
    public WebDriverWait webDriverWaitExplicit;

    public SeatSelectionPage( WebDriver driver ) {
        this.driver = driver;
    }

    public void performSeatSelection() throws Exception {

        webDriverWaitExplicit = new WebDriverWait( this.driver, Duration.ofSeconds(5) );

        //popup at side to select seat offer - skip it
        handleSeatBookingSuggestionPopup();

        //check how many connecting flights?
        WebElement div = driver.findElement( By.xpath("//div[@id='SEATS_N_MEALS']//div[@class='ancillaryScrollWrap']") );

        Thread.sleep(300 );
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", div);
        Thread.sleep(1000);

        int i = 1;
        List <WebElement> wb_filght_seat_picker_cards = driver.findElements( By.xpath(" //div[@id='SEATS_N_MEALS']//div[@class='ancillaryScrollWrap']/div") );
        for( WebElement wb_filght_seat_picker_card : wb_filght_seat_picker_cards ) {

            //skip this for first round - already handled on this page load
            if( i != 1 ) {
                //popup at side to select seat offer - skip it
                handleSeatBookingSuggestionPopup();
            }

            //wait for seat selection , seat header section to be fully visible after scroll
            webDriverWaitExplicit.until(ExpectedConditions.visibilityOf( wb_filght_seat_picker_card.findElement(
                    By.xpath(".//div[contains(@class,'seatHeader')]")) ));

            var seatFound = false;
            seatFound = this.findFreeSeat( wb_filght_seat_picker_card );
            if( seatFound == true ) {
                this.selectFreeSeat( wb_filght_seat_picker_card );
                Thread.sleep( 1000 );
            }

            if( seatFound == false ) {
                seatFound = this.findPaidSeat( wb_filght_seat_picker_card );
                if( seatFound == true ) {
                    this.selectPaidSeat( wb_filght_seat_picker_card );
                    Thread.sleep( 1000 );
                }
            }

            if( seatFound == false ) {
                throw new Exception("No Free nor Paid Seat Found");
            }

            //click the next button >  if its present
            if( this.isNextFlightSeatSelectionSetionPresent() ) {
                Thread.sleep(1000);
                this.moveToNextFlightSeatSelectionSection();
            } else {
                //click continue button below
                Thread.sleep(1000);
                driver.findElement( By.xpath("//form[@id='mainSection_1']/div[2]//button[contains(text(),'Continue')]") ).click();
            }

            i++;
        }

        // redirects to payment page form here if I click next again

    }

    private void handleSeatBookingSuggestionPopup() {
        try{
            By by_seat_booking_suggestion_offer_popup = By.xpath("//div[contains(@class,'seatBookOverlayWrap')]");
            webDriverWaitExplicit.until(ExpectedConditions.visibilityOfElementLocated(by_seat_booking_suggestion_offer_popup));
            WebElement wb_seat_booking_suggestion_offer_popup = driver.findElement(by_seat_booking_suggestion_offer_popup);
            Thread.sleep(1000);
            wb_seat_booking_suggestion_offer_popup.findElement(By.xpath(".//p[contains(@class,'seatBookingOverlayCta')]//span")).click();
        } catch ( Exception e ) {
            //no popup showed.
            System.out.println( " Popup seat_booking_suggestion_offer_popup not found : skipping"  );
        }
    }

    private void moveToNextFlightSeatSelectionSection() {
        driver.findElement( By.xpath("//div[@id='SEATS_N_MEALS']//div[contains(@class,'ancillaryContainer')]//button[contains(@class,'sliderNextBtn')]") ).click();
    }

    /**
     * check to see it the > next arrow hover on right corner is present , if it is than there is a next section.
     * @return
     */
    private boolean isNextFlightSeatSelectionSetionPresent() {

        WebElement wb_next_button = null;
        try {
            wb_next_button = driver.findElement( By.xpath("//div[@id='SEATS_N_MEALS']//div[contains(@class,'ancillaryContainer')]//button[contains(@class,'sliderNextBtn')]") );
        } catch( Exception e ) {}

        return ( wb_next_button != null ) ? true : false;

    }

    private void selectPaidSeat(WebElement wb_filght_seat_picker_card) throws InterruptedException {

        WebElement wb_first_paid_seat = wb_filght_seat_picker_card.findElement(
                    By.xpath("(.//div[contains(@class,'seatBlock pointer') and contains(@style,'background-color: rgb(186, 218, 255)')] )[1]"));

        //check if element is visible and if not scroll to it.
        if (!wb_first_paid_seat.isDisplayed()) {
            Thread.sleep( 1000 );
            Utils.getOrCreateUtilsInstance( this.driver ).scrollToElement( wb_first_paid_seat );
        }

        Thread.sleep( 700 );
        wb_first_paid_seat.click();
    }

    private boolean findPaidSeat( WebElement wb_filght_seat_picker_card ) {

        WebElement wb_first_paid_seat = null;

        try {
            wb_first_paid_seat = wb_filght_seat_picker_card.findElement(
                    By.xpath("(.//div[contains(@class,'seatBlock pointer') and contains(@style,'background-color: rgb(186, 218, 255)')] )[1]"));

        } catch ( Exception e ) { }

        return (  wb_first_paid_seat == null ) ? false: true;

    }

    /**
     *
     * @param wb_filght_seat_picker_card | div[@class='ancillaryScrollWrap']/div
     * @return
     */
    private void selectFreeSeat( WebElement wb_filght_seat_picker_card ) throws InterruptedException {


        WebElement wb_first_free_seat = wb_filght_seat_picker_card.findElement(
                By.xpath("(.//div[contains(@class,'seatBlock pointer') and contains(@style,'background-color: rgb(80, 227, 194)')] )[1]"));

        //check if element is visible and if not scroll to it.
        if (!wb_first_free_seat.isDisplayed()) {
            Thread.sleep( 1000 );
            Utils.getOrCreateUtilsInstance( this.driver ).scrollToElement( wb_first_free_seat );
        }

        Thread.sleep( 700 );
        wb_first_free_seat.click();

    }

    /**
     * finds if one free seat is present.
     * @param wb_filght_seat_picker_card | div[@class='ancillaryScrollWrap']/div
     * @return
     */
    private boolean findFreeSeat( WebElement wb_filght_seat_picker_card ) {

        WebElement wb_first_free_seat = null;

        try {
             wb_first_free_seat = wb_filght_seat_picker_card.findElement(
                    By.xpath("(.//div[contains(@class,'seatBlock pointer') and contains(@style,'background-color: rgb(80, 227, 194)')] )[1]"));
        } catch ( Exception e ) { }

        return (  wb_first_free_seat == null ) ? false: true;

    }


}
