package br.com.ia.model;

import java.io.Serializable;

public class Livro implements Serializable, Cloneable{
	private static final long serialVersionUID = -2513588053122553364L;
	private Repository<Livro> repository=new LivroRepository();
	private long id;
	private String cdd;
	private String titulo;
	private String descricao;
	private int anoLancamento;
	private String autor;
	private String editora;
	private String observacao;
	private int qtdeExemplares;
	private boolean flagAtivo=true;
	private Idioma idioma;
	private Categoria categoria;
	
	public Livro() {}
	
	public Livro(Repository<Livro> repository, long id) {
		this.repository=repository;
		this.id = id;
	}
	
	public Livro(long id){
		this.id=id;
	}

	public Livro(Repository<Livro> repository, String cdd, String titulo, String descricao,
			int anoLancamento, String autor, String editora,int qtdeExemplares, String observacao,
			Idioma idioma, Categoria categoria) {
		this.repository = repository;
		preencheDadosDoLivro(cdd,titulo,descricao,anoLancamento,autor,editora,qtdeExemplares,observacao,idioma,categoria);
	}
	
	public Livro(String cdd, String titulo, String descricao,
			int anoLancamento, String autor, String editora,int qtdeExemplares, String observacao,
			Idioma idioma, Categoria categoria) {
		preencheDadosDoLivro(cdd,titulo,descricao,anoLancamento,autor,editora,qtdeExemplares,observacao,idioma,categoria);
	}
	
	private void preencheDadosDoLivro(String cdd, String titulo, String descricao,
			int anoLancamento, String autor, String editora,int qtdeExemplares, String observacao,
			Idioma idioma, Categoria categoria){
		this.cdd = cdd;
		this.titulo = titulo;
		this.descricao = descricao;
		this.anoLancamento = anoLancamento;
		this.autor = autor;
		this.editora = editora;
		this.qtdeExemplares=qtdeExemplares;
		this.observacao = observacao;
		this.idioma = idioma;
		this.categoria = categoria;
	}

	public void salvar() {
		repository.salvar(this);
	}
	
	public Livro atualizar() {
		return repository.atualizar(this);
	}

	public Livro recuperar() {
		return repository.recuperar(this.id);
	}

	public Livro inativarLivro() {
		this.flagAtivo = false;
		return this.atualizar();
	}
	
	public Livro clone() {
		Livro livro = new Livro(this.repository, this.cdd, this.titulo, this.descricao,this.anoLancamento, this.autor, this.editora, this.qtdeExemplares, this.observacao, this.idioma, this.categoria);
		livro.id=this.id;
		livro.flagAtivo=this.flagAtivo;
		return livro; 
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCdd() {
		return cdd;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public int getAnoLancamento() {
		return anoLancamento;
	}
	public String getAutor() {
		return autor;
	}
	public String getEditora() {
		return editora;
	}
	public String getObservacao() {
		return observacao;
	}
	public Idioma getIdioma() {
		return idioma;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public int getQtdeExemplares() {
		return qtdeExemplares;
	}

	public boolean isFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}	
}