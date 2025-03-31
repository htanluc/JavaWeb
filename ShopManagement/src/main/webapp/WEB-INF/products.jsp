<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Danh sách Sản phẩm</title>
<!-- Link CSS Bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
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
			<a href="addProduct" class="btn btn-success">Thêm sản phẩm</a>
		</div>

		<table class="table table-bordered table-striped">
			<thead class="table-dark">
				<tr>
					<th>Mã SP</th>
					<th>Tên SP</th>
					<th>Giá</th>
					<th>Hình ảnh</th>
					<th>Hành động</th>
				</tr>
			</thead>
			<tbody>
				<%
				java.util.List<com.mytech.shopmgmt.models.Product> products = (java.util.List<com.mytech.shopmgmt.models.Product>) request
						.getAttribute("products");
				if (products == null || products.isEmpty()) {
				%>
				<tr>
					<td colspan="5" class="text-center">Không có sản phẩm nào để
						hiển thị.</td>
				</tr>
				<%
				} else {
				for (com.mytech.shopmgmt.models.Product p : products) {
				%>
				<tr>
					<td><%=p.getCode()%></td>
					<td><%=p.getName()%></td>
					<td><%=p.getPrice()%></td>
					<td><img src="<%=p.getImagePath()%>"
						alt="<%=p.getName()%>"></td>
					<td><a href="editProduct?id=<%=p.getCode()%>"
						class="btn btn-primary btn-sm">Edit</a> <a
						href="deleteProduct?id=<%=p.getCode()%>"
						class="btn btn-danger btn-sm"
						onclick="return confirm('Bạn có chắc chắn muốn xóa sản phẩm này?');">
							Delete </a></td>
				</tr>
				<%
				}
				}
				%>
			</tbody>
		</table>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
