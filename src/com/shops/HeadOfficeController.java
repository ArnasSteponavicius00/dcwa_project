package com.shops;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="headOfficeController")
@SessionScoped
public class HeadOfficeController {

	private MongoDAO mdao;
	private ArrayList<HeadOffice> office;
	
	//Constructor
	public HeadOfficeController() {
		super();
		try {
			mdao = new MongoDAO();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadOffice() {
		try {
			office = mdao.loadOffice();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addOffice(HeadOffice office) {
		try {
			office = mdao.addOffice(office);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<HeadOffice> getOffice() {
		return office;
	} 
}
