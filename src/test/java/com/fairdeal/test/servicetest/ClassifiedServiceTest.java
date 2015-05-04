package com.fairdeal.test.servicetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fairdeal.Constants;
import com.fairdeal.service.ClassifiedService;
import com.fairdeal.test.BaseTest;
import com.fairdeal.util.LoggerUtil;

public class ClassifiedServiceTest extends BaseTest{

	@Autowired
	private ClassifiedService service;
	
	private String shorttitle="testShortTitle";
	private String description="testShortdesc";
	private String address="testAddress";
	private String city="faridabad";
	private String state="haryana";
	private String pincode="121003";
	private String contactNumber="971161613513";
	private String price="20000000";
	private String email="test@testemail.com";
	private Map<String, InputStream> imagesMap;
	
	@Test
	public void registerClassified(){
		int agentId = service.registerClassified(shorttitle, description, address, city, state, pincode, contactNumber,email, price, imagesMap);
		LoggerUtil.debug("Classified is registered");
		assertNotNull(agentId);
	}
	
	
	@Test
	public void testfetchClassifiedSizeTags(){
		for (int i=0; i<50; i++){
			int agentId = service.registerClassified(shorttitle+i, description+i, address+i, city+i, state+i, pincode+i, contactNumber,email, price, imagesMap);
		}
		
		long size1 = service.getTotalClassifiedCount("Faridabad");
		assertEquals(50, size1);
		
		
		long size2 = service.getTotalClassifiedCount("Puneet");
		assertEquals(0, size2);
	}
	
	@Test
	public void testfetchClassifiedSizeTagsIsNull(){
		for (int i=0; i<50; i++){
			int agentId = service.registerClassified(shorttitle+i, description+i, address+i, city+i, state+i, pincode+i, contactNumber,email, price, imagesMap);
		}
		
		long size1 = service.getTotalClassifiedCount("Faridabad");
		assertEquals(50, size1);
		
		
		long size2 = service.getTotalClassifiedCount("Puneet");
		assertEquals(0, size2);
		
		long size3 = service.getTotalClassifiedCount(null);
		assertEquals(50, size3);
	}
	
	@Test
	public void testfetchClassifiedWithNoTags(){
		for (int i=0; i<50; i++){
			int agentId = service.registerClassified(shorttitle+i, description+i, address+i, city+i, state+i, pincode+i, contactNumber,email, price, imagesMap);
		}
		
		List l = service.getClassifieds(0, 10, "Faridabad,Haryana");
		assertEquals(l.size(), 10);
		
		List l2 = service.getClassifieds(0, 10);
		assertEquals(l2.size(), 10);
		
		
		List l3 = service.getClassifieds(0, 20);
		assertEquals(l3.size(), 20);
	}
	
	
	
	@Override
	public void setUp(){
		deleteTable("Classified");
		
		imagesMap = new HashMap<String, InputStream>();
		try {
			File file = new File("mainImage.jpg");
			imagesMap.put(Constants.Classified.CLASIFIED_IMAGE1, new FileInputStream(file));
			imagesMap.put(Constants.Classified.CLASIFIED_IMAGE2, new FileInputStream(file));
			imagesMap.put(Constants.Classified.CLASIFIED_IMAGE3, new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
