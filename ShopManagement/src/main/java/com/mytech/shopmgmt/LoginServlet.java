package com.mytech.shopmgmt;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public LoginServlet() {
        super();
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // Thực hiện các cấu hình khởi tạo nếu cần
    }
    
    @Override
    public void destroy() {
        // Thực hiện giải phóng tài nguyên nếu cần
        super.destroy();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Với GET, chuyển hướng đến trang login.jsp
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Đặt content type cho response
        response.setContentType("text/html;charset=UTF-8");
        
        // Lấy dữ liệu từ form đăng nhập
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Kiểm tra đăng nhập cứng: username là "admin" và password là "123"
        if ("admin".equals(username) && "123".equals(password)) {
            // Nếu đăng nhập thành công, chuyển hướng đến trang home.jsp
            response.sendRedirect("dashboard.jsp");
            //
            
            
            
        } else {
            // Nếu đăng nhập thất bại, hiển thị thông báo lỗi
        	 request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
