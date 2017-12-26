package com.ruk.mettier;

import java.util.Date;
import java.util.List;

import com.ruk.entity.Affectation;
import com.ruk.entity.Besoin;
import com.ruk.entity.Category;
import com.ruk.entity.Equipment;
import com.ruk.entity.FeadBack;
import com.ruk.entity.Fournisseur;
import com.ruk.entity.Maintenance;
import com.ruk.entity.Notification;
import com.ruk.entity.Planification;
import com.ruk.entity.Planifications;
import com.ruk.entity.SimpleUser;
import com.ruk.entity.State;
import com.ruk.entity.User;

public interface IEquipmentSysMettier {
	

	public Notification addNotification(Notification notification );
//	public Besoin addBesoin(Besoin besoin );
////data for User
	public User addSimpleUser(SimpleUser user);
	public Fournisseur addFournisseur(Fournisseur fournisseur );
	
	
	public void upDateUser(User user);
	public  SimpleUser getSimpleUser(int idUser);
	public  List<SimpleUser>  getSimpleUserByMail(String mail);
	public List<SimpleUser>  getAllSimpleUser();
	public List<SimpleUser>  getAllSimpleUserByKey(String mc);
	
	public  Fournisseur getFourniseur(int idUser);
	public List<Fournisseur>  getFourniseur();
	
	/////---Notification data
	
	public Notification addNotification(Notification notification,int idUser);
	public Notification getNotification(int idUser);
	public List<Notification> getNotificationList(int idUser);
	public Notification delNotification(int idUser);
	  
	//--Affectation
	public Affectation addAffectation(Affectation affectation, int idEquip);
	public void updateAffectation(Affectation notification);
	public List<Affectation> getAllAfByKeyWord(String keString);
	public List<Affectation> getAllAfByUser(int idUser);
	public List<Affectation> getAllAfByKeyDate(Date dateStart, Date dateEnd);
	public List<Affectation> getAllAfByEquip(int IdEquip);
	public Affectation getAffectation(int idAff);
	public List<Affectation> getAllAffectation();
	public List<Affectation> getAllAfByKeyDate(String dateStart, String dateEnd);
	
	///--- Equipment
	 
	public Equipment addEquipment(Equipment equipment,int idCat,int idFourn);
	public Equipment getEquipment(int idEquip);
	public void updateEquipment(Equipment equipment);
	public List<Equipment>getEquipmentByState(String state);
	public List<Equipment>getEquipmentByDate(String dateStart, String dateEnd);
	public List<Equipment> getEquipmentByPage(String statutEquip,int position, int nombreEquip);
	public List<Equipment>getEquipmentByKey(String state);
	public List<Equipment>getAllEquipmet();
	public List<Equipment>getAllAviableEquipmet();
	public List<Affectation> trackEquipmentByAff(int idEquip);
	public List<State> trackEquipmentBySate(int idEquip);
	public List<State> getStateByEquip (int idEquip);
		 
	///--- Category
	public Category addCategory(Category cat);
	public  void updateCategory(Category cat);
	public List<Category>getAllCategory();
	public Category getCategory(int idCat);
	public List <Category> getCategoryByKey(String mc);
//	public Category getCategory(String NAme);
	 
	///---FeadBack
	public FeadBack addFeadBack(FeadBack  feadBack ,int idEquip);
	public FeadBack getFeadback(int idEquip);
	public List<FeadBack>  getListFeadBackByEquip(int idEquip);
	
	////---Mainetance	
	public Maintenance addMaitenance(Maintenance maintenance,int idEquip,int idUser);
	public List<Maintenance> getAllMaintenanceByDate(String dateStart, String dateEnd);
	public List<Maintenance> getAllMaintenanceByEquip(int idEquip);
	public List<Maintenance>getAllMaintenanceByKey(String keyWord);
	public List<Maintenance>getMaintenanceByUser(int idUser);
	public Maintenance getMaintenance(int idMaint);

///---Besions
	public Besoin addBesion(Besoin besion,int idUser);
	public Besoin getBesion(int idBesion);
	public Affectation validateBesion(int idBesion);
	public List<Besoin>BesionByParam(String Param);
	public List<Besoin>BesionByDate(String dateStart, String dateEnd);
	public List<Besoin>BesionByUser(int idUser);
	public List<Besoin> getAllBesion();
	public Besoin updateBesoin( Besoin besoin);
	
	
	// ---State
	public State addState(State state,int idEquip);

	
	public Planifications addPlan(Planifications planification);
	public Planification addPlan(Planification planification);
	public List <Planification> listPlanBuyUser(int idUser);
	public List<Planification> listPlan() ;
	public Planification getPlan(Long idPlan);
	
	public Planification updatePlan(Planification planification);
	
	public void removePlan(Long long1);
}
