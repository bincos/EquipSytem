package com.ruk.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * @author BIN
 * @version 1.0
 * @created 03-août-2017 23:46:05
 */
@Entity
public class FeadBack {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty
	private String descriptionFead;
	private String typeFead;
	private String nameUser;
	private int	idUser;
	private int idEquip;
	private Date date;

	@ManyToOne
	@JoinColumn(name="m_SimpleUser")
	private SimpleUser m_SimpleUser;
	
	@ManyToOne
	@JoinColumn(name="m_FeadBack")
	private Equipment m_Equipment;
	
	
	public FeadBack(){

	}


	public FeadBack(String descriptionFead, String typeFead, Date date,
			SimpleUser m_SimpleUser, Equipment m_Equipment) {
		super();
		this.descriptionFead = descriptionFead;
		this.typeFead = typeFead;
		this.date = date;
		this.m_SimpleUser = m_SimpleUser;
		this.m_Equipment = m_Equipment;
	}


	

	public FeadBack(String descriptionFead, String typeFead, String nameUser, int idUser, int idEquip, Date date) {
		super();
		this.descriptionFead = descriptionFead;
		this.typeFead = typeFead;
		this.nameUser = nameUser;
		this.idUser = idUser;
		this.idEquip = idEquip;
		this.date = date;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDescriptionFead() {
		return descriptionFead;
	}


	public void setDescriptionFead(String descriptionFead) {
		this.descriptionFead = descriptionFead;
	}


	public String getTypeFead() {
		return typeFead;
	}


	public void setTypeFead(String typeFead) {
		this.typeFead = typeFead;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public SimpleUser getM_SimpleUser() {
		return m_SimpleUser;
	}


	public void setM_SimpleUser(SimpleUser m_SimpleUser) {
		this.m_SimpleUser = m_SimpleUser;
	}


	public Equipment getM_Equipment() {
		return m_Equipment;
	}


	public void setM_Equipment(Equipment m_Equipment) {
		this.m_Equipment = m_Equipment;
	}


	public String getNameUser() {
		return nameUser;
	}


	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}


	public int getIdUser() {
		return idUser;
	}


	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}


	public int getIdEquip() {
		return idEquip;
	}


	public void setIdEquip(int idEquip) {
		this.idEquip = idEquip;
	}




}