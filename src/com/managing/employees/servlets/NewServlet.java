package com.managing.employees.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.managing.employees.beans.Employee;
import com.managing.employees.db.EmployeeInterface;
import com.managing.employees.db.Factory;
import com.managing.employees.db.UserInterface;
import com.managing.employees.utils.Useful;

/**
 * Servlet implementation class NewServlet
 */
@WebServlet("/NewServlet")
public class NewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeInterface employeeDAO;
    
	@Override
	public void init() throws ServletException {
		Factory factory = Factory.getInstance();
        this.employeeDAO = factory.getEmployeeDao();
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("name") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		request.setAttribute("today", Useful.getTodayDate());
		this.getServletContext().getRequestDispatcher("/WEB-INF/employees/new.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		String salary = request.getParameter("salary");
		String HireDate = request.getParameter("HireDate");
		//double salary = Double.parseDouble(request.getParameter("salary"));
		
		request.setAttribute("oldemail", email);
		request.setAttribute("oldname", name);
		request.setAttribute("oldsalary", salary);
		request.setAttribute("oldtel", tel);
		request.setAttribute("oldHireDate", HireDate);
		if(Useful.isValidEmail(email) && name != "" && Useful.isValidPhone(tel) && Useful.isFloat(salary) && Useful.isDate(HireDate) && Useful.CompareTwoDates(HireDate) ) {
			
			if(Double.parseDouble(salary) == 0) {
				request.setAttribute("oldsalary", "0.00");
				request.setAttribute("not_salary", "Please choose a valid Salary!");
			}else {
				if(employeeDAO.employeeExist(email)) {
					request.setAttribute("oldemail", "");
					request.setAttribute("not_email", "This Email already exist!");
				}else {
					Employee employee = new Employee();
					employee.setName(name);
					employee.setEmail(email);
					employee.setTel(tel);
					employee.setHireDate(Useful.getMyHireDate(HireDate));
					employee.setSalary(Double.parseDouble(salary));
					employeeDAO.newEmployee(employee);
					request.setAttribute("added", "The Employee Added with success!!");
					response.sendRedirect(request.getContextPath() + "/dashboard");
					return;
					//this.getServletContext().getRequestDispatcher("/WEB-INF/employees/dashboard.jsp").forward(request, response);
				}
			}
			
		}else {
			if(!Useful.isValidEmail(email)) {
				request.setAttribute("oldemail", "");
				request.setAttribute("not_email", "Please choose a valid email!");	
			}
			if (name == "") {
				request.setAttribute("not_name", "Please choose a Name!");
			}
			if(!Useful.isFloat(salary)) {
				request.setAttribute("oldsalary", "0.00");
				request.setAttribute("not_salary", "Please choose a valid Salary!");	
			}
			if(!Useful.isValidPhone(tel)) {
				request.setAttribute("oldtel", "");
				request.setAttribute("not_tel", "Please choose a valid number phone!");	
			}
			if(!Useful.isDate(HireDate) || !Useful.CompareTwoDates(HireDate)) {
				request.removeAttribute("oldHireDate");
				request.setAttribute("not_HireDate", "Please choose a valid Hire Date!");	
			}
		}
		doGet(request, response);
	}

}
