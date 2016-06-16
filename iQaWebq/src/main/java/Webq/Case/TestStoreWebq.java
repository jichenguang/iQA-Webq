package Webq.Case;


import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import Webq.Action.actionGetSession;
import Webq.Action.actionPageHome;
import Webq.Page.PageAd;
import Webq.Page.PageCart;
import Webq.Page.PageOrder;
import Webq.Page.PageOrderDetails;
import Webq.Page.PageOrderLists;
import Webq.Page.PageOrderPay;
import Webq.Page.PageProduct;
import Webq.Page.PageTransitional;
import Webq.utils.TestNGListener;
import Webq.utils.UITest;

@Listeners({ TestNGListener.class })
public class TestStoreWebq extends UITest {

//	注释掉，使用默认浏览器。
//	WebDriver driver = DriverFactory.getChromeDriver();
	WebDriver driver = new FirefoxDriver();


	@BeforeMethod(alwaysRun = true)
	public void init() {		
		super.init(driver);
		actionPageHome.setDriver(driver);
	}

	@Test(groups = "loginTest")
	public void actionCartSubCancleOrder() throws Exception {
		/*登陆*/
		actionPageHome.viewMainPage();	
		/*登陆后，获取Cookie.*/
		actionGetSession.addCookie(driver);
		/*然后执行业务操作*/
		/*发邮件*/
//		MailTest.ssSendMail();
		/*商品AD页面*/
		PageAd.affairCartAd(driver);
		/*商品详情页的操作*/
		PageProduct.affairCartProduct(driver);
		/*过渡页面*/
		PageTransitional.affairTransitional(driver);
		/*加入购物车*/
		PageCart.affairCart(driver);
		/*提交订单*/
		PageOrder.ActionOrder(driver);
		/*支付页面确认提交状态*/
		PageOrderPay.ActionOrderPay(driver);
		/*订单列表页面*/
		PageOrderLists.ActionOrder(driver);
		/*订单详情页面*/
		PageOrderDetails.ActionOrder(driver);		
	}
	


	@Override
	@AfterMethod(alwaysRun = true)
	public void stop() {
		super.stop();
	}

}
