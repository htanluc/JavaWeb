package com.mytech.shopmgmt;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void destroy() {
        System.out.println("LoginServlet is being destroyed...");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy tham số đăng nhập
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Kiểm tra nếu username hoặc password bị null hoặc rỗng
        if (username == null || password == null || username.trim().isEmpty() || password.trim().isEmpty()) {
            request.setAttribute("error", "Vui lòng nhập tên đăng nhập và mật khẩu!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Kiểm tra thông tin đăng nhập (chỉ chấp nhận admin/123456)
        if ("admin".equals(username) && "123456".equals(password)) {
            // Tạo session và lưu thông tin đăng nhập
            HttpSession session = request.getSession();
            session.setAttribute("user", username);

            // Tạo cookie lưu username và thời gian đăng nhập
            Cookie ckUsername = new Cookie("username", username);
            Cookie ckLoginDate = new Cookie("loginDate", String.valueOf(System.currentTimeMillis()));

            // Thiết lập thời gian sống cho cookie (1 ngày = 86400 giây)
            ckUsername.setMaxAge(86400);
            ckLoginDate.setMaxAge(86400);

            // Thêm cookie vào response
            response.addCookie(ckUsername);
            response.addCookie(ckLoginDate);

            // Lưu thông tin đăng nhập bổ sung vào session
            session.setAttribute("username", username);
            session.setAttribute("loginDate", System.currentTimeMillis());

            // Chuyển hướng đến dashboard.jsp bằng forward
            RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
            dispatcher.forward(request, response);
        } else {
            // Nếu thông tin đăng nhập không đúng, quay lại login.jsp và hiển thị thông báo lỗi
            request.setAttribute("error", "Sai tên đăng nhập hoặc mật khẩu!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
