package com.weborder;

import java.util.Random;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Order {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", 
		"C:\\Users\\Aybolek\\Documents\\selenium dependencies\\drivers\\chromedriver.exe");
		
		WebDriver driver=new ChromeDriver();
		driver.navigate().to("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester");
		driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("test");
		driver.findElement(By.name("ctl00$MainContent$login_button")).click();
		Thread.sleep(1000);
		Thread.sleep(1000);
		driver.findElement(By.linkText("Order")).click();
		Scanner keyboard=new Scanner(System.in);
		//int limit=keyboard.nextInt();
		int number=randomGenerator(100);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(String.valueOf(number));
		
		//name="ctl00$MainContent$fmwOrder$txtName"
		//Enter Customer Name: John <middle_name> Smith. 
		//Instead of <middle_name> your code should enter a random string every time.
		
		String[] midNames= {"Blake", "Dean", "Grant", "Hugh", "James", "Charles", "George", "Jude",
				"Rhett", "Kent", "Lee", "Brett", "Luke", "Chase","Claude", "Paul", "Reese", "Sean",
				"Trey", "Bram"};
	
		int index=randomGenerator(midNames.length);
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName")).sendKeys("John "+midNames[index]+" Smith");
		//Enter street: 123 Any st
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox2")).sendKeys("123 Any st");
		//Enter City: Anytown
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3")).sendKeys("Alexandria");
		//Enter State: Virginia
		
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox4")).sendKeys("Virginia");
		//Enter a random 5 digit number to the zip code field
		String zip="";
		for (int i = 0; i < 5; i++) {
			zip+=randomGenerator(9);
		}
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox5")).sendKeys(zip);
		
		//Select any card type. Every time your code should select a different type.
		//Enter any card number. If you selected Visa, card number should start with 4. 
		//If you selected Master, card number should start with 5. 
		//If you selected American Express, card number should start with 3. 
		//New card number should be auto generated every time you run the test. 
		//Card numbers should be 16 digits for Visa and Master, 15 for American Express.
		
		int choice=randomGenerator(3);
		
		if(choice==1) {
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_0")).click();
			String cardNumber="4" + cardNumberGenerator(15);
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(cardNumber);
		}else if(choice==2) {
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_1")).click();
			String cardNumber="5" + cardNumberGenerator(15);
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(cardNumber);	
		}else {
			
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_2")).click();
			String cardNumber="3" + cardNumberGenerator(14);
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(cardNumber);
		}
		
		
		//Enter any valid expiration date 
		//Click on Process
		//Verify than the page contains text New order has been successfully added.
		
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox1")).sendKeys("02/18");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();

	
		if(driver.getPageSource().contains("New order has been successfully added.")) {
			System.out.println("Text present");
		}else {
			System.out.println("Text not present");
		}
		//		Thread.sleep(1000);
//		Thread.sleep(1000);
//		Thread.sleep(1000);
//		Thread.sleep(1000);
//		
//		driver.close();
		

	}
	
	public static int randomGenerator(int limit) {
		Random r=new Random();
		return r.nextInt(limit)+1;
		
	}
	
	public static String cardNumberGenerator(int length) {
		String cardNumber="";
		for (int i = 0; i < length; i++) {
			int num=randomGenerator(9);
			cardNumber+=num;
		}
		return cardNumber;
	}
	
	public static void verifyTitle(String expected , String actual) {
		if(actual.contains(expected)) {
			System.out.println("Passed.");
		}else {
			System.out.println("Failed.");
			System.out.println("Expected: " + expected);
			System.out.println("Actual: " + actual);
		}
	}

}
