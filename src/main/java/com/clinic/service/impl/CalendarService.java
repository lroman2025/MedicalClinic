package com.clinic.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.clinic.domain.Calendar;

public interface CalendarService {

	public List<Calendar> getDoctorsCalendar(String pesel) throws SQLException;
	
	public List<Calendar> getPatientsCalendar(String pesel) throws SQLException;
	
	public void setCalendar(Calendar calendar) throws SQLException;
	
	public void delete(Long id) throws SQLException;
}
