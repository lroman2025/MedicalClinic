package com.clinic.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.domain.Description;
import com.clinic.service.impl.DescriptionService;

@RestController
@RequestMapping("/description")
public class DescriptionController {

	@Autowired
	private DescriptionService descriptionService;
	
	@RequestMapping(value="/getByCalendar", method=RequestMethod.GET)
	public Description getByCalendar(@RequestParam("calendarId") Long id) throws SQLException{
		return descriptionService.getByCalendar(id);
	}
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	public List<Description> getAll() throws SQLException{
		return descriptionService.getAll();
	}
	
	public Description getById(Long id) throws SQLException{
		return descriptionService.getById(id);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public void saveDoctorComment(@RequestBody Map<String, String> map) throws SQLException{
		
		Long id = Long.parseLong(map.get("id"));
		String comment = map.get("comment");
		
		Description description = getById(id);
		description.setDoctorComment(comment);
		descriptionService.update(description);
	} 
}
