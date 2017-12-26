package com.ruk.pdfView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.ruk.entity.Affectation;
import com.ruk.entity.Category;
import com.ruk.mettier.IEquipmentSysMettier;


public class CreatePdf {


	// pr fair l'injection de dépendance from mettier
	@Autowired
	private IEquipmentSysMettier mettier; 
	
	
	
	
	private static Font TIME_ROMAN = new Font(Font.FontFamily.TIMES_ROMAN, 18,Font.BOLD);
	private static Font TIME_ROMAN_SMALL = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	 
	/**
	* @param args
	*/
	
	public static Document createPDF(String file) {
		
	
	Document document = null;
		try {
	document = new Document();
	PdfWriter.getInstance(document, new FileOutputStream(file));
	document.open();
	 
	addMetaData(document);
	 
	addTitlePage(document);
	 
	createTable(document);
	document.close();
	 
	} catch (FileNotFoundException e) {
	 
	e.printStackTrace();
	} catch (DocumentException e) {
	e.printStackTrace();
	}
	return document;// this is my retrun Result
	 
	}
	 
	// Differents Méthodes
	private static void addMetaData(Document document) {
	document.addTitle("Generated PDF report");
	document.addSubject("Generated PDF Report");
	document.addAuthor("Java Honk");
	document.addCreator("Java Honk");
	}
	 
	private static void addTitlePage(Document document)
	throws DocumentException {
	 
	Paragraph preface = new Paragraph();
	creteEmptyLine(preface, 1);
	preface.add(new Paragraph("PDF Rélevet Compte Banquaire", TIME_ROMAN));
	 
	creteEmptyLine(preface, 1);
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
	preface.add(new Paragraph("Report created on "
	+ simpleDateFormat.format(new Date()), TIME_ROMAN_SMALL));
	document.add(preface);
	 
	}
	 
	private static void creteEmptyLine(Paragraph paragraph, int number) { /// Méthode pour créé un Esapce ligne
	for (int i = 0; i < number; i++) {
	paragraph.add(new Paragraph(" "));
	}
	}
	 
	
	private static void createTable(Document document) throws DocumentException {
	Paragraph paragraph = new Paragraph();
	creteEmptyLine(paragraph, 2);
	document.add(paragraph);
	PdfPTable table = new PdfPTable(3);// on créé un  tableau à 3 colone
	
	/*Olde Code Worked
	
	PdfPCell c1 = new PdfPCell(new Phrase("First Name"));
	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	table.addCell(c1);
	 
	c1 = new PdfPCell(new Phrase("Last Name"));
	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	table.addCell(c1);
	 
	c1 = new PdfPCell(new Phrase("Test"));
	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	table.addCell(c1);
	
	table.setHeaderRows(1);
	 	for (int i = 0; i < 5; i++) { /// ici on incrémente les donné à remplir dans le tableau
	table.setWidthPercentage(100);
	table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
	table.addCell("Java");
	table.addCell("Honk");
	table.addCell("Success");
	}
	 
	document.add(table);
	}
	 
		
	} */  //-- End Olde Code

	
	PdfPCell c1 = new PdfPCell(new Phrase("Date Opération"));
	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	table.addCell(c1);
	 
	c1 = new PdfPCell(new Phrase("Numéro Opération"));
	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	table.addCell(c1);
	 
	c1 = new PdfPCell(new Phrase("Type Doperation"));
	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	table.addCell(c1);
	
	c1 = new PdfPCell(new Phrase("Montant"));
	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	table.addCell(c1);
	
	table.setHeaderRows(1);
	System.out.println("***********Début Accessing DAO from Liste Operation**************");
	///Star New code trying to load data from DataBase
	//	List<Category> ops = mettier.consulterOperations("CC1",0,5);// Ici c'eat à dire qu'on va prendre 5 element par page
	/*
	Affectation affectation=mettier.getAffectation(form.getIdEquip());
	
		
	// operation
	for (Iterator iterator = ops.iterator(); iterator.hasNext();) {
		Operation operation = (Operation) iterator.next();
	
		table.setWidthPercentage(100);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(""+ operation.getDateOperation());
		table.addCell(""+operation.getNumeroOperation());
		table.addCell(""+ operation);
		
		System.out.println("***Addind raws data***");
	}
	 	*/
	
	 
	document.add(table);
	}
	 
		
	}
