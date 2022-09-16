package com.store.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int productId;
	
	private String name;
	
	private int quantity;
	
	private String category;
	
	private double originalPrice;
	
	private double discount;
	
	public Product() {
		
	}

	public Product(String name, int quantity, String category, double originalPrice, double discount) {
		this.name = name;
		this.quantity = quantity;
		this.category = category;
		this.originalPrice = originalPrice;
		this.discount = discount;
	}
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return "Product [name=" + name +  ", quantity=" + quantity + ", category=" + category
				+ ", original_price=" + originalPrice + ", discount=" + discount + "]";
	}
	
}
