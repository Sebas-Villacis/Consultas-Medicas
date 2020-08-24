package com.sebas.service;

import com.sebas.model.Patient;

public interface IPatientService extends ICRUD<Patient>{
	Integer countPatients();
}
