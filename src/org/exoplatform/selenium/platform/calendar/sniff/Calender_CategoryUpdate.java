package org.exoplatform.selenium.platform.calendar.sniff;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.internal.seleniumemulation.IsAlertPresent;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Calender_CategoryUpdate {
	private static WebDriver driver; 
	private static WebDriverWait wait; 
	private static String URL_HOST = "http://localhost:8080"; 
	private static String URL_PAGE = "http://localhost:8080/portal/intranet/calendar"; 
	private static String ACC_USERNAME = "chinhdtt7";
	private static String ACC_PASS = "gtn123";
	private static String STR_INPUT = "chinh123";
	private static String STR_EDIT = "ChinhDT test"; 
	private static String XPATH_POPUP = "//i[@class='uiIconCalSimplePlus uiIconLightGray']"; 
	private static String XPATH_EVENT_CATEGORY = "//div[@id='tmpMenuElement']//i[@class='uiIconCalCreateEvent uiIconLightGray']"; 
	private static String XPATH_CLOSE_BTN = "//form[@id='UIEventCategoryForm']//div[@class='uiAction uiActionBorder']//button[@class='btn']"; 
	private static String XPATH_EDIT_BTN = "//div[@id='UIEventCategoryList']//table/tbody/tr[5]/td[@class='center actionContainer']/a[@class='actionIcon']/i[@class='uiIconEdit uiIconLightGray']"; 
	private static String XPATH_DELETE_BTN = "//div[@id='UIEventCategoryList']//table/tbody/tr[3]/td[@class='center actionContainer']/a[@class='actionIcon']/i[@class='uiIconDelete uiIconLightGray']"; 
	private static String CSS_POPUP = "i.uiIconCalSimplePlus.uiIconLightGray"; 
	private static String CSS_EVENT_CATEGORY = "div#tmpMenuElement i.uiIconCalCreateEvent.uiIconLightGray"; 
	private static String CSS_CLOSE_BTN = "form#UIEventCategoryForm div.uiAction.uiActionBorder button.btn"; 
	
	public static void main(String[] args) throws Exception{
		//Open browser
		driver = new FirefoxDriver();
		driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
		localLogin();
		addCategory();
		editCategory();
		deleteCategory();
//		wait = new WebDriverWait(driver,600); 
		driver.close();
	}
	//login 
	public static void localLogin(){
		driver.get(URL_HOST);
		 System.out.println("Page title is: " + driver.getTitle());
		 WebElement element = driver.findElement(By.className("uiLogin")); 
		 element.findElement(By.id("username")).sendKeys(ACC_USERNAME);
		 element.findElement(By.id("UIPortalLoginFormControl")).sendKeys(ACC_PASS);
		 element.findElement(By.className("button")).click();
		 driver.get(URL_PAGE);
		}
	//Open new window
	public static void openPopup(){
//		 driver.findElement(By.xpath(XPATH_POPUP)).click();
//		 driver.findElement(By.xpath(XPATH_EVENT_CATEGORY)).click();
		
		 driver.findElement(By.cssSelector(CSS_POPUP)).click(); 
		 driver.findElement(By.cssSelector(CSS_EVENT_CATEGORY)).click(); 
		 String parentHandle =  driver.getWindowHandle();
		 driver.switchTo().window(parentHandle);
		 System.out.println("new popup");
	}
	//Add valid value
	public static void addCategory(){
		try{
		openPopup();
		driver.findElement(By.id("eventCategoryName")).sendKeys(STR_INPUT);
        driver.findElement(By.id("btnEventCategoryFormContainer")).click();
//        if(isAlertPresent()){
//        	Alert alert = driver.switchTo().alert();
//            alert.dismiss();
//        	System.out.println("Alert");
//        }
         Thread.sleep(1000);
//        wait = new WebDriverWait(driver,1000); 
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(CSS_CLOSE_BTN)));
        //Close button 
//       driver.findElement(By.xpath(XPATH_CLOSE_BTN)).click();
         driver.findElement(By.cssSelector(CSS_CLOSE_BTN)).click();
        System.out.println("add");
		}
		catch(Exception ex){
			ex.getMessage(); 
		}
	}
	//Check alert
	public static boolean isAlertPresent() {
		try {
			Alert alert = driver.switchTo().alert();
			return true; 		    
		  } 
		 catch (NoAlertPresentException ex) {
			  return false;
		  }
	}
	
	//Edit 
	public static void editCategory() throws InterruptedException{
		try{
			 openPopup();
			 System.out.println("edit");
			 driver.findElement(By.xpath(XPATH_EDIT_BTN)).click();
		     WebElement element1 = driver.findElement(By.id("eventCategoryName"));
			 element1.clear();
		     element1.sendKeys(STR_EDIT);
		     
		     WebElement element2 = driver.findElement(By.id("btnEventCategoryFormContainer")); 
		     if(element2.getText().equalsIgnoreCase("Update")); 
		     element2.click();
		     Thread.sleep(1000);
//		     wait = new WebDriverWait(driver,300); 
//		     wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(CSS_CLOSE_BTN)));
		   //Close button 
//		    driver.findElement(By.xpath(XPATH_CLOSE_BTN)).click();
		    driver.findElement(By.cssSelector(CSS_CLOSE_BTN)).click();
		}
		catch(Exception ex){
			ex.getMessage(); 
		}
	}
	//Delete
	public static void deleteCategory(){
		try{
			openPopup();
			System.out.print("delete");
			driver.findElement(By.xpath(XPATH_DELETE_BTN)).click();
		    Alert alert = driver.switchTo().alert();
		    alert.accept();
		    Thread.sleep(1000);
		  //Close button 
//	        driver.findElement(By.xpath(XPATH_CLOSE_BTN)).click();
		    driver.findElement(By.cssSelector(CSS_CLOSE_BTN)).click();
		}
		catch(Exception ex){
			ex.getMessage(); 
		}
	}
	
	
}
