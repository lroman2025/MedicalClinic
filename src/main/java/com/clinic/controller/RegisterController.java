package com.clinic.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.domain.UserData;
import com.clinic.exception.EmailAlreadyInUseException;
import com.clinic.exception.PeselAlreadyInUseException;
import com.clinic.service.impl.RegisterService;

@RestController
public class RegisterController {

	@Autowired
	RegisterService registerService;

	@RequestMapping(value = "/registerPatient", method = RequestMethod.POST)
	public void RegisterPatient(@RequestBody UserData userData)
			throws SQLException, EmailAlreadyInUseException,
			PeselAlreadyInUseException {
		registerService.registerPatient(userData);
	}

	@RequestMapping(value = "/registerDoctor", method = RequestMethod.POST)
	public void RegisterDoctor(@RequestBody UserData userData)
			throws SQLException, EmailAlreadyInUseException,
			PeselAlreadyInUseException {
		registerService.registerDoctor(userData);
	}

}
