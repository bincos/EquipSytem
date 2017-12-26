package com.ruk.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Planifications {
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
	
	public Planifications() {
		super();
	}

	public Planifications(int idUser, String nameUser, String title, Date date, String type, String email,
			String from) {
		super();
		this.idUser = idUser;
		this.nameUser = nameUser;
		this.title = title;
		this.date = date;
		this.type = type;
		this.email = email;
		this.fromUser = from;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFrom() {
		return fromUser;
	}

	public void setFrom(String from) {
		this.fromUser = from;
	}
	
	
	
	
	
}
