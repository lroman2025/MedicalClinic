package com.clinic.service.impl;

import java.sql.SQLException;

import com.clinic.domain.UserData;
import com.clinic.exception.EmailAlreadyInUseException;
import com.clinic.exception.PeselAlreadyInUseException;

public interface RegisterService {

	public void registerPatient(UserData userData) throws SQLException,
			EmailAlreadyInUseException, PeselAlreadyInUseException;

	public void registerDoctor(UserData userData) throws SQLException,
			EmailAlreadyInUseException, PeselAlreadyInUseException;
}
