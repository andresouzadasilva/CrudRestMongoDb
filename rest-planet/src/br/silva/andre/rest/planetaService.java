package br.silva.andre.rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.silva.andre.dao.PlanetaDao;
import br.silva.andre.entidade.Planeta;



@Path("/")
public class planetaService {
	private static final String CHARSET_UTF8 = ";charset=utf-8";
	@PostConstruct
	private void init() {
		
	}
	@POST
	@Path("novo-planeta/{nome}/{clima}/{terreno}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String addPlaneta(@PathParam("nome") String nome,@PathParam("terreno") String terreno,@PathParam("clima") String clima){
		String msg="";
		try {
			Planeta planetaTemp=new Planeta();
			planetaTemp.setNome(nome);
			planetaTemp.setTerreno(terreno);
			planetaTemp.setClima(clima);
			new PlanetaDao().addPlaneta(planetaTemp);
		
			
		}catch (Exception e){
			msg="Erro ao incluir!";
			e.printStackTrace();
		}
		return msg;
		
	}

	@GET
	@Path("planeta-por-nome/{nome}")
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public Planeta getPlaneta(@PathParam("nome") String nome) {
	
		Planeta toReturn=null;
		try {
			toReturn=new PlanetaDao().getPlaneta(nome);
		} catch (Exception e){
			e.printStackTrace();;
			
		}
		return toReturn;
	}
		
	

	@GET
	@Path("planeta-por-id/{id}")
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public Planeta getPlaneta(@PathParam("id") int id) {
		Planeta toReturn=null;
		try {
			toReturn=new PlanetaDao().getPlaneta(id);
		} catch (Exception e){
			
			e.printStackTrace();
		}
		return toReturn;
	}

	@GET
	@Path("planetas")
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public List<Planeta> listarPlanetas(){
		List<Planeta> lista=null;
		lista=new PlanetaDao().listPlanetas();
		return lista;
	}

	@DELETE
	@Path("/planeta/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String removerPlaneta(@PathParam("id") int id) {
		String msg="";
		Planeta planeta=null;
		try {
			planeta=new PlanetaDao().getPlaneta(id);
		}catch (Exception e) {
			msg="Planeta inexistente";
			e.printStackTrace();
		}
		try {
			new PlanetaDao().deletePlaneta(planeta);
			msg="Planeta destruído pela Estrela da Morte";
		} catch (Exception e) {
			msg="Estrela da morte errou";
			e.printStackTrace();
		}
		
		return  msg;
	}
}
