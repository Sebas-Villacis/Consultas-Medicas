package com.sebas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="detailConsultation")
@Table(name="detailConsultation")
public class DetailConsultation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name = "id_consultation")
	private MedicalConsultation id_consultation;
	
	@Column(name = "diagnostic",nullable = false)
	private String diagnostic;
	
	@Column(name = "treatment",nullable = false)
	private String treatment;

	public DetailConsultation(int id, MedicalConsultation id_consultation, String diagnostic, String treatment) {
		super();
		this.id = id;
		this.id_consultation = id_consultation;
		this.diagnostic = diagnostic;
		this.treatment = treatment;
	}


	public DetailConsultation() {
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public MedicalConsultation getId_consultation() {
		return id_consultation;
	}


	public void setId_consultation(MedicalConsultation id_consultation) {
		this.id_consultation = id_consultation;
	}


	public String getDiagnostic() {
		return diagnostic;
	}

	public void setDiagnostic(String diagnostic) {
		this.diagnostic = diagnostic;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}
	
	
}
