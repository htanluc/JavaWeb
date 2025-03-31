package com.mytech.shopmgmt;

import com.mytech.shopmgmt.dao.ProductDao;
import com.mytech.shopmgmt.helpers.ServletHelper;
import com.mytech.shopmgmt.models.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet("/editProduct")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
                 maxFileSize = 1024 * 1024 * 10,       // 10MB
                 maxRequestSize = 1024 * 1024 * 50)    // 50MB
public class EditProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDao productDao;

    @Override
    public void init() throws ServletException {
        super.init();
        productDao = new ProductDao();
    }

    // Hiển thị form chỉnh sửa (GET)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy mã sản phẩm từ tham số URL (vd: ?id=BK001)
        String code = request.getParameter("id");
        
        // Lấy thông tin sản phẩm từ DB
        Product product = productDao.getProductByCode(code);
        
        // Gửi product sang JSP
        request.setAttribute("product", product);
        
        ServletHelper.forward(request, response, "editProduct");
    }

    // Xử lý cập nhật (POST)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Lấy các thông tin từ form
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        
        // Lấy đối tượng Product cũ (để giữ lại imagePath nếu người dùng không upload ảnh mới)
        Product oldProduct = productDao.getProductByCode(code);
        if (oldProduct == null) {
            // Không tìm thấy sản phẩm
            response.getWriter().println("Không tìm thấy sản phẩm để cập nhật!");
            return;
        }

        // Xử lý upload ảnh (nếu có)
        Part filePart = request.getPart("image"); // input name="image"
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        // Nếu người dùng không chọn ảnh mới (fileName rỗng), giữ nguyên ảnh cũ
        String imagePath = oldProduct.getImagePath();
        if (fileName != null && !fileName.isEmpty()) {
            // Người dùng chọn file ảnh mới
            String applicationPath = request.getServletContext().getRealPath("");
            String uploadPath = applicationPath + File.separator + "uploads";

            // Tạo thư mục "uploads" nếu chưa tồn tại
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Đường dẫn tuyệt đối đến file
            String filePath = uploadPath + File.separator + fileName;
            // Ghi file lên server
            filePart.write(filePath);

            // Lưu đường dẫn tương đối (để hiển thị)
            imagePath = "uploads/" + fileName;
        }

        // Tạo Product mới với dữ liệu đã chỉnh sửa
        Product product = new Product(code, name, price, imagePath);

        // Gọi DAO cập nhật
        boolean success = productDao.updateProduct(product);
        if (success) {
            // Cập nhật thành công, quay về trang danh sách sản phẩm
            response.sendRedirect("products");
        } else {
            response.getWriter().println("Lỗi khi cập nhật sản phẩm");
        }
    }
}
