<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>VIP Pro Sales - Login</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom Styles for VIP Pro Sales Login -->
  <style>
    body {
      background: linear-gradient(135deg, #1f4037, #99f2c8);
      font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
      height: 100vh;
      margin: 0;
      display: flex;
      justify-content: center;
      align-items: center;
      background-size: cover;
      background-repeat: no-repeat;
    }
    .login-container {
      background: rgba(255, 255, 255, 0.95);
      border-radius: 15px;
      box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
      padding: 40px;
      max-width: 400px;
      width: 100%;
      text-align: center;
    }
    .header-banner {
      margin-bottom: 20px;
      color: #ff9900;
      font-size: 28px;
      font-weight: bold;
      text-transform: uppercase;
      letter-spacing: 1px;
    }
    .subtext {
      margin-bottom: 20px;
      font-size: 16px;
      color: #666;
    }
    .login-container h2 {
      font-weight: bold;
      margin-bottom: 30px;
      color: #333;
    }
    .login-container .form-control {
      border-radius: 50px;
      padding: 15px;
    }
    .btn-custom {
      background-color: #ff9900;
      border: none;
      border-radius: 50px;
      padding: 10px 20px;
      font-weight: bold;
      font-size: 16px;
      transition: background-color 0.3s ease;
    }
    .btn-custom:hover {
      background-color: #e68a00;
    }
    .form-check-label {
      margin-left: 5px;
    }
    .error {
      color: red;
      margin-bottom: 15px;
    }
  </style>
</head>
<body>
  <div class="login-container">
    <div class="header-banner">VIP Pro Sales</div>
    <div class="subtext">Welcome to the exclusive sales portal</div>
    <h2>Login</h2>
    <!-- Hiển thị thông báo lỗi nếu có -->
    <c:if test="${not empty error}">
      <div class="error">${error}</div>
    </c:if>
    <form action="Login" method="post">
      <div class="mb-3">
        <input type="text" class="form-control" id="username" name="username" placeholder="Username" required>
      </div>
      <div class="mb-3">
        <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
      </div>
      <div class="mb-3 form-check text-start">
        <input type="checkbox" class="form-check-input" id="rememberMe" name="rememberMe">
        <label class="form-check-label" for="rememberMe">Remember me</label>
      </div>
      <button type="submit" class="btn btn-custom w-100">Login</button>
    </form>
  </div>
  <!-- Bootstrap JS Bundle -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
