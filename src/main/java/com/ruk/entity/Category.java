package com.ruk.entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import java.sql.Blob;

/**
 * @author BIN
 * @version 1.0
 * @created 03-août-2017 23:46:04
 */
@Entity
public class Category {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nameCat;
	private String descriptionCat;
	@Lob
	private byte[] photo;
	private String PhotoName;
	@OneToMany(mappedBy="m_Category",fetch=FetchType.LAZY)		
	private Collection <Equipment> m_Equipment;

	public Category(){

	}
	
	

	public Category(String nameCat, String descriptionCat, byte[] photo, String photoName) {
		super();
		this.nameCat = nameCat;
		this.descriptionCat = descriptionCat;
		this.photo = photo;
		PhotoName = photoName;
	}


	
	

	public Category(String nameCat, String descriptionCat, byte[] photo, String photoName,
			Collection<Equipment> m_Equipment) {
		super();
		this.nameCat = nameCat;
		this.descriptionCat = descriptionCat;
		this.photo = photo;
		PhotoName = photoName;
		this.m_Equipment = m_Equipment;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNameCat() {
		return nameCat;
	}



	public void setNameCat(String nameCat) {
		this.nameCat = nameCat;
	}



	public String getDescriptionCat() {
		return descriptionCat;
	}



	public void setDescriptionCat(String descriptionCat) {
		this.descriptionCat = descriptionCat;
	}



	public byte[] getPhoto() {
		return photo;
	}



	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}



	public String getPhotoName() {
		return PhotoName;
	}



	public void setPhotoName(String photoName) {
		PhotoName = photoName;
	}



	public Collection<Equipment> getM_Equipment() {
		return m_Equipment;
	}



	public void setM_Equipment(Collection<Equipment> m_Equipment) {
		this.m_Equipment = m_Equipment;
	}



}