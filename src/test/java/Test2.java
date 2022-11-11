import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import junit.framework.Assert;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver.Timeouts;


@TestInstance(Lifecycle.PER_CLASS)
class Test2 {
		
		
		WebDriver browser;
		String URL = "http://auto.drom.ru/";		
		JavascriptExecutor js;

		String expected = "Удалено из избранного. Отменить удаление", actual;
		String path = "C:\\Test_screen.png";
		WebElement element;
		//File scr = ((TakesScreenshot)browser).getScreenshotAs(OutputType.FILE);
		
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
	@Tag("positive")
	//@RepeatedTest(value = 3, name = RepeatedTest.LONG_DISPLAY_NAME)
	void test3()   {
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.findElement(By.xpath("//div[@class='css-16566ot e173iafn0']")).click();
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.findElement(By.id("sign")).sendKeys("79528023269");
		browser.findElement(By.id("password")).sendKeys("Bhkfyltw198206");
		browser.findElement(By.id("signbutton")).click();
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.findElement(By.xpath("//div[@class='flicking-camera']/a[2]")).click();
		
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		element = browser.findElement(By.xpath("//span[text()='Добавить в избранное']")); 
	
		js.executeScript("window.scrollBy(0,600)");
		js.executeScript("arguments[0].click()", element);
		//js.executeScript("arguments[0].scrollIntoView()", element);		
		//js.executeScript("alert('Объявление добавлено в избранное.\r\n" + 
		//		"Вы получите уведомление при изменении цены.')");
			
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.findElement(By.xpath("//div[@class='css-m1dp3y e153m2qo1 notification-group-enter-done']/div/div[2]/div/a")).click();
		
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.findElement(By.xpath("//a[@class='removeBookmark']")).click();;
		actual = browser.findElement(By.id("flashText")).getText();
		System.out.println(actual);
		assertEquals(expected, actual);
		
		
	}

}
