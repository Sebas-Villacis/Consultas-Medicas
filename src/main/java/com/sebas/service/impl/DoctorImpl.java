package com.sebas.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebas.dao.IDoctor;

import com.sebas.model.Doctor;
import com.sebas.service.IDoctorService;

import javassist.NotFoundException;
@Service
public class DoctorImpl implements IDoctorService{

	@Autowired
	private IDoctor dao;
	
	
	@Override
	public List<Doctor> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Doctor create(Doctor obj) {
		// TODO Auto-generated method stub
		return dao.save(obj);
	}

	@Override
	public Doctor find(Integer id) throws NotFoundException {
		Optional<Doctor> doctor = dao.findById(id);
	    return doctor.orElseThrow(
	        () ->  new NotFoundException("Unable to get Doctor with Code = " + id)
	    );  
	}

	@Override
	public Doctor update(Doctor obj) {
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
	public List<Doctor> getDoctorsBySpecialty(Integer id) {
		// TODO Auto-generated method stub
		return dao.getDoctorsBySpecialty(id);
	}

	@Override
	public Integer countDoctors() {
		// TODO Auto-generated method stub
		return dao.countDoctors();
	}

	

}
