package org.exoplatform.selenium.platform.calendar.sniff;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Caledar_Category_Update {
	private static WebDriver driver; 
	private static WebDriverWait wait; 
	private static String URL_HOST = "http://localhost:8080"; 
	private static String URL_PAGE = "http://localhost:8080/portal/intranet/calendar"; 
	private static String ACC_USERNAME = "chinhdtt9";
	private static String ACC_PASS = "gtn123";
	private static String STR_INPUT = "chinh123";
	private static String STR_EXIT; 
	private static String STR_INPUT_NUMBER = "123456789"; 
	private static String STR_INPUT_SPECIAL = "#$%%%^&&!@"; 
	private static String STR_INPUT_SPACE = "    ababc123"; 
	private static String STR_EDIT = "ChinhDT test"; 
	private static String STR_EDIT_NUMBER = "0987654321"; 
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
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		localLogin();
//	-------	Add category methods		---------
//		addCategory();
//		addCategory_number();
		addCategory_exit();
//		addCategory_special();
//		addCategory_blank();
//		addCategory_startSpace();
//		addCategory_cancel();
		
//		-------	Edit category methods		---------		
//		editCategory();
//		editCategory_number();
//		editCategory_exit();
//		editCategory_special();
//		editCategory_blank();
//		editCategory_space();
//		editCategory_cancel();
		
//		-------	Delete category methods		---------
//		deleteCategory();
//		cancel_DeleteCategory();
		
		wait = new WebDriverWait(driver,600); 
//		driver.close();
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
		System.out.println("add");
		driver.findElement(By.id("eventCategoryName")).sendKeys(STR_INPUT);
        driver.findElement(By.id("btnEventCategoryFormContainer")).click();
        Thread.sleep(1000);
        
//        wait = new WebDriverWait(driver,600); 
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_CLOSE_BTN)));
       
        //Close button 
//       driver.findElement(By.xpath(XPATH_CLOSE_BTN)).click();
        driver.findElement(By.cssSelector(CSS_CLOSE_BTN)).click();
        
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
				 System.out.println("No Alert");
				  return false;
			}
		}
		
	//Add numbers value
		public static void addCategory_number(){
			try{
				openPopup();
				driver.findElement(By.id("eventCategoryName")).sendKeys(STR_INPUT_NUMBER);
		        driver.findElement(By.id("btnEventCategoryFormContainer")).click();
		        Thread.sleep(1000);
		       
		        //Close button 
//		       driver.findElement(By.xpath(XPATH_CLOSE_BTN)).click();
		        driver.findElement(By.cssSelector(CSS_CLOSE_BTN)).click();
		        System.out.println("add an array number ");
				}
				catch(Exception ex){
					ex.getMessage(); 
				}
		}
		
	//Add exit value
		public static void addCategory_exit() throws Exception{
			try{
				String strExit; 
				openPopup();
		        WebElement tab = driver.findElement(By.cssSelector("div#UIEventCategoryList table.uiGrid.table.table-hover.table-striped")); 
			
//				WebElement tab=driver.findElement(By.xpath("//table[@class='uiGrid table table-hover table-striped']"));
				List<WebElement> rows=tab.findElements(By.tagName("tr"));
				strExit = rows.get(3).getText(); 
				System.out.println(rows.get(3).getText());
				
				driver.findElement(By.id("eventCategoryName")).sendKeys(strExit);
				driver.findElement(By.id("btnEventCategoryFormContainer")).click();
				System.out.println("add_exit");
				
				//------Click button on confirm box
				
				String childHandle =  driver.getWindowHandle();
				driver.switchTo().window(childHandle);
							
				System.out.println("get child popup1");
//				JavascriptExecutor js = (JavascriptExecutor)driver; 
				WebElement elm3 = driver.findElement(By.xpath("//*[@id='_438254538']")); 
				elm3.click();
				System.out.println(elm3.getTagName()); 
				System.out.println("AAAA"); 
				
				WebElement elm4 = driver.findElement(By.xpath("/html/body/div/div[5]/div/span"));
				System.out.println(elm4.getText()); 
				 
				driver.findElement(By.xpath("//*[text()='OK']")).click();
				
				System.out.println("get child popup2");
//				js.executeScript("$('.btn').click();",element2);
//				element2.click();
    			System.out.println("get child popup3");
//    			if(element2.getText().equalsIgnoreCase("OK")); 
			
			     
//				JavascriptExecutor js = (JavascriptExecutor)driver; 
//				WebElement element2 = driver.findElement(By.xpath("//html/body/div/div[5]/div[2]/div/a")); 
//				System.out.println("Test2"); 
//				js.executeScript("uiAction uiActionBorder",element2); 
//				System.out.println("Test3");
//				element2.click();
//				System.out.println("Test4");
				
				
				
				System.out.println("get child popup");
				
		        Thread.sleep(1000);
			}
			catch(Exception ie){
				ie.getMessage(); 
			}
			
		}
		
	//Add special value
		public static void addCategory_special(){
			try{
				openPopup();
				driver.findElement(By.id("eventCategoryName")).sendKeys(STR_INPUT_SPECIAL);
				driver.findElement(By.id("btnEventCategoryFormContainer")).click();
				Thread.sleep(1000);
       
				//Click on button on dialog box
//----------------
				
	        //Close button 
//	       driver.findElement(By.xpath(XPATH_CLOSE_BTN)).click();
				driver.findElement(By.cssSelector(CSS_CLOSE_BTN)).click();
				System.out.println("add special characters");
				}
			catch(Exception ex){
				ex.getMessage(); 
			}
		}
		
	//Add blank value
	
		public static void addCategory_blank(){
			try{
				openPopup();
				driver.findElement(By.id("eventCategoryName")).sendKeys("          ");
				driver.findElement(By.id("btnEventCategoryFormContainer")).click();
				Thread.sleep(1000);
       
				//Click on button on dialog box
//----------------
				
	        //Close button 
//	       driver.findElement(By.xpath(XPATH_CLOSE_BTN)).click();
				driver.findElement(By.cssSelector(CSS_CLOSE_BTN)).click();
				System.out.println("add blank string");
				}
			catch(Exception ex){
				ex.getMessage(); 
			}
		}
		
	//Add string start with space 
		public static void addCategory_startSpace(){
			try{
				openPopup();
				driver.findElement(By.id("eventCategoryName")).sendKeys(STR_INPUT_SPACE);
				driver.findElement(By.id("btnEventCategoryFormContainer")).click();
				Thread.sleep(1000);
       
				//Click on button on dialog box
//----------------
				
	        //Close button 
				driver.findElement(By.cssSelector(CSS_CLOSE_BTN)).click();
				System.out.println("add string start with spaces");
				}
			catch(Exception ex){
				ex.getMessage(); 
			}
		}
		
	//Add Cancel 
		public static void addCategory_cancel(){
			try{
				openPopup();
				driver.findElement(By.id("eventCategoryName")).sendKeys(STR_INPUT_SPACE);
				Thread.sleep(1000);
				
	        //Close button 
				driver.findElement(By.cssSelector(CSS_CLOSE_BTN)).click();
				System.out.println("Cancel add");
				}
			catch(Exception ex){
				ex.getMessage(); 
			}
		}
		
	//Edit 
		public static void editCategory(){
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
			  
			     //Close button 
			    driver.findElement(By.cssSelector(CSS_CLOSE_BTN)).click();
			}
			catch(Exception ex){
				ex.getMessage(); 
			}
		}
		
	//Edit number array
		public static void editCategory_number(){
			try{
				 openPopup();
				 driver.findElement(By.xpath(XPATH_EDIT_BTN)).click();
			     WebElement element1 = driver.findElement(By.id("eventCategoryName"));
				 element1.clear();
			     element1.sendKeys(STR_EDIT_NUMBER);
			     
			     WebElement element2 = driver.findElement(By.id("btnEventCategoryFormContainer")); 
			     if(element2.getText().equalsIgnoreCase("Update")); 
			     element2.click();
			     Thread.sleep(1000);
			     System.out.println("edit number");
			   //Close button 
			    driver.findElement(By.cssSelector(CSS_CLOSE_BTN)).click();
			}
			catch(Exception ex){
				ex.getMessage(); 
			}
		}
		
	//Edit exit value
		public static void editCategory_exit(){
			try{
				 openPopup();
				 WebElement tab = driver.findElement(By.cssSelector("div#UIEventCategoryList table.uiGrid.table.table-hover.table-striped")); 
				 List<WebElement> rows=tab.findElements(By.tagName("tr"));
				 STR_EXIT = rows.get(3).getText(); 
				 System.out.println(rows.get(3).getText());
				 
				 driver.findElement(By.xpath(XPATH_EDIT_BTN)).click();
			     WebElement element1 = driver.findElement(By.id("eventCategoryName"));
				 element1.clear();
				 element1.sendKeys(STR_EXIT);
				 
				 WebElement element2 = driver.findElement(By.id("btnEventCategoryFormContainer")); 
			     if(element2.getText().equalsIgnoreCase("Update")); 
			     element2.click();
			     
			     Thread.sleep(1000);
			     System.out.println("edit exit value");
			     
			   //Close button 
			    driver.findElement(By.cssSelector(CSS_CLOSE_BTN)).click();
			}
			catch(Exception e){
				e.getMessage(); 
			}
			
		}
	//Edit special value
		public static void editCategory_special(){
			try{
				 openPopup();
				 driver.findElement(By.xpath(XPATH_EDIT_BTN)).click();
			     WebElement element1 = driver.findElement(By.id("eventCategoryName"));
				 element1.clear();
			     element1.sendKeys(STR_INPUT_SPECIAL);
			     
			     WebElement element2 = driver.findElement(By.id("btnEventCategoryFormContainer")); 
			     if(element2.getText().equalsIgnoreCase("Update")); 
			     element2.click();
			     
			    //Click on button on dialog box
	//----------------
			     
			     Thread.sleep(1000);
			     System.out.println("edit special value");
			   //Close button 
			    driver.findElement(By.cssSelector(CSS_CLOSE_BTN)).click();
			}
			catch(Exception ex){
				ex.getMessage(); 
			}
		}
	//Edit blank value
		public static void editCategory_blank(){
			try{
				 openPopup();
				 driver.findElement(By.xpath(XPATH_EDIT_BTN)).click();
			     WebElement element1 = driver.findElement(By.id("eventCategoryName"));
				 element1.clear();
			     element1.sendKeys("     ");
			     
			     WebElement element2 = driver.findElement(By.id("btnEventCategoryFormContainer")); 
			     if(element2.getText().equalsIgnoreCase("Update")); 
			     element2.click();
			     
			    //Click on button on dialog box
	//----------------
			     
			     Thread.sleep(1000);
			     System.out.println("edit blank value");
			   //Close button 
			    driver.findElement(By.cssSelector(CSS_CLOSE_BTN)).click();
			}
			catch(Exception ex){
				ex.getMessage(); 
			}
		}
	//Edit string start with space
		public static void editCategory_space(){
			try{
				 openPopup();
				 driver.findElement(By.xpath(XPATH_EDIT_BTN)).click();
			     WebElement element1 = driver.findElement(By.id("eventCategoryName"));
				 element1.clear();
			     element1.sendKeys(STR_INPUT_SPACE);
			     
			     WebElement element2 = driver.findElement(By.id("btnEventCategoryFormContainer")); 
			     if(element2.getText().equalsIgnoreCase("Update")); 
			     element2.click();
			     
			    //Click on button on dialog box
	//----------------
			     
			     Thread.sleep(1000);
			     System.out.println("edit input string start with space");
			   //Close button 
			    driver.findElement(By.cssSelector(CSS_CLOSE_BTN)).click();
			}
			catch(Exception ex){
				ex.getMessage(); 
			}
		}
		
	//Edit cancel
	public static void editCategory_cancel(){
		try{
			 openPopup();
			 driver.findElement(By.xpath(XPATH_EDIT_BTN)).click();
		     WebElement element1 = driver.findElement(By.id("eventCategoryName"));
			 element1.clear();
		     element1.sendKeys(STR_EDIT); 
     
		     Thread.sleep(1000);
		     System.out.println("cancel edit");
		   //Close button 
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
	
	// Cancel Delete 
	public static void cancel_DeleteCategory(){
		try{
			openPopup();
			System.out.print("delete");
			driver.findElement(By.xpath(XPATH_DELETE_BTN)).click();
		    Alert alert = driver.switchTo().alert();
		    alert.dismiss();
		    Thread.sleep(1000);
		    driver.findElement(By.cssSelector(CSS_CLOSE_BTN)).click();
		}
		catch(Exception ex){
			ex.getMessage(); 
		}
	}
	
}
