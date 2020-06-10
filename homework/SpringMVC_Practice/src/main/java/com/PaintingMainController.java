package com;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@Controller
@RequestMapping("/api")  //SpringMVC_Practice/api
@CrossOrigin(origins = "*") //Dealing with CORS issues
public class PaintingMainController {
	
	private PaintingDAO paintingDAO;
	
	// this is a constructor for the session factory that we just made?...singleton.
	@Autowired
	public void PaintingDAOGenerator(PaintingDAO paintingDAO) {
		this.paintingDAO = paintingDAO;
	}
	
	// CREATE
	@PostMapping(value = "/addNewPainting")
	public @ResponseBody String addNewPainting(@RequestBody Painting incomingPainting) {
		paintingDAO.insert(incomingPainting);
		return "A new painting was added.";
	}
	
	// READ
	@RequestMapping(value = "/listAllPaintings", method = RequestMethod.GET)
	public @ResponseBody List<Painting> getAllPlanets() {
		System.out.println("List all paintings is running");
		return paintingDAO.selectAll();
	}
	
	// Update
	@PutMapping(value = "/editPaintingDetails")
	public @ResponseBody String editPaintingDetails(@RequestBody Painting selectedPainting) {
		paintingDAO.editPainting(selectedPainting);
		return "Painting Updated.";
	}
	
	// Delete
	@DeleteMapping(value = "/deletePainting")
	public @ResponseBody String deletePainting(@RequestBody Painting painting) {
		paintingDAO.delete(painting);
		return "Planet " + painting.toString() + " deleted";
	}
}
