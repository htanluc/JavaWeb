package com.mytech.shopmgmt;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.mytech.shopmgmt.helpers.ServletHelper;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Login page initializing....");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("Login page destroying....");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletHelper.forward(request, response, "login");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		PrintWriter outPrintWriter = response.getWriter();
		outPrintWriter.append("You loggin with:" + username + " :: " + password);
		
		//Kiem tra username = admin & password = 123456
		//Nếu đúng thì chuyển qua trang dashboard
		//Nếu sai thì chuyển về trang login
		if( "admin".equals(username) && "123456".equals(password)) {
//			RequestDispatcher requestDispatcher = request.getRequestDispatcher("dashboard.jsp");
//			requestDispatcher.forward(request, response);
			
			//Cookies
			Cookie ckUsername = new Cookie("username", username);
			Cookie ckLoginDate = new Cookie("loginDate", System.currentTimeMillis() + "");
			
			response.addCookie(ckUsername);
			response.addCookie(ckLoginDate);
			
			//Session
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("loginDate", System.currentTimeMillis() + "");
			
			response.sendRedirect("dashboard");
			
		} else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("error.jsp");
			requestDispatcher.include(request, response);
		}
	}

}
