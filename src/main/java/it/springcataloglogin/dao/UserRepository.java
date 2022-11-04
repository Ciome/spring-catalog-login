package it.springcataloglogin.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.springcataloglogin.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public User findUserByUsername(String userName);
	
	public User findUserById(Long id);
	
	public <S extends User> S save(User user);

	public List<User> findAll();

	public void deleteById(Long id);
	
	
}
