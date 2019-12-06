package com.shops;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DAO {

	private DataSource mysqlDS;
	
	/* ======================================================================================================
	 * Constructor
	 * ====================================================================================================== */
	public DAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/shops";
		mysqlDS = (DataSource) context.lookup(jndiName);
	}
	
	public ArrayList<ManageStores> loadStores() throws SQLException{	
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();
		
		String sql = "Select * from store";
		
		myStmt = myConn.createStatement();
		
		myRs = myStmt.executeQuery(sql);
		
		ArrayList<ManageStores> stores = new ArrayList<ManageStores>();
		
		while(myRs.next()) {
			
			ManageStores m = new ManageStores();
			
			m.setStoreId(myRs.getInt("id"));
			m.setStoreName(myRs.getString("name"));
			m.setFounded(myRs.getString("founded"));
			
			stores.add(m);
		}	
		return stores;
	}
	
	public void addStore(ManageStores stores) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();
		String sql = "insert into store (name, founded) values (?, ?)";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setString(1, stores.getStoreName());
		myStmt.setString(2, stores.getFounded());
		myStmt.execute();	
	}
	
	public void deleteStore(int sid) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();
		String sql = "delete from store where id = ?";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setInt(1, sid);
		myStmt.execute();			
	}
	
}
