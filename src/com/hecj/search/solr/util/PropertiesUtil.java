package com.hecj.search.solr.util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
	private static final Object LOCK = new Object();
	private static Properties props = null; 
	
	private final static void setProperties(){
		props = new Properties();
		try {
			props.load(PropertiesUtil.class.getResourceAsStream("/server/config/server.properties"));
		} catch (IOException e) {
			props = null;
			e.printStackTrace();
		}
	}
	public final static Properties getProperties(){
		if(props == null){
			synchronized (LOCK) {
				setProperties();
			}
		}
		return props;
	}

}
