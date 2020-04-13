package com.managing.employees.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.managing.employees.db.EmployeeInterface;
import com.managing.employees.db.Factory;

/**
 * Servlet implementation class Actionservlet
 */
@WebServlet("/Actionservlet")
public class Actionservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeInterface employeeDAO;
	private Factory factory = Factory.getInstance();
  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Actionservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String out =  request.getParameter("out");
		if(out == null) {
			if(request.getSession().getAttribute("name") == null) {
				response.sendRedirect(request.getContextPath() + "/login");
				return;
			}
			this.employeeDAO = this.factory.getEmployeeDao();
			String id =  request.getParameter("id");
			employeeDAO.DelEmployee(id);
			request.getSession().setAttribute("added", "The Employee deleted with success!!");
			response.sendRedirect(request.getContextPath() + "/dashboard");
			return;
		}else {
			request.getSession().removeAttribute("name");
			request.getSession().removeAttribute("email");
			response.sendRedirect(request.getContextPath() + "/");
			return;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
