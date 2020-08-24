package com.sebas.util;

public class ConsultDetail {

	private Integer idDetail;
	private Integer idConsult;
	
	public ConsultDetail(Integer idDetail, Integer idConsult) {
		super();
		this.idDetail = idDetail;
		this.idConsult = idConsult;
	}
	public Integer getIdDetail() {
		return idDetail;
	}
	public void setIdDetail(Integer idDetail) {
		this.idDetail = idDetail;
	}
	public Integer getIdConsult() {
		return idConsult;
	}
	public void setIdConsult(Integer idConsult) {
		this.idConsult = idConsult;
	}
	
	
}
