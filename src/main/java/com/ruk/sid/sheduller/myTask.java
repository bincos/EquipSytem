package com.ruk.sid.sheduller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;

import com.ruk.entity.Planification;
import com.ruk.entity.SimpleUser;
import com.ruk.mettier.IEquipmentSysMettier;
import com.ruk.sid.controllers.SendEmailController;
import com.ruk.sid.form.SearchForm;

public class myTask {
	
	// pour faire l'injection de dépendance de la couche métier via spring
		@Autowired
		private IEquipmentSysMettier mettier;

		@Autowired
		private JavaMailSender mailSender; // for sending Mail
		private static URL logoPath;
		private List<String> myString;
		
	//@Scheduled(fixedDelay = 5000)
  //  @Scheduled(fixedRate = 30000)
	@Scheduled(cron="0 30 20 * * *")
	//@Scheduled(cron="0 41 09 * * *")
    public void demoServiceMethod()
    {
		/*
    	for (int i = 0; i <2; i++) {
    		  System.out.println("Method executed at every 5 seconds. Current time is :: "+ new Date());
			
		}
		*/
    	
    	 System.out.println("............................................................................. ");
    	 
    	 int size;		

 	//	String name = principal.getName();
 		List<Planification> planifications = mettier.listPlan();
 	
 		Date dateOn;
 		Planification planification=null;
 		int counter1=0,counter2=0;
 		String titleT3="",tacheCurent="";
 		
 		myString = null;
 	//	myString.clear();
 		String[] recipientArray = new String[0];
 		
 		//try sending email
 		 SendEmailController sendEmailController=new SendEmailController();

 		String sub = "EquipSys WARNING Task Palanification ";
 		String message="";

 		
         for (int i = 0; i <  planifications.size(); i++) { // trying to parcourt the list and testing day Calculator in it
         	planification=planifications.get(i);
         	
       //  	System.out.println("**** XXL --" + planification.getDate());
        // 	System.out.println(dateCalculator(planification.getDate()));
         				//try making a condition
         				if(dateCalculator(planification.getDate())==0) {
         					counter1++;
         					System.out.println("**Your Task is Today Boss --"+"\n tache Title"+planification.getTitle());
         					tacheCurent +=planification.getTitle()+";";
         					
         					/// Then Try sending Email
         					
         			          message = "EquipSys WARNING info \t \n************************************************************\n Bonjour\t"
         			  				+ planification.getNameUser() + "!\n"
         			  				+ " Vous avez une tache a executer  Todaye \n*************************************************************"
         			  				+ "\n TITRE  :\t " + planification.getTitle() + "\n DESCRIPTION: \t" + planification.getDescription()
         			  				+ " \n DATE EXECUTION: \t" + planification.getDate()
         			  				+ "\n----------------------------------------------------------------------------------------\n Gerereted buy Equip Sys BIN-PC \t "
         			  				+ new Date() + "\n----------------------------------------------------------------------------------------\n";

         			  		System.out.println(message);
         			  		try {
         			  		 sendEmailController.doSendEmail(planification.getEmail(), sub, message,       			  		 mailSender);
         			  		 
                  			
							} catch (Exception e) {
								// TODO: handle exception
								System.out.println(e.getMessage());
							}
         			 				
         				}else if (dateCalculator(planification.getDate())>=1 && dateCalculator(planification.getDate()) <=3) {
         					
         					counter2++;
         				//	System.out.println("**Your Task is IN MORE THAN 3 DAYS Save Your time Big Boss --\n"+planification.getTitle());
         					titleT3 +=planification.getTitle()+";";
         					/// Then Try sending Email
         					

       			          message = "EquipSys WARNING info \t \n************************************************************\n Bonjour\t"
       			  				+ planification.getNameUser() + "!\n"
       			  				+ " Vous avez  des  Tâches Planifié A executer  Avant 4 jours \n*************************************************************"
       			  				+ "\n TITRE  :\t " + planification.getTitle() + "\n DESCRIPTION: \t" + planification.getDescription()
       			  				+ " \n DATE EXECUTION: \t" + planification.getDate()
       			  				+ "\n----------------------------------------------------------------------------------------\n Gerereted buy Equip Sys BIN-PC \t "
       			  				+ new Date() + "\n----------------------------------------------------------------------------------------\n";

       			  		System.out.println(message);
       			  		
       			  		try {
       			  		 sendEmailController.doSendEmail(planification.getEmail(), sub, message,  mailSender);
          				
						} catch (Exception e) {
							// TODO: handle exception
							System.out.println(e.getMessage());
						}
       			  			
    						}
 		}
         
         
/*
         if (counter1>0) {
        	 message = "EquipSys WARNING info \t \n************************************************************\n Bonjour\t"
 	  				 + "!\n"
 	  				+ " Vous avez  des  Tâches A executer  To day \n*************************************************************"
 	  				+ "\n Nombre des Taches  :\t " +counter1 + "\n TITRE  DIFERANTES TACHES: \t" + tacheCurent
 	  				+ " \n DATE EXECUTION: \t" + new Date()
 	  				+ "\n----------------------------------------------------------------------------------------\n Gerereted buy Equip Sys BIN-PC \t "
 	  				+ new Date() + "\n----------------------------------------------------------------------------------------\n";
        	
        	 sendEmailController.doSendEmail(planification.getEmail(), sub, message, mailSender);
 	  		System.out.println(message);
		}else if (counter2>0) {
			 message = "EquipSys WARNING info \t \n************************************************************\n Bonjour\t"
 	  				 + "!\n"
 	  				+ " Vous avez  des  Tâches Planifié A executer  Avant 4 jours    \n*************************************************************"
 	  				+ "\n Nombre des Taches  :\t " +counter2 + "\n TITRE  DIFERANTES TACHES: \t" + titleT3
 	  				+ " \n DATE EXECUTION: \t" + new Date()
 	  				+ "\n----------------------------------------------------------------------------------------\n Gerereted buy Equip Sys BIN-PC \t "
 	  				+ new Date() + "\n----------------------------------------------------------------------------------------\n";
			 sendEmailController.doSendEmail(planification.getEmail(), sub, message, mailSender);
			 System.out.println(message);
		}
	        
	  	*/
	  		 
      //   System.out.println("**DATA COUNTER =TODAY=0->"+counter1+"--Cou IN  4 DAYS--<>"+titleT3);
     		    
     	
     
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
