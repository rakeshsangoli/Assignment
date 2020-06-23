package assignment.ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.vocera.abx.configuration.ConfigOperation;

import assignment.api.Get;
import assignment.pojo.TempratureInfo;

public class CityTemprature {
	 private static HashMap<String, TempratureInfo> cityTempratureUI = 
             new HashMap<String, TempratureInfo>(); 
	public static synchronized HashMap<String, TempratureInfo> getTemprature() throws IOException{
		if(cityTempratureUI.size()>0)
			return cityTempratureUI;
		//Load properties
		Map properties=ConfigOperation.getProperties();
		// System Property for Chrome Driver   
		System.setProperty("webdriver.chrome.driver", (String) properties.get("chromedriver"));  

		// Instantiate a ChromeDriver class.     
		WebDriver driver=new ChromeDriver();  

		// Launch Website  
		driver.navigate().to((String) properties.get("url"));  

		//Maximize the browser  
		driver.manage().window().maximize();  
		 //Read existing data from csv
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 int totalCity=0;
		 for (int i = 1;; i++) {
			 totalCity++;
			try {
				if(!driver.findElement(By.xpath("//div[@id='messages']/div["+i+"]/label/input")).isDisplayed())
					break;
			} catch (Exception e) {
				break;
			}
			if(driver.findElement(By.xpath("//div[@id='messages']/div["+i+"]/label/input")).isSelected())
				driver.findElement(By.xpath("//div[@id='messages']/div["+i+"]/label/input")).click();
			
		}
		 System.out.println(totalCity);
		for (int i = 1;i<=totalCity; i++) {
				try {
					driver.findElement(By.xpath("//div[@id='messages']/div["+i+"]/label/input")).click();
					WebElement element = driver.findElement(By.xpath("//div[@class='cityText']"));
					JavascriptExecutor executor = (JavascriptExecutor)driver;
					executor.executeScript("arguments[0].click();", element);
					WebDriverWait wait = new WebDriverWait(driver,10);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Temp in Degrees')]")));
					String Temprature=driver.findElement(By.xpath("//*[contains(text(),'Temp in Degrees')]")).getText().split(":")[1].trim();
					String Humidity=driver.findElement(By.xpath("//b[contains(text(),'Humidity')]")).getText().split(":")[1].trim();
					String City=driver.findElement(By.xpath("//span[@style='margin-bottom:10px']")).getText().split(",")[0];
					cityTempratureUI.put(City, new TempratureInfo(Humidity, Temprature));
					System.out.println("City="+City+","+"Temprature="+Temprature);
					driver.findElement(By.xpath("//div[@id='messages']/div["+i+"]/label/input")).click();
					Thread.sleep(5 * 1000);
				} catch (Exception e) {
					System.out.println("Exception occured, "+e);
					try {
						driver.findElement(By.xpath("//div[@id='messages']/div["+i+"]/label/input")).click();
					} catch (Exception e2) {
						System.out.println("Exception occured on clicking on city input fields!!");
					}
				}
			}
//            System.out.println("key: " + name);
		return cityTempratureUI;
	}
	
	public static void main(String[] args) throws IOException {
		 
	}
}
