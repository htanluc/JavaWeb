<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chỉnh sửa sản phẩm</title>
    <!-- Link CSS Bootstrap nếu cần -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>Chỉnh sửa sản phẩm</h1>
    <%
        com.mytech.shopmgmt.models.Product product = 
            (com.mytech.shopmgmt.models.Product) request.getAttribute("product");
        if (product == null) {
    %>
        <p class="text-danger">Không tìm thấy sản phẩm!</p>
    <%
        } else {
    %>
    <form action="editProduct" method="post" enctype="multipart/form-data">
        <!-- Mã sản phẩm (ẩn) -->
        <input type="hidden" name="code" value="<%= product.getCode() %>"/>

        <div class="mb-3">
            <label for="name" class="form-label">Tên sản phẩm:</label>
            <input type="text" class="form-control" id="name" name="name" 
                   value="<%= product.getName() %>" required/>
        </div>

        <div class="mb-3">
            <label for="price" class="form-label">Giá:</label>
            <input type="number" step="0.01" class="form-control" id="price" name="price" 
                   value="<%= product.getPrice() %>" required/>
        </div>

        <div class="mb-3">
            <label>Hình ảnh hiện tại:</label><br/>
            <img src="<%= product.getImagePath() %>" alt="Ảnh sản phẩm" 
                 style="max-width: 150px; height: auto;"/>
        </div>

        <div class="mb-3">
            <label for="image" class="form-label">Chọn ảnh mới (nếu cần thay):</label>
            <input type="file" class="form-control" id="image" name="image"/>
        </div>

        <button type="submit" class="btn btn-primary">Cập nhật</button>
        <a href="products" class="btn btn-secondary">Hủy</a>
    </form>
    <%
        }
    %>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
