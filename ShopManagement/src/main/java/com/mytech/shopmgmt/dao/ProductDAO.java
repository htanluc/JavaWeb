package com.mytech.shopmgmt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import com.mytech.shopmgmt.db.Dbconnector;
import com.mytech.shopmgmt.models.Product;

public class ProductDAO {

	// Phương thức lấy danh sách sản phẩm
	public List<Product> getProducts() {
		EntityManager entityManager = Dbconnector.getEntityManager();
		TypedQuery<Product> query = entityManager.createNamedQuery("Product.findall", Product.class);
		return query.getResultList();
	}

	public Product getProductByCode(String code) {
		EntityManager entityManager = Dbconnector.getEntityManager();
		TypedQuery<Product> query = entityManager.createNamedQuery("Product.findByCode", Product.class);
		query.setParameter("code", code);
		return query.getSingleResult();
	}

	public Product getProductByName(String name) {
		EntityManager entityManager = Dbconnector.getEntityManager();
		TypedQuery<Product> query = entityManager.createNamedQuery("Product.findByName", Product.class);
		query.setParameter("name", "%" + name + "%");
		return query.getSingleResult();
	}

	// Phương thức thêm sản phẩm mới
	public boolean addProduct(Product product) {
		EntityManager entityManager = Dbconnector.getEntityManager();
		var trans= entityManager.getTransaction();
		trans.begin();
		entityManager.persist(product); // Lưu đối tượng Product vào DB
		trans.commit();
		return true;

	}

	public boolean updateProduct(Product product) {
	    EntityManager entityManager = Dbconnector.getEntityManager();
	    try {
	        // Bắt đầu transaction
	        entityManager.getTransaction().begin();
	        
	        // Lấy đối tượng cũ từ DB theo mã (giả sử code là primary key)
	        Product existingProduct = entityManager.find(Product.class, product.getCode());
	        if (existingProduct == null) {
	            return false; // Không tìm thấy sản phẩm cần update
	        }
	        
	        // Cập nhật các thuộc tính
	        existingProduct.setName(product.getName());
	        existingProduct.setPrice(product.getPrice());
	        existingProduct.setImagePath(product.getImagePath());
	        
	        // Merge đối tượng (nếu cần) hoặc chỉ cần commit nếu đối tượng đã được attach
	        entityManager.merge(existingProduct);
	        
	        // Commit giao dịch
	        entityManager.getTransaction().commit();
	        return true;
	    } catch (Exception e) {
	        if (entityManager.getTransaction().isActive()) {
	            entityManager.getTransaction().rollback();
	        }
	        e.printStackTrace();
	        return false;
	    }
	}


	public boolean deleteProductByCode(String code) {
		EntityManager entityManager = Dbconnector.getEntityManager();
		Product delPro= entityManager.find(Product.class, code);
		entityManager.remove(delPro);
		return true;
	}
}	
