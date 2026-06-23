package yuvraj.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ch.qos.logback.core.util.FileUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import yuvraj.TestComponents.BaseTest;
import yuvraj.pageobjects.CartPage;
import yuvraj.pageobjects.CheckoutPage;
import yuvraj.pageobjects.LandingPage;
import yuvraj.pageobjects.OrderPage;
import yuvraj.pageobjects.ProductCatalogue;
import yuvraj.pageobjects.confirmationPage;

public class SubmitOrderTest extends BaseTest {
	
	String productName = "IPHONE 13 PRO";

	@Test(dataProvider = "getData",groups = {"purchase"})
	public void submitOrder(String email, String password) throws IOException, InterruptedException {
		 
		ProductCatalogue productCatalogue = landingPage.loginApplication(email, password);

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		confirmationPage confirmationpage = checkoutPage.submitOrder();

		String confirmMessage = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	
	@Test (dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest()
	{
	ProductCatalogue productCatalogue= landingPage.loginApplication("yuvrajpatil@yopmail.com", "Yuvrajpatil@1");
	OrderPage ordersPage= productCatalogue.goToOrdersPage();
	Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	}
	
	
	@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {{"yuvrajpatil@yopmail.com", "Yuvrajpatil@1"},{"yuvrajpatil@yopmail.com", "Yuvrajpatil@12"}};
	}

}
