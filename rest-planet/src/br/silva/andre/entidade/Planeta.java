package br.silva.andre.entidade;

import org.mongodb.morphia.annotations.Id;

public class Planeta {
//	private Object _id;
	@Id
	private int _id;
	private String nome;
	private String clima;
	private String terreno;
	private int aparicao=0;
	
	public int getId() {
		return _id;
	}
	public void setId(int id) {
		this._id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getClima() {
		return clima;
	}
	public void setClima(String clima) {
		this.clima = clima;
	}
	public String getTerreno() {
		return terreno;
	}
	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}
	public int getAparicao() {
		return aparicao;
	}
	public void setAparicao(int aparicao) {
		this.aparicao = aparicao;
	}
	/*public Object get_id() {
		return _id;
	}
	public void set_id(Object _id) {
		this._id = _id;
	}*/

}
