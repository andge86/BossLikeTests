import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.BaseFlow;

public class FullPathBosslikeTest extends BaseTest {


    private final static int QUANTITY_OF_PAGES = 2;


    @Test(dataProvider = "credentialsData", threadPoolSize = 4)
    public void followOnInstagramTest(String instagramLogin, String instagramPassword, String bosslikeLogin, String bosslikePassword) {

        BaseFlow baseFlow = new BaseFlow(driver, wait, QUANTITY_OF_PAGES);
        baseFlow.makeRequiredClicks(bosslikeLogin, bosslikePassword, instagramLogin, instagramPassword);

    }


    @DataProvider(name = "credentialsData", parallel = true)
    public Object[][] credentialsOfUsers() {

        String[] instagramLogins = System.getenv("INSTAGRAM_LOGINS").split(",");
        String[] instagramPasswords = System.getenv("INSTAGRAM_PASSWORDS").split(",");
        String[] bosslikeLogins = System.getenv("BOSSLIKE_LOGINS").split(",");
        String[] bosslikePasswords = System.getenv("BOSSLIKE_PASSWORDS").split(",");


        return new Object[][]{
                {instagramLogins[0], instagramPasswords[0], bosslikeLogins[0], bosslikePasswords[0]},
                {instagramLogins[1], instagramPasswords[1], bosslikeLogins[1], bosslikePasswords[1]},
                {instagramLogins[2], instagramPasswords[2], bosslikeLogins[2], bosslikePasswords[2]},
                {instagramLogins[3], instagramPasswords[3], bosslikeLogins[3], bosslikePasswords[3]},
        };
    }
}