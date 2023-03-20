package testsUI;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WebShop extends BaseTest {

    @Test(priority=1)
    public void checkNumberOfElementsPerPage(){
        driver.get("http://demowebshop.tricentis.com/");
        homePage.clickComputers();
        homePage.clickDesktops();
        homePage.selectFromDisplayDropdown("4");
        Assert.assertEquals(homePage.getNumberOfItems(),4);
    }
    @Test(priority=2)
    public void checkIfMostExpensiveItemIsAddedToCart(){
        driver.get("http://demowebshop.tricentis.com/");
        homePage.clickComputers();
        homePage.clickDesktops();
        homePage.selectFromDisplayDropdown("4");
        homePage.selectFromSortByDropdown();
        homePage.addMostExpensiveItem();
        waiter.until(ExpectedConditions.visibilityOf(homePage.addToCart));
        homePage.addItemToCart();
        waiter.until(ExpectedConditions.visibilityOf(homePage.notificationBar));
        Assert.assertEquals(homePage.getNumberOfItems2(),"(1)");
    }
    @Test(priority=3)
    public void checkIfDesktopIsAddedToCart(){
        driver.get("http://demowebshop.tricentis.com/build-your-own-expensive-computer-2");
        homePage.setProcessor();
        homePage.setRam();
        homePage.setImageViewerCheckBox();
        homePage.setOfficeSuiteCheckBox();
        homePage.setOtherOfficeSuiteCheckBox();
        homePage.addItemToCart();
        waiter.until(ExpectedConditions.visibilityOf(homePage.notificationBar));
        Assert.assertEquals(homePage.getNumberOfItems2(),"(2)");
    }
    @Test(priority=4)
    public void checkIfItemIsInTheCartAndPriceIsCorrect(){
        driver.get("http://demowebshop.tricentis.com/build-your-own-expensive-computer-2");
        homePage.setProcessor();
        homePage.setRam();
        homePage.setImageViewerCheckBox();
        homePage.setOfficeSuiteCheckBox();
        homePage.setOtherOfficeSuiteCheckBox();
        homePage.addItemToCart();
        homePage.openShoppingCart();
        Assert.assertTrue(shoppingCartPage.isProductVisible());
        Assert.assertEquals(shoppingCartPage.getPrise(),"2105.00");
        shoppingCartPage.removeItemFromCart();
    }
}
