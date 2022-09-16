package com.store.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sales {
	
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int salesId;
	
	private String productSold;
	
	private int quantity;
	
	private int soldTo;
	
	private double sellingPrice;

	public Sales() {
		
	}


	public Sales(int salesId, String productSold, int quantity, int soldTo, double sellingPrice) {
		this.salesId = salesId;
		this.productSold = productSold;
		this.quantity = quantity;
		this.soldTo = soldTo;
		this.sellingPrice = sellingPrice;
	}


	public double getSellingPrice() {
		return sellingPrice;
	}


	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}


	public int getSalesId() {
		return salesId;
	}

	public void setSalesId(int salesId) {
		this.salesId = salesId;
	}

	public String getProductSold() {
		return productSold;
	}

	public void setProductSold(String productSold) {
		this.productSold = productSold;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSoldTo() {
		return soldTo;
	}

	public void setSoldTo(int soldTo) {
		this.soldTo = soldTo;
	}

	@Override
	public String toString() {
		return "Sales [salesId=" + salesId + ", productSold=" + productSold + ", quantity=" + quantity + ", soldTo="
				+ soldTo + "]";
	}

}
