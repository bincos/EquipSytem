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
public class Notification {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titleNot;
	@NotEmpty
	private String descriptionNot;
	private Date date;
	private String sourceNot;
	
	
	@ManyToOne
	@JoinColumn(name="m_SimpleUser")
	private SimpleUser m_SimpleUser;


	public Notification() {
		super();
	}


	public Notification(String titleNot, String descriptionNot, Date date,
			String sourceNot, SimpleUser m_SimpleUser) {
		super();
		this.titleNot = titleNot;
		this.descriptionNot = descriptionNot;
		this.date = date;
		this.sourceNot = sourceNot;
		this.m_SimpleUser = m_SimpleUser;
	}


	public Notification(String titleNot, String descriptionNot, Date date,
			String sourceNot) {
		super();
		this.titleNot = titleNot;
		this.descriptionNot = descriptionNot;
		this.date = date;
		this.sourceNot = sourceNot;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitleNot() {
		return titleNot;
	}


	public void setTitleNot(String titleNot) {
		this.titleNot = titleNot;
	}


	public String getDescriptionNot() {
		return descriptionNot;
	}


	public void setDescriptionNot(String descriptionNot) {
		this.descriptionNot = descriptionNot;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getSourceNot() {
		return sourceNot;
	}


	public void setSourceNot(String sourceNot) {
		this.sourceNot = sourceNot;
	}


	public SimpleUser getM_SimpleUser() {
		return m_SimpleUser;
	}


	public void setM_SimpleUser(SimpleUser m_SimpleUser) {
		this.m_SimpleUser = m_SimpleUser;
	}


}