package com.clinic.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.dao.DescriptionDAO;
import com.clinic.domain.Description;

@Service
@Transactional
public class DescriptionServiceImpl implements DescriptionService{

	@Autowired
	private DescriptionDAO descriptionDAO;
	
	@Override
	public Description getByCalendar(Long id) throws SQLException {
		return descriptionDAO.getByCalendar(id);
	}

	@Override
	public List<Description> getAll() throws SQLException {
		return descriptionDAO.getAll();
	}

	@Override
	public void update(Description description) throws SQLException {
		descriptionDAO.update(description);
	}

	@Override
	public Description getById(Long id) throws SQLException {
		return descriptionDAO.getByCalendar(id);
	}

	@Override
	public void save(Description description) throws SQLException {
		descriptionDAO.save(description);
	}

}
