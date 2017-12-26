package com.ruk.entity;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author BIN
 * @version 1.0
 * @created 03-août-2017 23:46:06
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)// comme il ya heritage
/* cat tout les resultats doit etre stocker dans la table Resultats alors il yaura une colone qui nous 
 permettra de differncier les types des resultat.-->> TYPE_RS @discriminator nous permettra de specifier cette colone

*/
@DiscriminatorColumn(name="TYPE_USER",discriminatorType=DiscriminatorType.STRING)
public class User implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty
	private String nameUser;
	@Email
	@NotEmpty
	private String email;
	private String password;
	private boolean actived;
	@NotEmpty
	private String adressUser;
	@NotEmpty
	private String phoneUser; 
	private String descriptionUser;
	private String roleName;
	@Lob
	private byte[] photo;
	private String photoName;
	private Date dateSave;
	
	

	@OneToMany(mappedBy="m_User",fetch=FetchType.LAZY)		
	private  Collection <Role> m_Role;

	
	
	public User(String nameUser, String email, String password, boolean actived, String adressUser, String phoneUser,
			String descriptionUser, String roleName, byte[] photo, String photoName,Date date) {
		super();
		this.nameUser = nameUser;
		this.email = email;
		this.password = password;
		this.actived = actived;
		this.adressUser = adressUser;
		this.phoneUser = phoneUser;
		this.descriptionUser = descriptionUser;
		this.roleName = roleName;
		this.photo = photo;
		this.photoName = photoName;
		this.dateSave=date;
	}

	
	public User(String nameUser, String email, String password, boolean actived, String adressUser, String phoneUser,
			String descriptionUser, String roleName, byte[] photo, String photoName, Collection<Role> m_Role) {
		super();
		this.nameUser = nameUser;
		this.email = email;
		this.password = password;
		this.actived = actived;
		this.adressUser = adressUser;
		this.phoneUser = phoneUser;
		this.descriptionUser = descriptionUser;
		this.roleName = roleName;
		this.photo = photo;
		this.photoName = photoName;
		this.m_Role = m_Role;
	}
	public User(){

	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNameUser() {
		return nameUser;
	}


	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}


	public String getEmailUser() {
		return email;
	}


	public void setEmailUser(String emailUser) {
		this.email = emailUser;
	}


	public String getAdressUser() {
		return adressUser;
	}


	public void setAdressUser(String adressUser) {
		this.adressUser = adressUser;
	}


	public String getPhoneUser() {
		return phoneUser;
	}


	public void setPhoneUser(String phoneUser) {
		this.phoneUser = phoneUser;
	}


	public String getDescriptionUser() {
		return descriptionUser;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isActived() {
		return actived;
	}


	public void setActived(boolean actived) {
		this.actived = actived;
	}


	public void setDescriptionUser(String descriptionUser) {
		this.descriptionUser = descriptionUser;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public Collection<Role> getM_Role() {
		return m_Role;
	}


	public void setM_Role(Collection<Role> m_Role) {
		this.m_Role = m_Role;
	}


	public byte[] getPhoto() {
		return photo;
	}


	public void setPhoto(byte[] bs) {
		this.photo = bs;
	}


	public String getPhotoName() {
		return photoName;
	}


	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}




	public Date getDateSave() {
		return dateSave;
	}




	public void setDateSave(Date dateSave) {
		this.dateSave = dateSave;
	}

	
	
	
}