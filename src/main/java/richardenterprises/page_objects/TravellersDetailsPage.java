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

    public final By byradioNoInsurance = By.xpath("(//div[@id='INSURANCE' and contains(@class,'oneCard-element')]//input[@type='radio'])[2]");

    public final By byButtonAddTraveller = By.cssSelector("button.addTravellerBtn");
    public final By byTextTravellerFirstName = By.xpath("(//div[@id='wrapper_ADULT']//input[@type='text'])[1]");
    public final By byTravellerLastName = By.xpath("(//div[@id='wrapper_ADULT']//input[@type='text'])[2]");
    public final By byLabelTravellerButton = By.xpath("//div[@id='wrapper_ADULT']//div[contains(@class,'selectTab')]//label[1]");

    public final By byContactNo = By.xpath("(//div[@id='contactDetails']//input)[2]");
    public final By byEmail = By.xpath("(//div[@id='contactDetails']//input)[3]");

    public final By byTextBillingAddressPin = By.xpath("(//div[@id='BILLING_ADDRESS']//input[@type='text'])[1]");
    public final By byLiStateName = By.xpath("//div[@id='BILLING_ADDRESS']//ul[@class='dropdownListWpr']/li[contains(text(),'Goa')]");
    public final By byTextAddress = By.xpath("(//div[@id='BILLING_ADDRESS']//input[@type='text'])[3]");

    public final By byContioneButton = By.xpath("//*[@id='mainSection_0']//button[contains(text(),'Continue')]");

    public final By byDivFlightChangeConfirmPopup = By.xpath("//div[contains(@class,'commonOverlay')]");
    public final By byButtonbDivFlightChangeConfirmPopupDismiss = By.xpath(".//button[@type='DISMISS']");

    public final By byDivConfirmSectionPopupContianer = By.xpath("//div[@class='overlay']");
    public final By byButtonConfirmSectionPopupConfirm = By.xpath("//div[@class='overlay']//button[contains(text(),'CONFIRM')]");

    public TravellersDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillTravellersDetails() throws InterruptedException {

        webDriverWaitExplicit = new WebDriverWait( this.driver, Duration.ofSeconds(5) );

        //handle - Your Selection Has Been Changed From Comfort|Comfort Plus to Comfort Plus|Comfort Popup click continue if present.
        handleFlightTypeChangePopup();

        Thread.sleep(300 );
        WebElement wb_insuranceSection = driver.findElement( By.id("INSURANCE") );
        Utils.getOrCreateUtilsInstance(driver).scrollToElement( wb_insuranceSection );
        Thread.sleep(1000);

        //dont want insurance.
        driver.findElement(byradioNoInsurance).click();

        //Fill Details :
        Thread.sleep(300 );
        WebElement wb_travelers_details = driver.findElement(By.id("TRAVELLER_DETAIL") );
        Utils.getOrCreateUtilsInstance(driver).scrollToElement( wb_travelers_details );
        Thread.sleep(700);

        driver.findElement( byButtonAddTraveller ).click();
        Thread.sleep(700);

        driver.findElement(byTextTravellerFirstName).sendKeys("Richard");
        driver.findElement(byTravellerLastName).sendKeys("Dsouza");
        driver.findElement(byLabelTravellerButton).click();

        //add contact
        Thread.sleep(300 );
        WebElement wb_contact_details = driver.findElement( By.id("contactDetails") );
        Utils.getOrCreateUtilsInstance(driver).scrollToElement( wb_contact_details );
        Thread.sleep(700);

        driver.findElement(byContactNo).sendKeys("8806637533");
        driver.findElement(byEmail).sendKeys("richarddsouza@gmail.com");

        //Billing Address
        driver.findElement(byTextBillingAddressPin).clear();
        driver.findElement(byTextBillingAddressPin).sendKeys("403517");

        Thread.sleep(1000);
        //Some TransparentOvelralyDiv show up for a while here.wait till it goes.
        WebElement p_overlay = this.driver.findElement(By.cssSelector("p.transparentOverlay"));
        Utils.getOrCreateUtilsInstance( this.driver ).removeWebElementIfPresent( p_overlay );

        driver.findElement( By.xpath("(//div[@id='BILLING_ADDRESS']//input[@type='text'])[2]") ).click();
        Thread.sleep(600 );
        WebElement wb_dropdown_elem_goa = driver.findElement(byLiStateName);
        Utils.getOrCreateUtilsInstance(driver).scrollToElement( wb_dropdown_elem_goa );
        Thread.sleep(400);
        wb_dropdown_elem_goa.click();

        driver.findElement(byTextAddress).sendKeys("Siolim Goa");

        /*driver.findElement( By.xpath("//div[@id='BILLING_ADDRESS']//input[@type='checkbox']") ).click();*/
        //getting ( org.openqa.selenium.ElementNotInteractableException: element not interactable )
        driver.findElement( By.xpath("//div[@id='BILLING_ADDRESS']//span[@class='checkboxWpr']") ).click();
        driver.findElement(byContioneButton).click();

        //confirm selection popup
        webDriverWaitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byDivConfirmSectionPopupContianer) );
        Thread.sleep(700);
        driver.findElement(byButtonConfirmSectionPopupConfirm).click();

    }

    private void handleFlightTypeChangePopup() {

        try{
            webDriverWaitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byDivFlightChangeConfirmPopup));
            WebElement wb_flight_change_confirmation_popup = driver.findElement(byDivFlightChangeConfirmPopup);
            Thread.sleep(1000);
            wb_flight_change_confirmation_popup.findElement(byButtonbDivFlightChangeConfirmPopupDismiss).click();
        } catch ( Exception e ) {
            //no popup showed.
            System.out.println( " Popup add not found : skipping"  );
        }
    }
}
