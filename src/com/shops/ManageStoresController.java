package com.shops;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mysql.jdbc.CommunicationsException;

@ManagedBean
@SessionScoped
public class ManageStoresController {
	
	DAO dao;
	ArrayList<ManageStores> stores;
	
	public ManageStoresController() {
		super();
		
		try {		
			dao = new DAO();		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadStores() {
		System.out.println("In loadStores()");
		try {
			stores = dao.loadStores();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public String addStore(ManageStores store) {
		System.out.println(store.getStoreName() + " " + store.getFounded());
		try {
			dao.addStore(store);
			return "index";
		} catch (SQLIntegrityConstraintViolationException e) {
			FacesMessage message = 
					new FacesMessage("Error: Store already exists");
					FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (CommunicationsException e) {
			FacesMessage message = 
					new FacesMessage("Error: Can't communicate with DB");
					FacesContext.getCurrentInstance().addMessage(null, message);
		}catch (Exception e) {
			FacesMessage message = 
					new FacesMessage("Error: " + e.getMessage());
					FacesContext.getCurrentInstance().addMessage(null, message);

			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteStore(int sid) {
		System.out.println("in deleteStore()");
		try {
			dao.deleteStore(sid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<ManageStores> getStores() {
		return stores;
	}
	
}
