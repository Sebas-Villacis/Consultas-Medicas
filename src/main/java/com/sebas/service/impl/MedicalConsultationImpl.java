package com.sebas.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebas.dao.IMedicalConsultation;

import com.sebas.model.MedicalConsultation;

import com.sebas.service.IMedicalConsultationService;
import com.sebas.util.ConsultDetail;

import javassist.NotFoundException;
@Service
public class MedicalConsultationImpl implements IMedicalConsultationService {

	@Autowired
	private IMedicalConsultation dao;
	@Override
	public List<MedicalConsultation> findAll() {
		return dao.findAll();
	}

	@Override
	public MedicalConsultation create(MedicalConsultation obj) {
		return dao.save(obj);
	}

	@Override
	public MedicalConsultation find(Integer id) throws NotFoundException {
		Optional<MedicalConsultation> medical = dao.findById(id);
	    return medical.orElseThrow(
	        () ->  new NotFoundException("Unable to get MedicalConsultation with Code = " + id)
	    );  
	}

	@Override
	public MedicalConsultation update(MedicalConsultation obj) {
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
	public List<ConsultDetail> getAllByIdPatient(Integer id) {
		// TODO Auto-generated method stub
		return dao.getAllByIdPatient(id);
	}

	@Override
	public List<ConsultDetail> getAllByIdDoctor(Integer id) {
		// TODO Auto-generated method stub
		return dao.getAllByIdDoctor(id);
	}

	@Override
	public Integer countConsults() {
		// TODO Auto-generated method stub
		return dao.countConsults();
	}


	
	



}
