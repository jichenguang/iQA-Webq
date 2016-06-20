package Webq.classforMatterOfEveryPage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Div 类：商品详情页的二级导航
 * @author 700sfriend
 *
 */
public class MatterNavigitionOfPageProduct {
		
	 private String pathmatterNavigition;
	 private WebDriver driver;
	 private WebElement matterNavigition;
	private String path;

	/**
	 * 构造函数：MatterNavigitionOfPageProduct
	 * @param driver
	 * @param path
	 */
	  public MatterNavigitionOfPageProduct(WebDriver driver,String path){
		  this.driver = driver;
		  this.path = path; 
			//	  getelement
		  this.getMatterNavigition() ;
	  }
	  
	  /**
	   * 方法：getMatterNavigition
	   * 获取二级导航的WebElement
	   * @return
	   */
	  public WebElement getMatterNavigition() {
			//		  location	
		  	 pathmatterNavigition = path + "//div[@class = 'crumb-trail']";
		  	 matterNavigition =  driver.findElement(By.xpath(pathmatterNavigition));
			 System.out.println(matterNavigition.getText());
			 return matterNavigition;
	  }
	  
	  /**
	   * 方法：获取导航栏的a节点
	   * @return
	   */  
	  public List<WebElement> getAdiv(){		  		
		List<WebElement> matterNavA = matterNavigition.findElements(By.tagName("a"));
		System.out.println("二级导航的节点数量为："+(matterNavA.size()+1));
		System.out.println(matterNavA.get(1).getText());
		return matterNavA;		  
	  }
	  
	   
	  
}
