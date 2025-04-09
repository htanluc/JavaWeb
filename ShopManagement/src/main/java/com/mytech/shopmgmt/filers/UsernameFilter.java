package com.mytech.shopmgmt.filers;

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
import java.util.Arrays;
import com.mytech.shopmgmt.helpers.ServletHelper;

/**
 * Servlet Filter implementation class UsernameFilter
 */
@WebFilter(urlPatterns = { "/users", "/login" }, 
           initParams = @WebInitParam(name = "notAllowedNames", value = "facebook,google,zalo"))
public class UsernameFilter extends HttpFilter implements Filter {

    private static final long serialVersionUID = 8199445854649977567L;
    private String[] nameNotAllowed;

    public UsernameFilter() {
        super();
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        String notAllowedNamesParam = fConfig.getInitParameter("notAllowedNames");
        if (notAllowedNamesParam != null) {
            nameNotAllowed = notAllowedNamesParam.split(",");
        } else {
            nameNotAllowed = new String[0];
        }
        System.out.println("Not allowed usernames: " + Arrays.toString(nameNotAllowed));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String username = request.getParameter("username");
        // Nếu username không null và nằm trong danh sách không cho phép, chặn request
        if (username != null && checkUsernameInName(username)) {
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpRequest.setAttribute("message", "Username is not allowed");
            ServletHelper.forward(httpRequest, httpResponse, "login");
            return;  // Dừng xử lý để không tiếp tục chain.doFilter()
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup nếu cần thiết
    }

    /**
     * Kiểm tra xem username có nằm trong danh sách không được phép hay không.
     *
     * @param username Chuỗi username cần kiểm tra.
     * @return true nếu username nằm trong danh sách không được phép, ngược lại false.
     */
    public boolean checkUsernameInName(String username) {
        for (String notAllowed : nameNotAllowed) {
            if (username.equalsIgnoreCase(notAllowed.trim())) {
                return true;
            }
        }
        return false;
    }
}
