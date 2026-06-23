package yuvraj.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import yuvraj.abstractComponents.abstractComponents;

public class LandingPage extends abstractComponents{
	WebDriver driver;
	public LandingPage(WebDriver driver) //constructor
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmail=driver.findElement(By.id("userEmail"));
	//driver.findElement(By.id("userPassword"))
	//driver.findElement(By.id("login"))
	//PageFactory= above we can write in like below format using Pagefactory
	
	@FindBy(id="userEmail")  //interviewer will ask us, how this annotation i.e. @findBY knows our driver then ans is there is one method 'initElements' which we need to write first in constructor
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordElement;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalogue loginApplication(String email, String password) 
	{
		userEmail.sendKeys(email);
		passwordElement.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue= new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	public void goTo() 
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
}
