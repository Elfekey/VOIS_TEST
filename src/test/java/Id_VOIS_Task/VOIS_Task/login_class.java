package Id_VOIS_Task.VOIS_Task;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

//login class or module
public class login_class {
	
	String Email_Name;
	String Password_s;
	
	@Test
	public void login_module(WebDriver driver, WebDriverWait  wait) {
		
	//we use this code to use our xml data file !!!  It'll Help Us when we need To maintain in many modules !!
				final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				try {
					final DocumentBuilder builder = factory.newDocumentBuilder();
					try {
						final Document document= builder.parse(new File("Test_data.xml"));
						document.getDocumentElement().normalize();
						Element root = document.getDocumentElement();
						
					//Test Data connection and getting
						Email_Name= root.getElementsByTagName("Email_Name").item(0).getTextContent();
						Password_s= root.getElementsByTagName("Password_s").item(0).getTextContent();

						  
					} catch (SAXException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					} catch (ParserConfigurationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
		
		
		

	//sign_in click 
		WebElement sign_in = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")));
			sign_in.click(); 

	//scrolling down the page !!  400 pixel vertical
			  ((JavascriptExecutor)driver).executeScript("scroll(0,400)");

	//e-mail    
		WebElement e_mail = wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
			e_mail.sendKeys(Email_Name);

	//password 
		WebElement Password = driver.findElement(By.id("passwd"));
			Password.sendKeys(Password_s);

	//sing_in_btn              
		WebElement sing_in_btn =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@class,'icon-lock left')]")));
			sing_in_btn.click();
				System.out.println(" sing_in_btn Clicked");

	//Note !! 
		System.out.println("------------------------------------------ \n        Second Task Complete!!\n------------------------------------------   ");





	}

}
