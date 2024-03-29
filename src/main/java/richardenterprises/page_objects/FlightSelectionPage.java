package richardenterprises.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import richardenterprises.recources.Utils;
import java.time.Duration;
import java.util.List;

public class FlightSelectionPage {


    public WebDriver driver;
    public WebDriverWait webDriverWaitExplicit;

    public final String fareTypeComfort = "comfort";

    public final By byDivCommonOverlay = By.xpath("//div[contains(@class,'commonOverlay')]");
    public final By bySpanOverlayCloseIcon = By.xpath("//div[contains(@class,'commonOverlay')]//span[contains(@class,'overlayCrossIcon')]");

    public final By byDivListingCards = By.cssSelector("div.clusterContent div.listingCard");
    public final By byPAirlinesName = By.xpath(".//p[contains(@class,'airlineName')]");
    public final By byButtonViewFares = By.xpath(".//button[contains(@class,'ViewFareBtn')]");

    public final By by_flight_fares_popup = By.xpath("//div[contains(@class,'fareFamilyPopupWrapper')]");
    public final By byDivFareFamilyCardWrapper = By.xpath(".//div[contains(@class,'fareFamilyCardWrapper')]");
    public final By byButtonBookNow = By.xpath(".//div[contains(@class,'ffCardFooter')]//*[@type='button' and text()='BOOK NOW']");

    public FlightSelectionPage(WebDriver driver ) {
        this.driver = driver;
    }


    public void selectCheapestFlightByName( String flightName ) throws InterruptedException {

        webDriverWaitExplicit = new WebDriverWait( this.driver, Duration.ofSeconds(5) );

        checkIfAddPresentAndClose();
        Thread.sleep( 1000 );

        //Loop through and Find Listing Cards.
        List<WebElement> list_wb_allFlightCards = this.driver.findElements(byDivListingCards);
        WebElement wb_matchedFlightCard = null;

        int i = 0;
        for ( WebElement wb_flightCard : list_wb_allFlightCards  ) {
            String airlineName = wb_flightCard.findElement( byPAirlinesName ).getText().trim();
            if( flightName.equalsIgnoreCase(airlineName) ) {
                wb_matchedFlightCard = wb_flightCard;
                break;
            }
            i++;
        }

        System.out.println( "card found at position :" + i );
        Thread.sleep(700);
        wb_matchedFlightCard.findElement(byButtonViewFares).click();
        Thread.sleep(700);

        /*
        * Two Flows -
        * 1 - On Click either redirect to new page directly
        * 2 - Popup Will open to select form multiple flight choice options
        * */

        if( Utils.getOrCreateUtilsInstance(driver).findStringInAllWindowHandles("reviewDetails") ) {

            //1 - On Click either redirect to new page directly
            //new page has opened and no popup will show as default is selected

        } else {

            // 2 - Popup Will open to select form multiple flight choice options
            //Flight Fare Window popup appears with   types of flights eg comfort , comfort + ,etc.
            //choose first one.
            webDriverWaitExplicit.until( ExpectedConditions.visibilityOfElementLocated( by_flight_fares_popup ) );
            WebElement wb_flight_fares_popup = driver.findElement( by_flight_fares_popup );
            List<WebElement> list_wb_fare_categories = wb_flight_fares_popup.findElements(byDivFareFamilyCardWrapper);
            WebElement wb_first_flight_category_card =  list_wb_fare_categories.iterator().next();
            Thread.sleep( 1000 );
            wb_first_flight_category_card.findElement(byButtonBookNow).click();

            /*
            Old Fare Price Logic where the types of flights eg comfort , comfort + , etc used to come under the booking only. Discontinued.

            List<WebElement> list_wb_fare_categories = wb_matchedFlightCard.findElements(By.xpath("./parent::div//div[contains(@class,'viewFaresOuter')]//div[@class='viewFareRowWrap']"));
            for ( WebElement wb_fare_category : list_wb_fare_categories )  {

                String fareTypeName = wb_fare_category.findElement( By.cssSelector("p.fareNameHead") ).getText();
                if( fareTypeName.toLowerCase().contains(FareTypeComfort) ) {

                    wb_fare_category.findElement( By.xpath(".//button[contains( text(),'Book Now')]")).click();
                    System.out.println("fare type comfort found and clicked.");
                }
            }
        */

        }

    }

    private void checkIfAddPresentAndClose() {

        try {
            webDriverWaitExplicit.until(ExpectedConditions.visibilityOfElementLocated( byDivCommonOverlay ) );
            this.driver.findElement( bySpanOverlayCloseIcon ).click();
        } catch ( Exception e ) {
            System.out.println( "No Add Skipping :" );
        }

    }
}
