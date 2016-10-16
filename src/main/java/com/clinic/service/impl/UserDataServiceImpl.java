package com.clinic.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.dao.UserDataDAO;
import com.clinic.domain.UserData;

@Service
@Transactional
public class UserDataServiceImpl implements UserDataService{

	@Autowired
	public UserDataDAO userDataDAO;
	
	@Override
	public List<UserData> getAll() throws SQLException {
		return userDataDAO.getAll();
	}
	
	@Override
	public void addUserData(UserData userData) throws SQLException {
		userDataDAO.addUserData(userData);
	}

	@Override
	public void update(UserData userData) throws SQLException {
		userDataDAO.update(userData);
	}
}
