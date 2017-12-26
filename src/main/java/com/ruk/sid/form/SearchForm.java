package com.ruk.sid.form;

import java.util.Date;

public class SearchForm {
	
	private String mc;
	private String page;
	private String param;
	private String destinPage; 
	private String promotion;
	private int idEquip;
	private int idUser;
	private int idBesoin;
	private String date1,date2;
	private Date date11,date22;
	
	
	
	public SearchForm() {
		super();
	}
	
	
	public String getDestinPage() {
		return destinPage;
	}




	public int getIdBesoin() {
		return idBesoin;
	}


	public void setIdBesoin(int idBesoin) {
		this.idBesoin = idBesoin;
	}


	public void setDestinPage(String destinPage) {
		this.destinPage = destinPage;
	}


	public String getPage() {
		return page;
	}


	
	public int getIdUser() {
		return idUser;
	}


	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}


	public Date getDate11() {
		return date11;
	}


	public void setDate11(Date date11) {
		this.date11 = date11;
	}


	public Date getDate22() {
		return date22;
	}


	public void setDate22(Date date22) {
		this.date22 = date22;
	}


	public void setPage(String page) {
		this.page = page;
	}


	public String getDate1() {
		return date1;
	}


	public void setDate1(String date1) {
		this.date1 = date1;
	}


	public String getDate2() {
		return date2;
	}


	public void setDate2(String date2) {
		this.date2 = date2;
	}


	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getPromotion() {
		return promotion;
	}
	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}
	public int getIdEquip() {
		return idEquip;
	}
	public void setIdEquip(int idEquip) {
		this.idEquip = idEquip;
	}
	
	

}
