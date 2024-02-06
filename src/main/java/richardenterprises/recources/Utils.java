package richardenterprises.recources;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utils {

    private static Utils utils;
    private WebDriver driver;

    private Utils( WebDriver driver ) {
        this.driver = driver;
    }

    public static Utils getOrCreateUtilsInstance( WebDriver driver ) {
        if( utils == null ) {
            utils = new Utils( driver );
        }
        return utils;
    }

    public void removeWebElementIfPresent( WebElement webElement ) {

        if( webElement != null ) {
            //send js to remove it.
            JavascriptExecutor jse_jse = ( JavascriptExecutor ) this.driver;
            String javascript = "arguments[0].remove();";
            jse_jse.executeScript( javascript, webElement );

        }
    }


    public void scrollToElement( WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) this.driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void executeJavascript( WebElement element, String script) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) this.driver;
        jsExecutor.executeScript( script, element);
    }

    public boolean findStringInAllWindowHandles( String targetString ) {

        // Get all window handles
        boolean found = false;

        int i = 1;
        String main_window_handle = "";
        for (String windowHandle : driver.getWindowHandles()) {
            if( i==1) {
                main_window_handle = windowHandle;
            }
            // Switch to the window
            driver.switchTo().window(windowHandle);
            // Get the current window's URL
            String currentURL = driver.getCurrentUrl();
            // Check if the URL contains the target string
            if (currentURL.contains(targetString)) {
                found = true;
                break;
            }
            i++;
        }
        driver.switchTo().window(main_window_handle);
        return found;
    }

}
