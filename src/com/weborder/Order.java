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

		WebDriver driver = new ChromeDriver();
		//navigates to required url
		driver.navigate().to("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		//enters user name
		driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester");
		//enters password 
		driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("test");
		//clicks login button
		driver.findElement(By.name("ctl00$MainContent$login_button")).click();
		//clicks Order link
		driver.findElement(By.linkText("Order")).click();
		
		//generates numbers 1 to 100 and enters into quantity textfield
		int number = randomGenerator(100);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(String.valueOf(number));
		
		//String array of middle names in each run uses differnt one
		String[] midNames = { "Blake", "Dean", "Grant", "Hugh", "James", "Charles", "George", "Jude", "Rhett", "Kent",
				"Lee", "Brett", "Luke", "Chase", "Claude", "Paul", "Reese", "Sean", "Trey", "Bram" };
		//generates random index
		int index = randomGenerator(midNames.length);
		//send name + middleName + lastName
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName"))
				.sendKeys("John " + midNames[index] + " Smith");
		//enteres street
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox2")).sendKeys("123 Any st");
		//enteres city
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3")).sendKeys("Alexandria");
		//enters state as required
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox4")).sendKeys("Virginia");
		//generates 5 digit zipcode and enters into requierd field
		String zip = "";
		for (int i = 0; i < 5; i++) {
			zip += randomGenerator(9);
		}
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox5")).sendKeys(zip);
		//chooses one of card type
		//in each run different card type selected
		//and card number generated using numbergenerator method
		int choice = randomGenerator(3);
		if (choice == 1) {
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_0")).click();
			String cardNumber = "4" + cardNumberGenerator(15);
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(cardNumber);
		} else if (choice == 2) {
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_1")).click();
			String cardNumber = "5" + cardNumberGenerator(15);
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(cardNumber);
		} else {

			driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_2")).click();
			String cardNumber = "3" + cardNumberGenerator(14);
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(cardNumber);
		}
		//provided valid expired date
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox1")).sendKeys("02/18");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();
		//Verified that the page contains text New order has been successfully added.
		if (driver.getPageSource().contains("New order has been successfully added.")) {
			System.out.println("Text present");
		} else {
			System.out.println("Text not present");
		}

	}

	public static int randomGenerator(int limit) {
		Random r = new Random();
		return r.nextInt(limit) + 1;

	}

	public static String cardNumberGenerator(int length) {
		String cardNumber = "";
		for (int i = 0; i < length; i++) {
			int num = randomGenerator(9);
			cardNumber += num;
		}
		return cardNumber;
	}

}
