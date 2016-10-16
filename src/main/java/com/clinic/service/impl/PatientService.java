package com.clinic.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.clinic.domain.Patient;
import com.clinic.domain.UserData;

public interface PatientService {
	
	public List<Patient> getAll() throws SQLException;
	
	public Patient getById(Long id) throws SQLException; 

	public Patient	getByPesel(String pesel) throws SQLException;
	
	public void add(UserData userData) throws SQLException;
}
