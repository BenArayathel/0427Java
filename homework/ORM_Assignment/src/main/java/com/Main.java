package com;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
	
	// grabs the context from my spring xml file
	public static ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
	
	// name it whatever???, but we use the application context to access the bean we made earlier
	public static PaintingDAO paintingDAO = ac.getBean("paintingDAO", PaintingDAO.class);

	public static void main(String[] args) {
		
		// make some objects to store
		paintingDAO.insert(new Painting(0, "Portrait 1", "Watercolor"));
		paintingDAO.insert(new Painting(0, "Abstract #6", "Oil"));
		paintingDAO.insert(new Painting(0, "Overpriced Gibberish", "Watercolor"));
		
		// read them back
		System.out.println(paintingDAO.selectAll());
		
		// delete one then read back again
		paintingDAO.delete(new Painting(1, "Portrait 1", "Watercolor"));
		// for this test system, we have to use the exact id that it is generating
		// with the @GeneratedValue(strategy = GenerationType.AUTO) line
		// in a real context we'd access the actual painting by name with another
		// method
		System.out.println("After deleting " + paintingDAO.selectAll());
		
		// update one
		paintingDAO.editPainting(new Painting(2, "Abstract #7!!", "Oil and Mixed Media"));
		System.out.println("After update " + paintingDAO.selectAll());
		
	}

}
