package com.fairdeal.util;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConversionException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.stereotype.Component;

@Component
public class Config {

	private String configFile;
	
	private static Configuration config;

	public Configuration getConfig() {
		return config;
	}

	public void setConfig(Configuration config) {
		this.config = config;
	}

	public String getConfigFile() {
		return configFile;
	}

	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}
	
	@PostConstruct
	public void init() throws ConfigurationException{
		config = new PropertiesConfiguration(configFile);
	}
	
	public static String getString(String key){
		return config.getString(key);
	}
	
	public static int getInt(String key){
		int result=0;
		try{
			result=config.getInt(key);
		}catch(ConversionException e){
			LoggerUtil.error("Not able to translate the config, returning zero");
		}
		return result;
	}
	
	public static List<Object> getList(String key){
		List<Object> list = config.getList(key);
		return list;
	}
	
}
