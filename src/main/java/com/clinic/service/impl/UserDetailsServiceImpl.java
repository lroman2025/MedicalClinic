package com.clinic.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.clinic.dao.DoctorDAO;
import com.clinic.dao.PatientDAO;
import com.clinic.domain.Doctor;
import com.clinic.domain.Patient;
import com.clinic.domain.Role.Roles;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private DoctorDAO doctorDAO;

	@Autowired
	private PatientDAO patientDAO;

	@Override
	public UserDetails loadUserByUsername(String pesel)
			throws UsernameNotFoundException {

		Doctor doctor = null;
		UserDetails user = null;

		doctor = doctorDAO.getByPesel(pesel);

		if (doctor == null) {
			Patient patient = null;
			patient = patientDAO.getByPesel(pesel);

			if (patient == null) {
				throw new UsernameNotFoundException(pesel + " was not found");
			}

			org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
					patient.getUserData().getPesel(), patient.getUserData()
							.getPassword(), true, true, true, true,
					getAuthorities(patient.getRole().getName()));
			user = userDetails;
		}
		
		if(doctor != null){
			org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
					doctor.getUserData().getPesel(), doctor.getUserData()
							.getPassword(), true, true, true, true,
					getAuthorities(doctor.getRole().getName()));
			
			user = userDetails;
		}
		return user;
	}

	/**
	 * Gets list of granted authorities based on user role saved in database.
	 * 
	 * @param role
	 *            User role stored on database
	 * @return List of granted authorities
	 */
	private List<GrantedAuthority> getAuthorities(Roles role) {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		if (role.equals(Roles.ROLE_DOCTOR))
			authList.add(new SimpleGrantedAuthority(Roles.ROLE_DOCTOR
					.getStatus()));
		else if (role.equals(Roles.ROLE_PATIENT))
			authList.add(new SimpleGrantedAuthority(Roles.ROLE_PATIENT
					.getStatus()));
		else if (role.equals(Roles.ROLE_ADMIN))
			authList.add(new SimpleGrantedAuthority(Roles.ROLE_ADMIN
					.getStatus()));
		
		return authList;
	}
}
