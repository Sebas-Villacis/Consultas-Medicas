package com.sebas.service;

import java.util.List;

import com.sebas.model.MedicalConsultation;
import com.sebas.util.ConsultDetail;

public interface IMedicalConsultationService extends ICRUD<MedicalConsultation>{
	List<ConsultDetail> getAllByIdPatient(Integer id);
	List<ConsultDetail> getAllByIdDoctor(Integer id);
	Integer countConsults();
}
