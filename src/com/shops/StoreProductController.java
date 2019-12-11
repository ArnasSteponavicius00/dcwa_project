package com.shops;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mysql.jdbc.CommunicationsException;

@ManagedBean(name = "storeProductController")
@SessionScoped
public class StoreProductController {

	// variables
	private DAO dao;
	private ArrayList<StoreProduct> products;
	private String name;

	// ==================================================================================
	// Constructor
	// ==================================================================================
	public StoreProductController() {
		super();
		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Load Products
	public void loadProducts(String store) {
		System.out.println("In loadProducts() Controller");
		// System.out.println(store + "loadProducts() Controller");
		name = store;
		try {
			products = dao.loadProducts(store);
			System.out.println(products);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// end loadProducts()

	// Used to display loadProducts on show_products.xhtml
	public void showStoreProducts() {
		loadProducts(name);
	}// end showStoreProducts()

	// Show all products in list_products.xhtml
	public void showAllProducts() {
		try {
			products = dao.showAllProducts();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// end showAllProducts()

	// Add Products
	public String addProduct(StoreProduct product) {
		System.out.println("In addStores() Controller");

		try {
			dao.addProduct(product);
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
	}// end addProduct()

	// Delete Product
	public void deleteProduct(int pid) {
		System.out.println("In deleteStore() Controller");
		try {
			dao.deleteProduct(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// end deleteProduct()

	public ArrayList<StoreProduct> getProducts() {
		return products;
	}

}
