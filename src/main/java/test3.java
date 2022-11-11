import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

public class test3 {

	public static void main(String[] args) throws IOException {
		
		String URL = "https://auto.drom.ru/";
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Chromedriver\\chromedriver.exe");
		WebDriver browser = new ChromeDriver();
		Actions action = new Actions(browser);
		JavascriptExecutor js;
		WebElement firms, counts;
		int rowNum = 2;
	
		js = (JavascriptExecutor)browser;
		
			HSSFWorkbook book = new HSSFWorkbook(); 
			HSSFSheet sheet = book.createSheet("rezult");
			HSSFRow row = sheet.createRow(rowNum);
			//Заголовки столбцов
			HSSFRow row1 = sheet.createRow(1);
			row1.createCell(0).setCellValue("Фирма");
			row1.createCell(1).setCellValue("Количество объявлений");
		
		
		browser.navigate().to(URL);
		browser.manage().window().maximize();
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.findElement(By.xpath("//a[text()='Другой город']")).click();
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.findElement(By.xpath("//input[@class='css-ni6opf e1bmfvzq0']")).sendKeys("Приморский край");
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.findElement(By.xpath("//div[@data-ga-stats-name='found_city']")).click();
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.findElement(By.xpath("//div[text()='Показать все']")).click();
		 		
		/*
		 //Получаем код страницы
		 String pars = browser.getPageSource();
		//Парсим
		Document doc = Jsoup.parse(pars);
		//Ищем по локатору
		Elements links = doc.select(".css-u4n5gw span:nth-child(2)");
		
		ArrayList<Integer> s = new ArrayList<Integer>();
		for (Element link:links) {
			String parse = link.text();
			//Переводи строку в интовую
			int parseInt = Integer.parseInt(parse);
			//Записываем в список
			s.add(parseInt);
			}
			//Сортировка
		Collections.sort(s);
		//Выбираем 20 
		for (int i = s.size()-1; i>s.size()-21; i--) {
			System.out.println(s.get(i));
			
		}*/
		

		List <WebElement> firm = browser.findElements(By.xpath("//div[@class='css-u4n5gw e4ojbx41']/div/div"));
		
		ArrayList<Rezult> employees = new ArrayList<Rezult>();
		
		for (int i = 0; i < firm.size(); i++) {
			
			String pairs = firm.get(i).getText();
			
		
			String[] pairsArray = pairs.split(" ");
			

			if (pairsArray.length < 2)
				continue;
			

			String name;
			if (pairsArray.length == 3) {
				name = pairsArray[0] + " " + pairsArray[1];
			}
			else {
				name = pairsArray[0];
			}
			

			int age;
			try {
				age = Integer.parseInt(pairsArray[pairsArray.length - 1]);
			}
			catch (NumberFormatException e) {
				age = 0;
			}
	
			Rezult employee = new Rezult(name, age);

			employees.add(employee);
		}

		Collections.sort(employees, new Comparator<Rezult>() {

			@Override
			public int compare(Rezult o1, Rezult o2) {
				return Integer.compare(o1.amount, o2.amount);
			}
		});
		
		/*for (int i = 0; i < employees.size(); i++) {
			Rezult employee = employees.get(i);
			System.out.println(employee.name + " " + employee.amount);
		}*/
		for (int i = employees.size()-1; i>employees.size()-21; i--) {
			Rezult employee = employees.get(i);
			//System.out.println(employee.name + " " + employee.amount);

			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			row.createCell(0).setCellValue(employee.name);
			row.createCell(1).setCellValue(employee.amount);
			
			rowNum ++;
			row = sheet.createRow(rowNum);
		}
		

		try {
			FileOutputStream fileOut = new FileOutputStream ("C: Drom_rezult.xls");
			book.write(fileOut);
			fileOut.close();
		}
		catch (Exception e) {
			System.out.println("��");
		}
		
		browser.close();
//		ArrayList<String> Sort = new ArrayList<String>();
//		int f = firm.size();
//		int count = 0;
//		
//		for (int i = 0; i < f; i++) {
//			String pars = firm.get(i).getText();
//			int t = pars.indexOf(" ");
//			if (t != -1) {
//				
//				Sort.add(pars);						
//			}
//		}
//		Collections.sort(Sort);
//
//		for (int i = Sort.size()-1; i>Sort.size()-21; i--) {
//			System.out.println(Sort.get(i));
//			
//		}
	}
}

	


