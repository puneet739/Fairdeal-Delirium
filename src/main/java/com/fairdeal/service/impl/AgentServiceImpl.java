package com.fairdeal.service.impl;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

import com.fairdeal.Constants;
import com.fairdeal.Results;
import com.fairdeal.dao.AgentDao;
import com.fairdeal.entity.Agent;
import com.fairdeal.entity.Classified;
import com.fairdeal.service.AgentService;
import com.fairdeal.util.Config;
import com.fairdeal.util.LoggerUtil;
import com.fairdeal.util.Util;

public class AgentServiceImpl implements AgentService {

	@Autowired
	private AgentDao agentDao;

	@Override
	@Transactional
	public List<Agent> getAllAgents() {
		LoggerUtil.debug("Fetching agents using agent Service");
		return agentDao.getAllAgents();
	}

	public AgentDao getAgentDao() {
		return agentDao;
	}

	public void setAgentDao(AgentDao agentDao) {
		this.agentDao = agentDao;
	}

	@Override
	@Transactional
	public int registerAgent(String firstName, String lastName, String address, String phoneNumber, String emailAddress, String agentDescription, String website, Map<String, InputStream> imagesMap) {
		try{
			Agent agent = generateAgentEntity(null,firstName, lastName, address, phoneNumber, emailAddress, agentDescription, website);
			agentDao.insertAgent(agent);
			uploadAgentImages(agent, imagesMap);
		}catch(ConstraintViolationException e){
			return Results.AGENT.EMAIL_ALREADY_REGISTERED;
		}
		return Results.SUCCESS;
	}

	@Override
	@Transactional
	public int modifyAgent(long agentId, String firstName, String lastName, String address, String phoneNumber, String emailAddress, String agentDescription, String website, Map<String, InputStream> imagesMap) {
		try{
			Agent agent = agentDao.getAgent(agentId);
			generateAgentEntity(agent, firstName, lastName, address, phoneNumber, emailAddress, agentDescription, website);
			agentDao.updateAgent(agent);
		}catch(ConstraintViolationException e){
			return Results.AGENT.EMAIL_ALREADY_REGISTERED;
		}
		return Results.SUCCESS;
	}
	
	private void uploadAgentImages(Agent agent, Map<String, InputStream> imagesMap) {
		if (imagesMap==null) return;
		String agentImage = Util.saveFile(imagesMap.get(Constants.Agent.AGENT_MAIN_IMAGE), Config.getString(Constants.Config.AGENTS_IMAGE_STORE_PATH), agent.getId()+"_" + Constants.Agent.AGENT_MAIN_IMAGE + ".jpg");
		String imageUrl1 = Util.saveFile(imagesMap.get(Constants.Agent.AGENT_IMAGE1), Config.getString(Constants.Config.AGENTS_IMAGE_STORE_PATH), agent.getId()+"_" + Constants.Agent.AGENT_IMAGE1 + ".jpg");
		String imageUrl2 = Util.saveFile(imagesMap.get(Constants.Agent.AGENT_IMAGE2), Config.getString(Constants.Config.AGENTS_IMAGE_STORE_PATH), agent.getId()+"_" + Constants.Agent.AGENT_IMAGE2 + ".jpg");
		String imageUrl3 = Util.saveFile(imagesMap.get(Constants.Agent.AGENT_IMAGE3), Config.getString(Constants.Config.AGENTS_IMAGE_STORE_PATH), agent.getId()+"_" + Constants.Agent.AGENT_IMAGE3 + ".jpg");
		String imageUrl4 = Util.saveFile(imagesMap.get(Constants.Agent.AGENT_IMAGE4), Config.getString(Constants.Config.AGENTS_IMAGE_STORE_PATH), agent.getId()+"_" + Constants.Agent.AGENT_IMAGE4 + ".jpg");
		agent.setAgentImage(agentImage);
		agent.setImageUrl1(imageUrl1);
		agent.setImageUrl2(imageUrl2);
		agent.setImageUrl3(imageUrl3);
		agent.setImageUrl4(imageUrl4);
		agentDao.updateAgent(agent);
	}
	
	public Agent generateAgentEntity(Agent agent, String firstName, String lastName, String address, String phoneNumber, String emailAddress, String agentDescription, String website){
		if (agent==null)
			agent = new Agent();
		agent.setFirstName(firstName);
		agent.setLastName(lastName);
		agent.setAddress(address);
		agent.setPhoneNumber(phoneNumber);
		agent.setEmailAddress(emailAddress);
		agent.setAgentDescription(agentDescription);
		agent.setWebsite(website);

//		String mainImageUrl = Util.saveFile(inputStreamImages[0], imagePrefix+"_mainImage.jpg");
//		String image1 = Util.saveFile(inputStreamImages[1], imagePrefix + "_image1.jpg");
//		String image2 = Util.saveFile(inputStreamImages[2], imagePrefix + "_image2.jpg");
//		String image3 = Util.saveFile(inputStreamImages[3], imagePrefix + "_image3.jpg");
//		agent.setAgentImage(mainImageUrl);
//		agent.setImageUrl(mainImageUrl);
//		agent.setImageUrl1(image1);
//		agent.setImageUrl2(image2);
//		agent.setImageUrl3(image3);

		return agent;
	}
}
