package com.clinic.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clinic.dao.DoctorDAO;
import com.clinic.dao.RoleDAO;
import com.clinic.dao.UserDataDAO;
import com.clinic.domain.Doctor;
import com.clinic.domain.UserData;
import com.clinic.domain.Doctor.Status;
import com.clinic.domain.Role.Roles;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService{

	@Autowired
	public DoctorDAO doctorDAO;
	
	@Autowired
	public UserDataDAO userDataDAO;
	
	@Autowired
	public RoleDAO roleDAO;
	
	@Override
	public List<Doctor> getAll() throws SQLException {
		return doctorDAO.getAll();
	}

	@Override
	public Doctor getDoctorById(Long id) throws SQLException {
		return doctorDAO.getDoctorById(id);
	}

	@Override
	public Doctor getByPesel(String pesel) throws SQLException {
		return doctorDAO.getByPesel(pesel);
	}
	
	public void add(UserData userData) throws SQLException{
		Doctor doctor = new Doctor();
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		
		userData.setPassword(encoder.encodePassword(userData.getPassword(), null));
		userDataDAO.save(userData);
		doctor.setUserData(userData);
		doctor.setRole(roleDAO.getRole(Roles.ROLE_DOCTOR));
		doctor.setStatus(Status.ACTIVE);
		doctorDAO.addDoctor(doctor);
	}

}
