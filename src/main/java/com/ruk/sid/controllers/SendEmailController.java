package com.ruk.sid.controllers;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
	
	//@Controller
///	@RequestMapping("/sendEmail")
	public class SendEmailController {
		
	 
		public SendEmailController() {
			super();
		}

		private    String recipientfull;
	//    @Autowired
	//    private JavaMailSender mailSender; // ce lui-ci  est une injection de  notre bean 

	    @Autowired
	//	private IvbootMetierUserImpl metierUserImpl;
	  				
	//    @RequestMapping(method = RequestMethod.POST)
	    public String doSendEmail(String recipient,  String subject,String message,JavaMailSender mailSender) {
	    	    		    	
	        // takes input from e-mail form
	   //     String recipientAddress = recipient;
	     //   String subj = subject;
	     //   String message = message;
	       // String message1 = request.getParameter("phone");
	        
	        
	  //  	List<String> recipientListe = metierUserImpl.getEmailListByFacultey("FSTA");
	        List<String> recipientListe = null;

			// creates a simple e-mail object
			SimpleMailMessage email = new SimpleMailMessage();
			/*

			for (Iterator iterator = recipientListe.iterator(); iterator.hasNext();) {
				String adressMail = (String) iterator.next();

				recipientfull = adressMail + ",";

				email.setTo(recipientfull);

				System.out.println("Data Loaded from ListCompte+++: --"
						+ recipientfull);

			}
	        */
	        // agffectation du reste du corp du message from
			email.setTo(recipient);
	        email.setSubject(subject);
	        email.setText(message);
	         
	        // sends the e-mail
	        mailSender.send(email);
	        
	        System.out.println("Data : --" + recipientfull );	            
	         
	        // prints debug info
	        System.out.println("***********************************************************************************");
	        System.out.println("To: " + recipient);
	        System.out.println("Subject: " + subject);
	        System.out.println("Message: " + message);
	         
	        System.out.println("***********************************************************************************");
	        System.out.println("***sending Mail Please waite...**");
	        	       
	        System.out.println("*******Sending Mail succefull*************");
	        // forwards to the view named "Result"
	        return "resultatEmailSent";
	    }
	    
	    public String generatePassWord() {
	    	

	        String alphabet1 = 
	                new String("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"); //9
	        int n = alphabet1.length(); //10

	        String result = new String(); 
	        Random r = new Random(); //11

	        for (int i=0; i<5; i++) { //12  // Random from 1-5 Caracte
	            result = result + alphabet1.charAt(r.nextInt(n)); //13
	        }
	       return result;
	      
	    }
	    
	    
	    
	    

}
