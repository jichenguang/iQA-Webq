package Webq.Action;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import Webq.Page.PageOrder;
import Webq.Page.PageOrderPay;

public class actionSubOrder {
	
	static WebDriver driver = null;
	static JavascriptExecutor jse;
	
	
	/**
	 * 
	 * @param driver
	 * @throws InterruptedException 
	 */	
	public static void toSubOrder(WebDriver Driver) throws InterruptedException {
	
			// TODO Auto-generated method stub
			driver = Driver;
			jse= (JavascriptExecutor)driver; 
			
//			点击结算页“提交”按钮；
			PageOrder.subOrder();
//			获取支付页面文案；
			PageOrderPay.TextPayPage();
//			检查是否提交成功；
			PageOrderPay.checkOrder();		
		}
		
	

}
