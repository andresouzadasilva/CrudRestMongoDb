package br.silva.andre.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class ConnectionFactoryMongoDb {
	
	MongoClient mongoClient;
public static  MongoClient getInstance(){
	return  new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
}
}
