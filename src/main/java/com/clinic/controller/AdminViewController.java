package com.clinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminViewController {

	@RequestMapping("/")
	public String adminIndex(){
		return "admin/index";
	}
	
	@RequestMapping("/patients")
	public String adminPatients(){
		return "admin/partials/patients";
	}
	
	@RequestMapping("/doctors")
	public String adminDoctors(){
		return "admin/partials/doctors";
	}
	
	@RequestMapping("/schedule")
	public String adminSchedule(){
		return "admin/partials/schedule";
	}
}
