package phptravels.com;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DemoForm {

	public static void main(String[] args) {
		// Initialize WebDriver for Microsoft Edge
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize(); // Maximize the browser window
		driver.get("https://phptravels.com/demo/"); // Open the form page

		// Fill in the form details
		WebElement fname = driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
		fname.sendKeys("Mani");
		WebElement lname = driver.findElement(By.xpath("//input[@placeholder='First Name']"));
		lname.sendKeys("kandan");
		WebElement busname = driver.findElement(By.xpath("//input[@placeholder='Business Name']"));
		busname.sendKeys("Manikandan");
		WebElement email = driver.findElement(By.xpath("//input[@placeholder=\"Email\"]"));
		email.sendKeys("testusernew416@gmail.com");

		// Get the values of num1 and num2 and calculate their sum
		WebElement num1 = driver.findElement(By.id("numb1"));
		String a = num1.getText();
		System.out.println("Value of number 1 is : " + a);

		WebElement num2 = driver.findElement(By.id("numb2"));
		String b = num2.getText();
		System.out.println("Value of number 2 is : " + b);

		int c = Integer.parseInt(a) + Integer.parseInt(b);
		System.out.println("Result is: " + c);

		// Enter the sum into the "Result ?" field
		WebElement res = driver.findElement(By.xpath("//input[@placeholder='Result ?']"));
		res.sendKeys(String.valueOf(c));

		// Submit the form
		WebElement btn = driver.findElement(By.id("demo"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
		wait.until(ExpectedConditions.elementToBeClickable(btn)).click();

		// Verify if the form was submitted successfully
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(3000));
		WebElement msg = wait2.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("h2[class='text-center cw mt-3'] strong")));
		String smess = msg.getText();
		System.out.println(smess);

		if (smess.equalsIgnoreCase("Thank you!")) {
			System.out.println("Form submitted succcessfully");
		} else {
			System.out.println("Form not submitted");
		}

		// Take a screenshot of the page after form submission
		String path = "D:/Mani-Workings/Guvi/Java code/Dropdown_Synchronization/Screenshots";
		File screenshotFile = ((EdgeDriver) driver).getScreenshotAs(OutputType.FILE);
		try {
			Files.copy(screenshotFile.toPath(), Paths.get(path, "form_submission.png"),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Close the browser
		driver.quit();
	}

}
