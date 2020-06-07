package com.controller;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.SessionStatus;

import com.dao.EmpDao;
import com.model.EmployeeVO;

@Controller
@RequestMapping("/api")  //SpringMVC/mvcexample/api
@CrossOrigin(origins = "*") //Dealing with CORS issues
public class EmpController {
	
	private EmpDao empDao;
	private Validator validator;

	
	public EmpController() {
		super();
		
		System.out.println("Constructor init");
	}
	
	
	
	
	
	@Autowired
	public EmpController(EmpDao empDao) {
		super();
		this.empDao = empDao;
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();
	}






	@ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
//	@RequestMapping(value = "/getAllPlanets", method = RequestMethod.GET) //SpringMVC/mvcexample/api/getAllPlanets
	@GetMapping(value = "/getAllEmployees")
	public @ResponseBody List<EmployeeVO> getAllEmployees() {
		System.out.println("Inside GetAllEmployees!");
		return empDao.selectAll();
	}
	
	@PostMapping(value = "/getEmpById")
	public @ResponseBody EmployeeVO getEmpById(@RequestParam("id") int num) {
		return empDao.selectById(num);
	}
	
	@PostMapping(value = "/getEmpById2", produces = "application/json", params = {"id"})
	public ResponseEntity<EmployeeVO> getEmpById2(int id){
		return new ResponseEntity<EmployeeVO>(empDao.selectById(id), HttpStatus.I_AM_A_TEAPOT);
	}
	
	@GetMapping(value = "/getEmpById3/{id}")
	public @ResponseBody EmployeeVO getPlanetByURI(@PathVariable("id") int id) {
		return empDao.selectById(id);
	}
	
	@PutMapping(value = "/createEmployee")
	public @ResponseBody String createNewEmployee(@RequestBody EmployeeVO incomingEmp, BindingResult result, SessionStatus status) {
		
		/*
		 * If an incoming JSON doesn't have ALL the necessary planet model fields, then Spring will simply give the misssing 
		 * model fields their default values. ALSO, if the incoming JSON has MORE fields than the Planet model, then it'll simply 
		 * ignore the fields. 
		 */
		System.out.println("Create Put method ran" + incomingEmp.toString());
		
		
		
		
		
	    Set<ConstraintViolation<EmployeeVO>> violations = validator.validate(incomingEmp);

	    int myStatus = -1;
	    
	    for (ConstraintViolation<EmployeeVO> violation : violations)

	    {

	        String propertyPath = violation.getPropertyPath().toString();

	        String message = violation.getMessage();

	        

	        

	        result.addError(new FieldError("employee",propertyPath,

	                               "Invalid "+ propertyPath + "(" + message + ")"));
	        
	        /**
	         * this is not detecting null though !!
	         */
	        if (violation.getMessage() == null) {
				myStatus = 1;
			}

	    }

	    if (result.hasErrors()) {
	    	
	    	if (myStatus == 1) {
				System.out.println("Hey one of the Key's was likely incorrect");
			}

	        //return "addEmployee";
	    	return result.toString();

	    }

	    

	    

	    

	    status.setComplete();

	    //return "redirect:addNew/success";
		
		
	    empDao.insert(incomingEmp);
		
		return "Success";
	}
	
	@DeleteMapping(value = "/deletingEmployee")
	public @ResponseBody String deleteEmployee(@RequestBody EmployeeVO emp) {
		
		empDao.delete(emp);
		
		return "Employee Successfully Destroyed";
	}
	
	
	

}
