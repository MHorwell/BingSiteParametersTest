package bingsite;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage {
	
	@FindBy(id = "sb_form_q")
	private WebElement searchBox;
	
	@FindBy(id = "sb_form_go")
	private WebElement sendQuery;
	
	public void sendSearch(String search) {
		searchBox.sendKeys(search);
	}
	
	public void submit() {
		sendQuery.click();
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/ol[1]/li[14]/nav")))
	}

}
