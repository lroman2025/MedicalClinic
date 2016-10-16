package com.clinic.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.clinic.domain.Doctor;

@Transactional
@Repository
public class DoctorDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Doctor> getAll(){
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Doctor.class);
		return crit.list(); 
	}
	
	@SuppressWarnings("unchecked")
	public Doctor getDoctorById(Long id){
		List<Doctor> crit =  sessionFactory.getCurrentSession().createCriteria(Doctor.class).add(Restrictions.eq("id", id)).list();
		return crit.get(0);
	}
	
	public void addDoctor(Doctor doctor){
		sessionFactory.getCurrentSession().save(doctor);
	}
	
	@SuppressWarnings("unchecked")
	public Doctor getByPesel(String pesel){
		Doctor doctor = null;
		List<Doctor> check = sessionFactory.getCurrentSession().createCriteria(Doctor.class).list();
		for(int i=0;i<check.size();i++){
			if(check.get(i).getUserData().getPesel().equals(pesel)){
				doctor = check.get(i);
				break;
			}
			else doctor = null;
		}
		return doctor;
	}
}
