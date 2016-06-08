package Webq.Page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Webq.Element.ElementPageOrderDetails;
import Webq.Element.ElementPageOrderLists;


public class PageOrderLists {
	
	static WebDriver OrderDriver;
	static JavascriptExecutor jse; 
	protected static forLoggerPage log = new forLoggerPage(PageOrderLists.class);
	
	/**
	 * 结算页里的Action
	 * @param driver
	 * @throws InterruptedException 
	 */
	public static void ActionOrder(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		
		OrderDriver = driver;
		jse= (JavascriptExecutor)driver; 

//		进入订单列表页
		PageOrderLists();
//		进入订单详情页
		PageOrderDetails();
	}
	
	/**
	 * 进入订单列表页
	 * @throws InterruptedException 
	 */	
	private static void PageOrderLists() throws InterruptedException {
		// TODO Auto-generated method stub
		log.info("进入订单列表页");		
		try{
			OrderDriver.get(ElementPageOrderLists.strUrl);	
		}catch(Exception e){
			log.error("进入订单列表失败，开始重试！");
			Thread.sleep(5000);	
			try{  
				OrderDriver.get(ElementPageOrderLists.strUrl);
				}catch(Exception e1){
					log.error("进入订单列表失败，重试失败！");
				}
		}
		if(OrderDriver.getTitle().equals(ElementPageOrderLists.strOrderLists)){
			log.info("成功进入订单列表页");
		}else{
			log.error("没有成功进入订单列表页");
		}
	}
	
	
	
	
	/**
	 * 进入订单详情页
	 * @throws InterruptedException 
	 */
	public static void PageOrderDetails() throws InterruptedException{
		// TODO Auto-generated method stub
		System.out.println("进入订单详情页");
		WebElement ButtonOrderDetails = ElementPageOrderLists.getButtonOrderDetails(OrderDriver);		
		System.out.println(ButtonOrderDetails.getText());
		if(ButtonOrderDetails.getText()!=null){	
			jse.executeScript("arguments[0].click();", ButtonOrderDetails);
			Thread.sleep(3000);	
			
//			切换windows handle
			for(String handle : OrderDriver.getWindowHandles())
			{
			    OrderDriver.switchTo().window(handle);
			    System.out.println(OrderDriver.getTitle());
			}			
			System.out.println(OrderDriver.getTitle());
			}

		
		if(OrderDriver.getTitle().equals(ElementPageOrderDetails.strOrderDetails)){
			System.out.println("成功进入订单详情页");
			Thread.sleep(3000);	
		}else{
			System.out.println("没有成功进入订单详情页");
		}	
	}
	

}

	

