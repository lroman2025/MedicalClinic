package com.clinic.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.dao.CalendarDAO;
import com.clinic.domain.Calendar;

@Service
@Transactional
public class CalendarServiceImpl implements CalendarService{
	
	@Autowired
	private CalendarDAO calendarDAO;

	@Override
	public List<Calendar> getDoctorsCalendar(String pesel) throws SQLException {
		return calendarDAO.getDoctorsCalendar(pesel);
	}

	@Override
	public List<Calendar> getPatientsCalendar(String pesel) throws SQLException {
		return calendarDAO.getPatientsCalendar(pesel);
	}

	@Override
	public void setCalendar(Calendar calendar) throws SQLException {
		calendarDAO.setCalendar(calendar);
	}

	@Override
	public void delete(Long id) throws SQLException {
		calendarDAO.delete(id);
	}

}
