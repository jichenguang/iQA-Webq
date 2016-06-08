package Webq.Page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Webq.Element.ElementPageAd;
import Webq.Element.ElementPageCart;
import Webq.Element.ElementPageProduct;
import Webq.Element.ElementPageTransitional;


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
			IsButtonNoEnunghPr();			
			checkColer();			
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
			WebElement SellOnceButton = ElementPageAd.getSellOnceButton(cartDriver);	
			if(SellOnceButton.getText()!=null){
				System.out.println("点击："+SellOnceButton.getText());
				jse.executeScript("arguments[0].click();", SellOnceButton);
			}			
		}
		
		
		/**
		 * action:商品详情页，加入购物车
		 * @param driver
		 * @throws InterruptedException 
		 */
		public static void ButtonAddCart() throws InterruptedException{
			WebElement addcartButton = ElementPageProduct.getAddcartButton(cartDriver);
			if(addcartButton.getText()!=null){
			System.out.println("点击:"+addcartButton.getText());	
			jse.executeScript("arguments[0].click();", addcartButton);  
			}
		}
		
		
		/**
		 * action:商品详情页,判断库存不足
		 * @return 
		 */
		public static Boolean IsButtonNoEnunghPr(){
			try {
				ButtonAddCart();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			@SuppressWarnings("unused")
			WebElement ButtonNoEnunghPr = ElementPageProduct.getButtonNoEnunghPr(cartDriver);
			WebElement buttonSorryText = ElementPageProduct.getTextSorryCart(cartDriver);
			WebElement buttonConfirm = ElementPageProduct.getButtonConfirm(cartDriver);
			if(buttonSorryText != null){
				log.info(buttonSorryText.getText());
				jse.executeScript("arguments[0].click();", buttonConfirm);
//				checkColer();
			}else{
				log.info("库存信息正常。");
				return false;
			}
			return true;
		};
		
		
		
		
		/**
		 * 选择商品的颜色
		 */
		private static boolean checkColer() {
			// TODO Auto-generated method stub
			ElementPageProduct.getProductInfo(cartDriver);
			ElementPageProduct.getButtonColour(cartDriver);
			ElementPageProduct.getUlChooseColour(cartDriver);
			IsOpenListChooseColour();
			CheckOpenListColour(3);
			return true;
		}
		
		
		
		/**
		 * 判断颜色菜单是否已打开
		 */		
		public static boolean IsOpenListChooseColour(){
			if(ElementPageProduct.getUlChooseColour(cartDriver).getAttribute("style").contains("display: block")){									
				log.info("菜单已打开,请选择颜色");
				return true;
			}else{	
				log.info("菜单未打开");
				return false;
			}
		}
		
		
		
		/**
		 * 
		 * 点击菜单并选择颜色
		 * @return 
		 */		
		public static WebElement CheckOpenListColour(int m){
			WebElement ColourChoosed;
			if (IsOpenListChooseColour()!=true){
				log.info("点击打开菜单");
				jse.executeScript("arguments[0].click();", ElementPageProduct.getButtonColour(cartDriver));
//				System.out.println(ElementPageProduct.getAllChooseColour(cartDriver).size());
				ColourChoosed = ElementPageProduct.getAllChooseColour(cartDriver).get(m);
				log.info("选择的颜色是： "+ColourChoosed.getAttribute("data-color"));
				jse.executeScript("arguments[0].click();", ColourChoosed);
			}else{
				ColourChoosed = ElementPageProduct.getAllChooseColour(cartDriver).get(m);
				log.info("选择的颜色是： "+ColourChoosed.getAttribute("data-color"));
				jse.executeScript("arguments[0].click();", ColourChoosed);
			}
			/**
			 * 选中颜色之后，再判断当前选中的是否与预期一致
			 */
			if(getCurrentColour(m,ColourChoosed)!= null){
				return ColourChoosed;
			}else{
				return null;
			}
									
		}
		

		
		 /**
		  * 判断当前选中的颜色
		 * @param colourChoosed 
		  * 
		  */
		public static String getCurrentColour(int m, WebElement colourChoosed){
			String ColourCurrentString = null;			
			WebElement ColourCurrent;
			
			ColourCurrent = ElementPageProduct.getButtonColour(cartDriver);
			ColourCurrentString = ColourCurrent.getText();
			log.info("当前的颜色是：" + ColourCurrentString);
			if(ColourCurrentString.equals(colourChoosed.getText())|IsOpenListChooseColour()!=true){
				log.info("当前的颜色与选择的" + ColourCurrentString + "色一致！");
				return ColourCurrentString;
			}else{
				String failmessage = "选择的颜色与显示的颜色，不一致！";
				log.error(failmessage);
				return null;
			}			
		}
		 
		
		

		/**
		 * action:过渡页：加入购物车成功页
		 * @param cartDriver
		 */
		public static WebElement CartSuccess() {
		// TODO Auto-generated method stub
			WebElement checkText = ElementPageTransitional.getCheckText(cartDriver);			
			return checkText;
		   
		}
		


		/**
		 *  action:过渡页，判断是否加入成功
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
		 * action:过渡页，进入购物车
		 * @param driver
		 */
		public static void ToCart() {
		// TODO Auto-generated method stub			
			WebElement ButtonToCart = ElementPageTransitional.getButtonToCart(cartDriver);			
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
