package br.silva.andre.dao;

import java.util.ArrayList;
import java.util.List;


import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import br.silva.andre.config.ConnectionFactoryMongoDb;
import br.silva.andre.entidade.Planeta;

import static com.mongodb.client.model.Filters.eq;

public class PlanetaDao {
	
	MongoClient mongocliente= ConnectionFactoryMongoDb.getInstance();
	//	DB db=mongocliente.getDB(dbName)
	 MongoDatabase database = mongocliente.getDatabase("Starwars");
	 MongoCollection<Document> collection = database.getCollection("Planetas");
	// static MongoCollection<Document> counter = database.getCollection("Counter");/* Coleção auxiliar, para garantir ids inteiros únicos na coleção Planetas*/
	// static MongoCollection<Document> counterMax = database.getCollection("Counter_max");/*Coleção de um valor que guarda o maior id inteiro da coleção Planetas. Criada apenas por problemas de performance ao localizar o maior id e inserir um novo documento. */
	
	public String addPlaneta(Planeta planeta) {
		
		try{
			
			collection.insertOne(createDBObject(planeta));
			
		} catch (Exception e) {
			return "Não foi possivel adicionar";
		}
		finally {
	//		mongocliente.close();
		}
			return "Planeta Criado";
		
		
	}
	public List<Planeta> listPlanetas(){
		
		List<Planeta> planetas= new ArrayList<Planeta>();
		MongoCursor<Document> doc=collection.find().iterator();
		while (doc.hasNext()) {
			Document document=doc.next();
			planetas.add(montaPlaneta(document));
		}
	//	mongocliente.close();
		return planetas;	
	}
	public Planeta getPlaneta(String nome) {

		if (collection.find(eq("nome", nome)).iterator().hasNext()) {
			Document doc=collection.find(eq("nome", nome)).iterator().next();
			return montaPlaneta(doc);
			
		}
		
	//	mongocliente.close();
		return null;
	}
	public Planeta getPlaneta(int id) {
		if (collection.find(eq("_id", id)).iterator().hasNext()) {
			Document doc=collection.find(eq("_id", id)).iterator().next();
			return montaPlaneta(doc);
			
		}
		
	//	mongocliente.close();
		return null;
	}
	/*nome Alternativo: fireDeathStarAt(Planeta planeta)*/
	public String deletePlaneta(Planeta planeta) {
		String msg="Estrela da Morte não encontrou planeta- Planeta não encontrado";
		if 	(collection.find((eq("_id", planeta.getId()))).iterator().hasNext()) {
			collection.deleteOne((eq("_id", planeta.getId())));
			msg= planeta.getNome() + " destruído pela Estrela da Morte";
		
		};
	//	mongocliente.close();
		return msg;
	}
	/* Método para montar o POJO planeta a partir de um Document*/
	private  Planeta montaPlaneta(Document doc) {
		Planeta planeta=new Planeta();
		planeta.setId(doc.getInteger("_id"));
		planeta.setNome(doc.getString("nome"));
		planeta.setClima(doc.getString("clima"));
		planeta.setTerreno(doc.getString("terreno"));
		planeta.setAparicao(doc.getInteger("aparicao"));
		return planeta;
		
	}
	/*----------------------------------------------*/
	
	private int getNewId() {
		int count=0;
		
			
		MongoCursor<Document> doc=collection.find().iterator();
	
	
	
		while (doc.hasNext()) {
			if (doc.next().getInteger("_id")>count) {
				count=doc.next().getInteger("_id");
				count++;
			}
			
		}
	//Métodos com o uso das tabelas auxiliares
	/*	count=counterMax.find().iterator().next().getInteger("Max*(counter)");
		count++;	*/
		return count;
	}
	
	private  Document createDBObject(Planeta planeta) {
		
		Document docBuilder = new Document("nome",planeta.getNome())
				.append("clima", planeta.getClima())
				.append("terreno", planeta.getTerreno())
				.append("Aparicao", planeta.getAparicao());
		if (!(planeta.getId()==0)) {
			int i=getNewId();
			docBuilder.append("_id", i);
	
			/*Atualiza a coleção auxiliar  e o documento que armazena o maior valor
			Document doc =new Document("Max*(counter)",i);		
			Document document= new Document("counter",i);
			counter.insertOne(document);
			counterMax.findOneAndUpdate(counterMax.find().first(), doc);*/
		}
		
								

	

		return docBuilder;
	}
	

}
