package testpages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileReader;
import java.io.IOException;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import pages.LoginPage;

public class TestloginwithValidAndwithInvalidCredentials extends TestBase {

    LoginPage login;
    CSVReader reader;

    @Test
    public void validateLoginFunctionlitywithvalidcredentials() throws CsvValidationException, IOException {
        String csvFile = System.getProperty("user.dir") + "/src/test/java/data/data.csv";
        reader = new CSVReader(new FileReader(csvFile));

        String[] csvCell;
        while ((csvCell = reader.readNext()) != null) {
            String username = csvCell[0];
            String password = csvCell[1].trim();
           
            login = new LoginPage(driver);
            login.userCanLoginByValidCredentials(username, password);
            assertTrue(driver.getTitle().contains("Swag Labs"));
        }
    }

    @Test
    public void validateLoginfunctionalityWithInvalidCredentials() throws CsvValidationException, IOException {
        String csvFile = System.getProperty("user.dir") + "/src/test/java/data/data.csv";
        reader = new CSVReader(new FileReader(csvFile));

        String[] csvCell;
        while ((csvCell = reader.readNext()) != null) {
            String invalidUsername = csvCell[2];
            String invalidPassword = csvCell[3];
            
            login = new LoginPage(driver);
            login.userCannotLoginByInvalidCredentials(invalidUsername, invalidPassword);
            assertEquals(login.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
        }
    }
    
    @Test
    public void testUserCanLogout() throws CsvValidationException, IOException {
    	   String csvFile = System.getProperty("user.dir") + "/src/test/java/data/data.csv";
           reader = new CSVReader(new FileReader(csvFile));

           String[] csvCell;
           while ((csvCell = reader.readNext()) != null) {
               String username = csvCell[0];
               String password = csvCell[1].trim();
    	 login = new LoginPage(driver);
    	 login.userCanLoginByValidCredentials(username, password);
    	 login.userCanLogout();

           assertTrue(login.isAtLoginPage(), "User did not redirected to the login page after logging out.");
    }
}
}