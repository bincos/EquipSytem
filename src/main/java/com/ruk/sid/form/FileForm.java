package com.ruk.sid.form;

import org.hibernate.validator.constraints.NotEmpty;

public class FileForm {

	
	
	@NotEmpty
	private String titreHorraire;
	@NotEmpty
	private String AprouvedBuy;
	private String datefrom;
	private String dateTo;
	
	public FileForm() {
		super();
	}

	
	
	

	public FileForm( String titreHorraire, String aprouvedBuy,
			String datefrom, String dateTo) {
		super();
		
		this.titreHorraire = titreHorraire;
		AprouvedBuy = aprouvedBuy;
		this.datefrom = datefrom;
		this.dateTo = dateTo;
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




	
	

}
