package com.mytech.shopmgmt;

import com.mytech.shopmgmt.dao.ProductJDBCDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteProduct")
public class DeleteProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductJDBCDao productDao;
    
    @Override
    public void init() throws ServletException {
        productDao = new ProductJDBCDao();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy mã sản phẩm từ tham số "id" trên URL (ví dụ: ?id=BK001)
        String code = request.getParameter("id");
        if (code != null && !code.isEmpty()) {
            boolean deleted = productDao.deleteProduct(code);
            if (deleted) {
                response.sendRedirect("products"); // Quay lại danh sách sản phẩm
            } else {
                response.getWriter().println("Lỗi: Không thể xóa sản phẩm.");
            }
        } else {
            response.getWriter().println("Lỗi: Mã sản phẩm không hợp lệ.");
        }
    }
    
    // Cho phép xử lý yêu cầu POST (nếu cần)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
