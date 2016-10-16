package com.clinic.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.clinic.domain.Patient;

@Repository
@Transactional
public class PatientDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Patient> getAll() throws SQLException{
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Patient.class);
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public Patient getById(Long id) throws SQLException{
		List<Patient> crit = sessionFactory.getCurrentSession().createCriteria(Patient.class).add(Restrictions.eq("id", id)).list();
		return crit.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public Patient getByPesel(String pesel){
		List<Patient> crit = sessionFactory.getCurrentSession().createCriteria(Patient.class, "patient")
				.createAlias("patient.userData", "userData", JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.eq("userData.pesel", pesel)).list();
		return crit.get(0);
	}
	
	public void save(Patient patient){
		sessionFactory.getCurrentSession().save(patient);
	}
}
