package com.sebas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sebas.model.DetailConsultation;
import com.sebas.util.PatientHistory;

@Repository
public interface IDetaiConsultation extends JpaRepository<DetailConsultation, Integer>{


	@Query("select new com.sebas.util.PatientHistory(con.id,doc.firstname,doc.lastName,de.diagnostic,de.treatment,con.createDate) from consultation con inner join doctor doc on con.doctor.id = doc.id inner join detailConsultation de  on con.id = de.id_consultation.id where con.patient.id = :idPatient")

	List<PatientHistory> getPatientHistory(@Param("idPatient") Integer id);
}

