package com.ruk.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author BIN
 * @version 1.0
 * @created 03-août-2017 23:46:05
 */
@Entity
public class Planification {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int idUser;
	private String nameUser;
	private String title;
	private Date date;
	private String type;
	private String email;
	private String fromUser;
	private String statePlan;
	
	
	public Planification(int idUser, String nameUser, String title, String description, Date date, String type,
			String email, String from,String statePlan ) {
		super();
		this.idUser = idUser;
		this.nameUser = nameUser;
		this.title = title;
		this.description = description;
		this.date = date;
		this.type = type;
		this.email = email;
		this.fromUser = from;
		this.statePlan=statePlan;
	}

	public Planification() {
		super();
	}

	private String description;
	

	


	


	public Long getId() {
		return id;
	}

	
	
	public String getFrom() {
		return fromUser;
	}

	public void setFrom(String from) {
		this.fromUser = from;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNameUser() {
		return nameUser;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String getStatePlan() {
		return statePlan;
	}

	public void setStatePlan(String statePlan) {
		this.statePlan = statePlan;
	}
	
	
	
	
	
	

}
