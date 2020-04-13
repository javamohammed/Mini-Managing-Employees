package com.managing.employees.db;

import java.util.List;

import com.managing.employees.beans.Employee;

public interface EmployeeInterface {

	public void newEmployee(Employee employee);
	public void EditEmployee(Employee employee);
	public void DelEmployee(String id);
	public boolean employeeExist(String email);
	public List<Employee> all();
	public Employee getEmployee(String id);
}
