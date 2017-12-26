package com.ruk.sid.controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.ruk.mettier.IEquipmentSysMettier;
import  com.ruk.entity.*;

@Controller
@SessionAttributes("profileInSession")
public class LogRegisterController  {
	// pour faire l'injection de dépendance  dew la couche métier via spring
		
	@Autowired
	private IEquipmentSysMettier mettier;
	
	

	 @Autowired
	    private JavaMailSender mailSender;  //for sending Mail
		
	 @RequestMapping(value="/login")
		public String login(Model model,String logout,String denied ){
		 String message="";
		 if (logout != null) {  
			   message = "Logged Out successfully, login again to continue !";  
			  model.addAttribute("loginStatus", message);
			  } 
		 else if (denied != null) {  
			   message = "Access denied for this user Please LogIn !"; 
			   model.addAttribute("loginStatus", message);
			  }  
		 
			//return "loginUser";
			return "LogRegister/login";
		}
	 
	 @RequestMapping(value="/logout")
		public String logout(Model model ){
		
			//return "loginUser";
		 return "LogRegister/login";
		}
	
	 @RequestMapping(value="/fail2login", method = RequestMethod.GET)  
	 public String loginerror(ModelMap model) {  		   
	  model.addAttribute("error", "true");  
	 // return "loginUser";  
	  return "LogRegister/login";
	   
	 }  
	
	 @RequestMapping("403page")  
	 public String ge403denied() {  
	  return "redirect:/login?denied";  
	 }  
	
	 @RequestMapping(value="/checkingRole") // this methodes has juste tagrget on the applicationContext.xml
	 protected View welcome(Model model, Principal principal) {

	        Set<String> roles = AuthorityUtils
	                .authorityListToSet(SecurityContextHolder.getContext()
	                        .getAuthentication().getAuthorities());
	        if (roles.contains("ROLE_ADMIN_USER")) {
	        	 
	                   return new RedirectView("adminUser/index");
	        }
	        
	         else if (roles.contains("ROLE_ADMIN_SIMPLE_USER")) {
	        		
													            return new RedirectView("adminSimpleUser/index");
			} 
	        {
				  return new RedirectView("simpleUserFront1");
			}		       
	        
	    }
			
	 
	@RequestMapping(value="/simpleUserHome1")
	public String userHome(Model model){
		
	
		return "simpleUserFront1"; 
	}	
	@RequestMapping(value="/userHome")
	public String home(Model model){
		
	
		return "userFrontPage"; 
	}	
	
		@RequestMapping(value="/registerSimpleUser")
		public String userSearcn(Model model){
			
			System.out.println("******opening Register Page*********" );
			model.addAttribute("register", new SimpleUser());
		//	return "registerSimpleUser"; 
		 return "LogRegister/registerSimpleUser";
		}
		
		@RequestMapping(value="/registerFullUser")
		public String registerUser(Model model){
		
			model.addAttribute("registerUser", new SimpleUser());
			return "LogRegister/registerUser";
		}
		
		
					
		@RequestMapping(value="/registerBtn")
		public String userSearcnTest(Model model,@ModelAttribute("register")@Valid SimpleUser  simpleUser,  BindingResult bindingResult,MultipartFile file)throws IOException{
			
			if( bindingResult.hasErrors()){
				System.out.println("File hase erro man quick...........");	
				System.out.println("**** error***"+ bindingResult.getAllErrors());
			
			//	model.addAttribute("register", new SimpleUser(simpleUser.getNameUser(),simpleUser.getEmail(),simpleUser.getPassword(),true,simpleUser.getAdressUser(),simpleUser.getPhoneUser(),simpleUser.getDescriptionUser(),"ROLE",file.getBytes(),file.getOriginalFilename(),simpleUser.getFunction()));
				//return "registerSimpleUser";
				 return "LogRegister/registerSimpleUser";
				
			}
			
			if(!file.isEmpty()){
				//pr verifier si cet un vrai photo
				BufferedImage bi=ImageIO.read(file.getInputStream());
			
				simpleUser.setPhoto(file.getBytes());/// car cet un tab de byrte/
				simpleUser.setPhotoName(file.getOriginalFilename());// et le nom orig dela photo
			}
			
			
			try {
				String roleName="ROLE_ADMIN_USER";
				
				mettier.addSimpleUser(new SimpleUser(simpleUser.getNameUser(),simpleUser.getEmail(),simpleUser.getPassword(),true,
			simpleUser.getAdressUser(),simpleUser.getPhoneUser(),simpleUser.getDescriptionUser(),roleName,file.getBytes(),file.getOriginalFilename(),new Date(),simpleUser.getFunction()));
				
		
			
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("****File uploader error***");
				e.printStackTrace();
			}
			
			/// Then trying To Send Msg to the User
			
			/// trying to SEND EMAIL TO TE USER FOR HIS DATA 
			//initial date Info to be sent
					
			SendEmailController sendEmailController=new SendEmailController();
						
							String sub="EquipSys Information User Data";
							
							String message="EquipSys Data info \t \n-------------------------------------------------\n Bonjour\t"+simpleUser.getNameUser()+"!\n"+
									"Vos Information Relative a votre compte EquipSy garder votre Mot de pass Jalousement!\n============================"+
									"\n NOM: "+simpleUser.getNameUser()+"\n PHONE: "+simpleUser.getPhoneUser()+"\n UserName:"+simpleUser.getEmail()+"\nPassWord:"+simpleUser.getPassword()+
									"\n============================================================================\n Gerereted buy Equip Sys \t "+new Date()+
									"\n============================================================================\n";
											
			try {
				sendEmailController.doSendEmail(simpleUser.getEmail(), sub, message, mailSender);
				
			} catch (Exception e) {
				model.addAttribute("error","Error d'envoi Mail confirmation verifier votre Connection Internet!S");
				System.out.println(e.getMessage());
			}
			
			
			model.addAttribute("register", new SimpleUser());
			
		//	return "registerSimpleUser"; 
			return "LogRegister/login";
			
		}
		
		@RequestMapping(value="/registerUser")
		public String userRegister(Model model){
			
			System.out.println("******opening Register Page*********" );
			model.addAttribute("registerUser", new SimpleUserController());
		//	return "registerSimpleUser"; 
		 return "LogRegister/registerUser";
		}
		
		@RequestMapping(value="/registerFullUserBtn")
		public String registerUserbtn(Model model,@ModelAttribute("registerUser")@Valid Fournisseur  fournisseur,  BindingResult bindingResult){
			
			if( bindingResult.hasErrors()){
				System.out.println("File hase erro man quick...........");					
				return "registerUser";
				
			}
			
			System.out.println("**----------------------------------------------");
			/*
			fullUser.setActived(true);
			
			System.out.println("*************Statuts"+ fullUser.isActived() +"\n" + fullUser.getEmail());
			fullUser.setActived(true);
			String roleName="ROLE_ADMIN_USER";
							
			metierAdmin.addFullUser(new FullUser(fullUser.getUserName(), fullUser.getEmail(), fullUser.getPassword(), fullUser.isActived(),
					roleName, fullUser.getNameUser(), fullUser.getFirstName(), fullUser.getFaculte()));
			System.out.println("***Register new user Succefull****");	
			
			model.addAttribute("registerUser", new FullUser());
			*/
			return "LogRegister/login";
		}	
		

}
