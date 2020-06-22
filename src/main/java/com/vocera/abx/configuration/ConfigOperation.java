package com.vocera.abx.configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 
 * @author rsangoli
 *
 */
public class ConfigOperation {
	
	private  InputStream classLoad(String propFileName){
		return getClass().getClassLoader().getResourceAsStream(propFileName);
	}
	/**
	 * Get all conf properties
	 * @param Conf
	 * @return
	 * @throws IOException
	 */
	public static Map getProperties() throws IOException {
		InputStream inputStream = null;
		Map<String, String> data = new HashMap<String, String>();
		try {
			Properties prop = new Properties();
			String propFileName = "conf.properties";
			inputStream = new ConfigOperation().classLoad(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			Enumeration enm = prop.propertyNames();

		    while (enm.hasMoreElements()) {
		    	String Key=(String) enm.nextElement();
		      data.put(Key, prop.getProperty(Key));
		    }
		    
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
//			inputStream.close();
		}
		return data;
	}
	public Map getProperties(String FileName) throws IOException {
		InputStream inputStream = null;
		Map<String, String> data = new HashMap<String, String>();
		try {
			Properties prop = new Properties();
			String propFileName =FileName; 
					//"conf.properties";
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			Enumeration enm = prop.propertyNames();

		    while (enm.hasMoreElements()) {
		    	String Key=(String) enm.nextElement();
		      data.put(Key, prop.getProperty(Key));
		    }
		    
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
//			inputStream.close();
		}
		return data;
	}
	public static void main(String[] args) throws IOException {
		System.out.println("hiii");
		ConfigOperation readconfig= new ConfigOperation();
		System.out.println(readconfig.getProperties().toString());;
	}
}
