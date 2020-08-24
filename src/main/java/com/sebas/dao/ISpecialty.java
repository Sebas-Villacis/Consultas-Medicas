package com.sebas.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sebas.model.Specialty;

@Repository
public interface ISpecialty extends JpaRepository<Specialty, Integer>{
	@Query("select count(sp.id) from specialty sp")
	Integer countSpecialty();
}
