package com.managing.employees.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.managing.employees.beans.Employee;
import com.managing.employees.db.EmployeeInterface;
import com.managing.employees.db.Factory;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
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
    public DashboardServlet() {
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
		String msg = (String) request.getSession().getAttribute("added");
		if(msg !=  null) {
			request.getSession().removeAttribute("added");
			request.setAttribute("added", msg );
		}
		
		List<Employee> employees = employeeDAO.all();
		request.setAttribute("employees", employees);
		this.getServletContext().getRequestDispatcher("/WEB-INF/employees/dashboard.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
