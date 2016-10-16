package com.clinic.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.dao.ScheduleDAO;
import com.clinic.domain.Schedule;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {
	
	@Autowired
	private ScheduleDAO scheduleDAO;

	@Override
	public void saveSchedule(Schedule schedule) {
		scheduleDAO.setSchedule(schedule);
	}

	@Override
	public List<Schedule> getDoctorsSchedule(String pesel) {
		return scheduleDAO.getDoctorsSchedule(pesel);
	}

	@Override
	public void deleteSchedule(Long id) {
		scheduleDAO.deleteSchedule(scheduleDAO.getScheduleById(id));
		
	}

	@Override
	public List<Schedule> getLoggedDoctorSchedule() {
		return scheduleDAO.getLoggedDoctorSchedule();
	}

	@Override
	public Schedule getById(Long id) {
		return scheduleDAO.getScheduleById(id);
	}

}
