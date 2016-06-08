package Webq.Page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Webq.Element.ElementPageOrderDetails;


public class PageOrderDetails {
	
	static WebDriver OrderDriver;
	static JavascriptExecutor jse; 
	
	
	/**
	 * 结算页里的Action
	 * @param driver
	 * @throws InterruptedException 
	 */
	public static void ActionOrder(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		
		OrderDriver = driver;
		jse= (JavascriptExecutor)driver; 
		

//		获取订单号；
		getOrderNum();
//		取消订单
		CancleOrder();
		
	}


	
	
		
		/**
		 * 获取订单号
		 * @param driver
		 */	
		public static String getOrderNum() {
			// TODO Auto-generated method stub
			WebElement OrderNum = ElementPageOrderDetails.getOrderNum(OrderDriver);				
			String[] StringOrdernum = OrderNum.getText().split(":");
			String Ordernum = StringOrdernum[1];
			System.out.println("订单号："+Ordernum);
			return Ordernum;			
		}
		


		
		
		/**
		 * 在订单详情中取消订单。
		 * @throws InterruptedException 
		 */
		private static void CancleOrder() throws InterruptedException {
			// TODO Auto-generated method stub
			System.out.println("开始取消订单。");
//			先找到取消订单的按钮；
//			然后在判断是否取消成功；
			if(getOrderNum()!=null){
//				由于Alert对话框，在这里使用的是一个DIV，不是原生的Alert,所以这里使用了JS操作。
				WebElement ButtonCancleOrder = ElementPageOrderDetails.getButtonCancleOrder(OrderDriver);				
				if(iShasButtonCancleOrder(ButtonCancleOrder)){
				jse.executeScript("arguments[0].click();",ButtonCancleOrder);
				Thread.sleep(3000);
				WebElement ButtonAccept = ElementPageOrderDetails.getButtonAccept(OrderDriver);				
				jse.executeScript("arguments[0].click();",ButtonAccept);
				}else{
					System.out.println("没找到取消按钮！");
				}				
				
//				Alert conform = OrderDriver.switchTo().alert();					
//				System.out.println(conform.getText());
//				Thread.sleep(3000);
//				conform.accept();
				Thread.sleep(3000);
				if(IsCancleSuccesful()){
					System.out.println("订单取消成功！");
				}else{
					System.out.println("订单取消失败！");
				}
				
			}
		}





		/**
		 * 判断是否找到取消按钮
		 * @param ButtonCancleOrder
		 * @return 
		 * @throws InterruptedException
		 */
		private static boolean iShasButtonCancleOrder(WebElement ButtonCancleOrder) throws InterruptedException {
			// TODO Auto-generated method stub
			if(ButtonCancleOrder!=null){
				String TextButtonCancleOrder = ButtonCancleOrder.getText();
				System.out.println(TextButtonCancleOrder);
				Thread.sleep(3000);	
				return true;
			}else{
				return false;
			}
		}

		/**
		 * 判断订单是否取消成功。
		 * @return 
		 */
		private static boolean IsCancleSuccesful() {
			// TODO Auto-generated method stub
			WebElement orderStatus = ElementPageOrderDetails.getOrderStatus(OrderDriver);			
			String myStatus = orderStatus.getText();
			System.out.println(myStatus);
			if(myStatus.equals("已取消")){
				return true;
			}else{
				return false;
			}	
		}


}
