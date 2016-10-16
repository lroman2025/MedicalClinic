package com.clinic.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clinic.dao.PatientDAO;
import com.clinic.dao.RoleDAO;
import com.clinic.dao.UserDataDAO;
import com.clinic.domain.Patient;
import com.clinic.domain.UserData;
import com.clinic.domain.Patient.Status;
import com.clinic.domain.Role.Roles;

@Transactional
@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientDAO patientDAO;

	@Autowired
	private UserDataDAO userDataDAO;

	@Autowired
	private RoleDAO roleDAO;

	@Override
	public List<Patient> getAll() throws SQLException {
		return patientDAO.getAll();
	}

	@Override
	public Patient getById(Long id) throws SQLException {
		return patientDAO.getById(id);
	}

	@Override
	public Patient getByPesel(String pesel) throws SQLException {
		return patientDAO.getByPesel(pesel);
	}

	@Override
	public void add(UserData userData) throws SQLException {
		Patient patient = new Patient();
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();

		userData.setPassword(encoder.encodePassword(userData.getPassword(),
				null));

		userDataDAO.save(userData);

		patient.setUserData(userData);
		patient.setRole(roleDAO.getRole(Roles.ROLE_PATIENT));
		patient.setStatus(Status.ACTIVE);
		patientDAO.save(patient);
	}

}
