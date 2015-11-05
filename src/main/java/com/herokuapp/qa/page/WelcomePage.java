package com.herokuapp.qa.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class WelcomePage extends BasePage{

	@Autowired
	public WelcomePage(WebDriver driver) {
		super(driver);
	}
	
	@Value("${base.url}")
	private String baseUrl; 
	
	private By orderField = By.cssSelector("td>input");
	private By select = By.cssSelector("select");
	private By checkoutBtn = By.name("commit");
	
	public WebElement getCheckoutBtn() {
		return driver.findElement(checkoutBtn);
	}
	
	public WebElement getOrderField(WebElement row) {
		return row.findElement(orderField);
	}
	
	public void orderItem(String name, String amount) {
		filterRows(hasName.apply(name))
		.map(this :: getOrderField)
		.findFirst()
		.ifPresent(field -> driverUtil.type(field, amount));
	}
	
	public void orderItem(String name, int amount) {
		orderItem(name, Integer.toString(amount));
	}
	
	public void selectState(String state) {
		Select dropdown = new Select(driver.findElement(select));
		dropdown.selectByValue(state.toUpperCase());
	}

	public void goTo() {
		if( !isAt() ) {
		driver.navigate().to(baseUrl);
		}
		waitForPageToLoad();
	}
	
	private boolean isAt() {
		return driverUtil.isElementPresentBy.test(checkoutBtn);
	}

}
