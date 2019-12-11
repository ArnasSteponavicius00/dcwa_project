package com.shops;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="headOfficeController")
@SessionScoped
public class HeadOfficeController {

	//variables
	private MongoDAO mdao;
	private ArrayList<HeadOffice> office;
	
	// ==================================================================================
	// Constructor
	// ==================================================================================
	public HeadOfficeController() {
		super();
		try {
			mdao = new MongoDAO();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Load Offices
	public void loadOffice() {
		System.out.println("In loadOffice() Controller");
		try {
			office = mdao.loadOffice();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}//end loadOffice()
	
	//Add Offices
	public void addOffice(HeadOffice office) {
		System.out.println("In addOffice() Controller");
		try {
			office = mdao.addOffice(office);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//end addOffice()
	
	//Delete Offices
	public void deleteOffice(int id) {
		System.out.println("In deleteOffice() Controller");
		try {
			mdao.deleteOffice(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//end deleteOffice()

	public ArrayList<HeadOffice> getOffice() {
		return office;
	} 
}
