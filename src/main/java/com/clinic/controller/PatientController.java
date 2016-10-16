package com.clinic.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.domain.Patient;
import com.clinic.domain.UserData;
import com.clinic.service.impl.PatientService;

@RestController
@RequestMapping("/patientData")
public class PatientController {

	@Autowired
	private PatientService patientService;
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	public List<Patient> getAll() throws SQLException{
		return patientService.getAll();
	}
	
	@RequestMapping(value="/getById", method=RequestMethod.GET)
	public Patient getById(@RequestParam("patientId") Long patientId) throws SQLException{
		return patientService.getById(patientId);
	}
	
	@RequestMapping(value="/getUserData", method=RequestMethod.GET )
	public Patient getByPesel() throws SQLException{
		return patientService.getByPesel(SecurityContextHolder.getContext().getAuthentication().getName());
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public void add(@RequestBody UserData patient) throws SQLException{
		patientService.add(patient);
	}
}
