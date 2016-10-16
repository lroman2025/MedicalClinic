package com.clinic.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="visits_description")
public class Description {

	@Id
	@GeneratedValue
	@Column(name="vds_id", nullable=false)
	private Long id;
	
	@Column(name="vds_doc_comment", nullable=true)
	private String doctorComment;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cln_id", nullable=false)
	@Fetch(FetchMode.JOIN)
	private Calendar calendar;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDoctorComment() {
		return doctorComment;
	}

	public void setDoctorComment(String doctorComment) {
		this.doctorComment = doctorComment;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

}
