package com.fairdeal.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONValue;

import com.fairdeal.Constants;

public class Util {

	private static final String imagePath = "../../../../../../Images";
	public static String getJSON(List object) {
		return JSONValue.toJSONString(object);
	}

	/**
	 * This function will return the current Date
	 * 
	 * @return
	 */
	public static Date getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}
	
	public static Date getClassifiedExpiryDate() {
		Calendar cal = Calendar.getInstance();
		int daystoExpire = Config.getInt(Constants.Config.CLASSIFIED_EXPIRY_DAYS);
		cal.add(Calendar.DATE, daystoExpire);
		return cal.getTime();
	}

	public static Date uploadImage() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	public static String saveFile(InputStream inputStream, String filename) {
		return saveFile(inputStream,Config.getString(Constants.Config.AGENTS_IMAGE_STORE_PATH), filename);
	}
	
	public static String saveFile(InputStream inputStream, String outputPath, String filename) {
		String finalFilePath = null;
		if (inputStream!=null)
		try {
			File file = new File(outputPath+filename);
			LoggerUtil.debug("Saving the file at :" +file.getAbsolutePath());
			OutputStream outputStream = new FileOutputStream(file);
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			finalFilePath=filename;
		} catch (IOException e) {
			LoggerUtil.error("Exception caused while saving the image");
		}catch (NullPointerException ne){
			LoggerUtil.error(" Null pointer Exceptoin occured, user have not provided the file");
		}
		
		return finalFilePath;
	}
	
	
	public static List<String> getTags(String text){
		List<String> currenttags = new LinkedList<String>();
		List<Object> allTags = Config.getList("dlm.tags.alltags");
		for (Object tag : allTags){
			String currentTag = ((String)tag).toUpperCase();
			if (text.toUpperCase().contains(currentTag)){
				currenttags.add(currentTag);
			}
		}
		return currenttags;
	}

	public static BigDecimal getbigDecimanPrice(String price) {
		try{
			return new BigDecimal(price);
		}catch(Exception e){
			LoggerUtil.error("Exception caused while parsing price to big decimal"+price);
		}
		return BigDecimal.ZERO;
	}
}
