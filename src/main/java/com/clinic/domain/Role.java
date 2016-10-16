package com.clinic.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class Role {
	
	public enum Roles{
		ROLE_ADMIN("ROLE_ADMIN"), ROLE_PATIENT("ROLE_PATIENT"), ROLE_DOCTOR("ROLE_DOCTOR");
		
		private String s;
		
		private Roles(String s){
			this.setStatus(s);
		}

		public String getStatus() {
			return s;
		}

		public void setStatus(String s) {
			this.s = s;
		}
		
	}
	
	@Id
	@GeneratedValue
	@Column(name="rol_id", nullable=false)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="rol_name", nullable=false)
	private Roles name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Roles getName() {
		return name;
	}

	public void setName(Roles name) {
		this.name = name;
	}
	
}
