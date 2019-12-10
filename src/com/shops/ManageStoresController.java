package com.shops;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mysql.jdbc.CommunicationsException;

@ManagedBean(name="manageStoresController")
@SessionScoped
public class ManageStoresController {

	// variables
	private DAO dao;
	private ArrayList<ManageStores> stores;

	// Constructor
	public ManageStoresController() {
		super();
		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// display stores by calling loadStores method from DAO.java
	public void loadStores() {
		System.out.println("In loadStores() Controller");

		try {
			stores = dao.loadStores();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// add a store by calling addStore method from DAO.java
	public String addStore(ManageStores store) {
		System.out.println("In addStores() Controller");
		System.out.println(store.getStoreName() + " " + store.getFounded());

		try {
			dao.addStore(store);
			return "index";
		} catch (SQLIntegrityConstraintViolationException e) {
			FacesMessage message = new FacesMessage("Error: Store already exists");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (CommunicationsException e) {
			FacesMessage message = new FacesMessage("Error: Can't communicate with DB");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			FacesMessage message = new FacesMessage("Error: " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);

			e.printStackTrace();
		}
		return null;
	}

	// delete a store by calling deleteStore method from DAO.java
	public void deleteStore(int sid) {
		System.out.println("In deleteStore() Controller");

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
