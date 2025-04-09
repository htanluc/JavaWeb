package com.mytech.shopmgmt.filers;

import java.io.IOException;
import java.net.http.HttpResponse;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import wrappers.AppRequestWrapper;


@WebFilter("/*")
public class AppFilter extends HttpFilter implements Filter {

    /**
     * Default constructor.
     */
    public AppFilter() {
        super();
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // Không cần xử lý gì thêm khi destroy filter
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Ép kiểu request thành HttpServletRequest trước khi khởi tạo wrapper
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        AppRequestWrapper appRequestWrapper = new AppRequestWrapper(httpRequest);
       
        // Truyền đối tượng wrapper thay thế request gốc vào chain
        chain.doFilter(appRequestWrapper, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // Không cần xử lý gì thêm khi khởi tạo filter
    }
}
