package com.sid.vboot;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.mail.internet.InternetAddress;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.ruk.entity.Besoin;
import com.ruk.entity.Equipment;
import com.ruk.entity.FeadBack;
import com.ruk.entity.Fournisseur;
import com.ruk.entity.Notification;
import com.ruk.entity.Planification;
import com.ruk.entity.Planifications;
import com.ruk.entity.SimpleUser;
import com.ruk.mettier.EquipmentSysMetier;
import com.ruk.mettier.IEquipmentSysMettier;

public class Mytest {
	ClassPathXmlApplicationContext    context= 
			new ClassPathXmlApplicationContext(new String[]{"aplicationContext.xml"});;

    @Before
public void setUp() {
    	ClassPathXmlApplicationContext    context= new ClassPathXmlApplicationContext(new String[]{"aplicationContext.xml"});;
}

@After
public void tearDown() {
System.out.println("----Tesrtin SYstem Apss");
}
	


@Test
public  void main(){
	System.out.println("**************************");
	try {
		

		IEquipmentSysMettier mettier =  (IEquipmentSysMettier) context.getBean("metier");
			//on veut verifier si lajout dune categorie est correcte
						
					//	mettier.addNotification(new Notification("this is for You", "you are going todie", new Date(), "NULL"));
						//mettier.addBesion(new Besoin("TITLE", "category", "motif","Pending","description",new Date()),12);
					//	mettier.getAllEquipmet();
					//	mettier.addEquipment( new Equipment("Clavier",new Date(),"243MJG","inStock","LENOVO","this for Part only",2018,null,"myPic"),1,1 );
						mettier.addPlan( new Planifications(12, "RUK", "THIS IS", new Date(), "myTYpe", "email@gmail.com", "BIN-PC"));
					
						mettier.addPlan( new Planification(12, "RUK", "THIS IS", "mySDescription",new Date(), "myTYpe", "email@gmail.com", "BIN-PC","simpleUserS"));
						
						
						mettier.addFeadBack(new FeadBack("this is My Fead", "positive", "OMA", 12, 23, new Date()), 2);
						/*
						SimpleUser simpleUser=mettier.getSimpleUser(1);
						System.out.println("--DATA----"+simpleUser.getEmail());
					*/	
						System.out.println("---------");
						
						assertTrue("suucce.....",true);
						System.out.println("*********XXXXXXXXXXXXXX");
		
	} catch (Exception e) {
		// TODO: handle exception
		assertFalse(e.getMessage(),true);
	}
	
}


//@Test
	public void test2() {
		try {
			 
			JavaMailSender mailSender= (JavaMailSender) context.getBean("mailSender");
			 String recipientfull;
			 String [] mailAdresseTo= {"rukundobinos@gmailcom","bienfaitrukundo@gmail.com"};
			 //creating message  
			    SimpleMailMessage message = new SimpleMailMessage();  
			        message.setFrom("bienfaitrukundo@gmail.com");  
			    message.setTo(mailAdresseTo);//passing array of recipients  
			    message.setSubject("HelloMultople");  
			    message.setText("My MultiPart");  
			       //sending message  
			    
			    mailSender.send(message);     
				// agffectation du reste du corp du message from
				
				InternetAddress[] mailAddress_TO = new InternetAddress [mailAdresseTo.length] ;
		         for(int i=0;i<mailAdresseTo.length;i++){
		             mailAddress_TO[i] = new InternetAddress(mailAdresseTo[i]);
		         }
		       
				 System.out.println("***********************************************************************************");
			        System.out.println("***sending Mail Please waite...**");
				// sends the e-mail
			//	mailSender.send(email);				 		       
			        System.out.println("*******Sending Mail succefull*************");
				
			      
		} catch (Exception e) {
			
			   System.out.println("****+++++++++++***"+e.getMessage());
		}
		
		System.out.println("-----------------------------------");
		assertTrue(true);
	}
}
