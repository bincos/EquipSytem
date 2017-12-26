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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
@SessionAttributes({ "nameUser", "profileInSession", "equipAffSession", "besionInS" })
@RequestMapping(value = "/adminSimpleUser")
public class SimpleUserController {

	// private static String FILE = "C:/PDF/iTextHelloWorld.pdf";
	// Try saving the file in the temp One

	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

	// pour faire l'injection de dépendance de la couche métier via spring
	@Autowired
	private IEquipmentSysMettier mettier;

	@Autowired
	private JavaMailSender mailSender; // for sending Mail
	private static URL logoPath;
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
			SimpleUser userSimple = listUser.get(0);
			System.out.println("..........." + name);
			 model.addAttribute("profileInSession", userSimple);
			 
			 try {
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
		String titleT3="",tacheCurent="",idPlan1="",idPlan2="",idPlanExp="",tacheExpired="";
		
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
        					idPlan1 +=planification.getId()+";";
        					
        				}else if (dateCalculator(planification.getDate())>=1 && dateCalculator(planification.getDate()) <=3) {
        					
        					counter2++;
        					System.out.println("**Your Task is IN MORE THAN 3 DAYS Save Your time Big Boss --\n"+planification.getTitle());
        					titleT3 +=planification.getTitle()+";";
        					idPlan2 +=planification.getId();       					
        				
						}else if (dateCalculator(planification.getDate())<0) { // if <0 then we have to remouve that Planification
							tacheExpired  +=planification.getTitle()+";";
							idPlanExp +=planification.getId()+";";
							removed++;
							System.out.println("**Expired Task -->>"+ removed+"<-DATA->"+tacheExpired);
						//	mettier.removePlan(planification.getId());
      
						}
		}
		  }
        System.out.println("**DATA COUNTER ==0->"+counter1+"--Coute 2--<>"+counter2 +"<-- ID splited-->>"+idPlan1);
    	
    	System.out.println("**This is Title Recevied from Me ==0->\n"+titleT3 );
    	
    	
    	if (counter1!=0 ||counter2!=0 || removed!=0) {
    		model.addAttribute("planToday", counter1);
        	model.addAttribute("plan3Days", counter2);
        	model.addAttribute("expired", removed);
		}else {
			model.addAttribute("planToday", 0);
        	model.addAttribute("plan3Days", 0);
        	model.addAttribute("expired", removed);
		} 
    	
    	model.addAttribute("thisArray", titleT3); 
    	model.addAttribute("thisArrayCurent", tacheCurent);
    	model.addAttribute("idPlanExp", idPlanExp);
    	model.addAttribute("idPToday", idPlan1);
    	model.addAttribute("idP3Day", idPlan2);
    	
    	model.addAttribute("tacheExpired", tacheExpired);
		return "simpleUser/index";

	}
	
	/// Equip List oon simple User template
	@RequestMapping(value = "/equipList")
	public String equipList(Model model, Principal principal ,SearchForm searchForm) {
		String name = principal.getName();
		int position=0,numbrePage=0,numbreLigne=70,page=0,nombreElement=0;
		
		System.out.println("......XXXX....." + searchForm.getIdEquip() +"XXXX->");
		List<SimpleUser> listUser = mettier.getSimpleUserByMail(name);
		if (listUser != null) {
			SimpleUser userSimple = listUser.get(0);
			System.out.println("..........." + name);
			model.addAttribute("profileInSession", userSimple);
		}
		

		try {
			
			// first trying to de fne the position
			List<Equipment> equipment = mettier.getAllAviableEquipmet();
			nombreElement=equipment.size();
			numbrePage=(nombreElement/numbreLigne)+1; // to get numbre page
			System.out.println("...NB Element......" + nombreElement);
			System.out.println("...NB PAGE......" + numbrePage);
			position= numbreLigne*numbrePage;
			System.out.println("....N Position...." + position);
			List<Equipment> equipment2=mettier.getEquipmentByPage("aviable",searchForm.getIdEquip() ,numbreLigne ); ///chatger de 0  juska 2
		//	List<Equipment> equipment2=mettier.getEquipmentByPage("aviable", position,numbreLigne ); ///chatger de 0  juska 2
			
			
			System.out.println(".....TEST PAGE......>" + equipment2.size());

			model.addAttribute("nombreElement",nombreElement);
			model.addAttribute("nombrePage",numbrePage);
			model.addAttribute("page",page);
			model.addAttribute("size", nombreElement);
			model.addAttribute("EquipmentList", equipment2);

		} catch (Exception e) { // If lis Empty generate eror
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
			System.out.println("..........." + e.getMessage());
		}

		model.addAttribute("editForm", new SearchForm());

		return "simpleUser/listEquip";

	}
	
	@RequestMapping(value = "/equipListParam")
	public String equipListParam(Model model, Principal principal ,@ModelAttribute("idPage") int idPage) {
		String name = principal.getName();
		int position=0,numbrePage=0,numbreLigne=70,nombreElement=0;
		
		System.out.println("......XXXX....." +"XXXX->" +idPage);
		List<SimpleUser> listUser = mettier.getSimpleUserByMail(name);
		if (listUser != null) {
			SimpleUser userSimple = listUser.get(0);
			System.out.println("..........." + name);
			model.addAttribute("profileInSession", userSimple);
		}
		

		try {
			
			// first trying to de fne the position
			List<Equipment> equipment = mettier.getAllAviableEquipmet();
			nombreElement=equipment.size();
			numbrePage=(nombreElement/numbreLigne)+1; // to get numbre page
			System.out.println("...NB Element......" + nombreElement);
			System.out.println("...NB PAGE......" + numbrePage);
			position= numbreLigne*numbrePage;
			System.out.println("....N Position...." + position);
			List<Equipment> equipment2=mettier.getEquipmentByPage("aviable",idPage,numbreLigne ); ///chatger de 0  juska 2
		//	List<Equipment> equipment2=mettier.getEquipmentByPage("aviable", position,numbreLigne ); ///chatger de 0  juska 2
			
			
			System.out.println(".....TEST PAGE......>" + equipment2.size());

			model.addAttribute("nombreElement",nombreElement);
			model.addAttribute("nombrePage",numbrePage);
			model.addAttribute("page",idPage);
			model.addAttribute("size", nombreElement);
			model.addAttribute("EquipmentList", equipment2);

		} catch (Exception e) { // If lis Empty generate eror
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
			System.out.println("..........." + e.getMessage());
		}

		model.addAttribute("editForm", new SearchForm());

		return "simpleUser/listEquip";

	}
	
	
	
	@RequestMapping(value = "/planExpDetail")
	public String planDetail(Model model,@ModelAttribute("editForm") @Valid SearchForm searchForm,HttpServletResponse response) {
		
		System.out.println(".....ID RECIEVED....>>>" +searchForm.getParam() );

		model.addAttribute("editForm", searchForm);
		
		List<Planification> planifications = new  ArrayList<Planification>(); // initialize a liste aray for Planification
		
	//	String Str = new String("Welcome-to-Tutorialspoint.com");
		String Str=searchForm.getParam();
	      System.out.println("Return Value  Stplited:" );      
	      
	      for (String retval: Str.split(";")) {
	    	  Long idPlan=Long.parseLong(retval);
	         System.out.println(retval);	        
	         planifications.add(mettier.getPlan(idPlan));
	         
	   //      mettier.pl
	      }
	   
	      System.out.println("..getting Data Succes!!");
		//planifications.add(new Planification());
	      model.addAttribute("size", planifications.size());
		model.addAttribute("planList", planifications);
	
		//anifications.remove(0);
		
		if (searchForm.getPage().equals("expired")) {
			model.addAttribute("title", "PLANIFICATION  en Rétard  et Non encore Validé !!!");
		}else if(searchForm.getPage().equals("toDay")) {
			model.addAttribute("title", "TACHES  Planifié pour Ajourd'huit Non  encore Validé !!!");
			
		}

			return "templatesViewsAdd/PlanListExpired";

	}
	
	
	
	
	@RequestMapping(value = "/faultyList")
	public String faultyList(Model model) {
		
		try {
			List<Equipment> equipment = mettier.getEquipmentByState("faulty");

			model.addAttribute("size", equipment.size());
			model.addAttribute("EquipmentList", equipment);

		} catch (Exception e) { // If lis Empty generate eror
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
			System.out.println("..........." + e.getMessage());
		}

		model.addAttribute("editForm", new SearchForm());

		return "simpleUser/listEquip";

	}
	
		
	@RequestMapping(value = "/delEquip")
	public String delEquip(Model model,@RequestParam("idPEquip")int idPEquip,HttpServletResponse response) throws IOException {

//	public String delEquip(Model model,@RequestParam("editForm")SearchForm searchForm) {
Equipment equipment = mettier.getEquipment(idPEquip); // getting the equip
	//	Equipment equipment = mettier.getEquipment(searchForm.getIdEquip()); // getting the equip

		equipment.setNameState("faulty"); // trying to set faulty the Equipment
		/// then making the Upadate
		mettier.updateEquipment(equipment);
		// then try to adda data in the Stade Table
		mettier.addState(new State(equipment.getId(), "faulty", new Date(), "Equipment was added the the faulty List"), 1);
		
		
		response.sendRedirect("equipList");
		
		
		return "simpleUser/listEquip";

	}

	// add Planification

	@RequestMapping(value = "/addPlan")
	public String addPlan(Model model, Principal principal) {
		String name = principal.getName();
		System.out.print("....Addding Plan." + name);
		model.addAttribute("addPlan", new Planification());

		return "templatesViewsAdd/PlanAdd";

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
					planification.getDate(), planification.getType(), planification.getEmail(),"simpleUser","waitting"));

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

		try {
			System.out.println(message);
			sendEmailController.doSendEmail(planification.getEmail(), sub, message,mailSender);

			
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		
		model.addAttribute("searForm", new SearchForm());

		// return "registerCat"; 
		
		//then Redirect to
		response.sendRedirect("planList");
		return "templatesViewsAdd/PlanAdd";

	}

	// addEquip
	@RequestMapping(value = "/registerEquip")
	public String registerEquip(Model model) {

		System.out.println("******opening RegisterEquip Page*********");
		
		model.addAttribute("registerEquip", new RegisterEquipForm());
		;

		model.addAttribute("CategoryList", mettier.getAllCategory());
	
		return "templatesViewsAdd/EquipAdd";

	}

	@RequestMapping(value = "/registerUser")
	public String registerSimpleUser(Model model) {
		model.addAttribute("register", new SimpleUser());
		return "LogRegister/registerUserFrom";

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

	@RequestMapping(value = "/registerUserBtn")
	public String userSearcnTest(Model model, @ModelAttribute("register") @Valid SimpleUser simpleUser,
			BindingResult bindingResult, MultipartFile file) throws IOException {

		if (bindingResult.hasErrors()) {

			return "LogRegister/registerSimpleUser";

		}

		try {
			String roleName = "ROLE_ADMIN_USER";

			mettier.addSimpleUser(new SimpleUser(simpleUser.getNameUser(), simpleUser.getEmail(),
					simpleUser.getPassword(), true, simpleUser.getAdressUser(), simpleUser.getPhoneUser(),
					simpleUser.getDescriptionUser(), roleName, file.getBytes(), file.getOriginalFilename(), new Date(),
					simpleUser.getFunction()));

		} catch (IOException e) {
			model.addAttribute("error", e.getMessage());
			e.printStackTrace();
		}

		/// trying to SEND EMAIL TO TE USER FOR HIS DATA
		// initial date Info to be sent

		SendEmailController sendEmailController = new SendEmailController();
		String pasWord = sendEmailController.generatePassWord();
		String sub = "EquipSys Information User Data";

		String message = "EquipSys Data info \t \n-------------------------------------------------\n Bonjour\t"
				+ simpleUser.getNameUser() + "!\n"
				+ "Vos Information Relative a votre compte EquipSy garder votre Mot de pass Jalousement!\n============================"
				+ "\n NOM: " + simpleUser.getNameUser() + "\n PHONE: " + simpleUser.getPhoneUser() + "\n UserName:"
				+ simpleUser.getEmail() + "\nPassWord:" + pasWord
				+ "\n============================================================================\n Gerereted buy Equip Sys BIN-PC \t "
				+ new Date() + "\n============================================================================\n";

		// System.out.println(message);
		sendEmailController.doSendEmail(simpleUser.getEmail(), sub, message, mailSender);

		model.addAttribute("simpleUserList", mettier.getAllSimpleUser());
		model.addAttribute("searForm", new SearchForm());

		// return "registerCat";
		return "simpleUser/UserList";
		// return "LogRegister/login";

	}

	@RequestMapping(value = "/registerBtn")
	public String userSearcnTest(Model model, @ModelAttribute("registerEquip") @Valid Equipment equipment,
			BindingResult bindingResult, MultipartFile file) throws IOException {

		if (bindingResult.hasErrors()) {
			System.out.println("File hase erro man quick...........");
			System.out.println("**** error***" + bindingResult.getAllErrors());
			model.addAttribute("registerEquip", new RegisterEquipForm());

			return "templatesViewsAdd/EquipAdd";

		}

		if (file.getSize() > 1100000) {
			System.out.println("F..>100.....>");
			model.addAttribute("registerEquip",  equipment);
			model.addAttribute("CategoryList", mettier.getAllCategory());
			model.addAttribute("sizeE", " Fichier > 1 Mega! Viller compressser Votre Fichier dabord");
			return "templatesViewsAdd/EquipAdd";
		}

		System.out.println(
				"Fil GET DATA FRAOM LIST..........>" + equipment.getCategoryName() + "--SIZE->" + file.getSize());

		try {

			Date curDate = new Date();
			SimpleDateFormat format = new SimpleDateFormat();

			String DateToStr = format.format(curDate);
			System.out.println("Default pattern: " + DateToStr);

			format = new SimpleDateFormat("yyyy-MM-dd");

			DateToStr = format.format(curDate);

			if (!file.isEmpty()) {
				System.out.println("----DAta Equip-->>" + equipment.getModelEquip());

				mettier.addEquipment(new Equipment(equipment.getNameEquip(), DateToStr, equipment.getNumSerie(),
						equipment.getModelEquip(), equipment.getDecriptionEquip(), equipment.getYearFabrication(),
						"aviable", file.getBytes(), equipment.getPhotoName(), equipment.getCategoryName(),
						equipment.getFournissorName()), 1, 1);
				/// trying to set aviable in Status Table

				mettier.addState(new State(equipment.getId(), "aviable", new Date(), "Enregistrement dans le System"),
						1);
				// return "simpleUser/index";
			}

			if (file.isEmpty()) {
				System.out.println("----DAta Equip-->>" + equipment.getModelEquip());

				mettier.addEquipment(new Equipment(equipment.getNameEquip(), DateToStr, equipment.getNumSerie(),
						equipment.getModelEquip(), equipment.getDecriptionEquip(), equipment.getYearFabrication(),
						"aviable", file.getBytes(), equipment.getPhotoName(), equipment.getCategoryName(),
						equipment.getFournissorName()), 1, 1);

				/// trying to set aviable in Status Table
				mettier.addState(new State(equipment.getId(), "aviable", new Date(), "Enregistrement dans le System"),
						1);

				// return "simpleUser/index";
			}

			System.out.println("----DAta in try catch>>>" + equipment.getCategoryName() + "==");

		} catch (Exception e) {
			// TODO: handle exception
		}

		model.addAttribute("registerEquip", new Equipment());
		/// model.addAttribute("EquipmentList", mettier.getAllEquipmet())
		// model.addAttribute("registerEquip", new RegisterEquipForm()); ;
		model.addAttribute("EquipmentList", mettier.getAllAviableEquipmet());

		// return "registerEquip";
		return "simpleUser/index";

	}

	/// Modifier Equipment

	// try updating Equipment Info

	/// displaying picthuhfre from DB to the View
	@RequestMapping(value = "/photoEquip", produces = MediaType.IMAGE_JPEG_VALUE) // on doit returner une img JPEG
																					// >>produce
	@ResponseBody // la repose sera envoyer ds le corm dela repo http
	public byte[] photoEquip1(@RequestParam("idEquip") int idEquip) throws IOException {
		Equipment eq = mettier.getEquipment(idEquip);// o cherche la cat
		if (eq.getPhoto() !=null) {			
			return IOUtils.toByteArray( new ByteArrayInputStream(eq.getPhoto()));// la photo se trouve ds la db et pout le recuperer IOUtils		
		}
		return null;// 																	// recuperer IOUtils
	}

	@RequestMapping(value = "/photoCat", produces = MediaType.IMAGE_JPEG_VALUE) // on doit returner une img JPEG
																				// >>produce
	@ResponseBody // la repose sera envoyer ds le corm dela repo http
	public byte[] photoCat(@RequestParam("idCat") int idCat) throws IOException {
		Category eq = mettier.getCategory(idCat);// o cherche la cat
		if (eq.getPhoto() !=null) {			
			return IOUtils.toByteArray( new ByteArrayInputStream(eq.getPhoto()));// la photo se trouve ds la db et pout le recuperer IOUtils		
		}
		return null;// la photo se trouve ds la db et pout le
																			// recuperer IOUtils
	}

	@RequestMapping(value = "/photoProfile", produces = MediaType.IMAGE_JPEG_VALUE) // on doit returner une img JPEG
																					// >>produce
	@ResponseBody // la repose sera envoyer ds le corm dela repo http
	public byte[] photoProfile(@RequestParam("idUser") int idUser) throws IOException {

		SimpleUser simpleUser = mettier.getSimpleUser(idUser);
		if (simpleUser.getPhoto() !=null) {			
			return IOUtils.toByteArray( new ByteArrayInputStream(simpleUser.getPhoto()));// la photo se trouve ds la db et pout le recuperer IOUtils
					}
		return null;// la photo se trouve ds la db et
																					// pout le recuperer IOUtils
	}

	///////////////////////////////////////////////////////////

	@RequestMapping(value = "/registerCat")
	public String registerCat(Model model) {
		List<Category> categories = mettier.getAllCategory();
		int size = categories.size();

		model.addAttribute("size", size);
		model.addAttribute("registerCat", new Category());

		model.addAttribute("CategoryList", categories);

		// return "registerCat";
		return "templatesViewsAdd/CatAdd";
	}

	@RequestMapping(value = "/registerCatBtn")
	public String registerCatBtn(Model model, @ModelAttribute("registerCat") @Valid Category category,
			BindingResult bindingResult, MultipartFile file) throws IOException {

		if (bindingResult.hasErrors()) {
			System.out.println("File hase erro man quick...........");
			System.out.println("**** error***" + bindingResult.getAllErrors());
			model.addAttribute("registerCat", new Category());
			model.addAttribute("CategoryList", mettier.getAllCategory());

			// return "registerCat";
			return "templatesViewsAdd/CatAdd";

		}

		try {

			if (!file.isEmpty()) {

			}

			// Blob blob = Hibernate.createBlob(file.getInputStream());

			System.out.println("--gettin data From RegisterEquip-->>" + category.getNameCat());

			mettier.addCategory(new Category(category.getNameCat(), category.getDescriptionCat(), file.getBytes(),
					file.getOriginalFilename()));
			System.out.println("----DAta in try catch>");

		} catch (Exception e) {
			// TODO: handle exception
		}

		model.addAttribute("registerCat", new Category());
		model.addAttribute("CategoryList", mettier.getAllCategory());
		// return "registerCat";
		return "templatesViewsAdd/CatAdd";

	}

	@RequestMapping(value = "/affectEquip")
	public String affectEquip(Model model) {
		int size;
		System.out.println("******opening AffectedPage Page XXXL*********");
		// model.addAttribute("registerAffect", new AffectationWay());
	
		
			System.out.println("-- No Parameted-XXXXXXXXXXXXX-");
			List<Affectation> affectation = mettier.getAllAffectation();
			size = affectation.size();
			model.addAttribute("size", size);
			model.addAttribute("AffectList", affectation);
			model.addAttribute("editForm", new SearchForm());
	
		
		// return "registerAffect";
		return "templatesViewsAdd/AffList";
	}

	
	@RequestMapping(value = "/affectEquipFilter")
	public String affectEquipFilter(Model model,@ModelAttribute("editForm") @Valid SearchForm searchForm) {
		int size;
		System.out.println("******opening AffectedPage Page XXXL*********");
		// model.addAttribute("registerAffect", new AffectationWay());
	
			System.out.println("--Parameted--");
			String startDateString = searchForm.getDate1();			
			String startDateString2 = searchForm.getDate2();
			
				List<Affectation> aff = null;
				int sizeAf = 0;
				
				System.out.println("--DATE1-STR->" + searchForm.getDate1() + "-" + searchForm.getDate11() + "-XX"
						+ searchForm.getDate2());
				// model.addAttribute("AffectList",
				// mettier.getAllAfByKeyDate(startDateString,startDateString2));

				// model.addAttribute("AffectList",
				// mettier.getAllAfByKeyDate("2017-08-10","2017-08-12"));
				try {
					aff = mettier.getAllAfByKeyDate(startDateString, startDateString2);
					sizeAf = aff.size();
					//List<Affectation> affectation2 = mettier.getAllAffectation();
					size = aff.size();
					model.addAttribute("size", size);
					model.addAttribute("AffectList", aff);
					model.addAttribute("editForm", searchForm );

				} catch (Exception e) {
					model.addAttribute("error", e.getMessage());
				}
			
	
		
		// return "registerAffect";
		return "templatesViewsAdd/AffList";
	}
	
		
	@RequestMapping(value = "/planList")
	public String planList(Model model, HttpServletRequest httpServletRequest, Principal principal,@ModelAttribute("profileInSession")com.ruk.entity.User user) {
		int size=0;
		System.out
				.println("******opening Plan List Page* PPP********" + httpServletRequest.getAttribute("equipAffSession"));

		List<Planification> planifications = null;
		List<SimpleUser> listUser= null;
		System.out.println(".....LODAED.WWWWWWWWWWWWW.....");
		try {
			String name = principal.getName();					;

			listUser= mettier.getSimpleUserByMail(name);
			System.out.println(".....LODAED.XXXXXXXXXX....." + name+"-OR->"+user.getEmail());
			if (listUser != null) {
				SimpleUser userSimple = listUser.get(0);
				System.out.println("..........." + name);
				// model.addAttribute("profileInSession", userSimple);
				planifications = mettier.listPlanBuyUser(userSimple.getId());
				size = planifications.size();
				
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
	}

		model.addAttribute("size", size);
		model.addAttribute("planList", planifications);
		
		
			model.addAttribute("editForm", new SearchForm());
		
		return "templatesViewsAdd/PlanList";
	}
	@RequestMapping(value = "/validPlan")
	public String delPlan(Model model, HttpServletRequest httpServletRequest, Principal principal,@RequestParam("idPlan")Long idPlan) {
		// trying to dell the Plan
	//	mettier.removePlan(idPlan);
		
		int size;
		System.out
				.println("******opening Plan List Page*********" + httpServletRequest.getAttribute("equipAffSession"));

		String name = principal.getName();
		List<Planification> planifications = null;

		List<SimpleUser> listUser = mettier.getSimpleUserByMail(name);
		if (listUser != null) {
			SimpleUser userSimple = listUser.get(0);
			System.out.println("..........." + name);
			// model.addAttribute("profileInSession", userSimple);
			planifications = mettier.listPlanBuyUser(userSimple.getId());
			size = planifications.size();
			model.addAttribute("size", size);
			model.addAttribute("planList", planifications);
			model.addAttribute("editForm", new SearchForm());
		
		
		}
		
		Planification planification=mettier.getPlan(idPlan);
					planification.setStatePlan("finished");
					
		mettier.updatePlan(planification);
    	
		return "templatesViewsAdd/PlanList";
	}
	
	@RequestMapping(value = "/affectEquipAction")
	public String affectEquipAction(Model model) {

		System.out.println("******opening AffectedPage Page*********");
		// model.addAttribute("registerAffect", new AffectationWay());
		/// cheking All Equip disponnible
		try {
			model.addAttribute("equipAviable", mettier.getAllAviableEquipmet());

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());

		}
		model.addAttribute("editForm", new SearchForm());

		return "templatesViewsAdd/AffAdd";
		// return "AffectAction";
	}

	@RequestMapping(value = "/affectActionConf")
	public String affectEquipActionConf(Model model, @ModelAttribute("searForm") SearchForm form) {
		// public String affectEquipActionConf(Model model,@RequestParam("idEquip")int
		// idEquip){

		System.out.println("******opening AffectedPage CONFIRM Page*********");
		// model.addAttribute("registerAffect", new AffectationWay());
		/// cheking All Equip disponnible

		// model.addAttribute("affectDetails", mettier.getEquipment(idEquip));
		Equipment equipment = mettier.getEquipment(form.getIdEquip());

		model.addAttribute("affectDetails", equipment);

		model.addAttribute("idEquip", equipment.getId());
		System.out.println("-AFFCE CONFI->" + equipment.getId());
		// then trying add the Id EquiPment in sessionn Var
		model.addAttribute("equipAffSession", equipment);
		model.addAttribute("editForm", new SearchForm());

		model.addAttribute("affectation", new Affectation());
		/*
		 * if (form.getParam().equals("userLoaded")) {// if we have just load the user
		 * then we have to diplay the Affectation confirm details
		 * model.addAttribute("userConfirm","userConfirm" ); }
		 */
		return "templatesViewsAdd/AffConfirm";
		// return "rsAffectationDetail";
	}

	@RequestMapping(value = "/affectValidation")
	public String affectValidation(Model model, @ModelAttribute("searForm") SearchForm form) {

		System.out.println(
				"******opening AffectedPage Page**EquipId****" + form.getIdEquip() + "<-IDEEQUIP->" + form.getIdUser());
		// model.addAttribute("affectation", new AffectationWay());
		/// cheking All Equip disponnible

		model.addAttribute("idUser", form.getIdUser());
		model.addAttribute("UserName", form.getMc());

		/*
		 * model.addAttribute("equipAviable", mettier.getAllAviableEquipmet());
		 * searchBtnAvd
		 */

		model.addAttribute("idEquip", form.getIdEquip());
		model.addAttribute("editForm", new SearchForm());
		model.addAttribute("affectation", new Affectation());
		return "templatesViewsAdd/AffConfirmValid";
	}

	// affectationValid
	@RequestMapping(value = "/affectValidationBtn")
	public String affectationValid(Model model, @ModelAttribute("affectation") @Valid Affectation affectation,
			BindingResult bindingResult) throws IOException {
		int idEquip = affectation.getIdEquip();

		System.out.println("******opening AffectedPage  VPage*********ID USER" + affectation.getIdUser() + "=TO="
				+ affectation.getNameAffected() + "--ID->" + affectation.getIdEquip());
		Date curDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat();

		String DateToStr = format.format(curDate);
		System.out.println("Default pattern: " + DateToStr);

		format = new SimpleDateFormat("yyyy-MM-dd");

		DateToStr = format.format(curDate);

		if (affectation.getIdUser() == 0) {
			model.addAttribute("exception", "ID ne peut pas etre Null");
		} else {

			/// First trying to see if ID User is Valide
			SimpleUser simpleUser = mettier.getSimpleUser(affectation.getIdUser());

			if (simpleUser == null) { // User does Not exite
				affectation.getIdEquip();

				model.addAttribute("affectDetails", mettier.getEquipment(affectation.getIdEquip()));
				model.addAttribute("affectation", affectation);
				model.addAttribute("editForm", new SearchForm());
				model.addAttribute("error", "ERROR..Cette Utulisateur n'existe pas dans la BD, ID invalide");
				return "templatesViewsAdd/AffConfirm";

			} else {

				Equipment equipment = mettier.getEquipment(idEquip); // getting the equip
				equipment.setNameState("affected"); // trying to set The Aaffected State
				/// then making the Upadate
				mettier.updateEquipment(equipment);

				mettier.addAffectation(
						new Affectation(affectation.getDescriptionAff(), true, "AFF", new Date(), 2017,
								equipment.getNameEquip(), simpleUser.getNameUser(), DateToStr,
								affectation.getDateEndAff(), affectation.getIdEquip(), affectation.getIdUser()),
						affectation.getIdEquip());

				/// ==== THEN AFTER TRYING TO MEMAIL To Userr Affected
				String msg = " Bonjour \t" + simpleUser.getNameUser()
						+ "\n************************************************************\n Cette  Equipment est desormer Affecter à votre Compte:\n"
						+ "ID EQUIPMENT \t:\t" + equipment.getId() + "\n NOM EQUIPMENT\t:\t" + equipment.getNameEquip()
						+ "\n MODEL \t:\t " + equipment.getModelEquip() + " \n CATEGORY \t:\t"
						+ equipment.getCategoryName() + "\n Affectation valale jusqu'au \t"
						+ affectation.getDateEndAff()
						+ "\n************************************************************\n Date Affectation: \t"
						+ new Date()
						+ "\n Powered By EquipSys  BIN-PC\n--------------------------------------------------";

				SendEmailController mail = new SendEmailController();
				mail.doSendEmail(simpleUser.getEmail(), "Equipment Affectation Details", msg, mailSender);

				System.out.println(msg);
				System.out.println("***Affcted Susse!");
			}
		}

		// model.addAttribute("registerAffect", new AffectationWay());
		/// cheking All Equip disponnible
		model.addAttribute("editForm", new SearchForm());
		model.addAttribute("editForm", new SearchForm());
		model.addAttribute("AffectList", mettier.getAllAffectation());
		return "templatesViewsAdd/AffList";
		// return "registerAffect";
	}

	@RequestMapping(value = "/affFinaleDetail")
	// public String affFinaleDetail(Model model,@RequestParam("idAff")int idAff){

	public String affFinaleDetail(@ModelAttribute("editForm") SearchForm form, Model model) {

		System.out.println("******opening AffectedPage DEtailsFinal Page*********");
		// model.addAttribute("registerAffect", new AffectationWay());
		/// cheking All Equip disponnible
		Affectation affectation = mettier.getAffectation(form.getIdEquip());

		int idUser = affectation.getIdUser();

		System.out.println("--DATA----" + idUser);
		Equipment equipment = mettier.getEquipment(affectation.getIdEquip());
		SimpleUser simpleUser = new SimpleUser();

		simpleUser = mettier.getSimpleUser(idUser);
		System.out.println("--DATA---->>" + simpleUser.getEmail());
		// String mail=

		// String idEquip=affectation.getNameAffected();

		model.addAttribute("EquipDetail", equipment);
		model.addAttribute("UserDetail", simpleUser);
		model.addAttribute("UserMail", simpleUser.getEmail().toString());
		model.addAttribute("UserName", simpleUser.getNameUser().toString());
		model.addAttribute("UserPhone", simpleUser.getPhoneUser().toString());
		model.addAttribute("UserFunction", simpleUser.getFunction().toString());

		model.addAttribute("AffectS", affectation.getDateAff().toString());
		model.addAttribute("AffectE", affectation.getDateEndAff().toString());

		model.addAttribute("Affectation", affectation);

		return "templatesViewsAdd/AffCDetails";
		// return "rsAffectationDetail";
	}

	////////////////////////////////////////////////////////////////
	// Tracking Equip

	@RequestMapping(value = "/editEquip")
	// public String editEquip(@RequestParam("idEquip")int idEquip, Model model){
	public String editEquip(@ModelAttribute("editForm") SearchForm form, Model model) {
		Equipment equipment = mettier.getEquipment(form.getIdEquip());

		// utulisation de la var de session pour prender la phot actuele
		model.addAttribute("equipInSession", equipment);
		model.addAttribute("registerEquip", equipment);

		// model.addAttribute("registerEquip", new RegisterEquipForm()); ;

		model.addAttribute("CategoryList", mettier.getAllCategory());
		// model.addAttribute("FournList", mettier.getFourniseur());
		// model.addAttribute("EquipmentList", mettier.getAllEquipmet());

		// return "registerEquip";
		return "templatesViewsAdd/EquipAddEdit";// non de la vue
	}

	@RequestMapping(value = "/trackIndex")
	public String trackerEquip(Model model) {

		model.addAttribute("searForm", new SearchForm());

		// return "registerCat";
		return "simpleUser/trackIndex";
	}

	@RequestMapping(value = "/trackerEquipBtn")
	public String trackerEquipBtn(Model model, @ModelAttribute("trackEquip") @Valid SearchForm searchForm,
			BindingResult bindingResult) {

		int idEquip = Integer.parseInt(searchForm.getMc());
		// model.addAttribute("AffectInfo",
		// mettier.trackEquipmentByAff(searchForm.getIdEquip()));

		if (bindingResult.hasErrors()) {
			System.out.println("File hase erro man quick...........");

			return "simpleUser/trackIndex";

		}

		if (idEquip != 0) {

			// Equipment equipment= (Equipment)
			// mettier.getAllAfByEquip(searchForm.getIdEquip());
			Equipment equipment = mettier.getEquipment(idEquip);
			List<Affectation> affectation = mettier.getAllAfByEquip(idEquip);
			List<Maintenance> maintenances = mettier.getAllMaintenanceByEquip(idEquip);
			List<State> state = mettier.getStateByEquip(idEquip);

			if ( affectation.isEmpty()) { // cheking if data are not empty
				model.addAttribute("errorA", "Cette Equippemt na pas d'affectation");
				
			}
			if (maintenances.isEmpty()) { // cheking if data are not empty
				
				model.addAttribute("errorM", "Il nya pas des Maintenance disponnible");
			}

			if (equipment == null) {
				model.addAttribute("errorE", "Cette Equippemt  n'Existe pas!! Entrer un ID Valide");
			}

			// afecting data to Model
			model.addAttribute("equipment", equipment);
			model.addAttribute("affectation", affectation);
			model.addAttribute("maintenance", maintenances);
			model.addAttribute("state", state);

			System.out.println("......ID EQUIP SELECT.....");

			return "simpleUser/trackEquipDetail";
		} else if (searchForm.getMc().isEmpty()) {
			System.out.println("......KEYWOR SERACH.....");

		} else if (idEquip == 0) {

			System.out.println(" ...O NOT SUPORTED.......");

			return "simpleUser/trackIndex";
		}

		try {

		} catch (Exception e) { // If lis Empty generate eror
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
			System.out.println("..........." + e.getMessage());
		}

		// return "registerCat";
		// return "simpleUser/trackEquipDetail";

		model.addAttribute("searForm", new SearchForm());

		// return "registerCat";
		return "simpleUser/trackIndex";
	}

	//// Search ModuleController

	@RequestMapping(value = "/search")
	public String search(Model model, @ModelAttribute("searForm") @Valid SearchForm searchForm) {

		model.addAttribute("searForm", new SearchForm());

		// return "registerCat";
		return "simpleUser/search";
	}

	@RequestMapping(value = "/searchBtnAvd")
	public String searchBtnAvdanced(Model model, @ModelAttribute("searForm") @Valid SearchForm searchForm) {

		try {
			model.addAttribute("searRsUser", mettier.getAllSimpleUserByKey(searchForm.getMc()));
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}

		model.addAttribute("idEquip", searchForm.getIdEquip());
		model.addAttribute("idUser", searchForm.getIdUser());
		System.out.println("--idEQUIp AVD-?>" + searchForm.getIdEquip());
		model.addAttribute("searcheUser", "User ");
		model.addAttribute("searForm", searchForm);

		// return "registerCat";
		return "simpleUser/searchAdvancedAff";
	}

	@RequestMapping(value = "/searchBtn")
	public String searchBtn(Model model, @ModelAttribute("searForm") @Valid SearchForm searchForm) {

		if (searchForm.getPage().equals("tracking")) { // if requeste com from Tracking Module

			try {
				System.out.println("......YES Trackin Module Actived.....");

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

			if (searchForm.getParam().equals("Equipment")) {

				try {

					model.addAttribute("searForm", new SearchForm());
					model.addAttribute("searRs", mettier.getEquipmentByKey(searchForm.getMc()));
					model.addAttribute("searcheP", "youare ");
					return "simpleUser/search";
				} catch (Exception e) {
					model.addAttribute("error", e.getMessage());
					return "simpleUser/search";
				}

			} else if (searchForm.getParam().equals("Utulisateur")) {

				try {

					model.addAttribute("searForm", new SearchForm());

					model.addAttribute("searRsUser", mettier.getAllSimpleUserByKey(searchForm.getMc()));
					model.addAttribute("searcheUser", "User ");
					return "simpleUser/search";
				} catch (Exception e) {
					model.addAttribute("error", e.getMessage());
					return "simpleUser/search";
				}

			} else if (searchForm.getParam().equals("Category")) {
				try {

					model.addAttribute("searForm", new SearchForm());
					// model.addAttribute("searRs", mettier.getEquipmentByKey(searchForm.getMc()));
					return "simpleUser/search";
				} catch (Exception e) {
					model.addAttribute("error", e.getMessage());
					return "simpleUser/search";
				}

			} else if (searchForm.getParam().equals("Maintenance")) {
				try {

					model.addAttribute("searForm", new SearchForm());

					model.addAttribute("searRsMain", mettier.getAllMaintenanceByKey(searchForm.getMc()));
					model.addAttribute("destinPage", searchForm.getDestinPage());
					System.out.println("--DEST_serache_GETPARAM btn->" + searchForm.getDestinPage());
					model.addAttribute("searcheMain", "Maintence");
					return "simpleUser/search";
				} catch (Exception e) {
					model.addAttribute("error", e.getMessage());
					return "simpleUser/search";
				}

			}

		} else if (searchForm.getPage().equals("MaintAdd")) {// If request came from Maint or FeadBack Add

			model.addAttribute("destinPage", searchForm.getDestinPage());
			System.out.println("--DEST_serache_btn->" + searchForm.getDestinPage());
			model.addAttribute("searFormAdv", searchForm.getParam());
			model.addAttribute("searFormAdv", new SearchForm());
			return "simpleUser/searchAdvanced";

		} else if (searchForm.getPage().equals("MaintAddRs")) {// this One is for the Maint and Equip Serche

			try {

				model.addAttribute("searForm", new SearchForm());
				model.addAttribute("searRs", mettier.getEquipmentByKey(searchForm.getMc()));
				model.addAttribute("destinPage", searchForm.getDestinPage());
				model.addAttribute("searcheP", "youare ");
				return "simpleUser/searchAdvanced";
			} catch (Exception e) {
				model.addAttribute("error", e.getMessage());
				return "simpleUser/searchAdvanced";
			}
			// model.addAttribute("searFormAdv",new SearchForm());
			//

		} else if (searchForm.getPage().equals("AffAdd")) {
			model.addAttribute("idEquip", searchForm.getIdEquip());
			System.out.println("..Coming from AfectedPageCmd" + searchForm.getIdEquip());
			return "simpleUser/searchAdvancedAff";
		}

		model.addAttribute("searForm", new SearchForm());

		// return "registerCat";
		return "simpleUser/trackIndex";
	}

	@RequestMapping(value = "/rsDetails")
	public String rsEquipDetaail(Model model, @ModelAttribute("searForm") SearchForm searchForm) {

		if (searchForm.getParam().equals("equip")) {

			model.addAttribute("equipDetails", mettier.getEquipment(searchForm.getIdEquip()));

			System.out.print("-DesstinationPage-" + searchForm.getDestinPage());
			if (searchForm.getDestinPage().equals("feadBack")) {
				model.addAttribute("feadBack", searchForm.getDestinPage());
			}
			if (searchForm.getDestinPage().equals("Maintenance")) {
				model.addAttribute("Maintenance", searchForm.getDestinPage());
			}

			// model.addAttribute("Maint", searchForm.getDestinPage() );
			return "templatesViewsRs/rsEquipDetail";

		}
		if (searchForm.getParam().equals("user")) {
			model.addAttribute("userDetails", mettier.getSimpleUser(searchForm.getIdEquip()));
			return "templatesViewsRs/rsUserDetail";

		} else if (searchForm.getParam().equals("maint")) {
			model.addAttribute("maintDetails", mettier.getMaintenance(searchForm.getIdEquip()));
			return "templatesViewsRs/rsMaintenanceDetail";
		}

		// return "registerCat";
		return "templatesViewsRs/rsEquipDetail";
	}

	@RequestMapping(value = "/besoins")
	public String besoins(Model model, @ModelAttribute("searForm") @Valid SearchForm searchForm) {

		List<Besoin> besoins = mettier.getAllBesion();
		int size = besoins.size();

		model.addAttribute("size", size);
		model.addAttribute("besoinDetails", besoins);
		model.addAttribute("searForm", new SearchForm());

		// return "registerCat";
		return "simpleUser/besoinList";
	}

	@RequestMapping(value = "/besoinsBtn")
	public String besoinsBtn(Model model, @ModelAttribute("searForm") @Valid SearchForm searchForm,
			HttpServletResponse response) {
		model.addAttribute("besoinDetails", mettier.getAllBesion());
		model.addAttribute("searForm", new SearchForm());

		if (searchForm.getPage().equals("replyBesoin")) {
			Besoin besoin = mettier.getBesion(searchForm.getIdBesoin());/// I used the Id Equip in stead of IdBesoins
																		/// coz using only the form data
		

			model.addAttribute("besoin", besoin);
			model.addAttribute("searForm", searchForm);
			return "simpleUser/besoinReply";
			/// Trying to de sactivate the Besoin and send the man whogave the besoin

		} else if (searchForm.getPage().equals("replyBesoinBtn")) {
			SendEmailController sendEmailController = new SendEmailController();

			// Try Accesing to to it
			// Besoin besoin=(Besoin) httpServletRequest.getAttribute("besionInS");
			Besoin besoin = mettier.getBesion(searchForm.getIdBesoin());
			System.out.print("This is the id Besoin-->" + searchForm.getIdBesoin() + "<-UserName>");

			// getting User dataInfo
			SimpleUser simpleUser = mettier.getSimpleUser(searchForm.getIdUser());
			String sub = "Your Besoin Response...by EquipSystemS";
			System.out.print("This is the User data-->" + simpleUser.getNameUser());

			// updating Data
			besoin.setState("finished");
			besoin.setReponse(searchForm.getMc());
			mettier.updateBesoin(besoin);
			// first sendin mail
			String message = "EquipSys Besoin Notification \t \n-------------------------------------------------\n Bonjour\t"
					+ simpleUser.getNameUser() + "!\n" + "Voici  la réponse à  votre besoin exprimer  en date du "
					+ besoin.getDate() + " !\n============================" + "\n Titre: " + besoin.getTitle()
					+ "\n Description: " + besoin.getDescription()
					+ "\n============================================================================\n Response From EquipSystem: \n "
					+ besoin.getReponse()
					+ "\n============================================================================\n Genereted buy EquipSys  BIN-PC on "
					+ new Date();

			System.out.println(message);
			try {
				sendEmailController.doSendEmail(simpleUser.getEmail(), sub, message, mailSender);
				//response.sendRedirect("besoins");
			} catch (Exception e) {
				model.addAttribute("error", "impossible d'envoyer un Mail de confirmatiom car Error de connection Internet!");
			}
			
			// sending to besoin List
			
			List<Besoin> besoins = mettier.getAllBesion();
			int size = besoins.size();

			model.addAttribute("size", size);
			model.addAttribute("besoinDetails", besoins);
			model.addAttribute("searForm", new SearchForm());
			//return "simpleUser/besoinReply";
			/// Trying to de sactivate the Besoin and send the man whogave the besoin
		} else if (searchForm.getPage().equals("filtrerB")) {
			System.out.println("--This is Your Data+" + searchForm.getDate1() + "--" + searchForm.getDate2());
			try {
				List<Besoin> besoins = mettier.BesionByDate(searchForm.getDate1(), searchForm.getDate2());

				model.addAttribute("besoinDetails", besoins);
				int size = besoins.size();

				model.addAttribute("size", size);
				return "simpleUser/besoinList";
			} catch (Exception e) {
				model.addAttribute("error", e.getMessage());
			}
		}
		model.addAttribute("searForm", searchForm);
		return "simpleUser/besoinList";
	}

	@RequestMapping(value = "/UserList")
	public String SimpleUser(Model model) {

		List<SimpleUser> simpleUsers = mettier.getAllSimpleUser();
		int size = simpleUsers.size();

		model.addAttribute("size", size);
		model.addAttribute("simpleUserList", simpleUsers);
		model.addAttribute("searForm", new SearchForm());

		// return "registerCat";
		return "simpleUser/UserList";
	}

	@RequestMapping(value = "/addMaintenance")
	public String planingList(Model model, @ModelAttribute("searForm") @Valid SearchForm searchForm) {

		if (searchForm.getIdEquip() > 0) {
			model.addAttribute("idEquip", searchForm.getIdEquip());

		} else if (searchForm.getIdEquip() == 0) {
			model.addAttribute("btnSearch", "desapear");
		}
		// model.addAttribute("idEquip", searchForm.getMc());
		model.addAttribute("searForm", new SearchForm());
		model.addAttribute("maintenance", new Maintenance());

		// return "registerCat";
		return "templatesViewsAdd/MaintenanceAdd";
	}

	@RequestMapping(value = "/addMaintBtn")
	public String planingListB(Model model, @ModelAttribute("maintenance") @Valid Maintenance maintenance,
			@ModelAttribute("searForm") @Valid SearchForm searchForm) {
		Date curDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat();

		String DateToStr = format.format(curDate);
		System.out.println("Default pattern: " + DateToStr);

		format = new SimpleDateFormat("yyyy-MM-dd");

		DateToStr = format.format(curDate);

		Equipment equipment1 = mettier.getEquipment(maintenance.getIdEquip());
		if (equipment1 == null) {

			model.addAttribute("error", "..l'ID  Invalide Cette Equippment n'existe pas!!");
			model.addAttribute("maintenance", new Maintenance());
			return "templatesViewsAdd/MaintenanceAdd";
		} else {
			mettier.addMaitenance(new Maintenance(maintenance.getIdEquip(), maintenance.getIdUser(),
					maintenance.getTitle(), maintenance.getNameUser(), equipment1.getNameEquip(),
					maintenance.getDescriptionProblem(), maintenance.getSolutionProblem(), "REP", DateToStr), 1, 1);

			try {
				model.addAttribute("EquipmentList", mettier.getAllAviableEquipmet());

			} catch (Exception e) { // If lis Empty generate eror
				// TODO: handle exception
				model.addAttribute("error", e.getMessage());

			}

			model.addAttribute("searForm", new SearchForm());
			model.addAttribute("editForm", new SearchForm());

			return "simpleUser/index";

		}

		// model.addAttribute("editForm",new SearchForm());

		// return "simpleUser/index";

	}

	@RequestMapping(value = "/addMaintenanceBtn")
	public String addMaintenanceBtn(Model model, @ModelAttribute("maintenance") @Valid Maintenance maintenance) {
		Equipment equipment1 = mettier.getEquipment(maintenance.getIdEquip());
		if (equipment1 == null) {
			System.out.println(" .FUCKING ID EQUIP.......");
		}

		// model.addAttribute("simpleUserList", mettier.getAllSimpleUser());

		model.addAttribute("maintenance", new Maintenance());

		// return "registerCat";
		return "templatesViewsAdd/MaintenanceAdd";
	}

	@RequestMapping(value = "/addBesion")
	public String addBesion(Model model) {

		model.addAttribute("CategoryList", mettier.getAllCategory());

		model.addAttribute("addBesion", new Besoin());

		// return "registerCat";
		return "templatesViewsAdd/BesoinAdd";
	}

	@RequestMapping(value = "/getReportIndex")
	public String getReportIndex(Model model) {

		model.addAttribute("reporting", new SearchForm());
		model.addAttribute("searForm", new SearchForm());
		// return "templatesViewsAdd/ReportIndex";
		return "templatesViewsAdd/searchReporting";
	}

	@RequestMapping(value = "/reportBtnX")
	public String searchBtnX(Model model, @ModelAttribute("searForm") @Valid SearchForm searchForm,
			@DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {

		/*
		 * Random random= new Random(1); int randomRs=random.nextInt();
		 */
		String FILE = System.getProperty("java.io.tmpdir") + "RpSys.pdf";
		String startDateString = searchForm.getDate1();
		String userName = searchForm.getMc();
		String startDateString2 = searchForm.getDate2();
		if (searchForm.getParam().equals("Affectation")) {

			List<Affectation> aff = null;
			int sizeAf = 0;

			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			Date startDate, startDate2;
			System.out.println("--DATE1-STR->" + searchForm.getDate1() + "-" + searchForm.getDate11() + "-XX"
					+ searchForm.getDate2());
			// model.addAttribute("AffectList",
			// mettier.getAllAfByKeyDate(startDateString,startDateString2));

			// model.addAttribute("AffectList",
			// mettier.getAllAfByKeyDate("2017-08-10","2017-08-12"));
			try {
				aff = mettier.getAllAfByKeyDate(startDateString, startDateString2);
				sizeAf = aff.size();

				model.addAttribute("AffectList", aff);
				model.addAttribute("sizeAff", sizeAf);

			} catch (Exception e) {
				model.addAttribute("error", e.getMessage());
			}

			System.out.println("--DATE1-->" + searchForm.getDate1());
			model.addAttribute("reporting", new SearchForm());
			model.addAttribute("editForm", new SearchForm());
			model.addAttribute("reportingInfo", searchForm);

			/// Then trying To Generate PDF File

			try {

				File file = new File(FILE);
				file.getParentFile().mkdirs();// if dir not exit then create it
				BaseColor color = new BaseColor(0, 255, 0); // or red, green, blue, alpha

				Document document = new Document();

				PdfWriter.getInstance(document, new FileOutputStream(FILE));
				document.open();
				addMetaData(document);
				/// Adding Logo Img

				String tile = "RAPPORT D'AFFECTATION DES EQUIPMENTS";
				addTitlePage(document, tile, userName, startDateString, startDateString2);

				// addTitlePage(document);
				/// addContent(document);
				/// Trying

				PdfPTable table = new PdfPTable(4);

				table.setWidthPercentage(100);
				// table.setBorderColor(BaseColor.GRAY);
				// t.setPadding(4);
				// t.setSpacing(4);
				// t.setBorderWidth(1);
				PdfPCell c1 = new PdfPCell(new Phrase("ID"));
				c1.setPaddingLeft(10);
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(c1);

				c1 = new PdfPCell(new Phrase("Nom Equipment"));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setBackgroundColor(color);

				table.addCell(c1);

				c1 = new PdfPCell(new Phrase("Affected To"));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Date"));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(c1);
				table.setHeaderRows(1);

				// List<Operation> ops = mettier.consulterOperationsParDate(codeCompte,date1,
				// date2);

				// List<Operation> ops = mettier.consulterOperations( "CC1",0,15);
				for (Iterator iterator = aff.iterator(); iterator.hasNext();) {
					Affectation operation = (Affectation) iterator.next();

					table.addCell("" + operation.getId());
					table.addCell("" + operation.getNameEquip());
					table.addCell("" + operation.getNameAffected());
					table.addCell("" + operation.getDateStartAff());

				}

				table.getDefaultCell().setBackgroundColor(BaseColor.GREEN);
				table.addCell("TOTAL AFFECTATION");
				table.addCell("" + sizeAf);
				table.addCell("");
				table.addCell("");
				document.add(table);
				/// Adding Signature
				addSignature(document, userName);

				//// ----->>>> <<<-------------
				// addLogo(document);

				document.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			model.addAttribute("addFeadBack", new FeadBack());

			if (Desktop.isDesktopSupported()) {
				try {
					// File myFile = new File("C:/PDF/iTextHelloWorld.pdf");
					File myFile = new File(FILE);
					Desktop.getDesktop().open(myFile);
				} catch (IOException ex) {
					// no application registered for PDFs
				}
			}

			/// the One for the data Table

			return "templatesViewsAdd/reportingRs";
		} else if (searchForm.getParam().equals("Equipment")) {

			int sizeAvaible, sizeAffected, sizeFaulty, sizeAll, sizeMaint = 0, savedEquip = 0, sizeAllAff = 0;
			int sizeAf = 0;
			// Fisrt Getting Number of Avible Equipment
			/*
			 * List<Equipment> equipInter=mettier.getAllAviableEquipmet(); // getting
			 * Aviable Intervale sizeSavedInterval=equipInter.size();
			 */

			List<Affectation> aff = null;
			List<Affectation> affAll = null;
			List<Maintenance> maintenances = null;

			try {
				aff = mettier.getAllAfByKeyDate(startDateString, startDateString2);
				sizeAf = aff.size();
				affAll = mettier.getAllAffectation();
				sizeAllAff = affAll.size();
				System.out.println("----BEFOR-->");
				List<Equipment> equipSaved = mettier.getEquipmentByDate(startDateString, startDateString2);
				savedEquip = equipSaved.size();
				maintenances = mettier.getAllMaintenanceByDate(startDateString, startDateString2);
				sizeMaint = maintenances.size();

				System.out.println("----AFTER-->" + savedEquip);

				model.addAttribute("sizeAllAff", sizeAllAff);
				model.addAttribute("sizeAff", sizeAf);
				model.addAttribute("sizeEquipSAve", savedEquip);
				model.addAttribute("sizeEquipMaint", sizeMaint);
				model.addAttribute("equipMaint", maintenances);
			} catch (Exception e) {
				model.addAttribute("error", e.getMessage());
				System.out.print(e.getMessage());
			}
			try {

				List<Equipment> equip = mettier.getAllEquipmet(); // getting Aviable
				sizeAll = equip.size();

				List<Equipment> aviableEquip = mettier.getAllAviableEquipmet(); // getting Aviable
				sizeAvaible = aviableEquip.size();

				List<Equipment> affEquip = mettier.getEquipmentByState("affected");// Gette Affected
				sizeAffected = affEquip.size();

				List<Equipment> faultyEquip = mettier.getEquipmentByState("faulty");
				sizeFaulty = faultyEquip.size();
				model.addAttribute("reportingInfo", searchForm);

				model.addAttribute("sizeAll", sizeAll);
				model.addAttribute("sizeAllAvia", sizeAvaible);
				model.addAttribute("sizeAff", sizeAffected);
				model.addAttribute("sizeFaultS", sizeFaulty);
				// System.out.println("DATE Loadet -> All
				// AeEqui"+sizeAvaible+"<-Affected->"+sizeAffected+"<-sizeFaulty->"+sizeFaulty);
				System.out.println("DATE Loadet -> All AeEqui+" + sizeAvaible + "+sizeAffected+" + sizeAffected
						+ "FAOU=>" + sizeFaulty + "AFFETCET>" + sizeAf + "--MAIN->" + sizeMaint);

				//// Genetating PDF SECTION

				File file = new File(FILE);
				file.getParentFile().mkdirs();// if dir not exit then create it
				BaseColor color = new BaseColor(0, 255, 0); // or red, green, blue, alpha

				Document document = new Document();

				PdfWriter.getInstance(document, new FileOutputStream(FILE));
				document.open();
				addMetaData(document);
				/// Adding Logo Img

				String tile = "RAPPORT D'UTULISATION DES EQUIPMENTS";
				addTitlePage(document, tile, userName, startDateString, startDateString2);

				PdfPTable table = new PdfPTable(2);

				table.setWidthPercentage(100);
				// table.setBorderColor(BaseColor.GRAY);
				// t.setPadding(4);
				// t.setSpacing(4);
				// t.setBorderWidth(1);
				PdfPCell c1 = new PdfPCell(new Phrase("SYNTHESE GENERAL"));
				c1.setPaddingLeft(10);
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setBackgroundColor(color);
				table.addCell(c1);

				c1 = new PdfPCell(new Phrase("Etat"));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setBackgroundColor(color);

				table.addCell(c1);

				table.setHeaderRows(1);

				/// Adding raw

				table.addCell("Nombre Total Equipment Disponnible");
				table.addCell("" + sizeAvaible);

				table.addCell("Nombre Total Equipment Affecte");
				table.addCell("" + sizeAllAff);

				table.addCell("Nombre Total Equipment Faulty");
				table.addCell("" + sizeFaulty);
				table.getDefaultCell().setBackgroundColor(BaseColor.GREEN);
				table.addCell("NOMBRE TOTAL EQUIPMENT");
				table.addCell("" + sizeAll);

				table.getDefaultCell().setBackgroundColor(BaseColor.RED);
				table.addCell("" + "..");
				table.addCell("" + "..");
				table.getDefaultCell().setBackgroundColor(BaseColor.GREEN);
				// Adding More Info
				table.addCell("SYNTHESE DEMANDE");
				table.addCell("" + "..");

				table.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
				table.addCell("Nombre Equipment Ajouté");
				table.addCell("" + savedEquip);

				table.addCell("Nombre Equipment Affecté");
				table.addCell("" + sizeAffected);

				table.addCell("Nombre Equipment Faulty");
				table.addCell("" + sizeFaulty);
				table.addCell("Nombre Maintenance Effectué");
				table.addCell("" + sizeMaint);

				table.getDefaultCell().setBackgroundColor(BaseColor.RED);
				table.addCell("" + "..");
				table.addCell("" + "..");

				document.add(table);
				/// Adding Signature

				/// ----------------------- Then adding second Table
				Paragraph preface = new Paragraph();
				// We add one empty line
				addEmptyLine(preface, 2);
				preface.add(
						new Paragraph(" MAINTENANCE \t[\t" + startDateString + "\t To  \t" + startDateString2 + "\t]", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
								smallBold));
				addEmptyLine(preface, 1);
				document.add(preface);

				PdfPTable table2 = new PdfPTable(4);

				table2.setWidthPercentage(100);

				PdfPCell c2 = new PdfPCell(new Phrase("ID"));
				c2.setPaddingLeft(10);
				c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				c2.setBackgroundColor(color);
				table2.addCell(c2);

				c2 = new PdfPCell(new Phrase("EQUIPMENT"));
				c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				c2.setBackgroundColor(color);
				table2.addCell(c2);

				c2 = new PdfPCell(new Phrase("BY"));
				c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				c2.setBackgroundColor(color);
				table2.addCell(c2);

				c2 = new PdfPCell(new Phrase("DATE"));
				c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				c2.setBackgroundColor(color);
				table2.addCell(c2);

				table2.setHeaderRows(1);

				// List<Operation> ops = mettier.consulterOperationsParDate(codeCompte,date1,
				// date2);

				// List<Operation> ops = mettier.consulterOperations( "CC1",0,15);

				for (Iterator iterator = maintenances.iterator(); iterator.hasNext();) {
					Maintenance operation = (Maintenance) iterator.next();

					table2.addCell("" + operation.getId());
					table2.addCell("" + operation.getNameEquip());
					table2.addCell("" + operation.getNameUser());
					table2.addCell("" + operation.getDate());

				}
				table2.getDefaultCell().setBackgroundColor(BaseColor.GREEN);
				table2.addCell("" + "MAINTENANCE");
				table2.addCell("" + sizeMaint);
				table2.addCell("" + ".");
				table2.addCell("" + ".");

				document.add(table2);

				addSignature(document, userName);

				//// ----->>>> <<<-------------
				// addLogo(document);

				document.close();
			} catch (Exception e) {
				model.addAttribute("error", e.getMessage());

			}
			/// Trying Generete PDF
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}

			model.addAttribute("addFeadBack", new FeadBack());

			if (Desktop.isDesktopSupported()) {
				try {
					// File myFile = new File("C:/PDF/iTextHelloWorld.pdf");
					File myFile = new File(FILE);
					Desktop.getDesktop().open(myFile);
				} catch (IOException ex) {
					// no application registered for PDFs
				}

			}

			return "templatesViewsAdd/reportingRsEquip";

		}

		return "templatesViewsAdd/reportingRs";
	}

	@RequestMapping(value = "/getReportIndexBtn")
	public String getReportIndexBtn(Model model, @ModelAttribute("reporting") @Valid SearchForm searchForm) {

		System.out.println("--DATE1-->" + searchForm.getDate1());
		// model.addAttribute("AffectList",
		// mettier.getAllAfByKeyDate(searchForm.getDate1(),searchForm.getDate2()));
		model.addAttribute("editForm", new SearchForm());

		return "templatesViewsAdd/AffList";
	}

	@RequestMapping(value = "/addBesionBtn")
	public String addBesionBtn(Model model, @ModelAttribute("maintenance") @Valid Besoin besoin) {
		Date curDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat();

		String DateToStr = format.format(curDate);
		System.out.println("Default pattern: " + DateToStr);

		format = new SimpleDateFormat("yyyy-MM-dd");

		DateToStr = format.format(curDate);

		mettier.addBesion(new Besoin(besoin.getTitle(), besoin.getCategory(), besoin.getMotif(),
				besoin.getDescription(), DateToStr, "pending", "", besoin.getNameUser(), besoin.getIdUser(),besoin.getEmail()),
				besoin.getIdUser());

		model.addAttribute("besoinDetails", mettier.getAllBesion());
		model.addAttribute("searForm", new SearchForm());

		// return "registerCat";
		return "simpleUser/besoinList";

	}

	@RequestMapping(value = "/addfeadBack")
	public String addfeadBack(Model model, @ModelAttribute("searForm") @Valid SearchForm searchForm) {
		System.out.println("IDE->" + searchForm.getIdEquip());
		if (searchForm.getIdEquip() > 0) {
			model.addAttribute("idEquip", searchForm.getIdEquip());

		} else if (searchForm.getIdEquip() == 0) {
			model.addAttribute("btnSearch", "desapear");
		}

		model.addAttribute("searForm", new SearchForm());
		model.addAttribute("addFeadBack", new FeadBack());
		return "templatesViewsAdd/FeadBackAdd";
	}

	@RequestMapping(value = "/addFeadBackBtn")
	public String addfeadBackBtn(Model model, @ModelAttribute("addFeadBack") @Valid FeadBack feadBack) {

		Equipment equipment = mettier.getEquipment(feadBack.getIdEquip());

		if (equipment == null) {
			model.addAttribute("error", "..ERROR  l'ID de cette Equipement n'Existe Pas");
			model.addAttribute("addFeadBack", new FeadBack());
			return "templatesViewsAdd/FeadBackAdd";
		} else {

			mettier.addFeadBack(new FeadBack(feadBack.getDescriptionFead(), feadBack.getTypeFead(),
					feadBack.getNameUser(), feadBack.getIdUser(), feadBack.getIdEquip(), new Date()),
					feadBack.getIdEquip());

		}
		model.addAttribute("addFeadBack", new FeadBack());
		return "templatesViewsAdd/FeadBackAdd";
	}

	////////////////////////////////////////////////// PDF/////////////////////CONTROLLER
	////////////////////////////////////////////////// BUSINESS
	@RequestMapping(value = "/pdfTest")
	public String pdefTest(Model model, HttpServletResponse response) {

		/*
		 * try { Document document = new Document(); PdfWriter.getInstance(document, new
		 * FileOutputStream(FILE)); document.open(); addMetaData(document); //
		 * addTitlePage(document); String tile="RAPPORT D'AFFECTATION DES EQUIPMENT";
		 * addTitlePage(document,tile,"BIN","Date1","Date2");
		 * 
		 * addContent(document); document.close(); } catch (Exception e) {
		 * e.printStackTrace(); }
		 * 
		 * model.addAttribute("addFeadBack", new FeadBack());
		 * 
		 * if (Desktop.isDesktopSupported()) { try { File myFile = new
		 * File("C:/PDF/iTextHelloWorld.pdf"); Desktop.getDesktop().open(myFile); }
		 * catch (IOException ex) { // no application registered for PDFs } }
		 */

		return "templatesViewsAdd/FeadBackAdd";
	}

	///// pdf Methhode logic
	// iText allows to add metadata to the PDF which can be viewed in your Adobe
	// Reader
	// under File -> Properties
	public static void addMetaData(Document document) {
		document.addTitle("EquiSys Report");
		document.addSubject("Using iText");
		document.addKeywords("Java, PDF, iText");
		document.addAuthor("Bienfait Rukundo");
		document.addCreator("+243999 20 89 70");
	}

	public static void addTitlePage(Document document, String title, String UserName, String Start, String end)
			throws DocumentException {
		Paragraph preface = new Paragraph();
		// We add one empty line
		addEmptyLine(preface, 1);
		// Lets write a big header
		preface.add(new Paragraph(
				title + " \n---------------------------------------------------------------------------", catFont));

		addEmptyLine(preface, 1);
		// Will create: Report generated by: _name, _date
		preface.add(new Paragraph("Report generated by: " + UserName + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$
																							// //$NON-NLS-3$
				redFont));
		addEmptyLine(preface, 1);
		preface.add(new Paragraph("Pendant La Periode du  \t \t " + Start + "Au \t \t" + end, smallBold));

		addEmptyLine(preface, 1);
		document.add(preface);
		// Start a new page
		// document.newPage();
	}

	private static void addContent(Document document) throws DocumentException {

		createTable(document);

		// now add all this to the document
		// document.add(catPart);

	}

	// public static void createTable(Section subCatPart)
	public static void createTable(Document document) throws DocumentException {

		PdfPTable table = new PdfPTable(5);

		table.setWidthPercentage(100);
		// table.setBorderColor(BaseColor.GRAY);
		// t.setPadding(4);
		// t.setSpacing(4);
		// t.setBorderWidth(1);

		PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
		c1.setPaddingLeft(10);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Table Header xxxxx 2"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Table Header 3"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase("Table Header 4"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase("Table Header 5"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		table.addCell("1.0");
		table.addCell("1.1");
		table.addCell("1.2");
		table.addCell("2.1");
		table.addCell("2.2");
		table.addCell("2.3");
		table.addCell("2.2");
		table.addCell("2.3");

		document.add(table);
		// subCatPart.add(table);

	}

	public static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	public static void addLogo(Document document) throws MalformedURLException, IOException, DocumentException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		String path = classLoader.getResource("/image/equipSys.PNG").getPath();

		Image image1 = Image.getInstance(path);
		// logoPath.getClass().getResource("resources/image/equipSys.PNG");

		// Fixed Positioning
		image1.setAbsolutePosition(100f, 550f);
		// Scale to new height and new width of image
		image1.scaleAbsolute(200, 200);
		// Add to document
		document.add(image1);
	}

	public static void addSignature(Document document, String UserName)
			throws DocumentException, MalformedURLException, IOException {
		Paragraph preface = new Paragraph();
		preface.add(new Paragraph(" @ EquipSys printed buy " + UserName, subFont));
		document.add(preface);

	}

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

}
