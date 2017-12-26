package com.sid.vboot;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import javax.validation.constraints.AssertTrue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


import org.springframework.context.support.ClassPathXmlApplicationContext;
public class TestJPA {
	
	
		    @Before
	    public void setUp() {
		    	ClassPathXmlApplicationContext    context= new ClassPathXmlApplicationContext(new String[]{"aplicationContext.xml"});;
	    }
	    
	    @After
	    public void tearDown() {
}
}