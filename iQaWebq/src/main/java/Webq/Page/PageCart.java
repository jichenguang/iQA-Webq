package Webq.Page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import Webq.Element.ElementPageCart;


public class PageCart {
		
	
	static PageLogin loginPage;
	static WebDriver cartDriver; 
	static JavascriptExecutor jse; 
	/**
	 * @author 700sfriend
	 * 这个log必须是静态的，那么我只能用PageCart.class来取代ew Log(this.getClass())的写法。
	 * 效果是一样的。
	 */	
		protected static forLoggerPage log = new forLoggerPage(PageCart.class);	
		public static void ActionCart(WebDriver driver) throws InterruptedException{
			cartDriver = driver;
			jse= (JavascriptExecutor)driver; 
			
//			购物车流程
			openProduct();
			checkSellOnce();
			ButtonAddCart();
			CartSuccess();
			IsOverCart();
			ToCart();
			CartToOrder();

			
			
		}
	
		/**
		 * action:打开商品页
		 * @param driver
		 */
		public static  void openProduct() {
			// TODO Auto-generated method stub
	//		进入后街页面		
			String houjieUrl = "http://webq.700paper.cn/houjie";
			cartDriver.get(houjieUrl);
			log.info("#############################################");
			log.info(houjieUrl);
			log.info("#############################################");
			

		}
	
		/**
		 * action:立即购买
		 * @throws InterruptedException 
		 */
		
		public static void checkSellOnce() throws InterruptedException {
			// TODO Auto-generated method stub
//			立即购买按钮
			WebElement SellOnceButton = ElementPageCart.getSellOnceButton(cartDriver);	
			if(SellOnceButton.getText()!=null){
				System.out.println("点击："+SellOnceButton.getText());
				jse.executeScript("arguments[0].click();", SellOnceButton);
			}			
		}
		
		/**
		 * action:加入购物车
		 * @param driver
		 * @throws InterruptedException 
		 */
		public static void ButtonAddCart() throws InterruptedException{
			WebElement addcartButton = ElementPageCart.getAddcartButton(cartDriver);
			if(addcartButton.getText()!=null){
			System.out.println("点击:"+addcartButton.getText());	
			jse.executeScript("arguments[0].click();", addcartButton);  
			}
		}
		
		
		
		/**
		 * action:加入购物车成功页
		 * @param cartDriver
		 */
		public static WebElement CartSuccess() {
		// TODO Auto-generated method stub
			WebElement checkText = ElementPageCart.getCheckText(cartDriver);			
			return checkText;
		   
		}
		


		/**
		 *  action:判断是否加入成功
		 * 
		 */

		public static void IsOverCart() {
		// TODO Auto-generated method stub
			String Textsuc = "商品已经成功加入购物车！";
			if(CartSuccess().toString().equals(Textsuc)){
				System.out.println("加入购物车成功");
			}
		}
		
		/**
		 * action:进入购物车
		 * @param driver
		 */

		public static void ToCart() {
		// TODO Auto-generated method stub			
			WebElement ButtonToCart = ElementPageCart.getButtonToCart(cartDriver);			
			if(ButtonToCart!=null){
			System.out.println("点击:"+ButtonToCart.getText());
			jse.executeScript("arguments[0].click();", ButtonToCart); 
			}else{
				System.out.println("没去成购物车");
			}
		}
		
		/**
		 * action:从购物车去结算页
		 * @param driver
		 * @throws InterruptedException 
		 */

		public static void CartToOrder() throws InterruptedException {
		// TODO Auto-generated method stub
			System.out.println("点击“去结算”");
			WebElement ButtonCartToOrder = ElementPageCart.getButtonCartToOrder(cartDriver);
			ButtonCartToOrder.click();
			jse.executeScript("arguments[0].click();", ButtonCartToOrder); 
		}
}
