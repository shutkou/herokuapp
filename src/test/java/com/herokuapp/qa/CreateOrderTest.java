package com.herokuapp.qa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.isIn;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.herokuapp.qa.dataProvider.TestDataProvider;
import com.herokuapp.qa.order.builder.OrderBuilder;
import com.herokuapp.qa.page.CheckoutPage;
import com.herokuapp.qa.page.WelcomePage;
import com.herokuapp.qa.util.PriceListReader;

public class CreateOrderTest extends BaseTest {

	private HashMap<String, BigDecimal> givenPrices;
	private HashMap<String, Integer> givenInventory;
	
	private BigDecimal expectedSubTotal;
	private BigDecimal expectedTaxes;
	private BigDecimal expectedTotal;
	
	private WelcomePage welcomePage;
	private CheckoutPage checkoutPage;
	private OrderBuilder orderBuilder;
	private PriceListReader priceListReader;
	private BigDecimal taxRate;
	private String stateName;
	
	@Factory(dataProvider = "taxes", dataProviderClass = TestDataProvider.class)
	public CreateOrderTest(String stateName, String taxRate){
		this.stateName = stateName;
		this.taxRate = new BigDecimal(taxRate);
	}
	
	@BeforeClass
	public void setUp(){
		setUpDriver();
		welcomePage = new WelcomePage(driver);
		checkoutPage = new CheckoutPage(driver);
		orderBuilder = new OrderBuilder(driver);
		priceListReader = new PriceListReader();
		givenPrices = priceListReader.getPrices();
		givenInventory = priceListReader.getInventoryQuantity();
		
		welcomePage.goTo();
	}
	
	@Test(priority = 1)
	public void verifyPricesOnWelcomePageTest(){
		
		HashMap<String, BigDecimal> actualPrices = welcomePage.getActualPrices();
		assertThat( givenPrices.entrySet(), everyItem(isIn(actualPrices.entrySet())));
	}
	
	@Test(priority = 1)
	public void verifyQuantityOnWelcomePageTest(){
		
		HashMap<String, Integer> actualQuantity = welcomePage.getActualQuantity();
		assertThat( givenInventory.entrySet(), everyItem(isIn(actualQuantity.entrySet())));
	}
	
	@Test(priority = 2)
	public void verifyPricesOnCheckoutPageTest(){
		
		givenInventory.forEach(orderBuilder :: addItem);
		orderBuilder.selectState(stateName)
					.submitOrder();
		
		HashMap<String, BigDecimal> actualPrices = checkoutPage.getActualPrices();
		assertThat( givenPrices.entrySet(), everyItem(isIn(actualPrices.entrySet())));
	}
	
	@Test(priority = 3)
	public void verifyQuantityOnCheckOutPageTest(){
		
		HashMap<String, Integer> actualInventory = checkoutPage.getActualQuantity();
		assertThat(givenInventory.entrySet(), everyItem(isIn(actualInventory.entrySet())));
	}
	
	@Test(priority = 3)
	public void verifySubTotalTest(){
		
		expectedSubTotal = checkoutPage.calculateSubTotal();
		BigDecimal actualSubTotal = checkoutPage.getSubTotal();
		assertThat(expectedSubTotal, equalTo(actualSubTotal));
	}
	
	@Test(priority = 4)
	public void verifyTaxesTest(){
		
		expectedTaxes = expectedSubTotal.multiply(taxRate).setScale(2, RoundingMode.HALF_UP);
		BigDecimal actualTaxes = checkoutPage.getTaxes();
		assertThat(expectedTaxes, equalTo(actualTaxes));
	}
	
	@Test(priority = 5)
	public void verifyTotalTest(){
		
		expectedTotal = expectedSubTotal.add(expectedTaxes);
		BigDecimal actualTotal = checkoutPage.getTotal();
		assertThat(expectedTotal, equalTo(actualTotal));
	}
	
	@AfterClass
	public void cleanUp() {
		quitDriver();
	}
	
}
