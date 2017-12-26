package com.ruk.entity;

import java.util.Collection;
import java.util.Date;
import java.sql.Blob;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;



/**
* @author BIN
* @version 1.0
* @created 03-août-2017 23:46:05
*/
@Entity
public class Equipment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty
	private String nameEquip;
	@DateTimeFormat
	private String dateSave;
	private String numSerie;
	@NotEmpty
	private String modelEquip;
	private String decriptionEquip;
	private int yearFabrication;
	private String nameState;
	@Lob
	private byte[]  photo;
	
	private String photoName;
	private String nameCat;
	private String FournissorName;
	
	
	
	
	

	@ManyToOne
	@JoinColumn(name="m_Fournisseur")
	private Fournisseur m_Fournisseur;
	
	@ManyToOne
	@JoinColumn(name="m_Affectation")
	private Affectation m_Affectation;
	
	@OneToMany(mappedBy="m_Equipment",fetch=FetchType.LAZY)	
	private Collection<State> m_State;
	
	@ManyToOne
	@JoinColumn(name="m_Category")
	private Category m_Category;
	
	@OneToMany(mappedBy="m_Equipment",fetch=FetchType.LAZY)	
	private Collection <Maintenance> m_Maintenace;
	
	@OneToMany(mappedBy="m_Equipment",fetch=FetchType.LAZY)	
	private Collection <FeadBack> m_FeadBack;

	

	public Equipment(){

	}








	
	




	public Equipment(String nameEquip, String dateSave, String numSerie, String modelEquip, String decriptionEquip,
			int yearFabrication, String nameState, byte[] photo, String photoName, String categoryName,
			String fournissorName) {
		super();
		this.nameEquip = nameEquip;
		this.dateSave = dateSave;
		this.numSerie = numSerie;
		this.modelEquip = modelEquip;
		this.decriptionEquip = decriptionEquip;
		this.yearFabrication = yearFabrication;
		this.nameState = nameState;
		this.photo = photo;
		this.photoName = photoName;
		this.nameCat = categoryName;
		FournissorName = fournissorName;
	}




	public Equipment(String nameEquip, String dateSave, String numSerie, String modelEquip, String decriptionEquip,
			int yearFabrication, String nameState, byte[] photo, String photoName, String categoryName,
			Collection<State> m_State) {
		super();
		this.nameEquip = nameEquip;
		this.dateSave = dateSave;
		this.numSerie = numSerie;
		this.modelEquip = modelEquip;
		this.decriptionEquip = decriptionEquip;
		this.yearFabrication = yearFabrication;
		this.nameState = nameState;
		this.photo = photo;
		this.photoName = photoName;
		this.nameCat = categoryName;
		this.m_State = m_State;
	}




	public String getFournissorName() {
		return FournissorName;
	}




	public void setFournissorName(String fournissorName) {
		FournissorName = fournissorName;
	}




	public String getNameState() {
		return nameState;
	}




	public void setNameState(String nameState) {
		this.nameState = nameState;
	}




	public String getCategoryName() {
		return nameCat;
	}




	public void setCategoryName(String categoryName) {
		this.nameCat = categoryName;
	}




	public void setM_Fournisseur(Fournisseur m_Fournisseur) {
		this.m_Fournisseur = m_Fournisseur;
	}




	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNameEquip() {
		return nameEquip;
	}



	public void setNameEquip(String nameEquip) {
		this.nameEquip = nameEquip;
	}



	public String getDateSave() {
		return dateSave;
	}



	public String getNameCat() {
		return nameCat;
	}




	public void setNameCat(String nameCat) {
		this.nameCat = nameCat;
	}




	public void setDateSave(String dateSave) {
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



	public byte[] getPhoto() {
		return photo;
	}



	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}




	



	public Fournisseur getM_Fournisseur() {
		return m_Fournisseur;
	}




	public Collection<State> getM_State() {
		return m_State;
	}




	public void setM_State(Collection<State> m_State) {
		this.m_State = m_State;
	}




	public Category getM_Category() {
		return m_Category;
	}



	public void setM_Category(Category m_Category) {
		this.m_Category = m_Category;
	}



	public Collection<Maintenance> getM_Maintenace() {
		return m_Maintenace;
	}



	public void setM_Maintenace(Collection<Maintenance> m_Maintenace) {
		this.m_Maintenace = m_Maintenace;
	}



	public Collection<FeadBack> getM_FeadBack() {
		return m_FeadBack;
	}



	public Affectation getM_Affectation() {
		return m_Affectation;
	}




	public void setM_Affectation(Affectation m_Affectation) {
		this.m_Affectation = m_Affectation;
	}




	public void setM_FeadBack(Collection<FeadBack> m_FeadBack) {
		this.m_FeadBack = m_FeadBack;
	}




	public String getPhotoName() {
		return photoName;
	}




	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}


}