package mavenforjenkins;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.Optional;
import io.github.bonigarcia.wdm.WebDriverManager;

public class UITest 
{

	
	@Test
	@Parameters("Browser")
	public void startBrowser(@Optional("chrome") String browserName) {
  	System.out.println("Parameter value is " + browserName);
   	WebDriver driver = null;

   	if (browserName != null && browserName.contains("Chrome")) {
        	WebDriverManager.chromedriver().setup();
        	ChromeOptions opt = new ChromeOptions();
        	opt.addArguments("--headless");
        	opt.addArguments("--no-sandbox");
        	opt.addArguments("--disable-dev-shm-usage");
        	driver = new ChromeDriver(opt);
    	} 
	else if (browserName != null && browserName.contains("Edge")) {
        	WebDriverManager.edgedriver().setup();
        	driver = new EdgeDriver();
    	}

    	if (driver != null) {
        	driver.manage().window().maximize();
        	driver.get("https://opensource-demo.orangehrmlive.com/");
        	Assert.assertTrue(driver.getTitle().contains("Orange"), "Title does not match");
        	driver.quit();
    	} 
	else 
	{
        	System.out.println("Invalid browser name or browser is null");
    }
}

	
	
}
