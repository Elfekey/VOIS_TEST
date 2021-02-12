package Id_VOIS_Task.VOIS_Task;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class check_cart {


	@Test
	public void check_cart_module(WebDriver driver, WebDriverWait  wait) throws InterruptedException {


		//cart_click    
		WebElement cart_btn =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a/b")));
			cart_btn.click();

		// cart_check_txt  to make sure it's empty or not   
		WebElement cart_check_txt = driver.findElement(By.xpath("//*[@id=\"center_column\"]/p"));
			String cart_txt = cart_check_txt.getText().toString();
			System.out.println(" cart_txt Appears  is : "+cart_txt);

		//if and else on the txt 
				if (cart_txt.contains("Your shopping cart is empty.")) {

						System.out.println("------------------------------------------ \n your cart is empty \n------------------------------------------  ");
					}
					else {
							System.out.println("your cart include some items ! ");
						}
			//scrolling down the page !!  350 pixel vertical
				  ((JavascriptExecutor)driver).executeScript("scroll(0,350)");


				Thread.sleep(3000);
		System.out.println("------------------------------------------ \n     The Third Task Complete!! \n------------------------------------------ ");


	}

}
