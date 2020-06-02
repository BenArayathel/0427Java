package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.model.Planet;

/*
 * Stereotypes are annotations that tell Spring that THIS class is a Spring bean and should be treated as such
 * 
 * There is a hierarchy to these annotations: @Component
 * 	@Component: used for a basic java class that doesn't need to be a bean that fits into a predetermined spot (in this design pattern)
 * Children:
 * 	@Service: used for service classes (i.e. service layer)
 * 	@Repository: used for repositories/dao classes (the dao layer
 * 	@Controller: used for controller classes (i.e. the controller layer)
 * 	@Configuration: used for classes that have functionality to configure Spring
 * 		In short there is a way to configure spring without relying on beans.xml
 * 	@RestController: Same as controller, but assumes your API will be RESTFUL API
 */

@Component//Stereotypes don't NEED to be given more metadata if you don't want to, but you should at least name it
public class PlanetDaoImpl implements PlanetDao {

	@Override
	public List<Planet> selectAll() {
		List<Planet> pList = new ArrayList<>();
		pList.add(new Planet(1, "Earth"));
		pList.add(new Planet(2, "Mars"));
		pList.add(new Planet(3, "Mercury"));
		return pList;
	}

}
