package com.ruk.sid.controllers;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Principal;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.ImageIcon;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.hibernate.Hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.orm.hibernate3.support.BlobByteArrayType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.ruk.entity.Affectation;
import com.ruk.entity.Besoin;
import com.ruk.entity.Category;
import com.ruk.entity.Equipment;
import com.ruk.entity.FeadBack;
import com.ruk.entity.Maintenance;
import com.ruk.entity.Planification;
import com.ruk.entity.SimpleUser;
import com.ruk.entity.State;
import com.ruk.mettier.IEquipmentSysMettier;
import com.ruk.pdfView.CreatePdf;
import com.ruk.sid.form.AffectationWay;
import com.ruk.sid.form.RegisterEquipForm;
import com.ruk.sid.form.SearchForm;
import com.ruk.sid.form.VbootForm;


@Controller
@RequestMapping(value="/adminUser")
@SessionAttributes("profileInSession")
public class UserController {
	// pour faire l'injection de dépendance  de la couche métier via spring
		@Autowired
		private IEquipmentSysMettier mettier;
		
		 @Autowired
		    private JavaMailSender mailSender;  //for sending Mail
				
		private List<String> myString;

		@InitBinder // for sendin date into A form
		public final void initBinderUsuariosFormValidator(final WebDataBinder binder, final Locale locale) {
			final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", locale);
			binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		}

		@RequestMapping(value = "/index")
		public String index(Model model, Principal principal) {
			int size=0;		

			String name = principal.getName();
			List<Planification> planifications = null;

			List<SimpleUser> listUser = mettier.getSimpleUserByMail(name);
			if (listUser != null) {
				
				 try {
					 SimpleUser userSimple = listUser.get(0);
						System.out.println("..........." + name);
						 model.addAttribute("profileInSession", userSimple);
					 planifications = mettier.listPlanBuyUser(userSimple.getId());
						size = planifications.size();
						model.addAttribute("size", size);
						model.addAttribute("planList", planifications);
						model.addAttribute("editForm", new SearchForm());
				} catch (Exception e) {
					model.addAttribute("error", e.getMessage());
				}
				
			}

			// trying calculate the Date
			// String dateStart = "01/14/2012 09:29:58";

			Date dateOn;
			Planification planification;
			int counter1=0,counter2=0,removed=0;
			String titleT3="",tacheCurent="";
			
			myString = null;
		//	myString.clear();
			String[] recipientArray = new String[0];
		    if (size>0) {/// if we found som Planification then we can do
			
		    	
		    	
		    	  for (int i = 0; i <  planifications.size(); i++) { // trying to parcourt the list and testing day Calculator in it
			        	planification=planifications.get(i);
			        	
			      //  	System.out.println("**** XXL --" + planification.getDate());
			        	System.out.println(dateCalculator(planification.getDate()));
			        				//try making a condition
			        				if(dateCalculator(planification.getDate())==0) {
			        					counter1++;
			        					System.out.println("**Your Task is Today Boss --"+"\n tache Title"+planification.getTitle());
			        					tacheCurent +=planification.getTitle()+";";
			        					
			        				}else if (dateCalculator(planification.getDate())>=1 && dateCalculator(planification.getDate()) <=3) {
			        					
			        					counter2++;
			        					System.out.println("**Your Task is IN MORE THAN 3 DAYS Save Your time Big Boss --\n"+planification.getTitle());
			        					titleT3 +=planification.getTitle()+";";
			        					       					
			        				
									}else if (dateCalculator(planification.getDate())<0) { // if <0 then we have to remouve that Planification
										removed++;
										mettier.removePlan(planification.getId());
			      
									}
					}
			}
	      
	        
	        System.out.println("**DATA COUNTER ==0->"+counter1+"--Coute 2--<>"+counter2);
	    	
	    	System.out.println("**This is Title Recevied from Me ==0->\n"+titleT3 );
	    	
	    	
	    	if (counter1!=0 ||counter2!=0) {
	    		model.addAttribute("planToday", counter1);
	        	model.addAttribute("plan3Days", counter2);
			}else {
				model.addAttribute("planToday", 0);
	        	model.addAttribute("plan3Days", 0);
			} 
	    	model.addAttribute("thisArray", titleT3); 
	    	model.addAttribute("thisArrayCurent", tacheCurent);
	    	
			return "User/index";

		}
		
		
		@RequestMapping(value = "/editUser")
		public String editUser(Model model,	@ModelAttribute("profileInSession") @Valid SimpleUser user) {
			model.addAttribute("editUser", user);
			return "LogRegister/editUserFrom";

		}
		
		@RequestMapping(value = "/editUserBtn")
		public String editUserBtn(Model model,		@ModelAttribute("editUser")SimpleUser user,@ModelAttribute("profileInSession") @Valid SimpleUser userSession,HttpServletResponse response) {
			
			SimpleUser simpleUser=mettier.getSimpleUser(userSession.getId());	
			
			if (user.getPhoto()!=null) {
				simpleUser.setPhoto(user.getPhoto());
				System.out.println(" emptyPicture..");
			}
			simpleUser.setNameUser(user.getNameUser());
			simpleUser.setEmail(user.getEmail());
			simpleUser.setPassword(user.getPassword());
			simpleUser.setPhoneUser(user.getPhoneUser());
			simpleUser.setFunction(user.getFunction());
			simpleUser.setAdressUser(user.getAdressUser());
			simpleUser.setDescriptionUser(user.getDescriptionUser());
			
			
			//simpleUser.setDateSave(new Date());
			mettier.upDateUser(simpleUser);
			
			System.out.println(" updating Succes!");
			try {
				response.sendRedirect("index");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "LogRegister/editUserFrom";

		}

		
		
		@RequestMapping(value="/equipList")
		public String equipList(Model model,Principal principal){	
			String name = principal.getName();  	
			
			 List<SimpleUser> listUser=mettier.getSimpleUserByMail(name);
			 SimpleUser  userSimple=listUser.get(0);
			 if (listUser !=null) {
				
					System.out.println("..........." + name);
					model.addAttribute("profileInSession", userSimple);
			}
				
			try {
				List <Affectation> equipment=mettier.getAllAfByUser(userSimple.getId());
				model.addAttribute("AffectList",equipment );
				model.addAttribute("size",equipment.size() );
			} catch (Exception e) { // If lis Empty generate eror
				// TODO: handle exception
				model.addAttribute("error", e.getMessage());
				System.out.println("..........." + e.getMessage());
			}
			 
			model.addAttribute("editForm",new SearchForm());
			
			
			
			
			return "User/AffList";
			
		//	 return "User/index";
		 //	
		}
		
		
		@RequestMapping(value="/addBesoin")
		public String addBesoin(Model model,Principal principal){	
			String name = principal.getName();  	
			
			 List<SimpleUser> listUser=mettier.getSimpleUserByMail(name);
			 SimpleUser  userSimple=listUser.get(0);
			 if (listUser !=null) {
				
					System.out.println("..........." + name);
					model.addAttribute("profileInSession", userSimple);
			}
				
			try {
				model.addAttribute("AffectList", mettier.getAllAfByUser(userSimple.getId()));
				
			} catch (Exception e) { // If lis Empty generate eror
				// TODO: handle exception
				model.addAttribute("error", e.getMessage());
				System.out.println("..........." + e.getMessage());
			}
			 
			model.addAttribute("editForm",new SearchForm());
			
			 return "User/index";
		 	
		}
			
		/// Modifier Equipment
		
		// try updating Equipment Info
		

		///displaying picthuhfre from DB to the View
			@RequestMapping(value="/photoEquip",produces=MediaType.IMAGE_JPEG_VALUE)// on doit returner une img JPEG >>produce
		@ResponseBody// la repose sera envoyer ds le corm dela repo http
			public byte[] photoEquip1(@RequestParam("idEquip")int idEquip) throws IOException{		
			Equipment eq=mettier.getEquipment(idEquip);// o cherche la cat
					
			if (eq.getPhoto() !=null) {				
				return IOUtils.toByteArray( new ByteArrayInputStream(eq.getPhoto()));// la photo se trouve ds la db et pout le recuperer IOUtils
			}
			return null;
			}	
			
			@RequestMapping(value="/photoCat",produces=MediaType.IMAGE_JPEG_VALUE)// on doit returner une img JPEG >>produce
			@ResponseBody// la repose sera envoyer ds le corm dela repo http
				public byte[] photoCat(@RequestParam("idCat")int idCat) throws IOException{		
				Category eq=mettier.getCategory(idCat);// o cherche la cat
				
				if (eq.getPhoto() !=null) {				
					return IOUtils.toByteArray( new ByteArrayInputStream(eq.getPhoto()));// la photo se trouve ds la db et pout le recuperer IOUtils
				}
				return null;
			}	
			

			@RequestMapping(value="/photoProfile",produces=MediaType.IMAGE_JPEG_VALUE)// on doit returner une img JPEG >>produce
			@ResponseBody// la repose sera envoyer ds le corm dela repo http
				public byte[] photoProfile(@RequestParam("idUser")int idUser) throws IOException{		
			
				SimpleUser simpleUser=mettier.getSimpleUser(idUser);
				if (simpleUser.getPhoto() !=null) {				
					return IOUtils.toByteArray( new ByteArrayInputStream(simpleUser.getPhoto()));// la photo se trouve ds la db et pout le recuperer IOUtils
				}
				return null;
					}	
			
		
			
			@RequestMapping(value="/search")
			public String search(Model model,@ModelAttribute("searForm")@Valid SearchForm searchForm){
								
				model.addAttribute("searForm", new SearchForm());
					 
		//	 return "registerCat";
				return "simpleUser/search";
			}
			
			@RequestMapping(value="/searchBtnAvd")
			public String searchBtnAvdanced(Model model,@ModelAttribute("searForm")@Valid SearchForm searchForm){
								
				try {
					model.addAttribute("searRsUser", mettier.getAllSimpleUserByKey(searchForm.getMc()));
				} catch (Exception e) {
					// TODO: handle exception
					model.addAttribute("error",e.getMessage());
				}
				
				model.addAttribute("idEquip",searchForm.getIdEquip());
				model.addAttribute("idUser",searchForm.getIdUser());
				System.out.println("--idEQUIp AVD-?>"+searchForm.getIdEquip());
				model.addAttribute("searcheUser", "User ");
				model.addAttribute("searForm", searchForm);
					 
		//	 return "registerCat";
				return "simpleUser/searchAdvancedAff";
			}
			
			@RequestMapping(value="/searchBtn")
			public String searchBtn(Model model,@ModelAttribute("searForm")@Valid SearchForm searchForm){
				
				
				if (searchForm.getPage().equals("tracking")) { // if requeste com from Tracking Module
					
					try {
						System.out.println("......YES Trackin Module Actived....." );
						
						model.addAttribute("searForm", new SearchForm());
						model.addAttribute("searRs", mettier.getEquipmentByKey(searchForm.getMc()));
						model.addAttribute("searcheTrack", "youare ");
						return "simpleUser/search";
					} catch (Exception e) {
						model.addAttribute("error", e.getMessage());
						return "simpleUser/search";
					}
									
				}
				
				
				else if (searchForm.getPage().equals("search")) { // if requeste com from Tracking Module
						
							 if(searchForm.getParam().equals("Equipment")) {
								 
								 try {
																			
										model.addAttribute("searForm", new SearchForm());
										model.addAttribute("searRs", mettier.getEquipmentByKey(searchForm.getMc()));
										model.addAttribute("searcheP", "youare ");
										return "simpleUser/search";
									} catch (Exception e) {
										model.addAttribute("error", e.getMessage());
										return "simpleUser/search";
									}
							
							}
								else if(searchForm.getParam().equals("Utulisateur")) {
									
									 try {
											
											model.addAttribute("searForm", new SearchForm());
											
											model.addAttribute("searRsUser", mettier.getAllSimpleUserByKey(searchForm.getMc()));
											model.addAttribute("searcheUser", "User ");
											return "simpleUser/search";
										} catch (Exception e) {
											model.addAttribute("error", e.getMessage());
											return "simpleUser/search";
										}
									 
								
							}else if(searchForm.getParam().equals("Category")) {
								 try {
										
										model.addAttribute("searForm", new SearchForm());
									//	model.addAttribute("searRs", mettier.getEquipmentByKey(searchForm.getMc()));
										return "simpleUser/search";
									} catch (Exception e) {
										model.addAttribute("error", e.getMessage());
										return "simpleUser/search";
									}
								
							}	else if(searchForm.getParam().equals("Maintenance")) {
								 try {
										
										model.addAttribute("searForm", new SearchForm());
										
										model.addAttribute("searRsMain", mettier.getAllMaintenanceByKey(searchForm.getMc()));
										model.addAttribute("destinPage",searchForm.getDestinPage());
										System.out.println("--DEST_serache_GETPARAM btn->"+searchForm.getDestinPage());
										model.addAttribute("searcheMain", "Maintence");
										return "simpleUser/search";
									} catch (Exception e) {
										model.addAttribute("error", e.getMessage());
										return "simpleUser/search";
									}
								
							}					
								
									
				}else if(searchForm.getPage().equals("MaintAdd")) {// If request came from Maint or FeadBack Add
					
					model.addAttribute("destinPage",searchForm.getDestinPage());
					System.out.println("--DEST_serache_btn->"+searchForm.getDestinPage());
					model.addAttribute("searFormAdv",searchForm.getParam());
					model.addAttribute("searFormAdv",new SearchForm());
					return "simpleUser/searchAdvanced";
					
				} else if(searchForm.getPage().equals("MaintAddRs")) {// this One is for the Maint and Equip Serche
								
					 try {
							
							model.addAttribute("searForm", new SearchForm());
							model.addAttribute("searRs", mettier.getEquipmentByKey(searchForm.getMc()));
							model.addAttribute("destinPage",searchForm.getDestinPage());
							model.addAttribute("searcheP", "youare ");
							return "simpleUser/searchAdvanced";
						} catch (Exception e) {
							model.addAttribute("error", e.getMessage());
							return "simpleUser/searchAdvanced";
						}
				//	model.addAttribute("searFormAdv",new SearchForm());
				//
					
				}else if (searchForm.getPage().equals("AffAdd")) {
					model.addAttribute("idEquip",searchForm.getIdEquip());
					System.out.println("..Coming from AfectedPageCmd"+searchForm.getIdEquip());
					return "simpleUser/searchAdvancedAff";
				} 

				model.addAttribute("searForm", new SearchForm());
					 
		//	 return "registerCat";
				return "simpleUser/trackIndex";
			}
			
			
			@RequestMapping(value="/rsDetails")
			public String rsEquipDetaail(Model model,@ModelAttribute("searForm") SearchForm searchForm ){			
			
				if (searchForm.getParam().equals("equip")) {
					
				 model.addAttribute("equipDetails", mettier.getEquipment(searchForm.getIdEquip()));
					
					System.out.print("-DesstinationPage-"+searchForm.getDestinPage());
							if (searchForm.getDestinPage().equals("feadBack")) {
								model.addAttribute("feadBack",searchForm.getDestinPage() );
							}if (searchForm.getDestinPage().equals("Maintenance")) {
								model.addAttribute("Maintenance",searchForm.getDestinPage() );
							}
				
				
					//model.addAttribute("Maint", searchForm.getDestinPage() );
					return "templatesViewsRs/rsEquipDetail";
					
				}if (searchForm.getParam().equals("user")) {
					model.addAttribute("userDetails", mettier.getSimpleUser(searchForm.getIdEquip()));
					return "templatesViewsRs/rsUserDetail";
					
				} else if(searchForm.getParam().equals("maint"))  { 
					model.addAttribute("maintDetails", mettier.getMaintenance(searchForm.getIdEquip()));
					return "templatesViewsRs/rsMaintenanceDetail";
				}
				
					 
		//	 return "registerCat";
				return "templatesViewsRs/rsEquipDetail";
			}
			
			@RequestMapping(value="/besoins")
			public String besoins(Model model,@ModelAttribute("searForm")@Valid SearchForm searchForm,Principal principal){	
				
				String name = principal.getName();  	
				
				 List<SimpleUser> listUser=mettier.getSimpleUserByMail(name);
				 SimpleUser  userSimple=listUser.get(0);
				 if (listUser !=null) {
									 
					 try {
						 List<Besoin> besoins=mettier.BesionByUser(userSimple.getId());
							int size=besoins.size();
							
							model.addAttribute("size", size);
							model.addAttribute("besoinDetails", besoins);
					} catch (Exception e) {
						// TODO: handle exception
						model.addAttribute("error", e.getMessage());
					}
				
				 } 
				model.addAttribute("searForm", new SearchForm());
					 
		//	 return "registerCat";
				return "User/besoinList";
			}
						
			@RequestMapping(value="/addMaintBtn")
			public String planingListB(Model model,@ModelAttribute("maintenance")@Valid Maintenance maintenance,@ModelAttribute("searForm")@Valid SearchForm searchForm){	
				Date curDate = new Date();
				SimpleDateFormat format = new SimpleDateFormat();
			
				        String DateToStr = format.format(curDate);			
				        System.out.println("Default pattern: " + DateToStr);		 
				
				        format = new SimpleDateFormat("yyyy-MM-dd");
				
				        DateToStr = format.format(curDate);		
				
				Equipment equipment1=mettier.getEquipment(maintenance.getIdEquip());
				if (equipment1==null) {
					
					model.addAttribute("error","..l'ID  Invalide Cette Equippment n'existe pas!!");
					model.addAttribute("maintenance", new Maintenance());
					return "templatesViewsAdd/MaintenanceAdd";
				}else {
					mettier.addMaitenance(new Maintenance(maintenance.getIdEquip(),maintenance.getIdUser(),maintenance.getTitle(),
							maintenance.getNameUser(),equipment1.getNameEquip(),maintenance.getDescriptionProblem(),maintenance.getSolutionProblem(),"REP",DateToStr),1,1);
				
					
					try {
						model.addAttribute("EquipmentList", mettier.getAllAviableEquipmet());
						
					} catch (Exception e) { // If lis Empty generate eror
						// TODO: handle exception
						model.addAttribute("error", e.getMessage());
						
					}
					
					model.addAttribute("searForm",new SearchForm());
					model.addAttribute("editForm",new SearchForm());
					
					 return "simpleUser/index";
					
				}
				
			
				 
			//	model.addAttribute("editForm",new SearchForm());
				
				// return "simpleUser/index";
					 

			}
			
			@RequestMapping(value="/addMaintenanceBtn")
			public String addMaintenanceBtn(Model model,@ModelAttribute("maintenance")@Valid Maintenance maintenance){			
				Equipment equipment1=mettier.getEquipment(maintenance.getIdEquip());
				if (equipment1==null) {
					System.out.println(" .FUCKING ID EQUIP.......");
				}
				
				//model.addAttribute("simpleUserList", mettier.getAllSimpleUser());
			
				model.addAttribute("maintenance", new Maintenance());
					 
		//	 return "registerCat";
				return "templatesViewsAdd/MaintenanceAdd";
			}
			
		
			
			@RequestMapping(value="/addBesion")
			public String addBesion(Model model){			
				
				model.addAttribute("CategoryList", mettier.getAllCategory());
			
				model.addAttribute("addBesion", new Besoin());
					 
		//	 return "registerCat";
				return "User/BesoinAdd";
			}
			
						
			@RequestMapping(value="/addBesionBtn")
			public String addBesionBtn(Model model,@ModelAttribute("maintenance")@Valid Besoin besoin){	
				SendEmailController sendEmailController = new SendEmailController();
				String sub = "EquipSys Besoin Data Confirmation";
				Date curDate = new Date();
				SimpleDateFormat format = new SimpleDateFormat();
			
				        String DateToStr = format.format(curDate);			
				        System.out.println("Default pattern: " + DateToStr);		 
				
				        format = new SimpleDateFormat("yyyy-MM-dd");
				
				        DateToStr = format.format(curDate);	
				    	mettier.addBesion(new Besoin(besoin.getTitle(), besoin.getCategory(), besoin.getMotif(),
								besoin.getDescription(), DateToStr, "pending", "", besoin.getNameUser(), besoin.getIdUser(),besoin.getEmail()),
								besoin.getIdUser());
				// Then After that I need to send An Email Confirmation
				
				// first sendin mail
				String message="EquipSys Besoin  Confimation Notification \t \n-------------------------------------------------\n Bonjour\t"+besoin.getNameUser()+"!\n"+
						" Nou vous confirmons de la prise en compte de votre besoin Exprimé en date du "+ new Date()+" !\n============================"+
						"\n Titre: "+besoin.getTitle()+"\n Description: "+besoin.getDescription()+
						"\n============================================================================\n Nous allons Traiter votre  requete prochenement: \n "+
						"\n============================================================================\n Genereted buy EquipSys BIN-PC on "+ new Date()+"\n ---------------------------------------------------------------";
				
System.out.println(message);
								
							try {
										sendEmailController.doSendEmail(besoin.getEmail(), sub, message, mailSender);
									} catch (Exception e) {
										model.addAttribute("error", "impossible d'envoyer un Mail de confirmatiom car Error de connection Internet!");
										
										System.out.println(e.getMessage());
									}
									/// then tryin to redire to besoins List
									
									
									 try {
										 List<Besoin> besoins=mettier.BesionByUser(besoin.getIdUser());
											int size=besoins.size();
											
											model.addAttribute("size", size);
											model.addAttribute("besoinDetails", besoins);
											model.addAttribute("searForm", new SearchForm());
									} catch (Exception e) {
										// TODO: handle exception
										model.addAttribute("error", e.getMessage());
									}
					return "User/besoinList";
					}
			
			@RequestMapping(value="/addfeadBack")
			public String addfeadBack(Model model,@ModelAttribute("searForm")@Valid SearchForm searchForm,Principal principal){	
				System.out.println("IDE->"+searchForm.getIdEquip());					
						String name = principal.getName();  	
						
						 List<SimpleUser> listUser=mettier.getSimpleUserByMail(name);
						 SimpleUser  userSimple=listUser.get(0);
						 if (listUser !=null) {
					
							 try {
									List <Affectation> equipment=mettier.getAllAfByUser(userSimple.getId());
									model.addAttribute("AffectList",equipment );
									model.addAttribute("size",equipment.size() );
								} catch (Exception e) { // If lis Empty generate eror
									// TODO: handle exception
									model.addAttribute("error", e.getMessage());
									System.out.println("..........." + e.getMessage());
								}	
							 
						}
						model.addAttribute("editForm",new SearchForm());
						return "User/FeadBackAdd";
			}
			
			@RequestMapping(value="/addFeadBackBtn")
			public String addfeadBackBtn(Model model,@ModelAttribute("addFeadBack")@Valid FeadBack feadBack,Principal principal){	
				
				Equipment equipment=mettier.getEquipment(feadBack.getIdEquip());
				
				if (equipment==null) {
					model.addAttribute("error","..ERROR  l'ID de cette Equipement n'Existe Pas" );
					model.addAttribute("addFeadBack", new FeadBack());
					return "templatesViewsAdd/FeadBackAdd";
				}else {
					
					mettier.addFeadBack(new FeadBack(feadBack.getDescriptionFead(), feadBack.getTypeFead(), feadBack.getNameUser(), feadBack.getIdUser(), feadBack.getIdEquip(), new Date()), feadBack.getIdEquip());
					
				}
						
				// After that we rerutn to affeftect Equip
				
				
				String name = principal.getName();  	
				
				 List<SimpleUser> listUser=mettier.getSimpleUserByMail(name);
				 SimpleUser  userSimple=listUser.get(0);
				 if (listUser !=null) {
					
						System.out.println("..........." + name);
						model.addAttribute("profileInSession", userSimple);
				}
					
				try {
					List <Affectation> equipment2=mettier.getAllAfByUser(userSimple.getId());
					model.addAttribute("AffectList",equipment2 );
					model.addAttribute("size",equipment2.size() );
				} catch (Exception e) { // If lis Empty generate eror
					// TODO: handle exception
					model.addAttribute("error", e.getMessage());
					System.out.println("..........." + e.getMessage());
				}
				 
				model.addAttribute("editForm",new SearchForm());
				
				
				
				
				return "User/AffList";
				
				
			}
		
			
			
			@RequestMapping(value = "/addfeadForm")
			public String addfeadBack(Model model, @ModelAttribute("editForm") @Valid SearchForm searchForm) {
				System.out.println("IDE->" + searchForm.getIdEquip());
				if (searchForm.getIdEquip() > 0) {
					model.addAttribute("idEquip", searchForm.getIdEquip());

				} else if (searchForm.getIdEquip() == 0) {
					model.addAttribute("btnSearch", "desapear");
				}

				model.addAttribute("searForm", new SearchForm());
				model.addAttribute("addFeadBack", new FeadBack());
				return "User/FeadAddForm";
			}
			
			@RequestMapping(value="/affFinaleDetail")
			//public String affFinaleDetail(Model model,@RequestParam("idAff")int idAff){
			
			public String affFinaleDetail(@ModelAttribute("editForm") SearchForm form , Model model){
				
				System.out.println("******opening AffectedPage DEtailsFinal Page*********" );
			//	model.addAttribute("registerAffect", new AffectationWay());
				/// cheking All Equip disponnible			
				Affectation affectation=mettier.getAffectation(form.getIdEquip());
				
				int idUser=affectation.getIdUser();
				
				System.out.println("--DATA----"+idUser);
				Equipment equipment=mettier.getEquipment(affectation.getIdEquip());
				SimpleUser simpleUser= new SimpleUser();
				
					simpleUser=mettier.getSimpleUser(idUser);
				System.out.println("--DATA---->>"+simpleUser.getEmail());
				//String mail=
				
				//String  idEquip=affectation.getNameAffected();
				
				
				model.addAttribute("EquipDetail", equipment);
				model.addAttribute("UserDetail", simpleUser);
				model.addAttribute("UserMail", simpleUser.getEmail().toString());
				model.addAttribute("UserName", simpleUser.getNameUser().toString());
				model.addAttribute("UserPhone", simpleUser.getPhoneUser().toString());
				model.addAttribute("UserFunction", simpleUser.getFunction().toString());
				
				
				model.addAttribute("AffectS", affectation.getDateAff().toString());
				model.addAttribute("AffectE", affectation.getDateEndAff().toString());
				
				model.addAttribute("Affectation", affectation);
				return "User/AffCDetails";
			// return "rsAffectationDetail";
			}
			
			@RequestMapping(value = "/addPlan")
			public String addPlan(Model model, Principal principal) {
				String name = principal.getName();
				System.out.print("....Addding Plan." + name);
				model.addAttribute("addPlan", new Planification());

				return "User/PlanAdd";

			} // addPlanBtn

			
			@RequestMapping(value = "/planDetail")
			public String planDetail(Model model, Principal principal, @ModelAttribute("editForm") @Valid SearchForm searchForm) {
				String name = principal.getName();
				System.out.print("....Addding Plan." + name);
				model.addAttribute("addPlan", new Planification());

				return "User/planDetail";

			} // addPlanBtn

			
			@RequestMapping(value = "/addPlanBtn")
			public String addPlanBtn(Model model, Principal principal,
					@ModelAttribute("addPlan") @Valid Planification planification, BindingResult bindingResult,HttpServletResponse response) throws IOException {
				if (bindingResult.hasErrors()) {

					model.addAttribute("addPlan", planification);
					return "LogRegister/registerSimpleUser";

				}

				try {
					
					
					mettier.addPlan(new Planification( planification.getIdUser(),
							planification.getNameUser(), planification.getTitle(), planification.getDescription(),
							planification.getDate(), planification.getType(), planification.getEmail(),"user","waitting"));

				} catch (Exception e) {
					model.addAttribute("error", e.getMessage());
					e.printStackTrace();
				}

				/// trying to SEND EMAIL TO TE USER FOR HIS DATA
				// initial date Info to be sent

				 SendEmailController sendEmailController=new SendEmailController();

				String sub = "EquipSys Planification  Information ";

				String message = "EquipSys Data info \t \n*********************************************************************************\n Bonjour\t"
						+ planification.getNameUser() + "!\n"
						+ "Votre Tache de Planification  dans EquipSystem a etait Sauvergarder Avec Succes\n============================================================================"
						+ "\n TITRE  :\t " + planification.getTitle() + "\n DESCRIPTION: \t" + planification.getDescription()
						+ " \n DATE EXECUTION: \t" + planification.getDate()
						+ "\n============================================================================\n Gerereted buy Equip Sys BIN-PC \t "
						+ new Date() + "\n============================================================================\n";

				System.out.println(message);
				
				
				try {
					sendEmailController.doSendEmail(planification.getEmail(), sub, message,mailSender);
} catch (Exception e) {
					model.addAttribute("error", "impossible d'envoyer un Mail de confirmatiom car Error de connection Internet!");
				}
				
				
				model.addAttribute("searForm", new SearchForm());

				// return "registerCat"; 
				
				//then Redirect to
				response.sendRedirect("planList");
				return "templatesViewsAdd/PlanAdd";

			}
			
			
			@RequestMapping(value = "/planList")
			public String planList(Model model, HttpServletRequest httpServletRequest, Principal principal) {
				int size;
				System.out
						.println("******opening Plan List Page* PPP********" + httpServletRequest.getAttribute("equipAffSession"));

				List<Planification> planifications = null;
				List<SimpleUser> listUser= null;
				System.out.println(".....LODAED.WWWWWWWWWWWWW.....");
				try {
					String name = principal.getName();					;

					listUser= mettier.getSimpleUserByMail(name);
					System.out.println(".....LODAED.XXXXXXXXXX....." + name);
					if (listUser != null) {
						SimpleUser userSimple = listUser.get(0);
						System.out.println("..........." + name);
						// model.addAttribute("profileInSession", userSimple);
						planifications = mettier.listPlanBuyUser(userSimple.getId());
						size = planifications.size();
						model.addAttribute("size", size);
						model.addAttribute("planList", planifications);
					}
				} catch (Exception e) {
					model.addAttribute("error", e.getMessage());

					
				}
				
				
					
					model.addAttribute("editForm", new SearchForm());
				
		    	
				return "User/PlanList";
			}
			
			@RequestMapping(value="/feadBackD")
			//public String affFinaleDetail(Model model,@RequestParam("idAff")int idAff){
			
			public String feadBackD(@ModelAttribute("editForm") SearchForm form , Model model){
				Equipment equipment=mettier.getEquipment(form.getIdEquip());
				
				System.out.println("--ID->"+form.getIdEquip()+"--EQUI NA-"+equipment.getNameEquip());
				model.addAttribute("equipDetails", equipment);
			
				return "User/rsFeadDetail";
			// return "rsAffectationDetail";
			}			
		

			/// Date Calculator
			
			public long dateCalculator(Date dateStop) {

				Date curDate = new Date();
				long diffDays = 0;
				SimpleDateFormat format = new SimpleDateFormat();

				String DateToStr = format.format(curDate);
			//	System.out.println("Default pattern: " + DateToStr);

				format = new SimpleDateFormat("yyyy-MM-dd");

				DateToStr = format.format(curDate);

				String dateStart = DateToStr;
				// String dateStop = "2017-08-22 17:31:48";

				// HH converts hour in 24 hours format (0-23), day calculation
				// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				Date d1 = null;
				Date d2 = null;

				try {
					d1 = format.parse(dateStart);
				//	d2 = format.parse(dateStop);
					
					d2 = dateStop;
					// in milliseconds
					long diff = d2.getTime() - d1.getTime();

					long diffSeconds = diff / 1000 % 60;
					long diffMinutes = diff / (60 * 1000) % 60;
					long diffHours = diff / (60 * 60 * 1000) % 24;
					diffDays = diff / (24 * 60 * 60 * 1000);

					System.out.println(diffDays + " days, ");
				//	System.out.print(diffHours + " hours, ");
				//	System.out.print(diffMinutes + " minutes, ");
			//		System.out.print(diffSeconds + " seconds.");

				} catch (Exception e) {
					e.printStackTrace();
				}
				return diffDays;

			}
			
			@RequestMapping(value = "/besoinsBtn")
			public String besoinsBtn(Model model, @ModelAttribute("searForm") @Valid SearchForm searchForm,
					HttpServletResponse response) throws IOException {
				model.addAttribute("besoinDetails", mettier.getAllBesion());
				model.addAttribute("searForm", new SearchForm());

				if (searchForm.getPage().equals("filtrerB")) {
					System.out.println("--This is Your Data+" + searchForm.getDate1() + "--" + searchForm.getDate2());
					try {
						List<Besoin> besoins = mettier.BesionByDate(searchForm.getDate1(), searchForm.getDate2());

						model.addAttribute("besoinDetails", besoins);
						int size = besoins.size();

						model.addAttribute("size", size);
						response.sendRedirect("besoins");
					} catch (Exception e) {
						model.addAttribute("error", e.getMessage());
					}

				}
				model.addAttribute("searForm", searchForm);
				response.sendRedirect("besoins");
				return "simpleUser/besoinList";
			}
	 

	}
