package Webq.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtilsJi {
	public static Log log = new Log(ConfigUtilsJi.class);
	
	public  static Properties getProperties(String config) throws FileNotFoundException{
		
		
		Properties myproperties = new Properties();
		log.info("启动文本路径是："+"/Users/700sfriend/Desktop/work/MyDEV/workspace-sts-3.7.1.RELEASE/testDemo/config.properties");
		log.info("Get the config file: " + "/Users/700sfriend/Desktop/work/MyDEV/workspace-sts-3.7.1.RELEASE/testDemo/config.properties");
		
		File myfile = new File("/Users/700sfriend/Desktop/work/MyDEV/workspace-sts-3.7.1.RELEASE/testDemo/config.properties");
		FileInputStream myinstream = new FileInputStream(config);
		
		try{
			myproperties.load(myinstream);
		}catch(Exception e) {
			log.error("can't find the config file ");
			log.error(e.getMessage());
		}
		return myproperties;
	}
}
