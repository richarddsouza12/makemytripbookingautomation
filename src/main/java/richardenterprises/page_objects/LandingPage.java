package richardenterprises.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import richardenterprises.recources.Utils;
import java.time.Duration;

public class LandingPage {

    public WebDriver driver;
    public WebDriverWait webDriverWaitExplicit;

    public final By byLiFromDropdownEmement = By.cssSelector("div.autoSuggestPlugin div[id='react-autowhatever-1'] ul.react-autosuggest__suggestions-list li:nth-child(1)");
    public final By byLiToDropdownEmement = By.cssSelector("div.searchToCity div.autoSuggestPlugin ul[role='listbox'] li:nth-child(1)");
    public final By byFromCityCustomSelectLabel = By.xpath("//label[@for='fromCity']");
    public final By byFromSelectDropdownTextBox = By.cssSelector("div.autoSuggestPlugin input[type='text']");
    public final By byToCityCustomSelectLabel = By.xpath("//label[@for='toCity']");
    public final By byToSelectDropdownTextBox = By.cssSelector("div.searchToCity input#toCity");
    public final By byDatePickerContainer = By.cssSelector("div.datePickerContainer");
    public final By byHighlightedCurrentDay = By.cssSelector("div.datePickerContainer div.DayPicker-Months div.DayPicker-Day--selected");
    public final By bySearhButton = By.cssSelector("a.widgetSearchBtn");

    public final By byDivSliderWrapper = By.xpath("//div[@class='loginSliderCompWrapper']");
    public final By bySpanCloseModal = By.xpath("//span[@data-cy='closeModal']");

    public final By byDivNotifContainer = By.xpath("//div[@id='webengage-notification-container']");

    public LandingPage( WebDriver driver ){
        this.driver = driver;
    }

    public void fillSeachDeatisforFilghtsToday(String fromPlace, String toPlace) throws InterruptedException {

        webDriverWaitExplicit = new WebDriverWait( this.driver, Duration.ofSeconds(5) );

        this.checkIfAddPresentAndCloseIt();

        this.checkForLoginPopupAndCloseIt();

        //From
        this.driver.findElement( byFromCityCustomSelectLabel ).click();
        Thread.sleep(700);
        this.driver.findElement( byFromSelectDropdownTextBox ).click();
        Thread.sleep(700);
        this.driver.findElement( byFromSelectDropdownTextBox ).sendKeys(fromPlace);
        Thread.sleep(1000);
        //clcik selection for dropdown
        this.driver.findElement(byLiFromDropdownEmement).click();

        //To
        this.driver.findElement(byToCityCustomSelectLabel).click();
        Thread.sleep(700);
        this.driver.findElement(byToSelectDropdownTextBox).sendKeys(toPlace);
        Thread.sleep(1000);
        this.driver.findElement(byLiToDropdownEmement).click();

        Thread.sleep(400);
        //departure already opens up
        webDriverWaitExplicit.until( ExpectedConditions.visibilityOfElementLocated( byDatePickerContainer ) );
        Thread.sleep(400);
        this.driver.findElement(byHighlightedCurrentDay).click();
        Thread.sleep(700);

    }

    public void clickSearchButton() {
        this.driver.findElement(bySearhButton).click();
    }

    /**
     * @desc : Check for modal Popup and close it - it shows sometimes , it dosen't show sometimes.
     */
    private void checkForLoginPopupAndCloseIt() {

        try {
            webDriverWaitExplicit.until(ExpectedConditions.visibilityOfElementLocated( byDivSliderWrapper ) );
            this.driver.findElement( bySpanCloseModal ).click();

         } catch( Exception e ) {
             System.out.println( " Popup not displayed : " +  e.getMessage() );
         }
    }

    private void checkIfAddPresentAndCloseIt() {

        try{
            this.webDriverWaitExplicit.until(ExpectedConditions.visibilityOfElementLocated( byDivNotifContainer ) );
            WebElement wb_add_container = this.driver.findElement( byDivNotifContainer );
            Utils.getOrCreateUtilsInstance( this.driver ).removeWebElementIfPresent( wb_add_container );
        } catch ( Exception e ) {
            System.out.println( " Popup add not found : " +  e.getMessage() );
        }
    }

}
