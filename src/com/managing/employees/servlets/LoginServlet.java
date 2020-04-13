package com.managing.employees.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.managing.employees.beans.User;
import com.managing.employees.db.Factory;
import com.managing.employees.db.UserInterface;
import com.managing.employees.utils.Useful;

import sun.invoke.empty.Empty;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
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
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("name") != null) {
			response.sendRedirect(request.getContextPath() + "/dashboard");
			return;
			//this.getServletContext().getRequestDispatcher("/WEB-INF/employees/dashboard.jsp").forward(request, response);
		}
			this.getServletContext().getRequestDispatcher("/WEB-INF/auth/login.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if(Useful.isValidEmail(email) && password.length() >= 8 ) {
			if(!userDAO.userExist(email)) {
				request.setAttribute("not_email", "This Email doesn't exist!");
			}else {
				request.setAttribute("oldemail", email);
				String name = userDAO.login(email, password);
				if(name == "") {
					request.setAttribute("not_password", "Password incorrect!");
				}else {
					//Add login to Session
					HttpSession session = request.getSession();
					session.setAttribute("email", email);
					session.setAttribute("name", name);
				}
			}
		}else {
			request.setAttribute("oldemail", email);
			if(!Useful.isValidEmail(email)) {
				request.setAttribute("not_email", "Please choose a valid email!");
				
			}
			if (password.length() < 8) {
				request.setAttribute("not_password", "Please password must be at least 8 characters!");
			}
		}
		doGet(request, response);
	}

}
