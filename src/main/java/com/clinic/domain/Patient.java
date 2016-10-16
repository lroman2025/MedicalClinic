package com.clinic.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="patient")
public class Patient {	

	public enum Status{
		ACTIVE("ACTIVE"), BLOCKED("BLOCKED");
		
		private String s;
		
		private Status(String s){
			this.setString(s);
		}
		
		public String getStatus(){
			return s;
		}
		
		public void setString(String s){
			this.s = s;
		}
	}
	
	@Id
	@GeneratedValue
	@Column(name = "pat_id", nullable=false)
	private Long id;
		
	@Enumerated(EnumType.STRING)
	@Column(name = "pat_status", nullable=false, length=20)
	private Status status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="usr_id", nullable=false)
	@Fetch(FetchMode.JOIN)
	private UserData userData;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="rol_id", nullable=false)
	@Fetch(FetchMode.JOIN)
	private Role role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
