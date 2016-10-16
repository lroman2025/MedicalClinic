package com.clinic.dao;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.clinic.domain.Description;

@Repository
@Transactional
public class DescriptionDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public Description getByCalendar(Long id) throws SQLException{
		List<Description> crit = sessionFactory.getCurrentSession().createCriteria(Description.class)
				.add(Restrictions.eq("calendar.id", id)).list();
		return crit.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<Description> getAll() throws SQLException{
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Description.class);
		return crit.list();
	}
	
	public void update(Description description) throws SQLException{
		sessionFactory.getCurrentSession().update(description);
	}
	
	@SuppressWarnings("unchecked")
	public Description getById(Long id) throws SQLException{
		List<Description> crit = sessionFactory.getCurrentSession().createCriteria(Description.class)
				.add(Restrictions.eq("id", id)).list();
		return crit.get(0);
	}
	
	public void save(Description description) throws SQLException{
		sessionFactory.getCurrentSession().save(description);
	}
	
	public void delete(Description description) throws SQLException{
		sessionFactory.getCurrentSession().delete(description);
	}
}
