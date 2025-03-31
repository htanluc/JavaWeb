<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm sản phẩm</title>
    <!-- Link CSS Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f0f4f8;
            font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
            min-height: 100vh;
        }
        .container {
            margin-top: 30px;
            max-width: 600px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="mb-4">Thêm sản phẩm</h1>
    <!-- Form upload file phải có enctype="multipart/form-data" -->
    <form action="addProduct" method="post" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="code" class="form-label">Mã sản phẩm:</label>
            <input type="text" class="form-control" id="code" name="code" placeholder="Nhập mã sản phẩm" required>
        </div>
        <div class="mb-3">
            <label for="name" class="form-label">Tên sản phẩm:</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="Nhập tên sản phẩm" required>
        </div>
        <div class="mb-3">
            <label for="price" class="form-label">Giá:</label>
            <input type="number" step="0.01" class="form-control" id="price" name="price" placeholder="Nhập giá sản phẩm" required>
        </div>
        <div class="mb-3">
            <label for="image" class="form-label">Hình ảnh:</label>
            <input type="file" class="form-control" id="image" name="image" required>
        </div>
        <button type="submit" class="btn btn-success">Thêm sản phẩm</button>
        <a href="products" class="btn btn-secondary">Hủy</a>
    </form>
</div>
<!-- Link JS Bootstrap nếu cần -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
