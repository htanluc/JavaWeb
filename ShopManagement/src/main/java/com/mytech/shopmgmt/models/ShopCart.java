package com.mytech.shopmgmt.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ShopCarts")
public class ShopCart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long cartId;
	
	@OneToOne
	Customer customer;
	
	@OneToMany(mappedBy="shopCart")
	List<Cartline> cartLines;
	
	Double total;

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Cartline> getCartLines() {
		return cartLines;
	}

	public void setCartLines(List<Cartline> cartLines) {
		this.cartLines = cartLines;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	
	
}
