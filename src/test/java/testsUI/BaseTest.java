package testsUI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import pages.HomePage;
import pages.ShoppingCartPage;

import static driver.WebDriverSetup.getChromeDriver;

public class BaseTest {
    public static WebDriver driver = getChromeDriver();
    HomePage homePage = new HomePage(driver);
    ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
    public WebDriverWait waiter = new WebDriverWait(driver, 5000);


    @AfterClass
    public static void quitBrowser(){
        driver.quit();
    }

}
