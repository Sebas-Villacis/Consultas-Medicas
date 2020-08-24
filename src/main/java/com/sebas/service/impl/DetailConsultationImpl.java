package com.sebas.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebas.dao.IDetaiConsultation;
import com.sebas.model.DetailConsultation;
import com.sebas.service.IDetailConsultationService;
import com.sebas.util.PatientHistory;

import javassist.NotFoundException;

@Service
public class DetailConsultationImpl implements IDetailConsultationService{

	@Autowired
	private IDetaiConsultation dao;
	@Override
	public List<DetailConsultation> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public DetailConsultation create(DetailConsultation obj) {
		// TODO Auto-generated method stub
		return dao.save(obj);
	}

	@Override
	public DetailConsultation find(Integer id) throws NotFoundException {
		Optional<DetailConsultation> detail = dao.findById(id);
	    return detail.orElseThrow(
	        () ->  new NotFoundException("Unable to get DetailConsultation with Code = " + id)
	    );  
	}

	@Override
	public DetailConsultation update(DetailConsultation obj) {
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
	public List<PatientHistory> getPatientHistory(Integer id) {
		// TODO Auto-generated method stub
		return dao.getPatientHistory(id);
	}


	
}
