package br.com.ia.model;

public class Categoria {
	
	private int id;
	private String descricao;
	private Assunto assunto;
	
	public Categoria(int id, String descricao, Assunto assunto) {
		this.id = id;
		this.descricao = descricao;
		this.assunto=assunto;
	}
	
	public int getId() {
		return id;
	}
	public String getDescricao() {
		return descricao;
	}

	public Assunto getAssunto() {
		return assunto;
	}

}
