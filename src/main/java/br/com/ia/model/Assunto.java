package br.com.ia.model;

public class Assunto {
	
	private int id;
	private String descricao;
	
	public Assunto(int id, String descricao) {
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
