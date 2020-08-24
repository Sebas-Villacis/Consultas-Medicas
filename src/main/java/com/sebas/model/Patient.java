package com.sebas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name = "patient")
@Table(name = "patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "firstName", length = 50, nullable = false)
	private String firstName;
	
	@Column(name="lastName", length = 50,nullable =false)
	private String lastName;
	
	@Column(name="dni", length =10 ,nullable = false)
	private String dni;
	
	@Column(name="numberClinicalHistory",nullable=false)
	private String numberClinicalHistory;
	
	@Transient
	private String fullName;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNumberClinicalHistory() {
		return numberClinicalHistory;
	}

	public void setNumberClinicalHistory(String numberClinicalHistory) {
		this.numberClinicalHistory = numberClinicalHistory;
	}
	
	public String getFullName() {
		StringBuilder sb = new StringBuilder();
		return sb.append(this.firstName).append(" "+this.lastName).toString();
	}

	
	
}
