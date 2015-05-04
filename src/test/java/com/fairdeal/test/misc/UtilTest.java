package com.fairdeal.test.misc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fairdeal.Constants;
import com.fairdeal.test.BaseTest;
import com.fairdeal.util.Config;
import com.fairdeal.util.LoggerUtil;
import com.fairdeal.util.Util;

public class UtilTest extends BaseTest{
	
	@Autowired
	Config config;

	@Test
	public void testSaveInputStreamToFile(){
		String filePath = Config.getString(Constants.Config.AGENTS_IMAGE_STORE_PATH);
		File file = new File(filePath+"puneet.properties");
		file.exists();
		LoggerUtil.debug("File absolute Path is "+file.getAbsolutePath());
	}
	
	@Test
	public void testReadingconfiguration(){
		String configOutPut = Config.getString("puneet.test");
		assertEquals("TestRun", configOutPut);
	}
	
	@Test
	public void testLoadAllPropertyFilesinFolder(){
		String configOutPut = Config.getString("puneet.test");
		assertEquals("TestRun", configOutPut);
		assertEquals("loaded", Config.getString("included.properties"));
		assertEquals(null, Config.getString("application2.propesr"));
	}
	
	@Test
	public void testTagsGenerated(){
		String desc = "This is a test description from noida. Faridabad is a good place. ";
		List<String> obj = Util.getTags(desc);
		assertTrue(obj.contains("NOIDA"));
		assertTrue(obj.contains("FARIDABAD"));
		assertFalse(obj.contains("DELHI"));
	}
	
	public void testSaveInputStreamToFileInputstreamNull(){
	}
	
	public void testSaveInputStreamToFilePathFromConfig(){
	}
	
}
