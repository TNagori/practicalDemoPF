package step.examples.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import step.handlers.javahandler.AbstractKeyword;
import step.handlers.javahandler.Keyword;

import java.time.Duration;

/**
 * This advanced example shows how to use Keyword properties and inputs in order to build reusable Keywords
 * It also shows how to use Keyword hooks in order to automatically generate screenshots
 * and attach them to the Keyword output on error.
 */
public class PaymentSeleniumKeywordExample extends AbstractKeyword {

    private WebDriver driver;

    @Keyword(name = "SetPayment")
    public void paymentPage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Exense\\chromedriver.exe");
        ChromeOptions co = new ChromeOptions();
        co.setBinary("C:\\Exense\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver(co);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        // Read the URL from the keyword properties. These properties can be defined as Parameter in Step
         driver.get("https://www.postfinance.ch/ap/ga/ob/html/finance/transfer/enter-payment");


        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        Thread.sleep(3000);
        //driver.switchTo().defaultContent();
        WebElement fieldIBAN;
        fieldIBAN = driver.findElement(By.xpath("//input[@placeholder='IBAN/Konto, Name, Zahlungsinfo']"));
        fieldIBAN.click();
        Actions act = new Actions(driver);
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys("CH03");

        WebElement chooseFirst = driver.findElement(By.id("smart-bar-flyout"));
        chooseFirst.click();
        act.sendKeys(Keys.TAB).perform();
        act.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window, scrollBy(0,250)");

        WebElement amount = driver.findElement(By.xpath("//input[@id='formly_30_amount-input_debitAmount_0']"));
        amount.getText();

        js.executeScript("window, scrollBy(0,400)");

        WebElement clickNextButton = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        clickNextButton.click();

        WebElement overviewAmount = driver.findElement(By.xpath("//span[@class=\"ng-star-inserted\"][2]"));
        overviewAmount.getText();

        // assertEquals(amount, overviewAmount);

        WebElement clickSend = driver.findElement(By.xpath("//button[@data-cy=\"send-button\"]"));
        clickSend.click();

        WebElement message = driver.findElement(By.xpath("//p[contains(text(),\"Demo\")]"));
        message.getText();

        WebElement clickBack = driver.findElement(By.xpath("//button[@data-cy=\"back-nav-button\"]"));
        clickBack.click();

    }


    }





