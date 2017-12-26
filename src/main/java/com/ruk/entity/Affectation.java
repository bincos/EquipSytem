package com.ruk.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * @author BIN
 * @version 1.0
 * @created 03-août-2017 23:46:04
 */
@Entity
public class Affectation {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty
	private String descriptionAff;
	private boolean statusAff;
	private String typeAff;
	private Date dateAff;
	private int yearAff;
	private String nameEquip;
	private String nameAffected;
	private String dateStartAff;
	private String dateEndAff;
	private int idEquip;
	private int idUser;
	@ManyToOne
	@JoinColumn(name="m_SimpleUser")
	private SimpleUser m_SimpleUser;
		
	@OneToMany(mappedBy="m_Affectation",fetch=FetchType.LAZY)	
	private Collection<Equipment> m_Equipment;

	public Affectation() {
		super();
	}

	public Affectation(String descriptionAff, boolean statusAff,
			String typeAff, Date dateAff, String dateStartAff,String dateEndAff,int yearAff,
			SimpleUser m_SimpleUser, Collection<Equipment> m_Equipment) {
		super();
		this.descriptionAff = descriptionAff;
		this.statusAff = statusAff;
		this.typeAff = typeAff;
		this.dateAff = dateAff;
		this.yearAff = yearAff;
		this.dateStartAff = dateStartAff;
		this.dateEndAff = dateEndAff;
		this.m_SimpleUser = m_SimpleUser;
		this.m_Equipment = m_Equipment;
	}
	
	
	
	

	public Affectation(String descriptionAff, boolean statusAff, String typeAff, Date dateAff, int yearAff,
			String nameEquip, String nameAffected, String dateStartAff, String dateEndAff,int idEquip,int idUser) {
		super();
		this.descriptionAff = descriptionAff;
		this.statusAff = statusAff;
		this.typeAff = typeAff;
		this.dateAff = dateAff;
		this.yearAff = yearAff;
		this.nameEquip = nameEquip;
		this.nameAffected = nameAffected;
		this.dateStartAff = dateStartAff;
		this.dateEndAff = dateEndAff;
		this.idEquip = idEquip;
		this.idUser = idUser;
	}

	public String getNameEquip() {
		return nameEquip;
	}

	public void setNameEquip(String nameEquip) {
		this.nameEquip = nameEquip;
	}

	public String getNameAffected() {
		return nameAffected;
	}

	public void setNameAffected(String nameAffected) {
		this.nameAffected = nameAffected;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescriptionAff() {
		return descriptionAff;
	}

	public void setDescriptionAff(String descriptionAff) {
		this.descriptionAff = descriptionAff;
	}

	public boolean isStatusAff() {
		return statusAff;
	}

	
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public void setStatusAff(boolean statusAff) {
		this.statusAff = statusAff;
	}

	public String getTypeAff() {
		return typeAff;
	}

	public void setTypeAff(String typeAff) {
		this.typeAff = typeAff;
	}

	public Date getDateAff() {
		return dateAff;
	}

	public void setDateAff(Date dateAff) {
		this.dateAff = dateAff;
	}

	public String getDateStartAff() {
		return dateStartAff;
	}

	public void setDateStartAff(String dateStartAff) {
		this.dateStartAff = dateStartAff;
	}

	public String getDateEndAff() {
		return dateEndAff;
	}

	public int getYearAff() {
		return yearAff;
	}

	public void setYearAff(int yearAff) {
		this.yearAff = yearAff;
	}

	public void setDateEndAff(String dateEndAff) {
		this.dateEndAff = dateEndAff;
	}

	public SimpleUser getM_SimpleUser() {
		return m_SimpleUser;
	}

	public void setM_SimpleUser(SimpleUser m_SimpleUser) {
		this.m_SimpleUser = m_SimpleUser;
	}

	public Collection<Equipment> getM_Equipment() {
		return m_Equipment;
	}

	public void setM_Equipment(Collection<Equipment> m_Equipment) {
		this.m_Equipment = m_Equipment;
	}

	public int getIdEquip() {
		return idEquip;
	}

	public void setIdEquip(int idEquip) {
		this.idEquip = idEquip;
	}
	


}