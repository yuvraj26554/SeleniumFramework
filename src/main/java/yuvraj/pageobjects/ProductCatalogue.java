package yuvraj.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import yuvraj.abstractComponents.abstractComponents;

public class ProductCatalogue extends abstractComponents {
	WebDriver driver;
	public ProductCatalogue(WebDriver driver) //constructor
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//List <WebElement>products=driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".mb-3")
	List <WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy =By.cssSelector(".mb-3");
	By addTocart =By.cssSelector(".card-body button:last-of-type");
	By toastMessage= By.cssSelector("#toast-container");
	
	public  List<WebElement>getProductList()
	{
		waitForElementToAppear(productsBy);
		return products;
		
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod=getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName)
	{
		WebElement prod= getProductByName(productName);
		prod.findElement(addTocart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner );
		
		
	}

	public ProductCatalogue loginApplication(String string, String string2) {
		// TODO Auto-generated method stub
		return null;
	}
 
}
