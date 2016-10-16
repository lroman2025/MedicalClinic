package com.clinic.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_data")
public class UserData {

	@Id
	@GeneratedValue
	@Column(name="usr_id", nullable=false)
	private Long id;
	
	@Column(name="usr_firstname", nullable=false)
	private String firstname;
	
	@Column(name="usr_lastname", nullable=false)
	private String lastname;
	
	@Column(name="usr_email", nullable=false)
	private String email;
	
	@Column(name="usr_phone", nullable=false)
	private String phone;
	
	@Column(name="usr_pesel", nullable=false)
	private String pesel;
	
	@Column(name="usr_address", nullable=false)
	private String address;
	
	@Column(name="usr_password", nullable=false)
	private String password;
	
	@Column(name="usr_birthdate", nullable=false)
	private Date birthday;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
