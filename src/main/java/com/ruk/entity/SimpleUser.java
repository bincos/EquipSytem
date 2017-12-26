package com.ruk.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author BIN
 * @version 1.0
 * @created 03-août-2017 23:46:06
 */
@Entity
@DiscriminatorValue("SIMPLE_USER") 
public class SimpleUser extends User implements Serializable{
	@NotEmpty
	private String function;
	
	
	@OneToMany(mappedBy="m_SimpleUser",fetch=FetchType.LAZY)		
	private  Collection <Notification> m_Notification;
	
	@OneToMany(mappedBy="m_SimpleUser",fetch=FetchType.LAZY)	
	private Collection <Affectation> m_Affectation;
	

	@OneToMany(mappedBy="m_SimpleUser",fetch=FetchType.LAZY)	
	private Collection <FeadBack> m_FeadBack;
	
	@OneToMany(mappedBy="m_simpleUser",fetch=FetchType.LAZY)	
	private Collection <Besoin> m_Besoin;
	
	@OneToMany(mappedBy="m_SimpleUser",fetch=FetchType.LAZY)	
	private Collection <Maintenance> m_Maintenace;

	public SimpleUser() {
		super();
	}






	public SimpleUser(String nameUser, String email, String password, boolean actived, String adressUser,
			String phoneUser, String descriptionUser, String roleName, byte[] photo, String photoName, Date date,
			String function) {
		super(nameUser, email, password, actived, adressUser, phoneUser, descriptionUser, roleName, photo, photoName,
				date);
		this.function = function;
	}






	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public Collection<Notification> getM_Notification() {
		return m_Notification;
	}

	public void setM_Notification(Collection<Notification> m_Notification) {
		this.m_Notification = m_Notification;
	}

	public Collection<Affectation> getM_Affectation() {
		return m_Affectation;
	}

	public void setM_Affectation(Collection<Affectation> m_Affectation) {
		this.m_Affectation = m_Affectation;
	}

	public Collection<FeadBack> getM_FeadBack() {
		return m_FeadBack;
	}

	public void setM_FeadBack(Collection<FeadBack> m_FeadBack) {
		this.m_FeadBack = m_FeadBack;
	}

	public Collection<Besoin> getM_Besoin() {
		return m_Besoin;
	}

	public void setM_Besoin(Collection<Besoin> m_Besoin) {
		this.m_Besoin = m_Besoin;
	}

	public Collection<Maintenance> getM_Maintenace() {
		return m_Maintenace;
	}

	public void setM_Maintenace(Collection<Maintenance> m_Maintenace) {
		this.m_Maintenace = m_Maintenace;
	}

	
	
	
}
