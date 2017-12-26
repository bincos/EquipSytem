package com.ruk.entity;

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
public class Role {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String roleName;
	private String DescriptionRole;
	
	@ManyToOne
	@JoinColumn(name="m_User")
	private  User m_User;

	public Role() {
		super();
	}

	public Role(String descriptionRole, String nameRole) {
		super();
		DescriptionRole = descriptionRole;
		this.roleName = nameRole;
	}

	public Role(String descriptionRole, String nameRole, User m_User) {
		super();
		DescriptionRole = descriptionRole;
		this.roleName = nameRole;
		this.m_User = m_User;
	}

	public String getDescriptionRole() {
		return DescriptionRole;
	}

	public void setDescriptionRole(String descriptionRole) {
		DescriptionRole = descriptionRole;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameRole() {
		return roleName;
	}

	public void setNameRole(String nameRole) {
		this.roleName = nameRole;
	}

	public User getM_User() {
		return m_User;
	}

	public void setM_User(User m_User) {
		this.m_User = m_User;
	}


	

}