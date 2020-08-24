package com.sebas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name="doctor")
@Table(name="doctor")
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="id_specialty")
	private Specialty specialty;
	
	@Column(name = "first_name", length = 50, nullable = false)
	private String firstname;
	
	@Column(name="last_name", length = 50,nullable =false)
	private String lastName;
	
	@Column(name="dni", length =10 ,nullable = false)
	private String dni;
	


	@Column(name="cmp",nullable=false)
	private String cmp;
	


	@Transient
	private String fullName;
	
	

	public Doctor(Integer id, String firstname, String lastName, String dni, String cmp) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastName = lastName;
		this.dni = dni;
		this.cmp = cmp;
	}

	
	public Doctor() {
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
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

	public String getCmp() {
		return cmp;
	}

	public void setCmp(String cmp) {
		this.cmp = cmp;
	}

	public String getFullName() {
		StringBuilder sb = new StringBuilder();
		return sb.append(this.firstname).append(" "+this.lastName).toString();
	}

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	
}
