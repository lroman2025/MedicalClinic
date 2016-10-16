package com.clinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/patient")
@Controller
public class PatientViewController {

	@RequestMapping("/")
	public String getIndex(){
		return "patient/index";
	}
	
	@RequestMapping("/schedule")
	public String getSchedule(){
		return "patient/partials/schedule";
	}
	
	@RequestMapping("/data")
	public String getData(){
		return "patient/partials/data";
	}
	
	@RequestMapping("/visits")
	public String getVisits(){
		return "patient/partials/visits";
	}
}
