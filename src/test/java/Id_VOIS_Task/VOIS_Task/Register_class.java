package Id_VOIS_Task.VOIS_Task;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class Register_class {
//variables to get data from xml data file
	String WebSite;
	String Email_Name;
	String Password_s;

	//Register Class or module
	@Test
	public void register_module(WebDriver driver,WebDriverWait  wait ) throws InterruptedException {
		
//we use this code to use our xml data file !!!  It'll Help Us when we need To maintain in many modules !!
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			final DocumentBuilder builder = factory.newDocumentBuilder();
			try {
				final Document document= builder.parse(new File("Test_data.xml"));
				document.getDocumentElement().normalize();
				Element root = document.getDocumentElement();
				
			//Test Data connection and getting
				WebSite= root.getElementsByTagName("WebSite").item(0).getTextContent();
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
		

	// get to the website 
		driver.get(WebSite);
		driver.manage().window().maximize();
	
   //getting to sign up page   
		WebElement sign_up = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")));
			sign_up.click(); 
			
	//waiting 3 seconds untill the next page laod
		 Thread.sleep(3000);
		 
	//scrolling down the page !!  400 pixel vertical
		  ((JavascriptExecutor)driver).executeScript("scroll(0,400)");


	// locating and filling E-mail with data !! 
		WebElement e_mail = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"email_create\"]")));
			e_mail.sendKeys(Email_Name);
			e_mail.sendKeys(Keys.RETURN);
 		
	//######################################
     //here We Checking if the EMail Exist already or not !!!!
     //********************
			try {
			    //A condition in case of  the account exist already !! 
				Boolean isPresent = driver.findElements(By.xpath("//*[@id=\"create_account_error\"]/ol")).size() > 0;
				if (isPresent) 
				{
					String email_exist_txt =driver.findElement(By.xpath("//*[@id=\"create_account_error\"]/ol")).getText().toString();
					System.out.println("The ERROR message is : "+email_exist_txt);
					System.out.println("\n This EMail Is Already Exist Use Another one Please ");
					System.out.println("Note... you can change it from test_data xml file And it'll be automaticaly changed in all modules!! ");
			  //Use Return To Stop Execution
					Thread.sleep(3000);
					driver.quit();
					System.exit(0);
				     return;
				}
				else {
				//checking else is working correctly
					System.out.println(" Hello VOIS from Else");
				}
			}
			catch (NoSuchElementException e) {
				System.out.println(e.getMessage());
				return;
				}

			//********************
	//choose gender 
		WebElement gender=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"uniform-id_gender1\"]")));

			if (gender.isSelected()){
				System.out.println("Gender Is Selected Already ");
			}
			else {
				gender.click();
			}

		//Filling The Register Elements !! 
	//First name
		WebElement first_name = driver.findElement(By.xpath("//*[@id=\"customer_firstname\"]"));
			first_name.sendKeys("Hussam");

	// last name
		WebElement l_name =driver.findElement(By.xpath("//*[@id=\"customer_lastname\"]"));
			l_name.sendKeys("elfekey");

	//password
		WebElement Password = driver.findElement(By.xpath("//*[@id=\"passwd\"]"));
			Password.sendKeys(Password_s);

		//Date Of Birth 
	//day
		Select day = new Select(driver.findElement(By.xpath("//*[@id=\"days\"]")));
			day.selectByIndex(16);
	//month 
		Select month = new Select(driver.findElement(By.xpath("//*[@id=\"months\"]")));
			month.selectByIndex(12);
	//year
		Select year = new Select(driver.findElement(By.xpath("//*[@id=\"years\"]")));
			year.selectByIndex(27);

		//Filling the Other Page Part Elements 
	//Company name
		WebElement company_name = driver.findElement(By.xpath("//*[@id=\"company\"]"));
			company_name.sendKeys("VOIS");

	// Address   
		WebElement address = driver.findElement(By.xpath("//*[@id=\"address1\"]"));
			address.sendKeys("25 El Azeez Bellah St.");

	//City
		WebElement city = driver.findElement(By.xpath("//*[@id=\"city\"]"));
			city.sendKeys("Cairo");

	//Country  
		Select Country = new Select(driver.findElement(By.xpath("//*[@id=\"id_country\"]")));
			Country.selectByIndex(1);

	//State 
		Select state = new Select(driver.findElement(By.xpath("//*[@id=\"id_state\"]")));
			state.selectByIndex(1);

	//zip_code  
		WebElement zip_code = driver.findElement(By.xpath("//*[@id=\"postcode\"]"));
			zip_code.sendKeys("35006");

	//home_no
		WebElement home_no = driver.findElement(By.xpath("//*[@id=\"phone\"]"));
			home_no.sendKeys("01005669014");

	//phone 
		WebElement phone = driver.findElement(By.xpath("//*[@id=\"phone_mobile\"]"));
			phone.sendKeys("01005669013");

	//Futute_address  
		WebElement Futute_address = driver.findElement(By.xpath("//*[@id=\"alias\"]"));

	//clearing the element first to type
			Futute_address.clear();
			Futute_address.sendKeys("17/25 El Azeez Bellah St");

	//clicking the register_button    //*[@id="submitAccount"]/span
		WebElement register_button = driver.findElement(By.xpath("//*[contains(text(),'Register')]"));

	//clearing the element first to type
			register_button.click();

	//Sing Out To sign in Again
		WebElement sing_out_btn = driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[2]/a"));
			sing_out_btn.click();
				System.out.println(" sing_out_btn clicked to sign in again !!");

		System.out.println("------------------------------------------ \n       First Task complete !! \n------------------------------------------ ");

	}
}
