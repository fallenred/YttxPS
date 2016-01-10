package com.yttx.yttxps.comm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author kereny
 *
 */
public class Config {
	
	private static Properties properties = new Properties();
	
    private static Logger logger = LoggerFactory.getLogger(Config.class);
    
    private static final String	CONFIG_PATH	= "/resources/config.properties";

    public Config() {

    }

    /**
     * 初始化
     */
    static {
        loadConfigFile();
    }


    	/**
         *  装载配置文件
         *  
    	 * @author kereny
    	 * @date 2015-6-3 下午3:03:15
    	 * void
         *
    	 */
    private static void loadConfigFile() {
        InputStream is = Config.class.getResourceAsStream(CONFIG_PATH);
        try {
            properties.load(is);
        } catch (IOException ex) {
        	logger.error("加载资源配置文件出错,请检查工程下[WEB-INF/classes/conf/Config.properties]文件是否存在!!");
            ex.printStackTrace();
        }
    }



    	/**
         *  返回配置文件属性对象this.properties
         *  
    	 * @author kereny
    	 * @date 2015-6-3 下午3:03:37
    	 * @return
    	 * Properties
         *
    	 */
    public static Properties getConfigProperties() {
        return properties;
    }
    

    
    	/**
         *  返回KEY对映的String值
         *  
    	 * @author kereny
    	 * @date 2015-6-3 下午3:03:55
    	 * @param key
    	 * @return
    	 * String
         *
    	 */
    
    public static String getValue(String key) {
		return properties.getProperty(key);
	}


		/**
	     *  返回KEY对映的String值,如果没有该KEY对取defaultValue
	     *  
		 * @author kereny
		 * @date 2015-6-3 下午3:04:16
		 * @param key
		 * @param defaultValue
		 * @return
		 * String
	     *
		 */
	public static String getValue(String key, String defaultValue) 
	{
		return properties.getProperty(key, defaultValue);
	}


		/**
	     *  返回KEY对映的int值
	     *  
	     *  
		 * @author kereny
		 * @date 2015-6-3 下午3:04:33
		 * @param key
		 * @return
		 * int
	     *
		 */
	public static int getIntValue(String key) {
		return Integer.parseInt(properties.getProperty(key));
	}

	

		/**
	     *  返回KEY对映的int值,如果没有该KEY对取defaultValue
	     *  
		 * @author kereny
		 * @date 2015-6-3 下午3:04:49
		 * @param key
		 * @param defaultIntValue
		 * @return
		 * int
	     *
		 */
	public static int getIntValue(String key, int defaultIntValue) {
		return Integer.parseInt(properties.getProperty(key, String.valueOf(defaultIntValue)));
	}


}
