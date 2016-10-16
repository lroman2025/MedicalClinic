package com.clinic.dao;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.clinic.domain.UserData;

@Transactional
@Repository
public class UserDataDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<UserData> getAll() throws SQLException{
		return sessionFactory.getCurrentSession().createCriteria(UserData.class).list();
	}
	
	public void addUserData(UserData userData) throws SQLException{
		sessionFactory.getCurrentSession().save(userData);
	}
	
	@SuppressWarnings("unchecked")
	public UserData getByPesel(String pesel) throws SQLException{
		List<UserData> crit = sessionFactory.getCurrentSession().createCriteria(UserData.class)
				.add(Restrictions.eq("pesel", pesel)).list();
		return crit.get(0);
	}
	
	public void update(UserData userData) throws SQLException{
		sessionFactory.getCurrentSession().update(userData);
	}
	
	@SuppressWarnings("unchecked")
	public boolean peselExist(String pesel) throws SQLException{
		List<UserData> crit = sessionFactory.getCurrentSession().createCriteria(UserData.class)
			.add(Restrictions.eq("pesel", pesel)).list();
		return crit.isEmpty() ? false : true;
	}
	
	@SuppressWarnings("unchecked")
	public boolean emailExist(String email) throws SQLException{
		List<UserData> crit = sessionFactory.getCurrentSession().createCriteria(UserData.class)
				.add(Restrictions.eq("email", email)).list();
		return crit.isEmpty() ? false : true;
	}
	
	public void save(UserData userData) throws SQLException{
		sessionFactory.getCurrentSession().save(userData);
	}
}
