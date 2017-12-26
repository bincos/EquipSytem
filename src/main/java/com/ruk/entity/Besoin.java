package com.ruk.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Besoin {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String category;
	private String  motif;
	@NotEmpty
	private String description;
	private String date;
	private String state;
	private String reponse;
	private String  nameUser;
	private int idUser;
	private String  email;	
	@ManyToOne
	@JoinColumn(name="m_SimpleUser")
	private SimpleUser m_simpleUser;

	public Besoin() {
		super();
	}

	
	public Besoin(String title, String category, String motif, String description, String date, String state,
			String reponse, String nameUser, int idUser,String email) {
		super();
		this.title = title;
		this.category = category;
		this.motif = motif;
		this.description = description;
		this.date = date;
		this.state = state;
		this.reponse = reponse;
		this.nameUser = nameUser;
		this.idUser = idUser;
		this.email=email;
	}








	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getReponse() {
		return reponse;
	}



	public void setReponse(String reponse) {
		this.reponse = reponse;
	}



	public String getNameUser() {
		return nameUser;
	}



	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getMotif() {
		return motif;
	}

	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public SimpleUser getM_simpleUser() {
		return m_simpleUser;
	}

	public void setM_simpleUser(SimpleUser m_simpleUser) {
		this.m_simpleUser = m_simpleUser;
	}



	public int getIdUser() {
		return idUser;
	}



	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	
	
	
}
