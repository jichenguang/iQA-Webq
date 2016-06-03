package Webq.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Webq.Page.PageCart;
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
	private static WebElement addCartButton = null;
	private static WebElement checkText = null;
	private static WebElement ButtonToCart = null;
	private static WebElement ButtonCartToOrder = null;
	
	
	/**
	 * 获取“立即购买”按钮
	 * @param cartDriver
	 * @return
	 */
	public  static WebElement getSellOnceButton(WebDriver cartDriver){
		SellOnceButton = cartDriver.findElement(By.xpath("html/body/div[2]/div/div/div[2]/a"));
		message = "元素名称是："+SellOnceButton.getText()+"  "+"获取的元素路径是："+"html/body/div[2]/div/div/div[2]/a";
		printLog(message);
		return SellOnceButton ;	
	}
	
	
	
	/**
	 * 获取“加入购物车”按钮
	 * @param cartDriver
	 * @return
	 */
	public static WebElement getAddcartButton(WebDriver cartDriver) {
		addCartButton  = cartDriver.findElement(By.xpath(".//*[@id='add_cart']"));
		message = "元素名称是："+addCartButton.getText()+"  "+"获取的元素路径是："+".//*[@id='add_cart']";
		printLog(message);
		return addCartButton ;	
	}
	
	
	/**
	 * 获取购物车成功页的文本
	 * @param cartDriver 
	 * @return
	 */
	public static WebElement getCheckText(WebDriver cartDriver) {
		// TODO Auto-generated method stub		
		checkText = cartDriver.findElement(By.xpath("html/body/div[2]/div[1]/div/p[2]"));
		message = "元素名称是："+checkText.getText()+"  "+"获取的元素路径是："+"html/body/div[2]/div[1]/div/p[2]";
		printLog(message);
		return checkText;
	}
	
	
	/**
	 * 获取去购物车的元素
	 * @param cartDriver
	 * @return
	 */
	public static WebElement getButtonToCart(WebDriver cartDriver) {
		// TODO Auto-generated method stub
		ButtonToCart = cartDriver.findElement(By.xpath(".//*[@id='toCheckOut']"));
		message = "元素名称是："+ButtonToCart.getText()+"  "+"获取的元素路径是："+".//*[@id='toCheckOut']";
		printLog(message);
		return ButtonToCart;
	}

	
	
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
