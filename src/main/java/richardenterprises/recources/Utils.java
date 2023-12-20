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

}
