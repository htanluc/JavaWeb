<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Products</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="index.jsp">MyShop</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
                    <li class="nav-item"><a class="nav-link active" href="products.jsp">Products</a></li>
                    <li class="nav-item"><a class="nav-link" href="cart.jsp">Cart</a></li>
                    <li class="nav-item"><a class="nav-link" href="profile.jsp">Profile</a></li>
                </ul>
            </div>
        </div>
    </nav>
    
    <div class="container mt-4">
        <h2 class="text-center">Our Products</h2>
        
        <div class="d-flex justify-content-between mb-3">
            <input type="text" id="searchBox" class="form-control w-50" placeholder="Search products...">
            <a href="addProduct.jsp" class="btn btn-primary">Add Product</a>
        </div>

        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Code</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Image</th>
                    <th>Cart</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
            	<!-- //Cach 1: JSP Scriptlet de hien listProducts
				//Cach 2: Su dung taglib JSTL de hien listProducts -->
                <!-- Product rows will be dynamically loaded here -->
                <c:forEach var="product" items="${listProducts}">
                	<tr>
                		<td>${product.code}</td>
                		<td>${product.name}</td>
                		<td>${product.price}</td>
                		<td><img src="${product.imagePath}"></td>
                		<td><a href="products?action=addCart?code=${product.code}}">Add to Cart</a></td>
                		<td><a href="products?action=update?code=${product.code}}">Edit</a></td>
                		<td><a href="products?action=delete?code=${product.code}}">Delete</a></td>
                	</tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>