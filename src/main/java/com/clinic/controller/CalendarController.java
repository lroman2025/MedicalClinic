package com.clinic.controller;

import java.sql.SQLException;
import java.text.DateFormat;
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

import com.clinic.domain.Calendar;
import com.clinic.domain.Description;
import com.clinic.service.impl.CalendarService;
import com.clinic.service.impl.DescriptionService;
import com.clinic.service.impl.PatientService;
import com.clinic.service.impl.ScheduleService;

@RestController
@RequestMapping("/calendar")
public class CalendarController {

	@Autowired
	private CalendarService calendarService;

	@Autowired
	private PatientService patientService;

	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private DescriptionService descriptionService;

	@RequestMapping(value = "/getDoctorsCalendar", method = RequestMethod.GET)
	public List<Calendar> getDoctorsCalendar() throws SQLException {
		return calendarService.getDoctorsCalendar(SecurityContextHolder.getContext().getAuthentication().getName());
	}
	
	@RequestMapping(value = "/getCalendarByDoctor", method = RequestMethod.GET)
	public List<Calendar> getCalendarByDoctor(@RequestBody String pesel) throws SQLException{
		return calendarService.getDoctorsCalendar(pesel);
	}

	@RequestMapping(value = "/getPatientsCalendar", method = RequestMethod.GET)
	public List<Calendar> getPatientsCalendar() throws SQLException {
		return calendarService.getPatientsCalendar(SecurityContextHolder.getContext().getAuthentication().getName());
	}

	@RequestMapping(value = "/setCalendar", method = RequestMethod.POST)
	public void setCalendar(@RequestBody Map<String, String> map)
			throws SQLException, ParseException {
		Calendar calendar = new Calendar();
		Description description = new Description();

		Long scheduleId = Long.valueOf(map.get("schedule"));
		String day = map.get("date");

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = format.parse(day);
		calendar.setSchedule(scheduleService.getById(scheduleId));
		calendar.setDate(date);
		calendar.setPatient(patientService.getByPesel(SecurityContextHolder
				.getContext().getAuthentication().getName()));
		calendarService.setCalendar(calendar);
		description.setCalendar(calendar);
		descriptionService.save(description);
	}
	
	@RequestMapping(value="/setCalendarByAdmin", method = RequestMethod.POST)
	public void setCalendarByAdmin(@RequestBody Map<String, String> map) throws SQLException, ParseException{
		Calendar calendar = new Calendar();
		Description description = new Description();

		Long scheduleId = Long.valueOf(map.get("schedule"));
		String day = map.get("date");
		Long patientId = Long.valueOf(map.get("patient"));

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = format.parse(day);
		calendar.setSchedule(scheduleService.getById(scheduleId));
		calendar.setDate(date);
		calendar.setPatient(patientService.getById(patientId));
		calendarService.setCalendar(calendar);
		description.setCalendar(calendar);
		descriptionService.save(description);
	}
	
	@RequestMapping(value="/cancel", method=RequestMethod.POST)
	public void delete(@RequestBody Long id) throws SQLException{
		calendarService.delete(id);
	}
}
