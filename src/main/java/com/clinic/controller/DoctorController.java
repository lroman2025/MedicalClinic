package com.clinic.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.domain.Doctor;
import com.clinic.domain.UserData;
import com.clinic.service.impl.DoctorService;

@RestController
@RequestMapping("/doctorData")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	public List<Doctor> getAll() throws SQLException{
		return doctorService.getAll();
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public void add(@RequestBody UserData doctor) throws SQLException{
		doctorService.add(doctor);
	}
	
	@RequestMapping(value="/getUserData", method=RequestMethod.GET )
	public Doctor getByPesel() throws SQLException{
		return doctorService.getByPesel(SecurityContextHolder.getContext().getAuthentication().getName());
	}
	
}
