package com.shops;

import java.util.ArrayList;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDAO {

	//variables
	String mongoDB = "storeHeadOfficeDB";
	String mongoCollection = "storeHeadOffice";

	MongoClient mongoClient;
	MongoDatabase database;
	MongoCollection<Document> collection;

	/* =============================================================================
	 * Constructor
	 * =============================================================================*/
	public MongoDAO() throws Exception {
		mongoClient = new MongoClient();
		database = mongoClient.getDatabase(mongoDB);
		collection = database.getCollection(mongoCollection);
	}

	// Load Office
	public ArrayList<HeadOffice> loadOffice() {
		System.out.println("In loadOffice() DAO");
		
		ArrayList<HeadOffice> officeList = new ArrayList<HeadOffice>();
		Gson gson = new Gson();
		FindIterable<Document> offices = collection.find().sort(new BasicDBObject("_id", 1));

		//loop over offices, put them into document, convert from json, add to arraylist
		for (Document d : offices) {
			HeadOffice office = gson.fromJson(d.toJson(), HeadOffice.class);
			System.out.println(office);
			officeList.add(office);
		}
		return officeList;
	}// end loadOffice()

	// Add Office
	public HeadOffice addOffice(HeadOffice office) throws Exception {
		System.out.println("In addOffice() DAO");
		
		//query
		Document doc = new Document()
				.append("_id", office.getId())
				.append("location", office.getLocation());

		//execute query
		collection.insertOne(doc);
		System.out.println("Document inserted");

		return office;
	}// end addOffice()

	// Delete Office
	public int deleteOffice(int id) {
		System.out.println("In deleteOffice() DAO");
		collection.deleteOne(new Document("_id", id));
		System.out.println("Document deleted");

		return id;
	}// end deleteOffice()
}
