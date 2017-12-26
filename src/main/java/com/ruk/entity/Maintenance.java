package com.ruk.entity;

import java.util.Date;

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
public class Maintenance {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int idEquip;
	private int  idUser;
	private String title;
	private String nameUser;
	private String nameEquip;
	@NotEmpty
	private String descriptionProblem;
	@NotEmpty
	private String solutionProblem;
	private String type;
	private String date;
	
	@ManyToOne
	@JoinColumn(name="m_SimpleUser")
	private SimpleUser m_SimpleUser;
	
	@ManyToOne
	@JoinColumn(name="m_Equipment")	
	private Equipment m_Equipment;

	public Maintenance() {
		super();
	}

	

	public Maintenance(int idEquip, int idUser, String title, String nameUser, String nameEquip,
			String descriptionProblem, String solutionProblem, String type, String date) {
		super();
		this.idEquip = idEquip;
		this.idUser = idUser;
		this.title = title;
		this.nameUser = nameUser;
		this.nameEquip = nameEquip;
		this.descriptionProblem = descriptionProblem;
		this.solutionProblem = solutionProblem;
		this.type = type;
		this.date = date;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescriptionProblem() {
		return descriptionProblem;
	}

	public void setDescriptionProblem(String descriptionProblem) {
		this.descriptionProblem = descriptionProblem;
	}

	public String getSolutionProblem() {
		return solutionProblem;
	}

	public void setSolutionProblem(String solutionProblem) {
		this.solutionProblem = solutionProblem;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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

	public int getIdEquip() {
		return idEquip;
	}

	public void setIdEquip(int idEquip) {
		this.idEquip = idEquip;
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

	public String getNameEquip() {
		return nameEquip;
	}

	public void setNameEquip(String nameEquip) {
		this.nameEquip = nameEquip;
	}
	

	

}