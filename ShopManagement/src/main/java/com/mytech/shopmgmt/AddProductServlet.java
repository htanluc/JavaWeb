package com.mytech.shopmgmt;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;


import com.mytech.shopmgmt.helpers.ServletHelper;
import com.mytech.shopmgmt.models.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/addProduct")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
    maxFileSize = 1024 * 1024 * 10,         // 10MB
    maxRequestSize = 1024 * 1024 * 50       // 50MB
)
public class AddProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Product productDao;
    
    @Override
    public void init() throws ServletException {
        super.init();
        productDao = new Product();
    }
    
    // Hiển thị form thêm sản phẩm
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletHelper.forward(request, response, "addProduct");
    }
    
    // Xử lý submit form thêm sản phẩm
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy các thông tin từ form
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        
        // Xử lý file upload hình ảnh
        Part filePart = request.getPart("image"); // Tên input file là "image"
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        
        // Xác định đường dẫn thư mục "uploads" nằm trong webapp
        String applicationPath = request.getServletContext().getRealPath("");
        String uploadPath = applicationPath + File.separator + "uploads";
        
        // Tạo thư mục nếu chưa tồn tại
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        
        // Lưu file vào thư mục "uploads"
        String filePath = uploadPath + File.separator + fileName;
//        System.out.println("filePath:" + uploadPath);
        filePart.write(filePath);
        
        // Lưu đường dẫn tương đối để lưu vào DB (để hiển thị hình ảnh)
        String imagePath = "uploads/" + fileName;
        
        // Tạo đối tượng Product
        Product product = new Product(code, name, price, imagePath);
        
        // Thêm sản phẩm vào DB thông qua DAO
       
    }
}
