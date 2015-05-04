package com.fairdeal.test;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.fairdeal.util.LoggerUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean/application-beans.xml")
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class BaseTest {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Before
	public void setUp(){
		LoggerUtil.debug("Doinin initializaion from BaseTest");
	}
	
	public void deleteTable(String tableName){
		sessionFactory.getCurrentSession().createQuery("delete from "+tableName).executeUpdate();
	}
	
}
