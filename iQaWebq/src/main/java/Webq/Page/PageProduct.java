package Webq.Page;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Webq.Do.DoNavigitionOfPageProduct;
import Webq.Element.ElementPageProduct;



public class PageProduct {

		static WebDriver cartDriver; 
		static JavascriptExecutor jse; 
		/**
		 * @author 700sfriend
		 * 这个log必须是静态的，那么我只能用PageCart.class来取代ew Log(this.getClass())的写法。
		 * 效果是一样的。
		 */	
		protected static forLoggerPage log = new forLoggerPage(PageProduct.class);
		
		
		
		/**
		 * 过度页面的所有操作
		 * @param driver
		 * @throws InterruptedException
		 */		
		public static  void affairCartProduct(WebDriver driver) throws InterruptedException{
			cartDriver = driver;
			jse = (JavascriptExecutor)driver; 
			
			DoCheckNavigitinOfPageProduct();
			ButtonAddCart();
			CheckStatesStock();	
		
		}
	


		
		
		private static void DoCheckNavigitinOfPageProduct() throws InterruptedException {
			// TODO Auto-generated method stub
			DoNavigitionOfPageProduct doNav = new DoNavigitionOfPageProduct(cartDriver);
			doNav.doNavgitionOfPageProduct();
			PageAd.checkSellOnce();
		}





		/**
		 * PageProduct:商品详情页，加入购物车
		 * @param driver
		 * @throws InterruptedException 
		 */
		public static void ButtonAddCart() throws InterruptedException{
			WebElement addcartButton = ElementPageProduct.getAddcartButton(cartDriver);
			if(addcartButton.getText()!=null){
			System.out.println("点击:"+addcartButton.getText());	
			jse.executeScript("arguments[0].click();", addcartButton); 
			log.info("已经点击加入购物车按钮");
			}
		}
		
		
		/**
		 * PageProduct:商品详情页,判断库存不足
		 * @return 
		 * @throws InterruptedException 
		 */
		public static Boolean IsButtonNoEnunghPr() throws InterruptedException{				
			
			ElementPageProduct elemPagePro = new ElementPageProduct(cartDriver);
			WebElement buttonNoEnunghpr = elemPagePro.getButtonNoEnunghPr();
			WebElement buttonSorryText = elemPagePro.getTextSorryCart();
			
			
			if(buttonNoEnunghpr.getAttribute("style").toString()!="display: none;"){
				Thread.sleep(1000);
				log.info(buttonSorryText.getText());
				return true;
				}else{
					log.info("库存信息正常。");
					return false;
					}			
		};
		
		/**
		 * PageProduct:操作库存不足的对话框
		 */
		private static void clickYes() {
			// TODO Auto-generated method stub
			ElementPageProduct elemPagePro = new ElementPageProduct(cartDriver);
			WebElement buttonConfirm = elemPagePro.getButtonConfirm();
			jse.executeScript("arguments[0].click();", buttonConfirm);
		}
		
		
		/**
		 * PageProduct:处理库存不足信息
		 * @throws InterruptedException
		 */
		private static boolean StockPr() throws InterruptedException {
			// TODO Auto-generated method stub
			if(IsButtonNoEnunghPr()){
				clickYes();
				log.info("库存不足，正在重新选择商品！");
				return false;
			}else{
				log.info("库存充足！");
				return true;
			}			
		}

		
		
		/**
		 * PageProduct检查库存状态：当点击加入购物车以后，要检查库存状态。
		 * @throws InterruptedException
		 */
		private static void CheckStatesStock() throws InterruptedException {
			// TODO Auto-generated method stub
			/*如果没有出现库存对话框，那么直接去判断这个对话框对象，就会找不到对象的路径，所以加try*/
			try{
				if(StockPr()!=true){
					checkColer();
					ButtonAddCart();
				}else{
					log.info("商品库存状态正常。");
				}
			}catch(Exception e){
				log.info("商品库存状态正常。");
			}			
		}
		
		
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
	
}
