package com.fairdeal.dao;

import java.util.List;

import com.fairdeal.entity.Classified;
import com.fairdeal.entity.User;

/**
 * This is the base package which will have details on how the User will be added to system
 * @author pbehl1
 *
 */
public interface UserDao extends BaseDao{
	public Integer insertUser(User currentUser);

	public Integer updateUser(User currentUser);

	public Integer DeleteUser(User currentUser);

	public Integer DeleteUser(Integer userId);
	
	public User getUser(Integer userId);
	
	public User getUser(String emailAddress);
	
	public List<User> getAllUsers();
	
	public List<User> getUsers(int firstResult, int maxResult);
}
