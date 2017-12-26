package com.ruk.sid.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruk.mettier.IEquipmentSysMettier;


@Controller
@RequestMapping(value="/admin")
public class AdminController {
	@Autowired
	private IEquipmentSysMettier mettier; 
	
	
}
