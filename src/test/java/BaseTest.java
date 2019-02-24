import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class BaseTest {


    WebDriver driver;
    WebDriverWait wait;


    @BeforeTest
    public void initBrowser() {

        System.out.println("Initializing WedDriver");
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=en-US");
        options.addArguments("disable-geolocation");
     //   options.addArguments("headless");
     //   options.addArguments("window-size=1200x600");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3L, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, 10);
    }

    @AfterTest
    public void driverShutdown() {
        if (driver != null) {
            driver.close();
            driver.quit();
            System.out.println("Closed WebDriver");
        }
    }
}