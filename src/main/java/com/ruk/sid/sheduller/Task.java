package com.ruk.sid.sheduller;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("myBean")
public class Task {
	
	/* 
	   * The method will run on 13:40:00 everyday.
	   * cron format: sec min hour day mon week
	   */
	  //@Scheduled(cron="0 40 13 * * *")
	@Scheduled(cron="0 11 19 * * *")
	  public void runTask() {
	    System.out.println("Runing at Programed by Ruk " + new Date());
	  }

}
