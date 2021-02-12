package Id_VOIS_Task.VOIS_Task;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;



public class run_class {
//variables to get data from xml data file
	String web_directory;
	ChromeOptions option;
	WebDriver driver ;
	WebDriverWait wait;
        

	
		//before class method
	@BeforeClass
	public void Before_c() {
		//we use this code to use our xml data file !!!  It'll Help Us when we need To maintain in many modules !!
				final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				try {
					final DocumentBuilder builder = factory.newDocumentBuilder();
					try {
						final Document document= builder.parse(new File("Test_data.xml"));
						document.getDocumentElement().normalize();
						Element root = document.getDocumentElement();
						
						//Test Data connection and getting
						web_directory= root.getElementsByTagName("web_directory").item(0).getTextContent();
						  
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
		
		System.setProperty("webdriver.chrome.driver",web_directory);
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--lang=en-GB");
		driver = new ChromeDriver(option);
		wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	}


		//the main function  where the execution  start from 
	@Test
	public void run_m() throws InterruptedException {
		//implicit wait

		//creating instance of each module and call the methods from them to run them
	//First Task  calling the module register function to run it !
		Register_class O1 = new Register_class();
			O1.register_module(driver, wait);
		Thread.sleep(5000);

	//second task   calling the module login function to run it !
		//get to login to continue by navigate 
		login_class O2 = new login_class();
			O2.login_module(driver, wait);
		Thread.sleep(5000);

	//Third task calling the module check cart function to run it !
		Thread.sleep(5000);
		check_cart O3 = new check_cart();
			O3.check_cart_module(driver, wait);
	}
		//after class method
	@AfterClass
	public void After_C() {
	//closing up all the browser !! and print a message !
		driver.quit();
		System.out.println("This THe End the class");
	}


}
