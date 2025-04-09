package com.mytech.shopmgmt.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.w3c.dom.css.CSSImportRule;

import com.mytech.shopmgmt.helpers.ServletHelper;

@WebFilter(urlPatterns = { "/users",
		"/login" }, initParams = @WebInitParam(name = "notAllowedNames", value = "facebook,google,zalo"))
public class UsernameFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 8144985062131488599L;

	private String[] namesNotAllowed;

	public UsernameFilter() {
		super();

	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String username = request.getParameter("username");

		if (checkUsernameNotAllowed(username)) {
			httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
			httpRequest.setAttribute("message", "username is not allowed");
			ServletHelper.forward(httpRequest, httpResponse, "login");
		} else {

			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		String notAllowedName = fConfig.getInitParameter("notAllowedNames");
		namesNotAllowed = notAllowedName.split(",");
		System.out.println("Name are not allowed: " + namesNotAllowed);
	}

	// CHECK USERNAME IS NOT ALLOWED
	public boolean checkUsernameNotAllowed(String username) {
		for (String name : namesNotAllowed) {
			if (name.equals(username)) {
				return true;
			}
		}
		return false;
	}
}
