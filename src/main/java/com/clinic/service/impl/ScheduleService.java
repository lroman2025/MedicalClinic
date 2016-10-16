package com.clinic.service.impl;

import java.util.List;

import com.clinic.domain.Schedule;

public interface ScheduleService {
	
	public void saveSchedule(Schedule schedule);
	
	public void deleteSchedule(Long id);

	public List<Schedule> getLoggedDoctorSchedule();

	public List<Schedule> getDoctorsSchedule(String pesel);
	
	public Schedule getById(Long id);
}
