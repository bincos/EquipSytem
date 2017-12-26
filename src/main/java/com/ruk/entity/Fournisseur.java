package com.ruk.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 * @author BIN
 * @version 1.0
 * @created 03-août-2017 23:46:05
 */
@Entity
@DiscriminatorValue("SIMPLE_FOURNISEUR") 
public class Fournisseur extends User implements Serializable {
	

	public Fournisseur() {
		super();
	}
	

	public Fournisseur(Collection<Equipment> m_Equipment) {
		super();
		this.m_Equipment = m_Equipment;
	}


	@OneToMany(mappedBy="m_Fournisseur",fetch=FetchType.LAZY)	
	private  Collection  <Equipment> m_Equipment;
	
	
	public Collection<Equipment> getM_Equipment() {
		return m_Equipment;
	}

	public void setM_Equipment(Collection<Equipment> m_Equipment) {
		this.m_Equipment = m_Equipment;
	}

	

}