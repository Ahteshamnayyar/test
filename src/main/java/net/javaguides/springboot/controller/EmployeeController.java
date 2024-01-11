package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.service.EmployeeService;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	
	private EmployeeService employeeService;

	
	  public EmployeeController(EmployeeService employeeService) { super();
	  this.employeeService = employeeService; }
	 
	
	//build create Employee RestAPI
	
	  @PostMapping() 
	  public ResponseEntity<Employee> saveEmployee(@RequestBody
	  Employee employee)
	  
	  { return new
	  ResponseEntity<Employee>(employeeService.saveEmployee(employee),HttpStatus.
	  CREATED); }
	 
	//build Get All Employees RestAPI	
	
	@GetMapping()
	public List<Employee> getAllEmployees()
	{
		return employeeService.getAllEmployees();
	}
	
	//build  Get Employee by ID RestAPI	
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id")long id)
	{
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(id),HttpStatus.OK);
	}
	
	//build update employee Rest API
	//http://localhost:8080/api/employees/1
	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee,@PathVariable("id") long id)
	{
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id),HttpStatus.OK);
	}
    //build delete employee Rest API
	//http://localhost:8080/api/employees/1
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id")long id)
	{
		employeeService.deleteEmployee(id);
		return new ResponseEntity<String>("Employee deleted",HttpStatus.OK);
	}
}
