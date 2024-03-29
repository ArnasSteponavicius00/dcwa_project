package com.shops;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class ManageStores {

	// variables
	private int sid;
	private String storeName;
	private String founded;

	// ==================================================================================
	// Constructors
	// ==================================================================================
	public ManageStores() {
	}

	// ==================================================================================
	// Getters and Setters
	// ==================================================================================
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getFounded() {
		return founded;
	}

	public void setFounded(String founded) {
		this.founded = founded;
	}

	// ==================================================================================
	// Override toString method
	// ==================================================================================
	@Override
	public String toString() {
		return "ID: " + this.sid + "\nName: " + this.storeName + "\nFounded: " + this.founded + "\n";
	}
}
