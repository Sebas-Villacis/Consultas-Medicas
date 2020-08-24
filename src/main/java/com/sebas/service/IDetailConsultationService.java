package com.sebas.service;

import java.util.List;

import com.sebas.model.DetailConsultation;
import com.sebas.util.PatientHistory;

public interface IDetailConsultationService extends ICRUD<DetailConsultation>{
	List<PatientHistory> getPatientHistory(Integer id);
	
	
}
