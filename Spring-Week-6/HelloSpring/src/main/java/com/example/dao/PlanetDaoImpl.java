package com.example.dao;

import java.util.ArrayList;
/*
 * Stereotype are annotaions that tell Spring that THIS class is a Spring bean and should be treated as such 
 * 
 * There is a hierarchy to these annotations. The ancestor is @Component
 * 
 * @Component is used for a basice java class, that doesn't need to be a bean that fits into a predetermined spot 
 * 
 * There are varouds types of children stereotypes:
 * 
 * 	@Service : used for service classes (service layer)
 * 	@Repository: used for repositories/dao classes (the dao layer)
 * 	@Controller used for controller classes (the controller layer)
 * 	@Configuration: is used for classes that have functionality to figure Spring 
 * 		(in short there is a way to configure spring without relying on the beans.xml)
 * 	@RestController: is the same as controller but it assumes your API will be a RESTFUL API
 */
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.model.Planet;

@Component //stereos don't NEED to be given more metadat if you don't want, but you should at least name it. 
public class PlanetDaoImpl implements PlanetDao{
	
	

	public List<Planet> selectAll() {
		
		List<Planet> pList = new ArrayList<>();
		
		pList.add(new Planet(1, "Earth"));
		pList.add(new Planet(2, "Mars"));
		pList.add(new Planet(3, "Mercury"));
		
		return pList;
		
	}

}
