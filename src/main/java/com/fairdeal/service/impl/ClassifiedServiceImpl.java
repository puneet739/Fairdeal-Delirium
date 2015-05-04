package com.fairdeal.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.fairdeal.Constants;
import com.fairdeal.Results;
import com.fairdeal.dao.ClassifiedDao;
import com.fairdeal.entity.Classified;
import com.fairdeal.service.ClassifiedService;
import com.fairdeal.util.Config;
import com.fairdeal.util.TagRepo;
import com.fairdeal.util.Util;

public class ClassifiedServiceImpl implements ClassifiedService {

	@Autowired
	ClassifiedDao classifiedDao;

	@Autowired
	TagRepo tagrepo;
	
	@Override
	public Integer registerClassified(String shortTitle, String description, String address, String city, String state, String pincode, String contactNumber, String email, String price, Map<String, InputStream> imagesMap) {
		Classified classified = generateClassifiedEntity(null, shortTitle, description, address, city, state, pincode, contactNumber,email , price, imagesMap);
		classifiedDao.insertClassified(classified);
		uploadClassifiedImages(classified, imagesMap);
		return Results.SUCCESS;
	}

	private void uploadClassifiedImages(Classified classified, Map<String, InputStream> imagesMap) {
		String imageUrl1 = Util.saveFile(imagesMap.get(Constants.Classified.CLASIFIED_IMAGE1), Config.getString(Constants.Config.CLASSIFIED_IMAGE_STORE_PATH), classified.getId()+"_" + Constants.Classified.CLASIFIED_IMAGE1 + ".jpg");
		String imageUrl2 = Util.saveFile(imagesMap.get(Constants.Classified.CLASIFIED_IMAGE2), Config.getString(Constants.Config.CLASSIFIED_IMAGE_STORE_PATH), classified.getId()+"_" + Constants.Classified.CLASIFIED_IMAGE2 + ".jpg");
		String imageUrl3 = Util.saveFile(imagesMap.get(Constants.Classified.CLASIFIED_IMAGE3), Config.getString(Constants.Config.CLASSIFIED_IMAGE_STORE_PATH), classified.getId()+"_" + Constants.Classified.CLASIFIED_IMAGE3 + ".jpg");
		String imageUrl4 = Util.saveFile(imagesMap.get(Constants.Classified.CLASIFIED_IMAGE4), Config.getString(Constants.Config.CLASSIFIED_IMAGE_STORE_PATH), classified.getId()+"_" + Constants.Classified.CLASIFIED_IMAGE4 + ".jpg");
		classified.setImageUrl1(imageUrl1);
		classified.setImageUrl2(imageUrl2);
		classified.setImageUrl3(imageUrl3);
		classified.setImageUrl4(imageUrl4);
		classifiedDao.updateClassified(classified);
	}

	@Override
	public Integer modifyClassified(long id, String shortTitle, String description, String address, String city, String state, String pincode, String contactNumber,String email, String price, Map<String, InputStream> imagesMap) {
		Classified classified = classifiedDao.getClassified(id);
		classified=generateClassifiedEntity(classified, shortTitle, description, address, city, state, pincode, contactNumber,email, price, imagesMap);
		classifiedDao.updateClassified(classified);
		return Results.SUCCESS;
	}

	@Override
	public List<Classified> getClassifieds() {
		return classifiedDao.getAllClassifed();
	}

	@Override
	public Classified getClassified(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Classified generateClassifiedEntity(Classified classified, String shortTitle, String description, String address, String city, String state, String pincode, String contactNumber,String email, String price, Map<String, InputStream> imagesMap) {
		if (classified == null) {
			classified = new Classified();
		}
		classified.setShorttitle(shortTitle);
		classified.setDescription(description);
		classified.setAddress(address);
		classified.setCity(city);
		classified.setState(state);
		classified.setPincode(pincode);
		classified.setContactNumber(contactNumber);
		classified.setPrice(Util.getbigDecimanPrice(price));
		classified.setEmail(email);
		classified.setContactNumber(contactNumber);
		Set<String> currentSet = new HashSet<String>();
		List<String> tags= Util.getTags(description);
		tags.addAll(Util.getTags(shortTitle));
		tags.addAll(Util.getTags(city));
		tags.addAll(Util.getTags(state));
		
		currentSet.addAll(tags);
		classified.setTags(tagrepo.getTag(new ArrayList(currentSet)));
		
		return classified;
	}

	@Override
	public List<Classified> getClassifieds(int firstResult, int maxResults, String tags) {
		return classifiedDao.getClassifiedByTags(firstResult, maxResults, tags);
	}

	@Override
	public List<Classified> getClassifieds(int firstResult, int maxResults) {
		return classifiedDao.getClassifiedByTags(firstResult, maxResults, null);
	}

	@Override
	public long getTotalClassifiedCount() {
		long totalSize = classifiedDao.getTotalSize();
		return totalSize;
	}

	@Override
	public long getTotalClassifiedCount(String tags) {
		long totalSize = classifiedDao.getTotalSize(tags);
		return totalSize;
	}

}
