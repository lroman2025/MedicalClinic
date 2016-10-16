package com.clinic.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="schedule")
public class Schedule {

	@Id
	@GeneratedValue
	@Column(name="sch_id", nullable=false)
	private Long id;
	
	@Column(name="sch_day", nullable=false)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date day;
	
	@Column(name="sch_hour_start", nullable=false)
	@Temporal(javax.persistence.TemporalType.TIME)
	private Date hourStart;

	@Column(name="sch_hour_end", nullable=false)
	@Temporal(javax.persistence.TemporalType.TIME)
	private Date hourEnd;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="doc_id", nullable=false)
	@Fetch(FetchMode.JOIN)
	private Doctor doctor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public Date getHourStart() {
		return hourStart;
	}

	public void setHourStart(Date hourStart) {
		this.hourStart = hourStart;
	}

	public Date getHourEnd() {
		return hourEnd;
	}

	public void setHourEnd(Date hourEnd) {
		this.hourEnd = hourEnd;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
}
