package com.shops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDAO {
	
	String mongoDB = "storeHeadOfficeDB";
	String mongoCollection = "storeHeadOffice";
	
	MongoClient mongoClient;
	MongoDatabase database;
	MongoCollection<Document> collection;
	
	/* ======================================================================================================
	 * Constructor
	 * ====================================================================================================== */
	public MongoDAO() throws Exception {
		mongoClient = new MongoClient();
		database = mongoClient.getDatabase(mongoDB);
		collection = database.getCollection(mongoCollection);
	}

	public ArrayList<HeadOffice> loadOffice() {
		ArrayList<HeadOffice> officeList = new ArrayList<HeadOffice>();
		Gson gson = new Gson();
		FindIterable<Document> offices = collection.find().sort(new BasicDBObject("_id", 1));
		
		for(Document d : offices) {
			HeadOffice office = gson.fromJson(d.toJson(), HeadOffice.class);
			System.out.println(office);
			officeList.add(office);
		}
		return officeList;
	}
	
	public HeadOffice addOffice(HeadOffice office) throws Exception {
		Document doc = new Document()
		.append("_id", office.getId())
		.append("location", office.getLocation());
		
		collection.insertOne(doc);
		System.out.println("Document inserted");
		
		return office;
	}
}
