package testpages;

import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import pages.ProdutsDisplayPage;

import pages.LoginPage;

public class TestProductDisplay extends TestBase {

	ProdutsDisplayPage productsPage ;
	LoginPage login;
	CSVReader reader;

	@BeforeMethod
	public void userLogin() throws CsvValidationException, IOException {

		String csvFile = System.getProperty("user.dir") + "/src/test/java/data/data.csv";
		reader = new CSVReader(new FileReader(csvFile));
		String[] csvCell = reader.readNext(); 
		String username = csvCell[0];
		String password = csvCell[1].trim();


		login = new LoginPage(driver);
		login.userCanLoginByValidCredentials(username, password);
	}

	@Test
	public void validateProductImageDisplayed() {

		productsPage = new ProdutsDisplayPage(driver);

		List<WebElement> productImages = driver.findElements(By.cssSelector(".inventory_item_img"));

		for (WebElement productImage : productImages) {
			assertTrue(productImage.isDisplayed(), "Product image is not displayed!");
		}
	}

	@Test
	public void verifyAllProductNames() {
		productsPage = new ProdutsDisplayPage(driver);
		List<String> productNames = productsPage.getAllProductNames();

		assertEquals(productNames.size(), 6, "Expected 6 products on the page.");


		for (String name : productNames) {
			assertNotNull(name, "Product name should not be null.");
			assertTrue(!name.isEmpty(), "Product name should not be empty.");
		}
	}

	@Test
	public void verifyAllProductDescriptions() {
		productsPage = new ProdutsDisplayPage(driver);
		List<String> productDescriptions = productsPage.getAllProductDescriptions();

		assertEquals(productDescriptions.size(), 6, "Expected 6 product descriptions on the page.");


		for (String description : productDescriptions) {
			assertNotNull(description, "Product description should not be null.");
			assertTrue(!description.isEmpty(), "Product description should not be empty.");
		}
	}

	@Test
	public void verifyAllProductPrices() {
		productsPage = new ProdutsDisplayPage(driver);
		List<String> productPrices = productsPage.getAllProductPrices();

		assertEquals(productPrices.size(), 6, "Expected 6 product prices on the page.");


		for (String price : productPrices) {
			assertNotNull(price, "Product price should not be null.");
			assertTrue(price.startsWith("$"), "Product price should start with '$'.");
			assertTrue(price.matches("\\$\\d+\\.\\d{2}"), "Product price should match format '$xx.xx'.");
		}
	}

	@Test
	public void validateAddToCartButtonExistsAndIsClickableForAllProducts() {
		productsPage = new ProdutsDisplayPage(driver);


		for (WebElement addToCartButton : productsPage.getAllAddToCartButtons()) {

			assertTrue(addToCartButton.isDisplayed(), "'Add to Cart' button is not displayed!");
			assertTrue(addToCartButton.isEnabled(), "'Add to Cart' button is not clickable!");
		}
	}

	@Test
	public void validateProductsAreSortedByPriceLowToHigh() {

		productsPage = new ProdutsDisplayPage(driver);

		productsPage.sortByPriceLowToHigh();

		List<Double> displayedPrices = productsPage.getDisplayedPrices();

		for (int i = 0; i < displayedPrices.size() - 1; i++) {
			assertTrue(
					displayedPrices.get(i) <= displayedPrices.get(i + 1),
					"Products are not sorted by price in ascending order."
					);
		}
	}
}
