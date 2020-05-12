package com.monitoring.microservices.employeeservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.monitoring.microservices.employeeservice.bean.Employee;

@RestController
public class EmployeeRestController {
	
	private Logger LOGGER =  LoggerFactory.getLogger(this.getClass());
	
	private static final Map<Integer,Employee> employeeData = new HashMap<Integer , Employee>();
	
	static {
	employeeData.put(111 , new Employee("Saikat" , 111));
	employeeData.put(222 , new Employee("Sam" , 222));
	}

	
	 @GetMapping(value = "/employees/{employeeId}")
	 public Employee getEmployeeDetails(@PathVariable Integer employeeId) {
		 
		LOGGER.info(" Employee details for " + employeeId);
		  
	        Employee employee = employeeData.get(employeeId);
	        if (employee == null) {
	             
	            employee = new Employee( "N/A" , 0);
	        }
	        return employee;
	    }
		 

}