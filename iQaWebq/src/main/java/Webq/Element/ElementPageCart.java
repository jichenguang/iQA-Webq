package Webq.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Webq.Page.forLoggerPage;


/**
 * 常量类：购物车页面的元素。
 * @author 700sfriend
 *
 */
public class ElementPageCart {
	
	protected static forLoggerPage log = new forLoggerPage(ElementPageCart.class);
	static String message = null;
	
	/**
	 * 购物车所有元素
	 */
	public static WebElement SellOnceButton = null;
	private static WebElement ButtonCartToOrder = null;
	
	
	/**
	 * 获取从购物车去结算页的元素
	 * @param cartDriver
	 * @return
	 */
	public static WebElement getButtonCartToOrder(WebDriver cartDriver) {
		// TODO Auto-generated method stub
		ButtonCartToOrder = cartDriver.findElement(By.xpath(".//*[@id='linkToPayPage']"));
		message = "元素名称是："+ButtonCartToOrder.getText()+"  "+"获取的元素路径是："+".//*[@id='linkToPayPage']";
		printLog(message);
		return ButtonCartToOrder;
	};
	
		
	
	/**
	 * 负责打印日志的方法。
	 * @author 700sfriend
	 * @param message
	 */
	public static void printLog(String message){
		log.info("########");
		log.info(message);
		log.info("########");
	}

	

}
