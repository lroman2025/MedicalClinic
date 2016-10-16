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
@Table(name="calendar")
public class Calendar {

	@Id
	@GeneratedValue
	@Column(name="cln_id", nullable=false)
	private Long id;
	
	@Column(name="cln_date", nullable=false)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date date;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sch_id", nullable=false)
	@Fetch(FetchMode.JOIN)
	private Schedule schedule;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="pat_id", nullable=false)
	@Fetch(FetchMode.JOIN)
	private Patient patient;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
}
