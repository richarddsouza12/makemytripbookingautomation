package richardenterprises.page_objects;

import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import richardenterprises.recources.Utils;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

public class FlightSelectionPage {

    public WebDriver driver;
    public WebDriverWait webDriverWaitExplicit;

    public FlightSelectionPage(WebDriver driver ){
        this.driver = driver;
    }


    public void selectCheapestFlightByName( String flightName ) throws InterruptedException {

        webDriverWaitExplicit = new WebDriverWait( this.driver, Duration.ofSeconds(5) );

        final String FareTypeComfort = "comfort";

        //check if add and close it.
        webDriverWaitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'commonOverlay')]")));
        this.driver.findElement(By.xpath("//div[contains(@class,'commonOverlay')]//span[contains(@class,'overlayCrossIcon')]") ).click();
        //end - check if add and close it.
        Thread.sleep( 1000 );

        //logic here.

        //Loop through and Find Listing Cards.
        List<WebElement> list_wb_allFlightCards = this.driver.findElements( By.cssSelector("div.clusterContent div.listingCard") );
        WebElement wb_matchedFlightCard = null;

        int i = 0;
        for ( WebElement wb_flightCard : list_wb_allFlightCards  ) {
            String airlineName = wb_flightCard.findElement( By.xpath(".//p[contains(@class,'airlineName')]") ).getText().trim();
            if( flightName.equalsIgnoreCase(airlineName) ) {
                wb_matchedFlightCard = wb_flightCard;
                break;
            }
            i++;
        }

        System.out.println( "card found at position :" + i );
        Thread.sleep(700);
        wb_matchedFlightCard.findElement( By.xpath(".//button[contains(@class,'ViewFareBtn')]") ).click();
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
            By by_flight_fares_popup = By.xpath("//div[contains(@class,'fareFamilyPopupWrapper')]");
            webDriverWaitExplicit.until( ExpectedConditions.visibilityOfElementLocated( by_flight_fares_popup ) );
            WebElement wb_flight_fares_popup = driver.findElement( by_flight_fares_popup );
            List<WebElement> list_wb_fare_categories = wb_flight_fares_popup.findElements( By.xpath(".//div[contains(@class,'fareFamilyCardWrapper')]"));
            WebElement wb_first_flight_category_card =  list_wb_fare_categories.iterator().next();
            Thread.sleep( 1000 );
            wb_first_flight_category_card.findElement(By.xpath(".//div[contains(@class,'ffCardFooter')]//*[@type='button' and text()='BOOK NOW']")).click();

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
}
