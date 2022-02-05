package challange;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Challange3Test {

	@Test
	public void verifyHigherPriceIteminCart() {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		List<WebElement> elements = driver.findElements(By.cssSelector(".inventory_item_price"));
		double maxPrice = Double.parseDouble(elements.get(0).getText().replace("$", ""));
		double currentPrice;
		int maxPricePosition = 0;
		for (int i = 1; i < elements.size(); i++) {
			currentPrice = Double.parseDouble(elements.get(i).getText().replace("$", ""));
			if (currentPrice > maxPrice) {
				maxPrice = currentPrice;
				maxPricePosition = i;
			}
		}
		elements.get(maxPricePosition).click();
		driver.findElement(By.xpath("(//div[@*='pricebar'])[" + (maxPricePosition + 1) + "]/button")).click();
		driver.findElement(By.xpath("//*[@*='shopping_cart_link']")).click();
		Assert.assertTrue(driver.getPageSource().contains("$" + maxPrice));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}

}
