package br.com.atlantico.model;

public class Idioma {
	
	private int id;
	private String descricao;
	
	public Idioma(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public int getId() {
		return id;
	}
	public String getDescricao() {
		return descricao;
	}

}
