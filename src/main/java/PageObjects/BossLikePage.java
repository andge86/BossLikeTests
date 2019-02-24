package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BossLikePage extends BasePage {

    WebDriver driver;
    WebDriverWait wait;


    @FindBy(id = "User_loginLogin")
    WebElement usernameTextField;
    @FindBy(id = "User_passwordLogin")
    WebElement passwordTextField;
    @FindBy(name = "submitLogin")
    WebElement logInButton;

    @FindBy(xpath = "//div[@class = 'media-info-desc text-overflow']")
    WebElement emailText;

    @FindBy(xpath = "//a[@class = 'dropdown-toggle nav-item_sm']")
    WebElement menuLink;
    @FindBy(xpath = "//ul[@class = 'dropdown-menu']//a[@href = '/profile/']")
    WebElement profileLink;
    @FindBy(xpath = "//div[@class = 'media-info']//span[@class = 'media-link']")
    WebElement goToInstagramLink;

    @FindBy(xpath = "//a[@href = '/tasks/']")
    WebElement tasksLink;
    @FindBy(id = "panelGroupMenu_h_3")
    WebElement instagramTasksLink;
    @FindBy(id = "list-3-3")
    WebElement instagramTaskFollowLink;

    @FindBy(xpath = "//div[@id = 'task_more']/button")
    WebElement moreTasksLink;

    @FindBy(xpath = "//article//a[@class = 'do do-task btn btn-sm btn-primary btn-block']")
    List<WebElement> allFollowTasksButtons;


    public BossLikePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);

    }

    public BossLikePage LogInToBossLike(String username, String password) throws InterruptedException {

        driver.get("https://bosslike.ru/login/");
        System.out.println("Logging in to bosslike as " + username);
        waitAndSendText(usernameTextField, username);
        waitAndSendText(passwordTextField, password);
        waitAndClick(logInButton);
        Thread.sleep(2000);
        System.out.println("Logged in to bosslike as " + username);

        return this;
    }

    public InstagramPage GoToInstagramPage(String instagramLogin) throws InterruptedException {

        waitAndClick(menuLink);
        waitAndClick(profileLink);
        waitAndClick(goToInstagramLink);
        Thread.sleep(2000);

        System.out.println("Went to instagram page");

        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        Thread.sleep(2000);
        driver.get("https://www.instagram.com/" + instagramLogin);
        return new InstagramPage(driver, wait);

    }


    public BossLikePage goToInstagramFollowingTasks() throws InterruptedException {

        waitAndClick(tasksLink);
        waitAndClick(instagramTasksLink);
        waitAndClick(instagramTaskFollowLink);

        System.out.println("Went to instagram following tasks");

        return this;
    }

    public BossLikePage moreTasksLinkClick() throws InterruptedException {

        waitAndGetText(moreTasksLink);
        waitAndClick(moreTasksLink);
        Thread.sleep(4000);

        return this;
    }

    public int quantityOfFpllowTasks() {
        return allFollowTasksButtons.size();

    }


    public BossLikePage followOnePage() throws InterruptedException {

        for (int i = 1; i <= quantityOfFpllowTasks(); i++) {

            String xpath = "//article[" + i + "]//div[@class = 'media-right media-middle task-btn']/*";
            System.out.println("Go: " + xpath);

            WebElement elem;

            try {
                Thread.sleep(1000);
                elem = driver.findElement(By.xpath(xpath));
                System.out.println(elem.getText());
                elem.click();

            } catch (Exception exep) {
                System.out.println("Not find element to click");
                elem = driver.findElement(By.xpath(xpath));
                System.out.println(elem.getText());
                continue;
            }
            Thread.sleep(2500);

            String winHandleBefore = driver.getWindowHandle();

            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle);
            }

            if (driver.getWindowHandles().size() == 2) {
                InstagramPage instagramPage = new InstagramPage(driver, wait);
                Thread.sleep(1000);
                try {
                    instagramPage.followLinkClick();
                } catch (Exception e) {
                    instagramPage.reFollowingLogic();
                }

                Thread.sleep(1000);
                driver.close();
                driver.switchTo().window(winHandleBefore);
                Thread.sleep(1000);
                elem = driver.findElement(By.xpath(xpath));
                System.out.println(elem.getText());
            } else {

                try {
                    Thread.sleep(1000);
                    System.out.println("Seems task was cancelled");
                    elem = driver.findElement(By.xpath(xpath));
                    System.out.println(elem.getText());

                } catch (Exception exe) {
                    System.out.println("No description found, some unpredictable issue");
                }
            }
        }
        return this;
    }
}
