package br.com.atlantico.model;

import java.util.Random;

public class Funcionario {
	
	private String nome;
	private Long matricula;
	private boolean ativo;
	
	public Funcionario(String nome, boolean ativo) {
		this.nome = nome;
		this.ativo = ativo;
		this.matricula = new Random().nextLong();
	}
	
	public String getNome() {
		return nome;
	}
	public Long getMatricula() {
		return matricula;
	}
	public boolean isAtivo() {
		return ativo;
	}
	
	public void inativar(){
		this.ativo=false;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
