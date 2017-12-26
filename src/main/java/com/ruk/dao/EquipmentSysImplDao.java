package com.ruk.dao;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

public class EquipmentSysImplDao implements IEquipmentSysDao{
	//-->> pour gérer la persistance des données on fait appel à JPA -->> on utilise  un Object du type Entity Manager
		// Ensuite il fautdrait spécifier l'anotation persistance contexe
		@PersistenceContext
		private EntityManager entityManager;
			
	@Override
	public Fournisseur addFournisseur(Fournisseur fournisseur) {
		// TODO Auto-generated method stub
		entityManager.persist(fournisseur);
		return fournisseur;
	}

	@Override
	public Notification addNotification(Notification notification) {
		System.out.println("--------SAVING******************");
		entityManager.persist(notification);
		return notification;
	}



	@Override
	public User addSimpleUser(SimpleUser user) {
		entityManager.persist(user);
		return user;
	}

	

	@Override
	public void upDateUser(User user) {
		entityManager.merge(user);
		
	}

	@Override
	public SimpleUser getSimpleUser(int idUser) {
		SimpleUser  simpleUser=entityManager.find(SimpleUser.class,idUser);
		return simpleUser;
	}

	@Override
	public List<SimpleUser> getAllSimpleUser() {
		
		Query query=entityManager.createQuery("SELECT s from SimpleUser s");
		return query.getResultList();
	}

	@Override
	public Fournisseur getFourniseur(int idUser) {
		Fournisseur  fournisseur=entityManager.find(Fournisseur.class,idUser);
		return fournisseur;
	}

	@Override
	public List<Fournisseur> getFourniseur() {

		Query query=entityManager.createQuery("SELECT s from Fournisseur s");
		return query.getResultList();
	}

	@Override
	public Notification addNotification(Notification notification, int idUser) {
		if(idUser ==0)throw new RuntimeException("Ooops.BIN...Selectionner Un Desstinateur"); // if no destination then Generate Exception
		if (idUser !=0){
			entityManager.merge(notification);			
		};
		return notification;			
		
	}

	@Override
	public Notification getNotification(int idUser) {
		Notification  notification=entityManager.find(Notification.class,idUser);
		return notification;
	}

	@Override
	public List<Notification> getNotificationList(int idUser) {

		Query query=entityManager.createQuery("SELECT nt FROM Notification  nt WHERE nt.m_SimpleUser=:x");
		query.setParameter("x",  idUser);	
		
		if (query.getResultList().isEmpty()) throw new RuntimeException("Vous avez 0 nitification");	
		return query.getResultList();
	}

	@Override
	public Notification delNotification(int idUser) {
		Notification macabee=entityManager.find(Notification.class, idUser);
		entityManager.remove(macabee);
		return null;
	}
	
	//-----AFFECCTATION

	@Override
	public Affectation addAffectation(Affectation affectation, int idEquip) {
	//	if (idEquip !=0) {
			entityManager.persist(affectation);
			/*
		}else throw new RuntimeException("..BIN..ERROR Vieller secetionune un Equipement..");
		*/
		return affectation;
	}

	@Override
	public void updateAffectation(Affectation notification) {
		entityManager.merge(notification);
	}

	@Override
	public List<Affectation> getAllAfByKeyWord(String keString) {
		Query query=entityManager.createQuery("SELECT rsc FROM Affectation rsc WHERE rsc.statusAff LIKE :x OR  rsc.year LIKE :x OR  rsc.dateAff LIKE :x ");
		query.setParameter("x", "%"+keString+"%");	

		if (query.getResultList().isEmpty()) throw new RuntimeException(".BIN.. Resultat Nos rétrouvable essayer de Changer d'othographe");	
		return query.getResultList();
	}
	
	@Override
	public List<Affectation> getAllAfByKeyDate(Date dateStart, Date dateEnd){
		
		Query req=entityManager.createQuery("select a from Affectation a where a.dateAff  between :x1 AND :x2 order by a.dateStartAff desc");
		//SELECT * FROM `affectation` WHERE dateAff between '2017-08-12' AND '2017-08-15' ORDER BY dateAff desc
		req.setParameter("x1", dateStart);
		req.setParameter("x2", dateEnd);

		if (req.getResultList().isEmpty()) throw new RuntimeException(".BIN.. Resultat Nos rétrouvable essayer de Changer d'othographe!!");	
		return req.getResultList();
		
	}
	
	@Override
	public List<Affectation> getAllAfByKeyDate(String dateStart, String dateEnd){
		
		Query req=entityManager.createQuery("select a from Affectation a where a.dateStartAff  between :x1 AND :x2 order by a.dateStartAff desc");
		//SELECT * FROM `affectation` WHERE dateAff between '2017-08-12' AND '2017-08-15' ORDER BY dateAff desc
		req.setParameter("x1", dateStart);
		req.setParameter("x2", dateEnd);
/*
		if (req.getResultList().isEmpty()) throw new RuntimeException(".BIN.. Vous avez 0 element de Rapport pendant cette periode!!");	
		*/
		return req.getResultList();
		
	}

	@Override
	public Affectation getAffectation(int idAff) {
		Affectation  affectation=entityManager.find(Affectation.class,idAff);
		return affectation;
	}

	@Override
	public Equipment addEquipment(Equipment equipment, int idCat, int idFourn) {		
		entityManager.persist(equipment);
		return equipment;
	}

	@Override
	public void updateEquipment(Equipment equipment) {
		entityManager.merge(equipment);
		
	}

	@Override
	public List<Equipment> getEquipmentByState(String state) {
										
		Query req=entityManager.createQuery("select e from Equipment e where e.nameState=:x");
		req.setParameter("x", state);
	//	if (req.getResultList().isEmpty()) throw new RuntimeException(".BIN.. Resultat Nos rétrouvable essayer de Changer d'othographe");	
		
		return req.getResultList();
	}

	@Override
	public List<Equipment> getEquipmentByKey(String state) {
		Query query=entityManager.createQuery("SELECT rsc FROM Equipment rsc WHERE  rsc.nameState LIKE :x OR  rsc.nameEquip LIKE :x OR  rsc.modelEquip LIKE :x OR  rsc.id LIKE :x ");
		query.setParameter("x", "%"+state+"%");	

		if (query.getResultList().isEmpty()) throw new RuntimeException(".BIN.. Resultat Nos rétrouvable essayer de Changer d'othographe");	
		return query.getResultList();
	}

	//---End Equip
	@Override
	public List<Affectation> trackEquipmentByAff(int idEquip) {
		
		Query query=entityManager.createQuery("SELECT m FROM Affectation  m WHERE m.idEquip=:x");
		query.setParameter("x",  idEquip);		
		return query.getResultList();
	}

	@Override
	public List<State> trackEquipmentBySate(int idEquip) {
		Query query=entityManager.createQuery("SELECT m FROM State  m WHERE m.m_Equipment=:x");
		query.setParameter("x",  idEquip);		
		return query.getResultList();
	}

	
	
	@Override
	public Category addCategory(Category cat) {
		entityManager.persist(cat);
		return cat;
	}

	@Override
	public void updateCategory(Category cat) {
		entityManager.merge(cat);
		
	}

	@Override
	public List<Category> getAllCategory() {
		Query query=entityManager.createQuery("SELECT m FROM Category m");
		return query.getResultList();
	}

	@Override
	public Category getCategory(int idCat) {
		Category  category=entityManager.find(Category.class,idCat);
		return category;
	}

	@Override
	public Equipment getEquipment(int idEquip) {
		Equipment  equipment=entityManager.find(Equipment.class,idEquip);
		return equipment;
	}
	@Override
	public FeadBack addFeadBack(FeadBack feadBack, int idEquip) {
	Equipment equipment=getEquipment(idEquip); // getting the Equippment
	
		feadBack.setM_Equipment(equipment); // binding Equippement to it		
		 entityManager.persist(feadBack);
		return feadBack;
	}

	@Override
	public FeadBack getFeadback(int idFead) {
		FeadBack  feadBack=entityManager.find(FeadBack.class,idFead);
		return feadBack;
	}

	@Override
	public List<FeadBack> getListFeadBackByEquip(int idEquip) {
		Query query=entityManager.createQuery("SELECT m FROM FeadBack  m WHERE m.m_Equipment=:x");
		query.setParameter("x",  idEquip);		
		return query.getResultList();
	}

	@Override
	public Maintenance addMaitenance(Maintenance maintenance, int idEquip, int idUser) {
		Equipment equipment=entityManager.find(Equipment.class, idEquip); // load the Equippement
		SimpleUser simpleUser=entityManager.find(SimpleUser.class, idUser); /// then load the User
		
		maintenance.setM_Equipment(equipment);
		maintenance.setM_SimpleUser(simpleUser);
		
		entityManager.persist(maintenance); // Then persit the Manetnace
		return null;
	}

	@Override
	public List<Maintenance> getAllMaintenanceByEquip(int idEquip) {
		
		Query query=entityManager.createQuery("SELECT m FROM Maintenance  m WHERE m.idEquip=:x");
		query.setParameter("x",  idEquip);		
		return query.getResultList();
		
	}

	@Override
	public List<Maintenance> getAllMaintenanceByKey(String keyWord) {

		Query query=entityManager.createQuery("SELECT rsc FROM Maintenance rsc WHERE rsc.descriptionProblem LIKE :x OR  rsc.solutionProblem LIKE :x");
		query.setParameter("x", "%"+keyWord+"%");	

		if (query.getResultList().isEmpty()) throw new RuntimeException("...0 Maintenace trouver Modifier les param pour une Meilleur resultat");	
		return query.getResultList();	
	}

	@Override
	public List<Maintenance> getMaintenanceByUser(int idUser) {
		Query query=entityManager.createQuery("SELECT m FROM Maintenance  m WHERE m.m_SimpleUser=:x");
		query.setParameter("x",  idUser);		
		return query.getResultList();
	}

	@Override
	public Besoin addBesion(Besoin besoin,int idUser ) {

		if (idUser !=0) { 
		
			SimpleUser simpleUser=entityManager.find(SimpleUser.class, idUser);// loading user
			besoin.setM_simpleUser(simpleUser); // then assign it to a Besion
		}		
		entityManager.persist(besoin);
		return besoin;
	}

	@Override
	public Besoin getBesion(int idBesion) {
		Besoin  besoin=entityManager.find(Besoin.class,idBesion);		
		return besoin;
	}

	@Override
	public Affectation validateBesion(int idBesion) {
		Besoin  besoin=entityManager.find(Besoin.class,idBesion); // first loading the Besoin
		entityManager.merge(besoin);// then updating it 
		return null;
	}

	@Override
	public List<Besoin> BesionByParam(String Param) {
		
		Query query=entityManager.createQuery("SELECT rsc FROM Besoin rsc WHERE rsc.date LIKE :x OR  rsc.category rsc.state LIKE :x");
		query.setParameter("x", "%"+Param+"%");	

		if (query.getResultList().isEmpty()) throw new RuntimeException(".BIN.. Resultat Nos rétrouvable essayer de Changer d'othographe");	
		return query.getResultList();	
	}

	@Override
	public List<Besoin> getAllBesion() {
		String state1="pending";
		
		Query query=entityManager.createQuery("SELECT m FROM Besoin m WHERE m.state=:x ORDER BY date DESC");
		query.setParameter("x",  state1);		
		
		return query.getResultList();
	}

	@Override
	public List<Equipment> getAllEquipmet() {
		Query query=entityManager.createQuery("SELECT m FROM Equipment m");
		return query.getResultList();
	}

	@Override
	public List<Affectation> getAllAffectation() {
		Query query=entityManager.createQuery("SELECT m FROM Affectation m  Order by  dateAff DESC");
		return query.getResultList();
	}

	@Override
	public List<Equipment> getAllAviableEquipmet() {
		String nameSate="aviable";
		Query query=entityManager.createQuery("SELECT m FROM Equipment m WHERE m.nameState=:x Order by  dateSave DESC" );
		query.setParameter("x",  nameSate);	
		
		if (query.getResultList().isEmpty()) throw new RuntimeException(" Vous avez 0 Equippement Disonnible!");	
		
		return query.getResultList(); 
	}

	@Override
	public  List<SimpleUser> getSimpleUserByMail(String mail) {
		Query query=entityManager.createQuery("SELECT m FROM SimpleUser m WHERE m.email=:x");
		query.setParameter("x",  mail);				
		return query.getResultList();
	}

	@Override
	public List<Affectation> getAllAfByEquip(int IdEquip) {
		if (IdEquip !=0) {
			Query query=entityManager.createQuery("SELECT m FROM Affectation  m WHERE m.idEquip=:x");
			query.setParameter("x",  IdEquip);	
			return query.getResultList();
		}
			
		return null;
	}

	@Override
	public List<State> getStateByEquip(int idEquip) {
		if (idEquip !=0) {
			Query query=entityManager.createQuery("SELECT m FROM State  m WHERE m.idEquip=:x");
			query.setParameter("x",  idEquip);	
			return query.getResultList();
		}
		return null;
	}

	@Override
	public State addState(State state, int idEquip) {
		entityManager.persist(state);
		return state;
	}

	@Override
	public List<SimpleUser> getAllSimpleUserByKey(String mc) {
		Query query=entityManager.createQuery("SELECT rsc FROM SimpleUser rsc WHERE  rsc.nameUser LIKE :x OR  rsc.phoneUser LIKE :x OR  rsc.email LIKE :x");
		query.setParameter("x", "%"+mc+"%");	

		if (query.getResultList().isEmpty()) throw new RuntimeException(".BIN.. Resultat Nos rétrouvable essayer de Changer d'othographe");	
		return query.getResultList();
	}

	@Override
	public List <Category> getCategoryByKey(String mc) {
		Query query=entityManager.createQuery("SELECT rsc FROM Category rsc WHERE  rsc.nameCat LIKE :x OR  rsc.description LIKE :x");
		query.setParameter("x", "%"+mc+"%");	

		if (query.getResultList().isEmpty()) throw new RuntimeException(".BIN.. Resultat Nos rétrouvable essayer de Changer d'othographe");	
		return query.getResultList();
	}

	@Override
	public Maintenance getMaintenance(int idMaint) {
		Maintenance  maintenance=entityManager.find(Maintenance.class,idMaint);
		
		return maintenance;
	}

	@Override
	public List<Equipment> getEquipmentByDate(String dateStart, String dateEnd) {

		Query req=entityManager.createQuery("select a from Equipment a where a.dateSave  between :x1 AND :x2 order by a.dateSave desc");
		//SELECT * FROM `affectation` WHERE dateAff between '2017-08-12' AND '2017-08-15' ORDER BY dateAff desc
		req.setParameter("x1", dateStart);
		req.setParameter("x2", dateEnd);
/*
		if (req.getResultList().isEmpty()) throw new RuntimeException(".BIN.. Vous avez 0 element de Rapport pendant cette periode!!");	
	*/
		return req.getResultList();
	}

	@Override
	public List<Maintenance> getAllMaintenanceByDate(String dateStart, String dateEnd) {

		Query req=entityManager.createQuery("select a from Maintenance a where a.date  between :x1 AND :x2 order by a.date desc");
		//SELECT * FROM `affectation` WHERE dateAff between '2017-08-12' AND '2017-08-15' ORDER BY dateAff desc
		req.setParameter("x1", dateStart);
		req.setParameter("x2", dateEnd);
/*
		if (req.getResultList().isEmpty()) throw new RuntimeException(".BIN.. Vous avez 0 element de Rapport pendant cette periode!!");	
	*/
		return req.getResultList();
	}

	@Override
	public List<Affectation> getAllAfByUser(int idUser) {
		Query query=entityManager.createQuery("SELECT m FROM Affectation  m WHERE m.idUser=:x");
		query.setParameter("x",  idUser);	
		
		if (query.getResultList().isEmpty()) throw new RuntimeException(".BIN.. Vous avez 0 Equipment Affecter !!");	
		
		return query.getResultList();
	}

	@Override
	public List<Besoin> BesionByUser(int idUser) {
		Query query=entityManager.createQuery("SELECT m FROM Besoin  m WHERE m.idUser=:x");
		query.setParameter("x",  idUser);	
		if (query.getResultList().isEmpty()) throw new RuntimeException(".BIN.. Vous avez 0 Besoins Exprimé !!");	
		
		return query.getResultList();
	}

	@Override
	public Besoin updateBesoin(Besoin besoin) {
		entityManager.merge(besoin);
		return besoin;
	}

	@Override
	public List<Besoin> BesionByDate(String dateStart, String dateEnd) {
		Query req=entityManager.createQuery("select a from Besoin a where a.date  between :x1 AND :x2 order by a.date desc");
		//SELECT * FROM `affectation` WHERE dateAff between '2017-08-12' AND '2017-08-15' ORDER BY dateAff desc
		req.setParameter("x1", dateStart);
		req.setParameter("x2", dateEnd);

		if (req.getResultList().isEmpty()) throw new RuntimeException(".BIN.. Resultat Nos rétrouvable essayer de Changer d'othographe!!");	
		return req.getResultList();
		
	}

	@Override
	public Planifications addPlan(Planifications planification) {
		// TODO Auto-generated method stub
		entityManager.persist(planification);
		return planification;
	}

	@Override
	public List<Planification> listPlanBuyUser(int idUser) {
		Query query=entityManager.createQuery("SELECT m FROM Planification  m WHERE m.idUser=:x");
		query.setParameter("x",  idUser);	
		if (query.getResultList().isEmpty()) throw new RuntimeException(".BIN.. Vous avez 0 taches Planification !!");	
		
		return query.getResultList();
	}

	@Override
	public void removePlan(Long idPlan) {

		Planification planification=entityManager.find(Planification.class, idPlan);// first charge the Planification
		entityManager.remove(planification);
		
	}

	@Override
	public List<Planification> listPlan() {
		String nameSate="simpleUser";
		Query query=entityManager.createQuery("SELECT m FROM Planification  m ");
	//	query.setParameter("x",  nameSate);	
		if (query.getResultList().isEmpty()) throw new RuntimeException(".BIN.. Vous avez 0 taches Planification !!");	
		
		return query.getResultList();
	}

	@Override
	public Planification addPlan(Planification planification) {
		// TODO Auto-generated method stub
				entityManager.persist(planification);
				return planification;
	}

	@Override
	public List<Equipment> getEquipmentByPage(String statutEquip,int position, int nombreEquip) {
		Query query=entityManager.createQuery("SELECT rsc FROM Equipment rsc WHERE  rsc.nameState=:x ORDER BY dateSave desc");
		query.setParameter("x", statutEquip);
		query.setFirstResult(position); /// position 
		query.setMaxResults(nombreEquip);
		if (query.getResultList().isEmpty()) throw new RuntimeException(".BIN..  Vous Avez 0 Equipment Identifier");	
		return query.getResultList();
	}

	@Override
	public Planification getPlan(Long idPlan) {
		// TODO Auto-generated method stub
		Planification planification=entityManager.find(Planification.class, idPlan);
		return planification;
	}

	@Override
	public Planification updatePlan(Planification planification) {
		entityManager.merge(planification);
		return planification;
	}


	
}
