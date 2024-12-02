package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddAndRemoveProductstoCart extends PageBase{

	public AddAndRemoveProductstoCart(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(css = ".inventory_item")
    private List<WebElement> allProducts;

    @FindBy(css = ".btn_inventory")
    private List<WebElement> addToCartButtons;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(css = ".btn_inventory")
    private List<WebElement> removeFromCartButtons;

   

 
    public void addProductToCart(int productIndex) {
        WebElement addButton = addToCartButtons.get(productIndex);
        addButton.click();
       
    }

  
    public void removeProductFromCart(int productIndex) {
        WebElement removeButton = removeFromCartButtons.get(productIndex);
        removeButton.click();

       
    }
    public String getCartBadgeCount() {
        return cartBadge.getText().trim();
    }

    public boolean isCartBadgeDisplayed() {
        try {
            return cartBadge.isDisplayed();
        } catch (Exception e) {
            return false; 
        }
}
}