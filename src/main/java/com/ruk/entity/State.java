package com.ruk.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * @author BIN
 * @version 1.0
 * @created 03-août-2017 23:46:06
 */
@Entity
public class State implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int idEquip;
	private String nomState;	
	private Date dateState;
	private String descriptionState;
	

	@ManyToOne
	@JoinColumn(name="m_Equipment")
	private Equipment m_Equipment;


	public State() {
		super();
	}


	public State(String nomState, Date dateState, String descriptionState,
			Equipment m_Equipment) {
		super();
		this.nomState = nomState;
		this.dateState = dateState;
		this.descriptionState = descriptionState;
		this.m_Equipment = m_Equipment;
	}


	

	public State(int idEquip, String nomState, Date dateState, String descriptionState) {
		super();
		this.idEquip = idEquip;
		this.nomState = nomState;
		this.dateState = dateState;
		this.descriptionState = descriptionState;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNomState() {
		return nomState;
	}


	public void setNomState(String nomState) {
		this.nomState = nomState;
	}


	public Date getDateState() {
		return dateState;
	}


	public void setDateState(Date dateState) {
		this.dateState = dateState;
	}


	public String getDescriptionState() {
		return descriptionState;
	}


	public void setDescriptionState(String descriptionState) {
		this.descriptionState = descriptionState;
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
	
	
	
}
