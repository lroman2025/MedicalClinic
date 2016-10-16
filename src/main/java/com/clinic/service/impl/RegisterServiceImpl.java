package com.clinic.service.impl;

import java.sql.SQLException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clinic.dao.DoctorDAO;
import com.clinic.dao.PatientDAO;
import com.clinic.dao.RoleDAO;
import com.clinic.dao.UserDataDAO;
import com.clinic.domain.Doctor;
import com.clinic.domain.Patient;
import com.clinic.domain.UserData;
import com.clinic.domain.Role.Roles;
import com.clinic.exception.EmailAlreadyInUseException;
import com.clinic.exception.PeselAlreadyInUseException;

@Transactional
@Service
public class RegisterServiceImpl implements RegisterService{

	@Autowired
	private UserDataDAO userDataDAO;
	
	@Autowired
	private PatientDAO patientDAO;
	
	@Autowired
	private DoctorDAO doctorDAO;
	
	@Autowired
	private RoleDAO roleDAO;
	
	@Override
	public void registerPatient(UserData userData) throws SQLException, EmailAlreadyInUseException, PeselAlreadyInUseException {
		
		if(userDataDAO.emailExist(userData.getEmail()))
			throw new EmailAlreadyInUseException("User with email \"" + userData.getEmail() + "\" already exist." );
		if(userDataDAO.peselExist(userData.getPesel()))
			throw new PeselAlreadyInUseException("User with pesel \"" + userData.getPesel() + "\" already exist.");
		
		Patient patient = new Patient();
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		
		userData.setPassword(encoder.encodePassword(userData.getPassword(), null));
		userDataDAO.save(userData);
		
		patient.setRole(roleDAO.getRole(Roles.ROLE_PATIENT));
		patient.setStatus(Patient.Status.ACTIVE);
		patient.setUserData(userData);
		patientDAO.save(patient);
	}
	
	@Override
	public void registerDoctor(UserData userData) throws SQLException, EmailAlreadyInUseException, PeselAlreadyInUseException {
		
		if(userDataDAO.emailExist(userData.getEmail()))
			throw new EmailAlreadyInUseException("User with email \"" + userData.getEmail() + "\" already exist." );
		if(userDataDAO.peselExist(userData.getPesel()))
			throw new PeselAlreadyInUseException("User with pesel \"" + userData.getPesel() + "\" already exist.");
		
		Doctor doctor = new Doctor();
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		
		userData.setPassword(encoder.encodePassword(userData.getPassword(), null));
		userDataDAO.save(userData);
		
		doctor.setRole(roleDAO.getRole(Roles.ROLE_PATIENT));
		doctor.setStatus(Doctor.Status.ACTIVE);
		doctor.setUserData(userData);
		doctorDAO.addDoctor(doctor);
	}

}
