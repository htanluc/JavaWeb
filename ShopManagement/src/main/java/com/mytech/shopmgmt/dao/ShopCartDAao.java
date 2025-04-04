package com.mytech.shopmgmt.dao;

import com.mytech.shopmgmt.db.Dbconnector;
import com.mytech.shopmgmt.models.Customer;
import com.mytech.shopmgmt.models.Product;

import jakarta.persistence.EntityManager;

public class ShopCartDAao {
	public boolean addShopCart(Customer customer,Product product,int quantity) {
		EntityManager entityManager = Dbconnector.getEntityManager();
		//tạo vỏ hàng
		return true;
	}
	
}
