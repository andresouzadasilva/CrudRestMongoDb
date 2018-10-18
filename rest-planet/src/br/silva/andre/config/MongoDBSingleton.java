package br.silva.andre.config;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoDBSingleton {

 private static MongoDBSingleton mDbSingleton;
 
 private static MongoClient mongoClient;
    
 private static DB db ;
 
 
 private static final String dbHost = "mongodb://localhost:27017";

 private static final String dbName = "dbName here";
 private static final String dbUser = "dbUser here";
 private static final String dbPassword = "dbPassword here";

 private MongoDBSingleton(){};
 
 public static MongoDBSingleton getInstance(){
  if(mDbSingleton == null){
   mDbSingleton = new MongoDBSingleton();
  }
  return mDbSingleton;
 } 
 
}