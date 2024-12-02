package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageBase {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "user-name")
    WebElement usernameTxtBox;

    @FindBy(id = "password")
    WebElement passwordTxtBox;

    @FindBy(id = "login-button")
    WebElement loginBtn;

    @FindBy(id = "react-burger-menu-btn")
    WebElement menuBtn;

    @FindBy(id = "logout_sidebar_link")
    WebElement logoutBtn;
    
    @FindBy(css = "h3[data-test='error']")
    WebElement errorMessage;

    public void userCanLoginByValidCredentials(String username, String password)  {
  
    	usernameTxtBox.sendKeys("standard_user");
    	passwordTxtBox.sendKeys("secret_sauce");
    	 clickButton(loginBtn);
    	
    	/*   setTextElementText(usernameTxtBox, username);
        passwordTxtBox.clear();
        setTextElementText(passwordTxtBox, password);
        clickButton(loginBtn);*/
    }

    public void userCannotLoginByInvalidCredentials(String invalidUsername, String invalidPassword) {
        setTextElementText(usernameTxtBox, invalidUsername);
        passwordTxtBox.clear();
        setTextElementText(passwordTxtBox, invalidPassword);
        clickButton(loginBtn);
    }

    public void userCanLogout()  {
        clickButton(menuBtn);
        clickButton(logoutBtn);
    }
   
    public String getErrorMessage() {
        return (errorMessage).getText();
    }
    public boolean isAtLoginPage() {
       
        return loginBtn.isDisplayed();
    }
}
