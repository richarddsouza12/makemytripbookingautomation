package richardenterprises.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import richardenterprises.recources.Utils;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class LandingPage {

    public WebDriver driver;
    public WebDriverWait webDriverWaitExplicit;

    public LandingPage( WebDriver driver ){
        this.driver = driver;
    }

    public void fillSeachDeatisforFilghtsToday(String fromPlace, String toPlace) throws InterruptedException {

        webDriverWaitExplicit = new WebDriverWait( this.driver, Duration.ofSeconds(5) );

        //check if add is present and close it:
        this.checkIfAddPresentAndCloseIt();
        //End - check if add is present and close it:

        this.checkForLoginPopupAndCloseIt();


        //For
        this.driver.findElement(By.xpath("//label[@for='fromCity']") ).click();
        Thread.sleep(700);
        this.driver.findElement(By.cssSelector("div.autoSuggestPlugin input[type='text']") ).click();
        Thread.sleep(700);
        this.driver.findElement(By.cssSelector("div.autoSuggestPlugin input[type='text']") ).sendKeys(fromPlace);
        Thread.sleep(700);
        //clcik selection for dropdown
        this.driver.findElement( By.cssSelector("div.autoSuggestPlugin div[id='react-autowhatever-1'] ul.react-autosuggest__suggestions-list li:nth-child(1)") ).click();

        //To
        this.driver.findElement(By.xpath("//label[@for='toCity']") ).click();
        Thread.sleep(700);
        this.driver.findElement(By.cssSelector("div.searchToCity input#toCity") ).sendKeys(toPlace);
        Thread.sleep(700);
        this.driver.findElement(By.cssSelector("div.searchToCity div.autoSuggestPlugin ul[role='listbox'] li:nth-child(1)") ).click();

        Thread.sleep(400);
        //departure already opens
            //this.driver.findElement(By.cssSelector("label[for='departure']") ).click();
            //Thread.sleep(400);
        webDriverWaitExplicit.until(ExpectedConditions.visibilityOfElementLocated( By.cssSelector("div.datePickerContainer")));
        Thread.sleep(400);
        this.driver.findElement(By.cssSelector("div.datePickerContainer div.DayPicker-Months div.DayPicker-Day--selected") ).click();
        Thread.sleep(700);
        this.driver.findElement(By.cssSelector("a.widgetSearchBtn") ).click();

        //using implicit wait.
        //we cant change the implicit wait again to 15 seconds as its set once.

        //check if a page is fully loaded using js executer.
        //lambda notation
        //wait.until( webDriver -> jsExecutor.executeScript("return document.readyState").equals("complete"));
        // Not working - check if a page is fully loaded using js executer.

        //used explicit wait


    }

    private void checkForLoginPopupAndCloseIt() {
        //Check for modal Popup and close it - it shows sometiems , it dosent show sometimes.
        try {
             webDriverWaitExplicit.until(ExpectedConditions.visibilityOfElementLocated( By.xpath("//div[@class='loginSliderCompWrapper']") ));
             //click the closes button inside modal.
             this.driver.findElement(By.xpath("//span[@data-cy='closeModal']") ).click();
         } catch( Exception e ) {
             //no popup showed.
             System.out.println( " Popup not displayed : " +  e.getMessage() );
         }
    }

    private void checkIfAddPresentAndCloseIt() {

        try{
            webDriverWaitExplicit.until(ExpectedConditions.visibilityOfElementLocated( By.xpath("//div[@id='webengage-notification-container']") ));
            WebElement wb_add_container = this.driver.findElement( By.xpath("//div[@id='webengage-notification-container']") );
            Utils.getOrCreateUtilsInstance( this.driver ).removeWebElementIfPresent( wb_add_container );

        } catch ( Exception e ) {
            //no popup showed.
            System.out.println( " Popup add not found : " +  e.getMessage() );

        }
    }

    public void login(String username, String password) {

    }
}
