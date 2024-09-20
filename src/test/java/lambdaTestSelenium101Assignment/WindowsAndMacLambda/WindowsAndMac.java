package lambdaTestSelenium101Assignment.WindowsAndMacLambda;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class WindowsAndMac {
	
	WebDriver driver = null;

	String browserName, browserVersion, osVersion;

	@Parameters({ "browserName", "browserVersion", "osVersion" })
	@BeforeMethod
	public void setUp(String browserName, String browserVersion, String osVersion) throws Exception {
		this.browserName = browserName;
		this.browserVersion = browserVersion;
		this.osVersion = osVersion;

		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", "priyanka.fly17");
		ltOptions.put("accessKey", "1ezWfXetmnB1fm7FCfuQxVWLYYNq3l6X02b6VSTL1XCQM1ywqk");
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("network", true);
		ltOptions.put("build", "Selenium101Assignment");
		ltOptions.put("project", "Selenium101Assignment");
		ltOptions.put("w3c", true);
		ltOptions.put("plugin", "java-testNG");

		if (browserName.equalsIgnoreCase("Chrome")) {

			ChromeOptions browserOptions = new ChromeOptions();

			browserOptions.setPlatformName(osVersion);

			browserOptions.setBrowserVersion(browserVersion);

			browserOptions.setCapability("LT:Options", ltOptions);

			driver = new RemoteWebDriver(new URL("https://hub.lambdatest.com/wd/hub"), browserOptions);
		}

		else if (browserName.equalsIgnoreCase("Firefox")) {

			FirefoxOptions browserOptions = new FirefoxOptions();

			browserOptions.setPlatformName(osVersion);

			browserOptions.setBrowserVersion(browserVersion);

			browserOptions.setCapability("LT:Options", ltOptions);

			driver = new RemoteWebDriver(new URL("https://hub.lambdatest.com/wd/hub"), browserOptions);
		}
	}

	public void acceptCookies(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		try {

			WebElement allowAllButton = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Allow all')]")));
			allowAllButton.click();

		} catch (Exception e) {
			System.out.println("Cookie consent pop-up not found or could not be clicked.");
			e.printStackTrace();
		}
	}

	@Test
	public void testScenario1() throws Exception {

		// Open Selenium Playground
		driver.get("https://www.lambdatest.com/selenium-playground");

		if (osVersion.equals("macOS Sonoma")) {
			acceptCookies(driver);
		}

		// Click on "Simple Form Demo"
		driver.findElement(By.linkText("Simple Form Demo")).click();
		Thread.sleep(3000);

		// Validate the URL
		Assert.assertTrue(driver.getCurrentUrl().contains("simple-form-demo"), "URL validation failed!");

		// Enter a message
		String message = "Welcome to LambdaTest";
		driver.findElement(By.id("user-message")).sendKeys(message);
		Thread.sleep(3000);

		// Click "Get Checked Value"
		driver.findElement(By.id("showInput")).click();
		Thread.sleep(3000);

		// Validate the displayed message
		String displayedMessage = driver.findElement(By.id("message")).getText();
		Assert.assertEquals(displayedMessage, message, "Displayed message validation failed!");
		Thread.sleep(3000);
	}

	@Test
	public void testScenario2() throws Exception {

		// Open Selenium Playground
		driver.get("https://www.lambdatest.com/selenium-playground");
		if (osVersion.equals("macOS Sonoma")) {

			acceptCookies(driver);
		}

		// Click on "Drag & Drop Sliders"
		driver.findElement(By.linkText("Drag & Drop Sliders")).click();

		// Select the slider and drag it to 95
		WebElement slider = driver.findElement(By.xpath("//*[@id=\"slider1\"]/div/input"));

		Actions action = new Actions(driver);
		action.dragAndDropBy(slider, 215, 250).perform();
		Thread.sleep(3000);

		// Validate the slider value
		String sliderValue = driver.findElement(By.id("range")).getText();
		Assert.assertEquals(sliderValue, "95", "Slider value validation failed!");
	}

	@Test
	public void testScenario3() throws Exception {

		// Open Selenium Playground
		driver.get("https://www.lambdatest.com/selenium-playground");

		driver.get("https://www.lambdatest.com/selenium-playground");
		if (osVersion.equals("macOS Sonoma")) {
			acceptCookies(driver);
		}

		// Click on "Input Form Submit"
		driver.findElement(By.linkText("Input Form Submit")).click();
		Thread.sleep(3000);

		// Click "Submit" without filling any form
		driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();

		WebElement nameField = driver.findElement(By.name("name"));
		Thread.sleep(3000);
		
		// Validate the error message
		String message = nameField.getAttribute("validationMessage");

		Thread.sleep(3000);
		Assert.assertTrue(message.contains("Please fill out this field.") || message.contains("Fill out this field."),
				"Error message validation failed! The message was: " + message);
		Thread.sleep(3000);
		
		// Fill in the form fields
		nameField.sendKeys("Kumari Priyanka");
		// driver.findElement(By.id("name")).sendKeys("Kumari Priyanka");
		Thread.sleep(1000);

		driver.findElement(By.id("inputEmail4")).sendKeys("priyanka@test.com");
		Thread.sleep(1000);
		
		driver.findElement(By.id("inputPassword4")).sendKeys("password123");
		Thread.sleep(1000);
		
		driver.findElement(By.id("company")).sendKeys("LambdaTest");
		Thread.sleep(1000);
		
		driver.findElement(By.id("websitename")).sendKeys("https://www.lambdatest.com");
		Thread.sleep(1000);

		// Select Country "United States"
		Select countryDropdown = new Select(driver.findElement(By.name("country")));
		countryDropdown.selectByVisibleText("United States");
		Thread.sleep(1000);

		// Fill other fields and submit
		driver.findElement(By.id("inputCity")).sendKeys("Ranchi");
		Thread.sleep(1000);
		
		driver.findElement(By.id("inputAddress1")).sendKeys("123 Street");
		Thread.sleep(1000);
		
		driver.findElement(By.id("inputAddress2")).sendKeys("Apt 4B");
		Thread.sleep(1000);
		
		driver.findElement(By.id("inputState")).sendKeys("Jharkhand");
		Thread.sleep(1000);
		driver.findElement(By.id("inputZip")).sendKeys("10001");
		Thread.sleep(1000);

		// Submit the form
		driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
		Thread.sleep(1000);

		// Validate the success message
		String successMessage = driver.findElement(By.xpath("//*[contains(text(),'Thanks for contacting us')]"))
				.getText();
		Assert.assertTrue(successMessage.contains("Thanks for contacting us, we will get back to you shortly."),
				"Success message validation failed!");
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}


}
