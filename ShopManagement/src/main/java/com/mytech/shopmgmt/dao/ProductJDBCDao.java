package com.mytech.shopmgmt.dao;

import com.mytech.shopmgmt.db.Dbconnector;
import com.mytech.shopmgmt.models.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductJDBCDao {

    // Phương thức thêm sản phẩm (có thể đã có sẵn)
    public boolean insertProduct(Product product) {
        String sql = "INSERT INTO product (code, name, price, imagePath) VALUES (?, ?, ?, ?)";
        try (Connection connection = Dbconnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getCode());
            statement.setString(2, product.getName());
            statement.setDouble(3, product.getPrice());
            statement.setString(4, product.getImagePath());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Phương thức lấy danh sách sản phẩm (có thể đã có sẵn)
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try (Connection connection = Dbconnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String imagePath = rs.getString("imagePath");
                
                Product product = new Product();
                product.setCode(code);
                product.setName(name);
                product.setPrice(price);
                product.setImagePath(imagePath);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
    
    // Phương thức lấy sản phẩm theo mã (getProductByCode)
    public Product getProductByCode(String code) {
        Product product = null;
        String sql = "SELECT * FROM product WHERE code = ?";
        try (Connection connection = Dbconnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, code);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setCode(rs.getString("code"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setImagePath(rs.getString("imagePath"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
    public boolean deleteProduct(String code) {
        String sql = "DELETE FROM product WHERE code = ?";
        try (Connection connection = Dbconnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, code);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    // Phương thức cập nhật sản phẩm (updateProduct)
    public boolean updateProduct(Product product) {
        String sql = "UPDATE product SET name = ?, price = ?, imagePath = ? WHERE code = ?";
        try (Connection connection = Dbconnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getImagePath());
            statement.setString(4, product.getCode());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
