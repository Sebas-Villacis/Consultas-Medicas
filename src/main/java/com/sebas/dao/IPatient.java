package com.sebas.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sebas.model.Patient;

@Repository
public interface IPatient extends JpaRepository<Patient, Integer>{

	@Query("select count(p.id) from patient p")
	Integer countPatients();
}
