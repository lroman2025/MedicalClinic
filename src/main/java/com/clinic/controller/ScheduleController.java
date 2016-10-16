package com.clinic.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.domain.Doctor;
import com.clinic.domain.Schedule;
import com.clinic.service.impl.DoctorService;
import com.clinic.service.impl.ScheduleService;

@RestController
@RequestMapping("/schedule")
public class ScheduleController{

	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private DoctorService doctorService;
	
	@RequestMapping(value="/getLoggedDoctorsSchedule", method=RequestMethod.GET)
	public List<Schedule> getLoggedDoctorSchedule(){
		return scheduleService.getLoggedDoctorSchedule();
	}
	
	@RequestMapping(value="/getDoctorsSchedule", method=RequestMethod.POST)
	public List<Schedule> getDoctorsSchedule(@RequestBody Doctor doctor){
		return scheduleService.getDoctorsSchedule(doctor.getUserData().getPesel());
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public void addSchedule(@RequestBody Map<String, String> map) throws SQLException, ParseException{
		
		Schedule schedule = new Schedule();

		Date day = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("day"));
		Date hourStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(map.get("hourStart"));
		Date hourEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(map.get("hourStop"));
		
		schedule.setDay(day);
		schedule.setHourStart(hourStart);
		schedule.setHourEnd(hourEnd);
		schedule.setDoctor(doctorService.getByPesel(SecurityContextHolder.getContext().getAuthentication().getName()));
		scheduleService.saveSchedule(schedule);
	}
	
	@RequestMapping(value="/addByDoctorId", method=RequestMethod.POST)
	public void addByDoctorId(@RequestBody Map<String, String> map) throws SQLException, ParseException{
		Schedule schedule = new Schedule();

		Date day = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("day"));
		Date hourStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(map.get("hourStart"));
		Date hourEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(map.get("hourStop"));
		Long id = Long.valueOf(map.get("doctorId"));
		
		schedule.setDay(day);
		schedule.setHourStart(hourStart);
		schedule.setHourEnd(hourEnd);
		schedule.setDoctor(doctorService.getDoctorById(id));
		scheduleService.saveSchedule(schedule);
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public void deleteSchedule(@RequestBody Long id) throws SQLException{
		scheduleService.deleteSchedule(id);
	}
}
