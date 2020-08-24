package com.sebas.util;

import java.util.Date;

public class PatientHistory {

	private int id;
	private String firstname;
	private String lastName;
	private Date createDate;
	private String diagnostic;
	private String treatment;

	
	public PatientHistory(int id, String firstname, String lastName, String diagnostic, String treatment,
			Date createDate) {
		this.id = id;
		this.firstname = firstname;
		this.lastName = lastName;
		this.diagnostic = diagnostic;
		this.treatment = treatment;
		this.createDate = createDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
