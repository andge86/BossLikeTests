package utils;

import PageObjects.BossLikePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Thread.currentThread;

public class BaseFlow {


    private WebDriver driver;
    private WebDriverWait wait;
    private int quantityOfPagesToFollow;


    public BaseFlow(WebDriver driver, WebDriverWait wait, int quantityOfPagesToFollow) {
        this.driver = driver;
        this.wait = wait;
        this.quantityOfPagesToFollow = quantityOfPagesToFollow;

    }


    public void makeRequiredClicks(String bossLikeLogin, String bossLikePassword,
                                   String instagramLogin, String instagramPassword) {


        BossLikePage bossLikePage = new BossLikePage(driver, wait);

        try {
            bossLikePage.LogInToBossLike(bossLikeLogin, bossLikePassword)
                    .GoToInstagramPage(instagramLogin)
                    .LogInToInstagram(instagramLogin, instagramPassword)
                    .goToInstagramFollowingTasks();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(currentThread().getName() + ": Error happened during login or path to following tasks: ");
            System.out.println(e.getMessage());
        }


        for (int j = 0; j < quantityOfPagesToFollow; j++) {

            System.out.println(currentThread().getName() + ": Remaining pages: " + (quantityOfPagesToFollow - j));
            try {
                bossLikePage.followOnePage()
                        .moreTasksLinkClick();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(currentThread().getName() + ": Error happened during following operations: ");
                System.out.println(e.getMessage());
            }
        }
    }
}
