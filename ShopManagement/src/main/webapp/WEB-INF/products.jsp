<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Danh sách Sản phẩm</title>
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
        }
        .table img {
            max-width: 80px;
            height: auto;
        }
        .header-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }
        .search-box {
            display: flex;
            gap: 5px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header-bar">
        <h2>Danh sách Sản phẩm</h2>
        <!-- Nút thêm sản phẩm -->
        <a href="addProduct.jsp" class="btn btn-success">Thêm sản phẩm</a>
    </div>
    
    <!-- Form tìm kiếm sản phẩm -->
    <form action="products" method="get" class="search-box mb-3">
        <input type="text" name="search" class="form-control" placeholder="Tìm kiếm sản phẩm...">
        <button type="submit" class="btn btn-primary">Search</button>
    </form>
    
    <!-- Bảng hiển thị danh sách sản phẩm -->
    <table class="table table-bordered table-striped">
        <thead class="table-dark">
            <tr>
                <th>Mã SP</th>
                <th>Tên SP</th>
                <th>Giá</th>
                <th>Hình ảnh</th>
                <th>Trong giỏ (Cart)</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <tbody>
            <%
                // Lấy danh sách sản phẩm từ request attribute
                java.util.List<com.mytech.shopmgmt.models.Product> products = 
                    (java.util.List<com.mytech.shopmgmt.models.Product>) request.getAttribute("products");
                if (products == null || products.isEmpty()) {
            %>
            <tr>
                <td colspan="6" class="text-center">Không có sản phẩm nào để hiển thị.</td>
            </tr>
            <%
                } else {
                    for (com.mytech.shopmgmt.models.Product p : products) {
            %>
            <tr>
                <td><%= p.getCode() %></td>
                <td><%= p.getName() %></td>
                <td><%= p.getPrice() %></td>
                <td>
                    <img src="<%= p.getImagePath() %>" alt="<%= p.getName() %>">
                </td>
            
                <td>
                    <a href="editProduct?id=<%= p.getCode() %>" class="btn btn-primary btn-sm">Edit</a>
                    <a href="deleteProduct?id=<%= p.getCode() %>" class="btn btn-danger btn-sm">Delete</a>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
</
