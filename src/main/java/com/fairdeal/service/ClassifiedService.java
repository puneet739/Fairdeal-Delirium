package com.fairdeal.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.fairdeal.entity.Classified;

@Transactional
public interface ClassifiedService {

	public Integer registerClassified(String shortTitle, String description, String address, String city, String state, String pincode, String contactNumber,String email, String price, Map<String, InputStream> imagesMap);

	public Integer modifyClassified(long id, String shortTitle, String description, String address, String city, String state, String pincode, String contactNumber,String email, String price, Map<String, InputStream> imagesMap);

	public List<Classified> getClassifieds();
	
	public List<Classified> getClassifieds(int firstResult, int maxResults,String tags );
	
	public List<Classified> getClassifieds(int firstResult, int maxResults );

	public Classified getClassified(String id);
	
	public long getTotalClassifiedCount();
	
	public long getTotalClassifiedCount(String tags);
}
