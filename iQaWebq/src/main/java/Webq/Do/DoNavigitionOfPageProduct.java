package Webq.Do;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Webq.Page.PageProduct;
import Webq.Page.forLoggerPage;
import Webq.classforMatterOfEveryPage.MatterMiddleOfPageProduct;
import Webq.classforMatterOfEveryPage.MatterNavigitionOfPageProduct;

public class DoNavigitionOfPageProduct {
	
	static WebDriver driver; 
	static JavascriptExecutor jse; 
	/**
	 * @author 700sfriend
	 * 这个log必须是静态的，那么我只能用PageCart.class来取代ew Log(this.getClass())的写法。
	 * 效果是一样的。
	 */	
	protected static forLoggerPage log = new forLoggerPage(PageProduct.class);
	

		/**
		 * 构造函数：集中管理，对元素的操作方法
		 * @param driver
		 */
		public DoNavigitionOfPageProduct(WebDriver driver){
			this.driver = driver;
			this.jse = (JavascriptExecutor)driver; 
		}
	
		
		/**
		 * Do:导航栏对象的操作
		 * @throws InterruptedException 
		 */
		public static void DoNavgitionOfPageProduct() throws InterruptedException {
			// TODO Auto-generated method stub
			MatterNavigitionOfPageProduct MatterNav = (new MatterMiddleOfPageProduct(driver)).getNavigtition();
			List<WebElement> NavAdiv = MatterNav.getAdiv();
			IsSamehref(NavAdiv);			
		}
		
		
		
		/**
		 * 判断导航栏的URL是否一致
		 * @param navAdiv 
		 */
		private static boolean IsSamehref(List<WebElement> navAdiv) {
			// TODO Auto-generated method stub
			String href = navAdiv.get(1).getAttribute("href");
			jse.executeScript("arguments[0].click();", navAdiv.get(1));
			String CurrentUrl = driver.getCurrentUrl();
			if(href.equals(CurrentUrl)){
				log.info("检查结果："+"一致");
				log.info(href);
				log.info(CurrentUrl);
				return true;
			}else{
				log.error("检查结果："+"与"+"不一致");
				log.info(href );
				log.info(CurrentUrl);
				return false;
			}
		}
	
	
	}
