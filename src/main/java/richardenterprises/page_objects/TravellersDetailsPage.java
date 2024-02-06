package richardenterprises.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import richardenterprises.recources.Utils;

import java.time.Duration;

public class TravellersDetailsPage {

    public WebDriver driver;
    public WebDriverWait webDriverWaitExplicit;

    public TravellersDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillTravellersDetails() throws InterruptedException {

        webDriverWaitExplicit = new WebDriverWait( this.driver, Duration.ofSeconds(5) );

        //handle - Your Selection Has Been Changed From Comfort|Comfort Plus to Comfort Plus|Comfort Popup click continue if present.
        handleFlightTypeChangePopup();

        Thread.sleep(300 );
        WebElement wb_insuranceSection = driver.findElement(By.id("INSURANCE") );
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", wb_insuranceSection);
        Thread.sleep(1000);

        driver.findElement( By.xpath("(//div[@id='INSURANCE' and contains(@class,'oneCard-element')]//input[@type='radio'])[2]") ).click();

        //Fill Details :
        Thread.sleep(300 );
        WebElement wb_travelers_details = driver.findElement(By.id("TRAVELLER_DETAIL") );
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", wb_travelers_details);
        Thread.sleep(700);

        driver.findElement( By.cssSelector("button.addTravellerBtn") ).click();
        Thread.sleep(700);

        driver.findElement( By.xpath("(//div[@id='wrapper_ADULT']//input[@type='text'])[1]") ).sendKeys("Richard");
        driver.findElement( By.xpath("(//div[@id='wrapper_ADULT']//input[@type='text'])[2]") ).sendKeys("Dsouza");
        driver.findElement( By.xpath("//div[@id='wrapper_ADULT']//div[contains(@class,'selectTab')]//label[1]") ).click();

        //add contact
        Thread.sleep(300 );
        WebElement wb_contact_details = driver.findElement(By.id("contactDetails") );
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", wb_contact_details);
        Thread.sleep(700);

        driver.findElement( By.xpath("(//div[@id='contactDetails']//input)[2]") ).sendKeys("8806637533");
        driver.findElement( By.xpath("(//div[@id='contactDetails']//input)[3]") ).sendKeys("richarddsouza@gmail.com");

        //Billing Address
        driver.findElement( By.xpath("(//div[@id='BILLING_ADDRESS']//input[@type='text'])[1]") ).clear();
        driver.findElement( By.xpath("(//div[@id='BILLING_ADDRESS']//input[@type='text'])[1]") ).sendKeys("403517");

        Thread.sleep(1000);
        //Some TransparentOvelralyDiv show up for a while here.wait till it goes.
        WebElement p_overlay = this.driver.findElement(By.cssSelector("p.transparentOverlay"));
        Utils.getOrCreateUtilsInstance( this.driver ).removeWebElementIfPresent( p_overlay );

        driver.findElement( By.xpath("(//div[@id='BILLING_ADDRESS']//input[@type='text'])[2]") ).click();
        Thread.sleep(600 );
        WebElement wb_dropdown_elem_goa = driver.findElement( By.xpath("//div[@id='BILLING_ADDRESS']//ul[@class='dropdownListWpr']/li[contains(text(),'Goa')]") );
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", wb_dropdown_elem_goa );
        Thread.sleep(400);
        wb_dropdown_elem_goa.click();

        driver.findElement( By.xpath("(//div[@id='BILLING_ADDRESS']//input[@type='text'])[3]") ).sendKeys("Siolim Goa");
        ///for the dropdown:
        //do smooth scroll also.

        /*driver.findElement( By.xpath("//div[@id='BILLING_ADDRESS']//input[@type='checkbox']") ).click();*/
        //getting ( org.openqa.selenium.ElementNotInteractableException: element not interactable )
        driver.findElement( By.xpath("//div[@id='BILLING_ADDRESS']//span[@class='checkboxWpr']") ).click();

        driver.findElement( By.xpath("//*[@id='mainSection_0']//button[contains(text(),'Continue')]") ).click();

        //confirm selection popup
        webDriverWaitExplicit.until(ExpectedConditions.visibilityOfElementLocated( By.xpath("//div[@class='overlay']")) );
        Thread.sleep(700);
        driver.findElement( By.xpath("//div[@class='overlay']//button[contains(text(),'CONFIRM')]") ).click();

    }

    private void handleFlightTypeChangePopup() {

        try{
            By by_flight_change_confirmation_popup = By.xpath("//div[contains(@class,'commonOverlay')]");
            webDriverWaitExplicit.until(ExpectedConditions.visibilityOfElementLocated(by_flight_change_confirmation_popup));
            WebElement wb_flight_change_confirmation_popup = driver.findElement(by_flight_change_confirmation_popup);
            Thread.sleep(1000);
            wb_flight_change_confirmation_popup.findElement(By.xpath(".//button[@type='DISMISS']")).click();
        } catch ( Exception e ) {
            //no popup showed.
            System.out.println( " Popup add not found : skipping"  );
        }
    }
}
