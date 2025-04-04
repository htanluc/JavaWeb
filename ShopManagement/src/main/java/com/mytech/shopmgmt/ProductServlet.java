package com.mytech.shopmgmt;

import java.io.IOException;
import java.util.List;

import com.mytech.shopmgmt.dao.ProductDAO;
import com.mytech.shopmgmt.helpers.ServletHelper;
import com.mytech.shopmgmt.models.Product;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
//    private ProductJDBCDao productDao; // Khai báo biến DAO
    private ProductDAO productDAO; // Khai báo biến DAO
    public ProductServlet() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        productDAO = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String action=request.getParameter("action");
    	System.out.println("action"+action);
    	if("add".equals(action)) {
    		  
            ServletHelper.forward(request, response, "add_products");
    	}
    	else {
    		List<Product> listProduct = productDAO.getProducts();
    	     
            request.setAttribute("products", listProduct);
            
    	}
    	
    	
    	
        // Lấy danh sách sản phẩm từ DAO
        List<Product> listProduct = productDAO.getProducts();
     
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
