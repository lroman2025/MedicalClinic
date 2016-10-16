package com.clinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doctor")
public class DoctorViewController {

	@RequestMapping("/")
	public String doctorIndex() {
		return "doctor/index";
	}

	/**
	 * @return View with doctor's schedule.
	 */
	@RequestMapping("/schedule")
	public String doctorSchedule() {
		return "doctor/partials/schedule";
	}

	/**
	 * @return View with doctor's calendar.
	 */
	@RequestMapping("/calendar")
	public String doctorCalendar() {
		return "doctor/partials/calendar";
	}

	/**
	 * 
	 * @return View with dashboard
	 */
	@RequestMapping("/dashboard")
	public String dashboard() {
		return "doctor/partials/dashboard";
	}

	@RequestMapping("/patient")
	public String patient() {
		return "patient";
	}
}
