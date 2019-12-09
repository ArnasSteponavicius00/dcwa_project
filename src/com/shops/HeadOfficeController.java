package com.shops;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class HeadOfficeController {
	
	private MongoDAO dao;
	private ArrayList<HeadOffice> office;
	
	//Constructor
	public HeadOfficeController() {
		super();
		try {
			dao = new MongoDAO();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}	
}
