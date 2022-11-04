package it.springcataloglogin.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import it.springcataloglogin.entity.Role;

import it.springcataloglogin.dao.RoleRepository;
import it.springcataloglogin.dao.UserRepository;
import it.springcataloglogin.entity.User;
import it.springcataloglogin.entity.UserData;
import it.springcataloglogin.user.CrmUser;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userDao;

	@Autowired
	private RoleRepository roleDao;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Transactional
	public List<User> findAll() {
		return userDao.findAll();
	}
	
	@Override
	@Transactional
	public User findUserByName(String userName) {
		return userDao.findUserByUsername(userName);
	}

	@Override
	@Transactional
	public void save(CrmUser crmUser) {
		UserData userData = new UserData(crmUser.getFirstName(),
				crmUser.getLastName(), crmUser.getEmail());
		User user = new User(crmUser.getUsername(), encoder.encode(crmUser.getPassword()), userData);
		user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_USER")));
		user.setEnabled(true);
		userDao.save(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findUserByUsername(userName);
		if (user == null)
			throw new UsernameNotFoundException("Invalid username or password.");
		if (!user.isEnabled())
			throw new UsernameNotFoundException("Account is disabled.");
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void update(CrmUser crmUser) {
		User user = userDao.findUserByUsername(crmUser.getUsername());
		System.out.println(user);
		if (user == null) {
			throw new UsernameNotFoundException("User not found.");
		}
		user.getUserData().setFirstName(crmUser.getFirstName());
		user.getUserData().setLastName(crmUser.getLastName());
		user.getUserData().setEmail(crmUser.getEmail());
		userDao.save(user);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		userDao.deleteById(id);
	}

	@Override
	@Transactional
	public void disableById(Long id) {
		User user = userDao.findUserById(id);
		user.setEnabled(false);
		userDao.save(user);
	}

	@Override
	@Transactional
	public void enableById(Long id) {
		User user = userDao.findUserById(id);
		user.setEnabled(true);
		userDao.save(user);
	}

	@Override
	@Transactional
	public void makeAdminById(Long id) {
		User user = userDao.findUserById(id);
		
		boolean isAdmin = false;
		for (Role r : user.getRoles()) {
			if (r.getName().equals("ROLE_ADMIN"))
				isAdmin = true;
		}
		
		if (!isAdmin)
			user.getRoles().add(roleDao.findRoleByName("ROLE_ADMIN"));
		
		userDao.save(user);
	}
	
	@Override
	@Transactional
	public void removeAdminById(Long id) {
		User user = userDao.findUserById(id);
		for (Role r : user.getRoles()) {
			if (r.getName().equals("ROLE_ADMIN"))
				user.getRoles().remove(r);
		}

		userDao.save(user);
	}

}
