package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class HomePage extends BasePage{
    @FindBy(xpath = "//div[@class='listbox']//a[@href='/computers']")
    public WebElement computers;
    @FindBy(xpath = "//li[@class='inactive']//a[@href='/desktops']")
    public WebElement desktops;
    @FindBy(xpath = "//select[@name = 'products-pagesize']")
    public WebElement displayPerPageDropdown;
    @FindBy(xpath = "//select[@name = 'products-orderby']")
    public WebElement sortByDropdown;
    @FindBy(xpath = "(//input[@class='button-2 product-box-add-to-cart-button'])[1]")
    public WebElement mostExpensiveItem;
    @FindBy(xpath = "//input[@class='button-1 add-to-cart-button']")
    public WebElement addToCart;
    @FindBy(xpath = "//span[@class='cart-qty']")
    public WebElement shoppingCardItem;
    @FindBy(xpath = "//div[@class='item-box']")
    public List<WebElement> desktopItems;
    @FindBy(xpath = "//div[@class='bar-notification success']")
    public WebElement notificationBar;
    @FindBy(xpath = "//label[@for='product_attribute_74_5_26_82']")
    public WebElement processor;
    @FindBy(xpath = "//label[@for='product_attribute_74_6_27_85']")
    public WebElement ram;
    @FindBy(xpath = "//label[@for='product_attribute_74_8_29_88']")
    public WebElement imageViewerCheckBox;
    @FindBy(xpath = "//label[@for='product_attribute_74_8_29_89']")
    public WebElement officeSuiteCheckBox;
    @FindBy(xpath = "//label[@for='product_attribute_74_8_29_90']")
    public WebElement otherOfficeSuiteCheckBox;

    public HomePage(WebDriver driver) {
        super(driver);
    }
    public void clickComputers(){
        computers.click();
    }
    public void clickDesktops(){
        desktops.click();
    }
    public void selectFromDisplayDropdown(String number){
        Select select = new Select(displayPerPageDropdown);
        select.selectByVisibleText(number);
    }
    public int getNumberOfItems(){
        return desktopItems.size();
    }
    public String getNumberOfItems2(){
        return shoppingCardItem.getText();
    }
    public void selectFromSortByDropdown(){
        Select select = new Select(sortByDropdown);
        select.selectByVisibleText("Price: High to Low");
    }
    public void addMostExpensiveItem(){
        mostExpensiveItem.click();
    }
    public void addItemToCart(){
        addToCart.click();
    }
    public void setProcessor(){
        processor.click();
    }
    public void setRam(){
        ram.click();
    }
    public void setImageViewerCheckBox(){
        imageViewerCheckBox.click();
    }
    public void setOfficeSuiteCheckBox(){
        officeSuiteCheckBox.click();
    }
    public void setOtherOfficeSuiteCheckBox(){
        otherOfficeSuiteCheckBox.click();
    }
    public void openShoppingCart(){
        shoppingCardItem.click();
    }
}
