package com.clinic.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.clinic.domain.Schedule;

@Transactional
@Repository
public class ScheduleDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 
	 * @param Doctors pesel
	 * @return List of schedules for doctor, where the day of schedule is greater then today
	 */
	@SuppressWarnings("unchecked")
	public List<Schedule> getDoctorsSchedule(String pesel){
		Date today = new Date();
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Schedule.class)
				.createAlias("doctor.userData", "userData")
				.add(Restrictions.eq("userData.pesel", pesel)).add(Restrictions.ge("day", today));
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Schedule> getLoggedDoctorSchedule(){
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Schedule.class)
				.createAlias("doctor.userData", "userData")
				.add(Restrictions.eq("userData.pesel", SecurityContextHolder.getContext().getAuthentication().getName()));
		return crit.list();
	}
	
	public void setSchedule(Schedule schedule){
		sessionFactory.getCurrentSession().save(schedule);
	}
	
	public void deleteSchedule(Schedule schedule){
		sessionFactory.getCurrentSession().delete(schedule);
	}
	
	@SuppressWarnings("unchecked")
	public Schedule getScheduleById(Long id){
		List<Schedule> schedule = sessionFactory.getCurrentSession().createCriteria(Schedule.class).add(Restrictions.eq("id", id)).list();
		return schedule.get(0);
	} 

}
