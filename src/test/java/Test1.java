import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver.Timeouts;


@TestInstance(Lifecycle.PER_CLASS)
class Test1 {
		
		WebDriver browser;
		String URL = "http://auto.drom.ru/";		
		JavascriptExecutor js;
		WebElement element, element1, element2;
		String name = " ̶T̶o̶y̶o̶t̶a̶ ̶H̶a̶r̶r̶i̶e̶r̶, ";
		
		// Chrome "C:\\Program Files\\Chromedriver\\chromedriver.exe";
		// Firefox "C:\\Program Files\\Geckodriver\\geckodriver.exe";
		
	@BeforeAll
	public void setUp() {
		//System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Chromedriver\\chromedriver.exe");
		System.setProperty("webdriver.firefox.FirefoxDriver", "C:\\Program Files\\Geckodriver\\geckodriver.exe");
		browser = new FirefoxDriver();
		//browser = new ChromeDriver();
		js = (JavascriptExecutor)browser;
		browser.get(URL);
		browser.manage().window().maximize();
		
		
		
	}
	@AfterAll
	public void tearDown () throws Throwable {
		Thread.sleep(2000);
		browser.quit();	
	}
	

	@Test
	public void test1() throws Throwable {
			
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.findElement(By.xpath("//div[@class='css-2tak37 e1lm3vns0'][1]/div[1]")).click();
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.findElement(By.xpath("//div[@class='css-u25ii9 e154wmfa0']/div/div[3]")).click();
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.findElement(By.xpath("//div[@class='css-2tak37 e1lm3vns0'][1]/div[@class='css-mwckxy evnwjo70'][2]")).click();
		
		element = browser.findElement(By.xpath("//div[@class='css-2tak37 e1lm3vns0'][1]/div[@class='css-mwckxy evnwjo70'][2]"));
		if(element.isEnabled()) {
			browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			browser.findElement(By.xpath("//input[@class='css-18e5l6y e1207tlp0']")).sendKeys("Harrier");
		}

		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.findElement(By.xpath("//div[@class='css-109956f e1x0dvi10']")).click();
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.findElement(By.xpath("//div[2]/div[@class='css-1k744as e1lm3vns0']/div[2]")).click();
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.findElement(By.xpath("//div[@class='css-u25ii9 e154wmfa0']/div[6]")).click();
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.findElement(By.xpath("//label[@for='sales__filter_unsold']")).click();
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.findElement(By.xpath("//span[text()='Расширенный поиск']")).click();
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.findElement(By.xpath("//input[@data-ftid='sales__filter_mileage-from']")).click();
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.findElement(By.xpath("//input[@data-ftid='sales__filter_mileage-from']")).sendKeys("1");
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.findElement(By.xpath("//button[text()='Год от']")).click();
		
		element1 = browser.findElement(By.xpath("//div[@class='css-u25ii9 e154wmfa0']/div[text()='2007']"));
		element2 = browser.findElement(By.xpath("//button[text()='Год от']"));
		
		js.executeScript("arguments[0].scrollIntoView()", element1);
		js.executeScript("arguments[0].scrollIntoView()", element2);
		//browser.findElement(By.xpath("//div[@class='css-u25ii9 e154wmfa0']/div[text()='2007']")).click();
		js.executeScript("arguments[0].click()", element1);
		
		browser.findElement(By.xpath("//button/div[text()='Показать']")).click();
		
		List<WebElement> title = browser.findElements(By.xpath("//span[@data-ftid='bull_title']"));
		List<WebElement> mileage = browser.findElements(By.xpath("//div[@data-ftid='component_inline-bull-description']/span[@data-ftid='bull_description-item'][5]"));
		int count = title.size();
		int other = mileage.size();
		
		for (int i=0; i< count; i++) {
			title = browser.findElements(By.xpath("//span[@data-ftid='bull_title']"));			
			String parse = title.get(i).getText();
			String[] parseItem = parse.split(", ");
			int parseInt = Integer.parseInt(parseItem[1]);
			//System.out.println(parseItem[0]);
			//assertEquals(name, parseItem);
			assertNotEquals(name, parseItem);
			if (parseInt >= 2007) {
				System.out.println("success");
			}
			else {
				System.out.println("failure " + title );
			}
		}
		
		for (int i=0; i< other; i++) {
			mileage = browser.findElements(By.xpath("//div[@data-ftid='component_inline-bull-description']/span[@data-ftid='bull_description-item'][5]"));			
			assertNotNull(mileage);					
		}
		
	}

}
