import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Initializator {


    public WebDriver initDriver() {

        WebDriver driver;

        System.out.println(Thread.currentThread().getName() + ": Initializing WedDriver");
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=en-US");
        options.addArguments("disable-geolocation");
        options.addArguments("headless");
        options.addArguments("window-size=1200x600");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3L, TimeUnit.SECONDS);

        return driver;
    }

    public WebDriverWait initWait(WebDriver driver) {
        return new WebDriverWait(driver, 10);
    }


    public void driverShutdown(WebDriver driver) {
        if (driver != null) {
            driver.close();
            driver.quit();
            System.out.println(Thread.currentThread().getName() + ": Closed WebDriver");
        }
    }
}
