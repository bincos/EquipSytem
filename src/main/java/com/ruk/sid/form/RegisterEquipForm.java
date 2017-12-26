package com.ruk.sid.form;

import java.util.Date;

import javax.persistence.Lob;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.ruk.entity.Equipment;

public class RegisterEquipForm {
	
	private String nameEquip;
	
	private Date dateSave;
	private String numSerie;
	
	private String modelEquip;
	private String decriptionEquip;
	private int yearFabrication;
	private String nameState;
	
	private byte[]  photo;
	
	private String photoName;
	
	private String CategoryList;
	private String categoryName;
	private String FournissorName;
	private String  nameCat;
	
	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public String getFournissorName() {
		return FournissorName;
	}


	public void setFournissorName(String fournissorName) {
		FournissorName = fournissorName;
	}


	public RegisterEquipForm() {
		super();
	}

	
	public String getNameEquip() {
		return nameEquip;
	}


	public void setNameEquip(String nameEquip) {
		this.nameEquip = nameEquip;
	}


	public Date getDateSave() {
		return dateSave;
	}


	public void setDateSave(Date dateSave) {
		this.dateSave = dateSave;
	}


	public String getNumSerie() {
		return numSerie;
	}


	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}


	public String getModelEquip() {
		return modelEquip;
	}


	public void setModelEquip(String modelEquip) {
		this.modelEquip = modelEquip;
	}


	public String getDecriptionEquip() {
		return decriptionEquip;
	}


	public void setDecriptionEquip(String decriptionEquip) {
		this.decriptionEquip = decriptionEquip;
	}


	public int getYearFabrication() {
		return yearFabrication;
	}


	public void setYearFabrication(int yearFabrication) {
		this.yearFabrication = yearFabrication;
	}


	public String getNameState() {
		return nameState;
	}


	public void setNameState(String nameState) {
		this.nameState = nameState;
	}


	public byte[] getPhoto() {
		return photo;
	}


	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}


	public String getPhotoName() {
		return photoName;
	}


	public String getNameCat() {
		return nameCat;
	}


	public void setNameCat(String nameCat) {
		this.nameCat = nameCat;
	}


	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}


	public String getCategoryList() {
		return CategoryList;
	}

	public void setCategoryList(String categoryList) {
		CategoryList = categoryList;
	}
	
	

}
