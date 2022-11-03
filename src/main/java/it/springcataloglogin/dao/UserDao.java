package it.springcataloglogin.dao;

import java.util.List;

import it.springcataloglogin.entity.User;

public interface UserDao {

	public User findUserByName(String userName);
	
	public User findUserById(Long id);
	
	public void save(User user);

	public List<User> findAll();

	public void deleteById(Long id);
	
	
}
