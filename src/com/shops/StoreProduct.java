package com.shops;

public class StoreProduct {
	private int pid;
	private int sid;
	private int stid;
	private String prodName;
	private String price;
	private String storeName;
	private String founded;

	// ==================================================================================
	// Constructors
	// ==================================================================================
	public StoreProduct() {
	}
	
	// ==================================================================================
	// Getters and Setters
	// ==================================================================================
	public int getStid() {
		return stid;
	}

	public void setStid(int stid) {
		this.stid = stid;
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

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	// ==================================================================================
	// Override toString method
	// ==================================================================================
	@Override
	public String toString() {
		return "\nID: " + this.sid + "\nName: " + this.storeName + "\nFounded: " + this.founded + "\nPID: " + this.pid
				+ "\nSID: " + this.sid + "\nName: " + this.prodName + "\nPrice: " + this.price + "\n";
	}
}
