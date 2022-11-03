package it.springcataloglogin.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import it.springcataloglogin.entity.User;
import it.springcataloglogin.user.CrmUser;

public interface UserService extends UserDetailsService {
	public List<User> findAll();
	public User findUserByName(String userName);
	public void save(CrmUser crmUser);
	public void update(CrmUser crmUser);
	public void deleteById(Long id);
	public void disableById(Long id);
	public void enableById(Long id);
	public void makeAdminById(Long id);
	public void removeAdminById(Long id);
}
