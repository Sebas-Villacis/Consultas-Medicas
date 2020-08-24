package com.sebas.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebas.dao.ISpecialty;
import com.sebas.model.Specialty;
import com.sebas.service.ISpecialtyService;

import javassist.NotFoundException;
@Service
public class SpecialtyImpl implements ISpecialtyService {

	@Autowired
	private ISpecialty dao;
	@Override
	public List<Specialty> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Specialty create(Specialty obj) {
		return dao.save(obj);
	}

	@Override
	public Specialty find(Integer id) throws NotFoundException {
		Optional<Specialty> specialty = dao.findById(id);
	    return specialty.orElseThrow(
	        () ->  new NotFoundException("Unable to get Specialty with Code = " + id)
	    );  
	}

	@Override
	public Specialty update(Specialty obj) {
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
	public Integer countSpecialty() {
		// TODO Auto-generated method stub
		return dao.countSpecialty();
	}

	

}
