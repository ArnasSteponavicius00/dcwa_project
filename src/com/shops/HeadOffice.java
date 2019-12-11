package com.shops;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class HeadOffice{
	//variables
	private int _id;
	private String location;
	
	// ==================================================================================
	// Getters and Setters
	// ==================================================================================
	public int getId() {
		return _id;
	}
	public void setId(int id) {
		this._id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	// ==================================================================================
	// Override toString method
	// ==================================================================================
	@Override
	public String toString() {
		return "\nSID: " + this._id + "\nLocation: " + this.location + "\n";
	}
}
