package com.clinic.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.clinic.domain.Doctor;
import com.clinic.domain.UserData;

public interface DoctorService {
	
	public List<Doctor> getAll() throws SQLException;
	
	public Doctor getDoctorById(Long id) throws SQLException;
	
	public Doctor getByPesel(String pesel) throws SQLException;
	
	public void add(UserData userData) throws SQLException;
	
}
