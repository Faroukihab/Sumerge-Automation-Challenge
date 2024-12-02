package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ProdutsDisplayPage extends PageBase {

	public ProdutsDisplayPage(WebDriver driver) {
		super(driver);

		
	}
	
	 @FindBy(css = ".inventory_item")
	 List<WebElement> allProductsList;
	 
	 @FindBy(css = ".inventory_item_name")
	 List<WebElement> allProductsNames;
	 
	 @FindBy(css = ".inventory_item_price")
	 List<WebElement> allProductsPrices;
	 
	 @FindBy(css = ".inventory_item_desc")
	 List<WebElement> allProductsDescription;
	 
	 @FindBy(css = ".btn_inventory")
	 List<WebElement> allProductsAddtocartBtns;
	 
	 @FindBy(css = ".product_sort_container")
	    WebElement sortDropdown;
	 
	 @FindBy(css = ".inventory_item_img")
	 WebElement allProductsimages;
	 
	
	    public int getTotalProductsCount() {
	        return allProductsList.size();
	    }
	    

	   
	    public List<String> getAllProductNames() {
	        return allProductsNames.stream().map(WebElement::getText).toList();
	    }

	    
	    public List<String> getAllProductPrices() {
	        return allProductsPrices.stream().map(WebElement::getText).toList();
	    }

	   
	    public List<String> getAllProductDescriptions() {
	        return allProductsDescription.stream().map(WebElement::getText).toList();
	    }

	   
	    public void clickAddToCartByIndex(int index) {
	    	allProductsAddtocartBtns.get(index).click();
	    }

	   
	    public String getProductDetailsByIndex(int index) {
	        return String.format(
	            "Name: %s | Price: %s | Description: %s",
	            allProductsNames.get(index).getText(),
	            allProductsPrices.get(index).getText(),
	            allProductsDescription.get(index).getText()
	        );
	    }
	    public WebElement getProductImage() {
	        return allProductsimages;
	    }
	    public List<WebElement> getAllAddToCartButtons() {
	        return allProductsAddtocartBtns;
	    }
	    public void sortByPriceLowToHigh() {
	       
	        Select sortSelect = new Select(sortDropdown);
	        sortSelect.selectByVisibleText("Price (low to high)");
	    }

	    public List<Double> getDisplayedPrices() {
	        List<Double> prices = new ArrayList<>();
	        for (WebElement priceElement : allProductsPrices) {
	        
	            String priceText = priceElement.getText().replace("$", "");
	            prices.add(Double.parseDouble(priceText));
	        }
	        return prices;
	    }
}
