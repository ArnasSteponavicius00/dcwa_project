package com.shops;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class StoreProductController {

	// variables
	private DAO dao;
	private ArrayList<StoreProduct> products;
	private String name;

	// Constructor
	public StoreProductController() {
		super();

		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
	}
	
	public void showAllProducts() {
		try {
			products = dao.showAllProducts();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void showStoreProducts() {
		loadProducts(name);
	}
	
	public void deleteProduct(int pid) {
		System.out.println("In deleteStore() Controller");

		try {
			dao.deleteProduct(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<StoreProduct> getProducts() {
		return products;
	}

}
