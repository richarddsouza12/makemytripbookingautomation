package richardenterprises.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookingSummaryPage {

    public WebDriver driver;
    public WebDriverWait webDriverWaitExplicit;

    public BookingSummaryPage( WebDriver driver ){
        this.driver = driver;
    }

    public boolean checkIfProceedToPayButtonIsPresent() {
        try {
            driver.findElement( By.xpath("//*[@id='ACKNOWLEDGE_SECTION']//button") );
            return true;
        } catch ( Exception e ) {
            return false;
        }

    }

}
