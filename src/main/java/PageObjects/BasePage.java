package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class BasePage {

    WebDriver driver;
    private WebDriverWait wait;


    BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }


    void waitAndClick(WebElement webElement) throws InterruptedException {
        Thread.sleep(600);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        webElement.click();
    }

    void waitAndSendText(WebElement webElement, String text) throws InterruptedException {
        Thread.sleep(600);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        webElement.sendKeys(text);
    }

    void waitAndGetText(WebElement webElement) throws InterruptedException {
        Thread.sleep(600);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        System.out.println(webElement.getText());
    }

}
