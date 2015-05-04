package com.fairdeal.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fairdeal.dao.AgentDao;
import com.fairdeal.entity.Agent;
import com.fairdeal.test.BaseTest;

public class RegisterMockData extends BaseTest {

	@Autowired
	AgentDao agentRepo;

	@Test
	public void addAgentMock() {
		for (int i = 0; i < 10; i++) {
			Agent agent = new Agent();
			agent.setAgentDescription("TestAgent"+i);
			agent.setFirstName("Puneet"+i);
			agent.setLastName("Behl"+i);
			agent.setImageUrl("http://wp.production.patheos.com/blogs/freedhearts/files/2012/09/love_41.jpg");
			agentRepo.insertAgent(agent);
		}
	}
}
