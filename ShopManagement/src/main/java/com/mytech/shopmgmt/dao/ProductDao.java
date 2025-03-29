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

public class ProductDao {

    // Thêm sản phẩm mới
    public boolean insertProduct(Product product) {
        String sql = "INSERT INTO products (code, name, price, image, cart) VALUES (?, ?, ?, ?, ?)";
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

   
 // Lấy danh sách tất cả sản phẩm
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM t3shop.products";
        try (Connection connection = Dbconnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String imagePath = rs.getString("imagePath");
                int cart = rs.getInt("cart");
                
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

}
