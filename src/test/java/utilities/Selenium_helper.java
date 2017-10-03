package utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class Selenium_helper 
{
	public static WebDriver driver;
	
	public void default_wait() 
	{
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	
	public void navigate_to(String url)
	{
		driver.navigate().to(url);
		default_wait();
	}
	
	public void click_xpath_element(String element) 
	{
		default_wait();
		driver.findElement(By.xpath(element)).click();
		default_wait();
	}
	
	public void click_xpath_element_hard(String element)
	{
		WebElement item = driver.findElement(By.xpath(element));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView()", item); 
		item.click();
		default_wait();
	}
	
	public void hover_xpath_element(String element)
	{
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath(element))).build().perform();
	}
	
	public void click_linkText_element(String element) 
	{
		default_wait();
		driver.findElement(By.linkText(element)).click();
	}	
	
	public String get_text_of_xpath_element(String element)
	{
		default_wait();
		WebElement element_xpath = driver.findElement(By.xpath(element));
		String text = element_xpath.getText();
		return text;
	}
	
	public void sendKey_to_xpath_element(String element, String key)
	{
		default_wait();
		WebElement send_key = driver.findElement(By.xpath(element));
		send_key.sendKeys(key);
	}
	
	public void submitKey_to_xpath_element(String element, String key)
	{
		default_wait();
		WebElement send_key = driver.findElement(By.xpath(element));
		send_key.sendKeys(key);
		send_key.submit();
	}
}