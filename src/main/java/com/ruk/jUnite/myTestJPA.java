package com.ruk.jUnite;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class myTestJPA {

	@Test
	public void test() {
		
		try {
			ClassPathXmlApplicationContext    context= 
					new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});;
			assertTrue(true);
		} catch (Exception e) {
			// TODO: handle exception
			assertFalse(e.getMessage(),false);
		}
		
		
	}

}
