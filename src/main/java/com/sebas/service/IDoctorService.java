package com.sebas.service;

import java.util.List;

import com.sebas.model.Doctor;


public interface IDoctorService extends ICRUD<Doctor>{
	List<Doctor> getDoctorsBySpecialty( Integer id);

	Integer countDoctors();
}
