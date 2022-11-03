package it.springcataloglogin.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.springcataloglogin.entity.Role;
import it.springcataloglogin.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public User findUserByName(String username) {
		Session session = entityManager.unwrap(Session.class);
		
		Query<User> query = session.createQuery("from User where username=:name", User.class);
		query.setParameter("name", username);
		
		User user = null;
		try {
			user = query.getSingleResult();
		} catch (Exception e) {
			user = null;
		}
		
		return user;
	}
	
	@Override
	public User findUserById(Long id) {
		Session session = entityManager.unwrap(Session.class);
		
		Query<User> query = session.createQuery("from User where id=:userId", User.class);
		query.setParameter("userId", id);
		
		User user = null;
		try {
			user = query.getSingleResult();
		} catch (Exception e) {
			user = null;
		}
		
		return user;
	}

	@Override
	public void save(User user) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(user);
	}

	@Override
	public List<User> findAll() {
		Session session = entityManager.unwrap(Session.class);
		Query<User> query = session.createQuery("from User", User.class);
		return query.getResultList();
	}

	@Override
	public void deleteById(Long id) {
		Session session = entityManager.unwrap(Session.class);
		
		Query<User> query = session.createQuery("from User where id=:userId", User.class);
		query.setParameter("userId", id);
		
		User user = null;
		try {
			user = query.getSingleResult();
		} catch (Exception e) {
			user = null;
		}
		
		session.delete(user);
	}


}
