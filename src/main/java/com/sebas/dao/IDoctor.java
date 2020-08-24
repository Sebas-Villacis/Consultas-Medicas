package com.sebas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sebas.model.Doctor;

@Repository
public interface IDoctor extends JpaRepository<Doctor, Integer>{

	    
	@Query("select new com.sebas.model.Doctor(d.id, d.firstname, d.lastName, d.dni, d.cmp ) from doctor d where d.specialty.id = :idSpecialty")
	List<Doctor> getDoctorsBySpecialty(@Param("idSpecialty") Integer id);
	
	@Query("select count(d.id) from doctor d")
	Integer countDoctors();
}
