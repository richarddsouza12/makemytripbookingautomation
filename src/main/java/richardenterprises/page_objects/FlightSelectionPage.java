package richardenterprises.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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

        List<WebElement> list_wb_fare_categories = wb_matchedFlightCard.findElements(By.xpath("./parent::div//div[contains(@class,'viewFaresOuter')]//div[@class='viewFareRowWrap']"));
        for ( WebElement wb_fare_category : list_wb_fare_categories )  {

            String fareTypeName = wb_fare_category.findElement( By.cssSelector("p.fareNameHead") ).getText();
            if( fareTypeName.toLowerCase().contains(FareTypeComfort) ) {

                wb_fare_category.findElement( By.xpath(".//button[contains( text(),'Book Now')]")).click();
                System.out.println("fare type comfort found and clicked.");
            }

        }

    }
}
