package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends BasePage {
    @FindBy(xpath = "(//td[@class='product'])[2]")
    public WebElement secondAddedItem;
    @FindBy(xpath = "(//span[@class='product-unit-price'])[2]")
    public WebElement price;
    @FindBy(xpath = "(//td[@class='remove-from-cart'])[2]")
    public WebElement removeFromCart;
    @FindBy(xpath = "//input[@value='Update shopping cart']")
    public WebElement updateCart;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }
    public boolean isProductVisible(){
        return secondAddedItem.isDisplayed();
    }
    public String getPrise(){
        return price.getText();
    }
    public void removeItemFromCart(){
        removeFromCart.click();
        updateCart.click();
    }
}
