package com.store.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ActivityHistory {

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int Id;
	
	private Date loginDate;
	
	private String viewHistory;
	
	private String purchaseHistory;

	public ActivityHistory() {
		
	}

	public ActivityHistory(int id, Date loginDate, String viewHistory, String purchaseHistory) {
		Id = id;
		this.loginDate = loginDate;
		this.viewHistory = viewHistory;
		this.purchaseHistory = purchaseHistory;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getViewHistory() {
		return viewHistory;
	}

	public void setViewHistory(String viewHistory) {
		this.viewHistory = viewHistory;
	}

	public String getPurchaseHistory() {
		return purchaseHistory;
	}

	public void setPurchaseHistory(String purchaseHistory) {
		this.purchaseHistory = purchaseHistory;
	}

	@Override
	public String toString() {
		return "ActivityHistory [Id=" + Id + ", loginDate=" + loginDate + ", viewHistory=" + viewHistory
				+ ", purchaseHistory=" + purchaseHistory + "]";
	}
	
	
}
