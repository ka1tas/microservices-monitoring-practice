package com.monitoring.microservices.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class EmployeeController {
	@Autowired
    RestTemplate restTemplate;
  
    @GetMapping(value = "/employees/{employeeid}")
    @HystrixCommand(fallbackMethod = "fallbackMethodgetEmployee")
    public String getStudents(@PathVariable int employeeid)
    {
        System.out.println("Getting Employee details for " + employeeid);

    
     
        
        String response = restTemplate.exchange("http://employee-service/employees/{employeeId}",
                                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, employeeid).getBody();
  
        System.out.println("Response Body " + response);
  
        return "Employee Id -  " + employeeid + " [ Employee Details " + response+" ]";
    }
    
    


     
    public String  fallbackMethodgetEmployee(int employeeid){
         
        return "Fallback response:: No employee details available temporarily";
    }
  
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
