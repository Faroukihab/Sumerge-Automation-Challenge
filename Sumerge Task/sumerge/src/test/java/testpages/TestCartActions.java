package testpages;
import java.io.FileReader;
import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import pages.AddAndRemoveProductstoCart;
import pages.LoginPage;
import pages.ProdutsDisplayPage;

public class TestCartActions extends TestBase{

	AddAndRemoveProductstoCart addandremove;
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
    public void testAddProductToCart() {
		addandremove = new AddAndRemoveProductstoCart(driver);

       
		addandremove.addProductToCart(0); 

       
        String expectedBadgeCount = "1"; 
        String actualBadgeCount = addandremove.getCartBadgeCount(); 
        assert actualBadgeCount.equals(expectedBadgeCount) : 
            "Cart badge count mismatch! Expected: " + expectedBadgeCount + ", but found: " + actualBadgeCount;
    }

    @Test
    public void testRemoveProductFromCart() {
    	
    	addandremove = new AddAndRemoveProductstoCart(driver);

    	addandremove.addProductToCart(0);
    	addandremove.removeProductFromCart(0); 
        boolean isBadgeDisplayed = addandremove.isCartBadgeDisplayed(); 
        assert !isBadgeDisplayed : "Cart badge should not be displayed after removing all products!";
    }
}
