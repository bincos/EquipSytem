package com.ruk.sid.form;

import javax.persistence.Lob;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;


public class VbootForm {

	
	@NotEmpty
	private String titreHorraire;
	@NotEmpty
	private String AprouvedBuy;
	
	private String datefrom;
	private String dateTo;
	@NotBlank
	private String faculte;
	@NotEmpty
	private String sendList;
	
	public VbootForm() {
		super();
	}


	public VbootForm(String titreHorraire, String aprouvedBuy, String datefrom,
			String dateTo, String faculte, String sendList) {
		super();
		this.titreHorraire = titreHorraire;
		AprouvedBuy = aprouvedBuy;
		this.datefrom = datefrom;
		this.dateTo = dateTo;
		this.faculte = faculte;
		this.sendList = sendList;
	}
	public String getFaculte() {
		return faculte;
	}
	public void setFaculte(String faculte) {
		this.faculte = faculte;
	}
	public String getTitreHorraire() {
		return titreHorraire;
	}

	public void setTitreHorraire(String titreHorraire) {
		this.titreHorraire = titreHorraire;
	}

	public String getAprouvedBuy() {
		return AprouvedBuy;
	}

	public void setAprouvedBuy(String aprouvedBuy) {
		AprouvedBuy = aprouvedBuy;
	}

	public String getDatefrom() {
		return datefrom;
	}

	public void setDatefrom(String datefrom) {
		this.datefrom = datefrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}


	public String getSendList() {
		return sendList;
	}

	public void setSendList(String sendList) {
		this.sendList = sendList;
	}

	
	
}
