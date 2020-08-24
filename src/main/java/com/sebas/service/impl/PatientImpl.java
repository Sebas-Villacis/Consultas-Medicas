package com.sebas.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebas.dao.IPatient;
import com.sebas.model.Patient;
import com.sebas.service.IPatientService;

import javassist.NotFoundException;
@Service
public class PatientImpl implements IPatientService {

	@Autowired
	private IPatient dao;
	@Override
	public List<Patient> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Patient create(Patient obj) {
		// TODO Auto-generated method stub
		return dao.save(obj);
	}

	@Override
	public Patient find(Integer id) throws NotFoundException {
		Optional<Patient> patient = dao.findById(id);
	    return patient.orElseThrow(
	        () ->  new NotFoundException("Unable to get patient with Code = " + id)
	    );  
	}

	@Override
	public Patient update(Patient obj) {
		// TODO Auto-generated method stub
		return dao.save(obj);
	}

	@Override
	public int delete(Integer id) {
		int rpta =0;
		try {
			dao.deleteById(id);
			rpta=1;
		} catch (Exception e) {
			rpta =0;
		}
				
		return rpta;
		
	}

	@Override
	public Integer countPatients() {
		// TODO Auto-generated method stub
		return dao.countPatients();
	}

	

}
