package com.shops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.catalina.Store;

public class DAO {

	//variables
	private DataSource mysqlDS;

/*
 * =============================================================================
 * Constructor
 * =============================================================================
 */
	public DAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/shops";
		mysqlDS = (DataSource) context.lookup(jndiName);
	}

//==================================================================================================================================
	// Manage Stores
//==================================================================================================================================
	public ArrayList<ManageStores> loadStores() throws SQLException {
		System.out.println("In loadStores() DAO");

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		//connect to db
		myConn = mysqlDS.getConnection();
		
		//execute statement
		String sql = "Select * from store";
		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery(sql);

		ArrayList<ManageStores> stores = new ArrayList<ManageStores>();
		
		// process result set, add to arraylist
		while (myRs.next()) {
			ManageStores s = new ManageStores();
			s.setSid(myRs.getInt("id"));
			s.setStoreName(myRs.getString("name"));
			s.setFounded(myRs.getString("founded"));
			// Debug
			// System.out.println(sid + " " + name + " " + founded);

			stores.add(s);
		}
		System.out.println(stores);

		return stores;
	}// loadStores() end
	
	//addStore()
	public void addStore(ManageStores stores) throws Exception {
		System.out.println("In addStore() DAO");

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		//connect to db
		myConn = mysqlDS.getConnection();

		//execute statement
		String sql = "insert into store (name, founded) values (?, ?)";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setString(1, stores.getStoreName());
		myStmt.setString(2, stores.getFounded());
		myStmt.execute();
	}// addStore() end

	//deleteStore()
	public void deleteStore(int sid) throws SQLException {
		System.out.println("In deleteStore() DAO");
		System.out.println(sid);

		Connection myConn = null;
		PreparedStatement myStmt = null;

		//connect to db
		myConn = mysqlDS.getConnection();

		//execute statement
		String sql = "delete from store where id = ?";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setInt(1, sid);
		myStmt.execute();
	}// deleteStore() end
//==================================================================================================================================
	// Store Products
//==================================================================================================================================
	public ArrayList<StoreProduct> loadProducts(String store) throws Exception {
		System.out.println("In loadProducts() DAO");
		// System.out.println(store + " loadProducts() DAO");
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		//connect to db
		myConn = mysqlDS.getConnection();

		//execute statement
		String sql = "select s.* , p.pid, p.prodName, p.price from store as s " + "join product as p on s.id = p.sid"
				+ " where s.name = '" + store + "' order by p.pid;";

		myStmt = myConn.prepareStatement(sql);
		myRs = myStmt.executeQuery(sql);

		System.out.println(store);

		ArrayList<StoreProduct> products = new ArrayList<StoreProduct>();

		// process result set, add to arraylist
		while (myRs.next()) {
			StoreProduct p = new StoreProduct();
			p.setStid(myRs.getInt("id"));
			p.setStoreName(myRs.getString("name"));
			p.setFounded(myRs.getString("founded"));
			p.setPid(myRs.getInt("pid"));
			p.setProdName(myRs.getString("prodName"));
			p.setPrice(myRs.getString("price"));

			products.add(p);
		}
		//DEBUG
		//System.out.println(products + " loadProducts() DAO");

		return products;
	}// loadProducts() end

	//showAllProducts()
	public ArrayList<StoreProduct> showAllProducts() throws SQLException {
		System.out.println("In loadStores() DAO");

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		//connect to db
		myConn = mysqlDS.getConnection();

		//execute statement
		String sql = "select p.sid, p.pid, p.prodName, p.price from product as p order by pid;";
		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery(sql);

		ArrayList<StoreProduct> products = new ArrayList<StoreProduct>();
		
		// process result set, add to arraylist
		while (myRs.next()) {
			StoreProduct p = new StoreProduct();
			p.setSid(myRs.getInt("sid"));
			p.setPid(myRs.getInt("pid"));
			p.setProdName(myRs.getString("prodName"));
			p.setPrice(myRs.getString("price"));

			products.add(p);
		}
		System.out.println(products);

		return products;
	}// showAllProducts() end

	//addProduct()
	public void addProduct(StoreProduct product) throws Exception {
		System.out.println("In addStore() DAO");

		Connection myConn = null;
		PreparedStatement myStmt = null;

		//connect to db
		myConn = mysqlDS.getConnection();

		//excute statement
		String sql = "insert into product (sid, prodName, price) values (?, ?, ?)";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setInt(1, product.getSid());
		myStmt.setString(2, product.getProdName());
		myStmt.setString(3, product.getPrice());
		myStmt.execute();
	}// addStore() end
	
	//deleteProduct()
	public void deleteProduct(int pid) throws SQLException {
		System.out.println("In deleteProduct() DAO");
		//System.out.println(pid);

		Connection myConn = null;
		PreparedStatement myStmt = null;

		//connect to db
		myConn = mysqlDS.getConnection();

		//execute statement
		String sql = "delete from product where pid = ?";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setInt(1, pid);
		myStmt.execute();
	}// deleteProduct() end
}
