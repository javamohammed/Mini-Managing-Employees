package com.managing.employees.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import com.managing.employees.beans.User;
import com.managing.employees.db.Factory;
import com.managing.employees.db.UserInterface;
import com.managing.employees.utils.Useful;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserInterface userDAO;
    
	@Override
	public void init() throws ServletException {
		Factory factory = Factory.getInstance();
        this.userDAO = factory.getUserDao();
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getSession().getAttribute("name") != null) {
			response.sendRedirect(request.getContextPath() + "/dashboard");
			return;
			//this.getServletContext().getRequestDispatcher("/WEB-INF/employees/dashboard.jsp").forward(request, response);
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/auth/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		if(Useful.isValidEmail(email) && name != "" && password.length() >= 8 ) {
			if(userDAO.userExist(email)) {
				request.setAttribute("not_email", "This Email already exist!");
			}else {
				User user = new User();
				user.setName(name);
				user.setEmail(email);
				
				user.setPassword(Useful.MD5(password));
				userDAO.AddUser(user);
				request.setAttribute("success","You are signin with success, You can go to login ...");
			}
		}else {
			request.setAttribute("oldemail", email);
			request.setAttribute("oldname", name);
			if(!Useful.isValidEmail(email)) {
				request.setAttribute("not_email", "Please choose a valid email!");
				
			}
			if (name == "") {
				request.setAttribute("not_name", "Please choose a Name!");
			}
			if (password.length() < 8) {
				request.setAttribute("not_password", "Please password must be at least 8 characters!");
			}
		}
		doGet(request, response);
	}

}
