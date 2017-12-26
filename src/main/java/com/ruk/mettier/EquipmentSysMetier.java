package com.ruk.mettier;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ruk.dao.IEquipmentSysDao;
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

@Transactional
public class EquipmentSysMetier implements IEquipmentSysMettier {
	
	// on fait un couplage faible avec la couche DAO  ne fait appeller les méthodes de la couche DAO
	private  IEquipmentSysDao dao;

	
	public IEquipmentSysDao getDao() {
		return dao;
	}

	public void setDao(IEquipmentSysDao dao) {
		this.dao = dao;
	}

	@Override
	public Notification addNotification(Notification notification) {
		// TODO Auto-generated method stub
		return dao.addNotification(notification);
	}

	@Override
	public User addSimpleUser(SimpleUser user) {
		// TODO Auto-generated method stub
		return dao.addSimpleUser(user);
	}

	@Override
	public Fournisseur addFournisseur(Fournisseur fournisseur) {
		// TODO Auto-generated method stub
		return dao.addFournisseur(fournisseur);
	}

	@Override
	public void upDateUser(User user) {
		dao.upDateUser(user);
		
	}

	@Override
	public SimpleUser getSimpleUser(int idUser) {
		// TODO Auto-generated method stub
		return dao.getSimpleUser(idUser);
	}

	@Override
	public List<SimpleUser> getAllSimpleUser() {
		// TODO Auto-generated method stub
		return dao.getAllSimpleUser();
	}

	@Override
	public Fournisseur getFourniseur(int idUser) {
		// TODO Auto-generated method stub
		return dao.getFourniseur(idUser);
	}

	@Override
	public List<Fournisseur> getFourniseur() {
		// TODO Auto-generated method stub
		return dao.getFourniseur();
	}

	@Override
	public Notification addNotification(Notification notification, int idUser) {
		// TODO Auto-generated method stub
		return dao.addNotification(notification, idUser);
	}

	@Override
	public Notification getNotification(int idUser) {
		// TODO Auto-generated method stub
		return dao.getNotification(idUser);
	}

	@Override
	public List<Notification> getNotificationList(int idUser) {
		// TODO Auto-generated method stub
		return dao.getNotificationList(idUser);
	}

	@Override
	public Notification delNotification(int idUser) {
		// TODO Auto-generated method stub
		return dao.delNotification(idUser);
	}

	@Override
	public Affectation addAffectation(Affectation affectation, int idEquip) {
		// TODO Auto-generated method stub
		return dao.addAffectation(affectation, idEquip);
	}

	@Override
	public void updateAffectation(Affectation notification) {
		// TODO Auto-generated method stub
		dao.updateAffectation(notification);
	}

	@Override
	public List<Affectation> getAllAfByKeyWord(String keString) {
		// TODO Auto-generated method stub
		return dao.getAllAfByKeyWord(keString);
	}

	@Override
	public List<Affectation> getAllAfByKeyDate(Date dateStart, Date dateEnd) {
		// TODO Auto-generated method stub
		return dao.getAllAfByKeyDate(dateStart, dateEnd);
	}

	@Override
	public Affectation getAffectation(int idAff) {
		// TODO Auto-generated method stub
		return dao.getAffectation(idAff);
	}

	@Override
	public Equipment addEquipment(Equipment equipment, int idCat, int idFourn) {
		// TODO Auto-generated method stub
		return dao.addEquipment(equipment, idCat, idFourn);
	}

	@Override
	public Equipment getEquipment(int idEquip) {
		// TODO Auto-generated method stub
		return dao.getEquipment(idEquip);
	}

	@Override
	public void updateEquipment(Equipment equipment) {
		dao.updateEquipment(equipment);
		
	}

	@Override
	public List<Equipment> getEquipmentByState(String state) {
		// TODO Auto-generated method stub
		return dao.getEquipmentByState(state);
	}

	@Override
	public List<Equipment> getEquipmentByKey(String state) {
		// TODO Auto-generated method stub
		return dao.getEquipmentByKey(state);
	}

	@Override
	public List<Affectation> trackEquipmentByAff(int idEquip) {
		// TODO Auto-generated method stub
		return dao.trackEquipmentByAff(idEquip);
	}

	@Override
	public List<State> trackEquipmentBySate(int idEquip) {
		// TODO Auto-generated method stub
		return dao.trackEquipmentBySate(idEquip);
	}

	@Override
	public Category addCategory(Category cat) {
		// TODO Auto-generated method stub
		return dao.addCategory(cat);
	}

	@Override
	public void updateCategory(Category cat) {
		// TODO Auto-generated method stub
		dao.updateCategory(cat);
		
	}

	@Override
	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		return dao.getAllCategory();
	}

	@Override
	public Category getCategory(int idCat) {
		// TODO Auto-generated method stub
		return dao.getCategory(idCat);
	}

	@Override
	public FeadBack addFeadBack(FeadBack feadBack, int idEquip) {
		// TODO Auto-generated method stub
		return dao.addFeadBack(feadBack, idEquip);
	}

	@Override
	public FeadBack getFeadback(int idEquip) {
		// TODO Auto-generated method stub
		return dao.getFeadback(idEquip);
	}

	@Override
	public List<FeadBack> getListFeadBackByEquip(int idEquip) {
		// TODO Auto-generated method stub
		return dao.getListFeadBackByEquip(idEquip);
	}

	@Override
	public Maintenance addMaitenance(Maintenance maintenance, int idEquip, int idUser) {
		// TODO Auto-generated method stub
		return dao.addMaitenance(maintenance, idEquip, idUser);
	}

	@Override
	public List<Maintenance> getAllMaintenanceByEquip(int idEquip) {
		// TODO Auto-generated method stub
		return dao.getAllMaintenanceByEquip(idEquip);
	}

	@Override
	public List<Maintenance> getAllMaintenanceByKey(String keyWord) {
		// TODO Auto-generated method stub
		return dao.getAllMaintenanceByKey(keyWord);
	}

	@Override
	public List<Maintenance> getMaintenanceByUser(int idUser) {
		// TODO Auto-generated method stub
		return dao.getMaintenanceByUser(idUser);
	}

	@Override
	public Besoin addBesion(Besoin besion, int idUser) {
		// TODO Auto-generated method stub
		return dao.addBesion(besion, idUser);
	}

	@Override
	public Besoin getBesion(int idBesion) {
		// TODO Auto-generated method stub
		return dao.getBesion(idBesion);
	}

	@Override
	public Affectation validateBesion(int idBesion) {
		// TODO Auto-generated method stub
		return dao.validateBesion(idBesion);
	}

	@Override
	public List<Besoin> BesionByParam(String Param) {
		// TODO Auto-generated method stub
		return dao.BesionByParam(Param);
	}

	@Override
	public List<Besoin> getAllBesion() {
		// TODO Auto-generated method stub
		return dao.getAllBesion();
	}

	@Override
	public List<Equipment> getAllEquipmet() {
		// TODO Auto-generated method stub
		return dao.getAllEquipmet();
	}

	@Override
	public List<Affectation> getAllAffectation() {
		// TODO Auto-generated method stub
		return dao.getAllAffectation();
	}

	@Override
	public List<Equipment> getAllAviableEquipmet() {
		// TODO Auto-generated method stub
		return dao.getAllAviableEquipmet();
	}


	@Override
	public List<Affectation> getAllAfByEquip(int IdEquip) {
		// TODO Auto-generated method stub
		return dao.getAllAfByEquip(IdEquip);
	}

	@Override
	public List<State> getStateByEquip(int idEquip) {
		// TODO Auto-generated method stub
		return dao.getStateByEquip(idEquip);
	}

	@Override
	public State addState(State state, int idEquip) {
		// TODO Auto-generated method stub
		return dao.addState(state, idEquip);
	}

	@Override
	public List<SimpleUser> getAllSimpleUserByKey(String mc) {
		// TODO Auto-generated method stub
		return dao.getAllSimpleUserByKey(mc);
	}

	@Override
	public List<Category> getCategoryByKey(String mc) {
		// TODO Auto-generated method stub
		return dao.getCategoryByKey(mc);
	}

	@Override
	public Maintenance getMaintenance(int idMaint) {
		// TODO Auto-generated method stub
		return dao.getMaintenance(idMaint);
	}

	@Override
	public List<SimpleUser> getSimpleUserByMail(String mail) {
		// TODO Auto-generated method stub
		return dao.getSimpleUserByMail(mail);
	}

	@Override
	public List<Affectation> getAllAfByKeyDate(String dateStart, String dateEnd) {
		// TODO Auto-generated method stub
		return dao.getAllAfByKeyDate(dateStart, dateEnd);
	}

	@Override
	public List<Equipment> getEquipmentByDate(String dateStart, String dateEnd) {
		// TODO Auto-generated method stub
		return dao.getEquipmentByDate(dateStart, dateEnd);
	}

	@Override
	public List<Maintenance> getAllMaintenanceByDate(String dateStart, String dateEnd) {
		// TODO Auto-generated method stub
		return dao.getAllMaintenanceByDate(dateStart, dateEnd);
	}

	@Override
	public List<Affectation> getAllAfByUser(int idUser) {
		// TODO Auto-generated method stub
		return dao.getAllAfByUser(idUser);
	}

	@Override
	public List<Besoin> BesionByUser(int idUser) {
		// TODO Auto-generated method stub
		return dao.BesionByUser(idUser);
	}

	@Override
	public Besoin updateBesoin(Besoin besoin) {
		// TODO Auto-generated method stub
		return dao.updateBesoin(besoin);
	}

	@Override
	public List<Besoin> BesionByDate (String dateStart, String dateEnd) {
		// TODO Auto-generated method stub
		return dao.BesionByDate(dateStart, dateEnd);
	}

	@Override
	public Planifications addPlan(Planifications planification) {
		// TODO Auto-generated method stub
		return dao.addPlan(planification);
	}

	@Override
	public List<Planification> listPlanBuyUser(int idUser) {
		// TODO Auto-generated method stub
		return dao.listPlanBuyUser(idUser);
	}

	@Override
	public void removePlan(Long idPlan) {
		dao.removePlan(idPlan);
		
	}

	@Override
	public List<Planification> listPlan() {
		// TODO Auto-generated method stub
		return dao.listPlan();
	}

	@Override
	public Planification addPlan(Planification planification) {
		// TODO Auto-generated method stub
		return dao.addPlan(planification);
	}

	@Override
	public List<Equipment> getEquipmentByPage(String statutEquip, int position, int nombreEquip) {
		// TODO Auto-generated method stub
		return dao.getEquipmentByPage(statutEquip, position, nombreEquip);
	}

	@Override
	public Planification getPlan(Long idPlan) {
		// TODO Auto-generated method stub
		return dao.getPlan(idPlan);
	}

	@Override
	public Planification updatePlan(Planification planification) {
		// TODO Auto-generated method stub
		return dao.updatePlan(planification);
	}
	

}
