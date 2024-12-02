package testpages;



import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utilities.helper;

public class TestBase {

    public static WebDriver driver;
    public static String downloadPath = System.getProperty("user.dir") + "\\Downloads";

    @BeforeMethod(alwaysRun = true)
    @Parameters({ "browser" })
    public void startDriver(@Optional("chrome") String browserName) {
        if (driver == null) {
            // Initialize the driver based on the browser type
            if (browserName.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
                driver = new ChromeDriver();
            } else if (browserName.equalsIgnoreCase("firefox")) {
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
                driver = new FirefoxDriver();
            } else if (browserName.equalsIgnoreCase("edge")) {
                System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "/drivers/msedgedriver.exe");
                driver = new EdgeDriver();
            }

            // Maximize the window and set timeouts
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            driver.navigate().to("https://www.saucedemo.com/"); // Navigate to the login page
        }
    }

    @AfterMethod(alwaysRun = true)
    public void stopDriver(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // Only take a screenshot if the driver is not null
            if (driver != null) {
                System.out.println("Test case failed!");
                System.out.println("Taking a screenshot....");
                helper.captureScreenshot(driver, result.getName());
            }
        }

        if (driver != null) {
            // Quit the driver after each test
            driver.quit();
            driver = null;  
        }
    }
}
