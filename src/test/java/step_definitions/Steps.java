package step_definitions;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utilities.Selenium_helper;

public class Steps extends Selenium_helper
{
	
	public static String BaseUrl;
	
	@Before
	public void setUp() throws IOException
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@After
	public void tearDown()
	{
		driver.quit();
	}
	
	@Given("^(?:|I) open '(.*?)'$")
	public void open_url(String url)
	{
		navigate_to(url);
	}
	
	@And("^(?:|I) want to start shoping using '(.*?)'$")
	public void select_shop(String shop)
	{
		String shop_format = shop.substring(0,1).toUpperCase() + shop.substring(1).toLowerCase();
		
		click_xpath_element("//a[text()='"+shop_format+"']");
		click_linkText_element("×");
	}
	
	@Given("^(?:|I) click on '(.*?)' wear$")
	public void click_on_category(String category)
	{
		click_xpath_element("//a[text()='"+category+"']");
	}
	
	@And("^(?:|I) search for the word '(.*?)'$")
	public void search_for_word(String word)
	{
		submitKey_to_xpath_element("//input[@type='text'][@placeholder='Search']", word);
	}
	
	@And("^(?:|I) want to make the following filters on search results:")
	public void filter_search_results(DataTable filters) throws InterruptedException
	{
		List<List<String>> data = filters.raw();
		int cells = data.get(0).size();
		for (int x = 0; x < cells; x=x+1)
		{
			String cell = data.get(0).get(x);
			String[] cell_values = cell.split(":");
			String filter = cell_values[0]; 
			String selection = cell_values[1];
			Thread.sleep(5000);
			click_xpath_element_hard("//span[text()='"+filter+"']");
			Thread.sleep(2000);
			click_xpath_element("//li/a[text()='"+selection+"']");
			Thread.sleep(2000);
			click_xpath_element_hard("//div[text()='Save']");
		}
				
	}
	
	@And("^(?:|I) click on item number (\\d+)$")
	public void click_on_item(String item_number) throws IOException, InterruptedException 
	{		
		Thread.sleep(5000);

		//click_xpath_element("//z-grid-item["+item_number+"]/div/a/div/img");
		click_xpath_element("(//DIV[@class='z-nvg-cognac_labels-2pOCU'])["+item_number+"]");
	}
	
	@And("^(?:|I) select '(.*?)' size$")
	public void select_the_size(String size) 
	{
		click_xpath_element("//div[text()='Choose your size']");
		click_xpath_element("//text()[.='"+size+"']/ancestor::div[1]");
	}
	
	@And("^(?:|I) add the item to shopping basket$")
	public void add_to_shopping_basket() 
	{
		click_xpath_element("//button/span[4]/span/*[local-name()='svg']");
	}
	
	@And("^(?:|I) click back$")
	public void click_back()
	{
		driver.navigate().back();
	}
	
	@When("^(?:|I) open the shopping basket$")
	public void open_shopping_basket() throws InterruptedException
	{
		click_xpath_element("//a[@href='/cart/']");
	}
	
	@Then("^(?:|I) should see (\\d+) items in the shopping basket$")
	public void both_items_should_be_displayed(Integer items_number) throws IOException
	{
		String text = get_text_of_xpath_element("//h4[text()[contains(.,'My Bag')]]");
		Assert.assertEquals("MY BAG ("+items_number+")", text.trim().toUpperCase());
	}
	
}