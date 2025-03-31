package com.mytech.shopmgmt;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.mytech.shopmgmt.dao.ProductDao;
import com.mytech.shopmgmt.models.Product;
import com.mytech.shopmgmt.helpers.ServletHelper;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ProductDao productDao; // Khai báo biến DAO

    public ProductServlet() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        productDao = new ProductDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy danh sách sản phẩm từ DAO
        List<Product> listProduct = productDao.getAllProducts();
     
        request.setAttribute("products", listProduct);
        // Chuyển tiếp đến trang hiển thị sản phẩm (ví dụ: "products.jsp" hoặc "products" tùy cấu hình ServletHelper)
        //cách 1 : jsp Scriplet de hien thi lisproduct
        
        
        
        ServletHelper.forward(request, response, "products");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
