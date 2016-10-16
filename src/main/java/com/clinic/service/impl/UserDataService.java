package com.clinic.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.clinic.domain.UserData;

public interface UserDataService {
	
	public List<UserData> getAll() throws SQLException;
	
	public void addUserData(UserData userData) throws SQLException;
	
	public void update(UserData userData) throws SQLException;
}
