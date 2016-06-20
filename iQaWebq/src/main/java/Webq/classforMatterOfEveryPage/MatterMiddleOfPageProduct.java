package Webq.classforMatterOfEveryPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * MatterClass:获取Product页面中部的元素
 * @author 700sfriend
 *
 */
public class MatterMiddleOfPageProduct{
	
	
	private WebDriver driver;


	private WebElement matterMiddleDiv;

	private String pathmatterMiddleDiv;


	/**
	 * 构造函数
	 */
	public  MatterMiddleOfPageProduct(WebDriver driver){
		this.driver = driver;
		
		this.getMatterMiddleDiv();
		this.getNavigtition();
//		this.getMainList();
//		this.getSideNav();
	}
	
	
	/**
	 * 获取div:页面中间Div
	 * @return
	 */
	private WebElement getMatterMiddleDiv() {
		// TODO Auto-generated method stub
		pathmatterMiddleDiv = ".//div[@class = 'content-holder graybg product-detail-page']";
		matterMiddleDiv = driver.findElement(By.xpath(pathmatterMiddleDiv));
		return  matterMiddleDiv;
	}
	

	/**
	 * 获取div:二级导航
	 * @return 
	 */
	public MatterNavigitionOfPageProduct getNavigtition() {
		// TODO Auto-generated method stub
		
	  /*
	   * 以前的写法
	    String pathmatterNavigition = pathmatterMiddleDiv + "//div[@]";
		matterNavigition = driver.findElement(By.xpath(pathmatterNavigition));
		return  matterNavigition;
		*/
		
		return new MatterNavigitionOfPageProduct(driver,pathmatterMiddleDiv);
	}
	
	

	
	private void getSideNav() {
		// TODO Auto-generated method stub
		
	}

	private void getMainList() {
		// TODO Auto-generated method stub
		
	}
	
}
