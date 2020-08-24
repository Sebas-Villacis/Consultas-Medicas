package com.sebas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sebas.model.MedicalConsultation;
import com.sebas.util.ConsultDetail;

@Repository
public interface IMedicalConsultation extends JpaRepository<MedicalConsultation, Integer>{

	@Query("select new com.sebas.util.ConsultDetail(de.id, con.id) from consultation con inner join detailConsultation de on con.id = de.id_consultation.id where con.patient.id = :idPatient")
	List<ConsultDetail> getAllByIdPatient(@Param("idPatient") Integer id);
	
	@Query("select new com.sebas.util.ConsultDetail(de.id, con.id) from consultation con inner join detailConsultation de on con.id = de.id_consultation.id where con.doctor.id = :idDoctor")
	List<ConsultDetail> getAllByIdDoctor(@Param("idDoctor") Integer id);
	
	@Query("select count(con.id) from consultation con")
	Integer countConsults();
}
