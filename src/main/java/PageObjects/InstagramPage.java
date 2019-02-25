package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Thread.currentThread;

public class InstagramPage extends BasePage {

    WebDriver driver;
    WebDriverWait wait;


    @FindBy(xpath = "//button[text() = 'Log In']")
    WebElement logInButtonToOpenLogInForm;
    @FindBy(name = "username")
    WebElement usernameTextField;
    @FindBy(name = "password")
    WebElement passwordTextField;
    @FindBy(xpath = "//button[@type = 'submit']")
    WebElement logInButton;


    @FindBy(xpath = "//button[text() = 'Following']")
    WebElement followingLink;
    @FindBy(xpath = "//button[text() = 'Unfollow']")
    WebElement unfollowLink;
    @FindBy(xpath = "//button[text() = 'Follow']")
    WebElement followLink;


    public InstagramPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);

    }


    public BossLikePage LogInToInstagram(String login, String password) throws InterruptedException {

        driver.get("https://www.instagram.com/" + login + "/");
        System.out.println(currentThread().getName() + ": Logging in to instagram as " + login);

        waitAndClick(logInButtonToOpenLogInForm);
        waitAndSendText(usernameTextField, login);
        waitAndSendText(passwordTextField, password);
        waitAndClick(logInButton);
        Thread.sleep(2500);
        System.out.println(currentThread().getName() + ": Logged in to instagram as " + login);
        driver.close();

        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }


        return new BossLikePage(driver, wait);
    }


    public InstagramPage reFollowingLogic() throws InterruptedException {

        System.out.println(currentThread().getName() + ": Seems already following, applying re-follow logic");
        waitAndClick(followingLink);
        waitAndClick(unfollowLink);
        waitAndClick(followLink);
        System.out.println(currentThread().getName() + ": Re-followed");

        return this;
    }

    public InstagramPage followLinkClick() throws InterruptedException {

        waitAndClick(followLink);
        System.out.println(currentThread().getName() + ": Followed");
        return this;
    }
}
