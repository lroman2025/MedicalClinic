package com.clinic.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.clinic.domain.Description;

public interface DescriptionService {

	public Description getByCalendar(Long id) throws SQLException;
	
	public List<Description> getAll() throws SQLException;

	public void update(Description description) throws SQLException;
	
	public Description getById(Long id) throws SQLException;
	
	public void save(Description description) throws SQLException;
}
