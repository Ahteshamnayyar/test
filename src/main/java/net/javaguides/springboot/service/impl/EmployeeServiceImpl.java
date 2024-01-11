package net.javaguides.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository employeerepository;
	
	
	public EmployeeServiceImpl(EmployeeRepository employeerepository) {
		super();
		this.employeerepository = employeerepository;
	}


	@Override
	public Employee saveEmployee(Employee employee) {
		
		return employeerepository.save(employee);
	}


	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeerepository.findAll();
	}


	@Override
	public Employee getEmployeeById(long id) {
		// TODO Auto-generated method stub
		//Optional<Employee> employee = employeerepository.findById(id);
		
		/*if(employee.isPresent())
		{
			return employee.get();
			
			
		}
		else {
			throw new ResourceNotFoundException("Employee","Id",id);
		}*/
		
		return employeerepository.findById(id).orElseThrow(()-> 
		                                                   new ResourceNotFoundException("Employee","Id",id));
}


	@Override
	public Employee updateEmployee(Employee employee,long id) {
		// TODO Auto-generated method stub
		// we need to check whether employee with given id exists in database or not.

		Employee existingEmployee =  employeerepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee","Id",id));
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());;
		existingEmployee.setEmail(employee.getEmail());
		//save existing employee to db
		employeerepository.save(existingEmployee);
		return existingEmployee;
		
	}


	@Override
	public void deleteEmployee(long id) {
		//check whether a employee exists in a DB or not
		employeerepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee","Id",id));
		
		employeerepository.deleteById(id);
	}
}
