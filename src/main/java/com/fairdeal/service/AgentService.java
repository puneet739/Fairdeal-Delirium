package com.fairdeal.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.fairdeal.entity.Agent;

public interface AgentService{
	
	public List<Agent> getAllAgents();

	public int registerAgent(String firstName, String lastName, String address, String phoneNumber, String emailAddress, String agentDescription,
			String webSite, Map<String, InputStream> imagesMap);
	
	
	public int modifyAgent(long agentId, String firstName, String lastName, String address, String phoneNumber, String emailAddress, String agentDescription,
			String webSite, Map<String, InputStream> imagesMap);
}
