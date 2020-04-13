package com.managing.employees.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.managing.employees.beans.Employee;
import com.managing.employees.utils.Useful;

public class EmployeeImplement implements EmployeeInterface {
	private Factory factory;

	public EmployeeImplement(Factory factory) {
		this.factory = factory;
	}
	@Override
	public void DelEmployee(String id) {
		Connection connexion = null;
	     PreparedStatement preparedStatement = null;
	     try {
	         connexion = factory.getConnection();
	         preparedStatement = connexion.prepareStatement("delete from employees WHERE id=?");
	         preparedStatement.setString(1,id);
	         preparedStatement.executeUpdate();
	     } catch (SQLException e) {
	         e.printStackTrace();
	     }
		
	}
	@Override
	public void EditEmployee(Employee employee) {
		Connection connexion = null;
	     PreparedStatement preparedStatement = null;
	     try {
	         connexion = factory.getConnection();
	         preparedStatement = connexion.prepareStatement("update employees set name=?, tel=?, HireDate=?, salary=? WHERE id=?");
	         preparedStatement.setString(1, employee.getName() );
	         preparedStatement.setString(2, employee.getTel());
	         Date  HireDate = new Date(employee.getHireDate().getTime());
	         preparedStatement.setDate(3, HireDate);
	         preparedStatement.setDouble(4, employee.getSalary());
	         preparedStatement.setString(5, employee.getId());
	         preparedStatement.executeUpdate();
	     } catch (SQLException e) {
	         e.printStackTrace();
	     }
		
	}
	@Override
	public Employee getEmployee(String id) {
		Connection connexion = null;
		 ResultSet resultat = null;
		 Employee employee = new Employee();
		 PreparedStatement preparedStatement = null;
		 try {
	         connexion = factory.getConnection();
	         preparedStatement = connexion.prepareStatement("SELECT * FROM employees WHERE id=?");
	         preparedStatement.setString(1, id);
	         resultat = preparedStatement.executeQuery();
	         while (resultat.next()) {
	        	 Date  HireDate = new Date(resultat.getDate("HireDate").getTime());
				employee.setId(resultat.getString("id"));
				employee.setName(resultat.getString("name"));
				employee.setEmail(resultat.getString("email"));
				employee.setTel(resultat.getString("tel"));
				employee.setHireDate(HireDate);
				employee.setSalary(Double.parseDouble(resultat.getString("salary")));
	            }
	     } catch (SQLException e) {
	         e.printStackTrace();
	     }
		return employee;
	}
	@Override
	public List<Employee> all() {
		Connection connexion = null;
		 Statement statement = null;
		 ResultSet resultat = null;
		 List<Employee> employees = new ArrayList<Employee>();
		 try {
			connexion = factory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM employees");
			while (resultat.next()) {
				Employee  employee = new Employee();
				Date  HireDate = new Date(resultat.getDate("HireDate").getTime());
				employee.setId(resultat.getString("id"));
				employee.setName(resultat.getString("name"));
				employee.setEmail(resultat.getString("email"));
				employee.setTel(resultat.getString("tel"));
				employee.setHireDate(HireDate);
				System.out.println("::::"+HireDate.toString());
				employee.setSalary(Double.parseDouble(resultat.getString("salary")));
				employees.add(employee);
				
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return employees;
	}
	@Override
	public void newEmployee(Employee employee) {
		Connection connexion = null;
	     PreparedStatement preparedStatement = null;
	     try {
	         connexion = factory.getConnection();
	         preparedStatement = connexion.prepareStatement("insert into employees(name, email, tel, HireDate, salary) values(?,?,?,?,?)");
	         preparedStatement.setString(1, employee.getName() );
	         preparedStatement.setString(2, employee.getEmail());
	         preparedStatement.setString(3, employee.getTel());
	         Date  HireDate = new Date(employee.getHireDate().getTime());
	         preparedStatement.setDate(4, HireDate);
	         preparedStatement.setDouble(5, employee.getSalary());
	         preparedStatement.executeUpdate();
	     } catch (SQLException e) {
	         e.printStackTrace();
	     }
		
	}
	@Override
	public boolean employeeExist(String email) {
		 boolean exist = false;
		 Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultat = null;
		 try {
	         connexion = factory.getConnection();
	         preparedStatement = connexion.prepareStatement("SELECT email FROM employees WHERE email=?");
	         preparedStatement.setString(1, email);
	         resultat = preparedStatement.executeQuery();
	         while (resultat.next()) {
	        	 exist = true;
	            }
	     } catch (SQLException e) {
	         e.printStackTrace();
	     }
		return exist;
	}
}
