package com.clinic.dao;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.clinic.domain.Role;
import com.clinic.domain.Role.Roles;

@Transactional
@Repository
public class RoleDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public Role getRole(Roles roles) throws SQLException{
		List<Role> crit = sessionFactory.getCurrentSession().createCriteria(Role.class)
				.add(Restrictions.eq("name", roles)).list();
		return crit.get(0);
		
	}
}
