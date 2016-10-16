package com.clinic.dao;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.clinic.domain.Calendar;

@Transactional
@Repository
public class CalendarDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DescriptionDAO descriptionDAO;
	
	@SuppressWarnings("unchecked")
	public List<Calendar> getDoctorsCalendar(String pesel) throws SQLException{
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Calendar.class)
				.createAlias("schedule.doctor.userData", "userData")
				.add(Restrictions.eq("userData.pesel", pesel));
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Calendar> getPatientsCalendar(String pesel) throws SQLException{
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Calendar.class)
				.createAlias("patient.userData", "userData")
				.add(Restrictions.eq("userData.pesel", pesel));
		return crit.list();
	}
	
	public void setCalendar(Calendar calendar) throws SQLException{
		sessionFactory.getCurrentSession().save(calendar);
	}
	
	@SuppressWarnings("unchecked")
	public Calendar getById(Long id) throws SQLException{
		List<Calendar> crit = sessionFactory.getCurrentSession().createCriteria(Calendar.class).add(Restrictions.eq("id", id)).list();
		return crit.get(0);
	}
	
	public void delete(Long id) throws SQLException{
		descriptionDAO.delete(descriptionDAO.getByCalendar(id));
		sessionFactory.getCurrentSession().delete(getById(id));;
	}
	
/*	public Calendar getBySchedule(Schedule schedule) throws SQLException{
		//List<Calendar> crit = sessionFactory.get
	}*/
}
