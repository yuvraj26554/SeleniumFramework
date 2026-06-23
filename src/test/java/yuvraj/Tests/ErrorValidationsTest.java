package yuvraj.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import yuvraj.TestComponents.BaseTest;
import yuvraj.pageobjects.CartPage;
import yuvraj.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = {"ErrorHandling"})
	public void submitOrder() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		landingPage.loginApplication("yuvrajpatil@yopmail.com", "Yuvrajpatil@11111");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

	@Test
	public void ProductErrorValidation() {
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("yuvrajpatil@yopmail.com", "Yuvrajpatil@1");

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay("Camers");
		Assert.assertFalse(match);
	}
	
}