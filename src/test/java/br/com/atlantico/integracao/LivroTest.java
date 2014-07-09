package br.com.atlantico.integracao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.atlantico.dao.BancoDeDadosLivros;
import br.com.atlantico.model.Categoria;
import br.com.atlantico.model.Idioma;
import br.com.atlantico.model.Livro;
import br.com.atlantico.model.LivroRepository;

public class LivroTest {
	
	private Livro livro;
	private Idioma idioma;
	private Categoria categoria;

	private LivroRepository repository;
	
	@Before
	public void antesDoTeste(){
		repository = new LivroRepository();
		BancoDeDadosLivros.iniciaBanco();
	}
	
	@Test
	public void salvarUmLivroComTodosOsDados(){
		livro = new Livro("109Se8.8", "TDD para iniciantes", "Livro que apresenta a famosa técnica de desenvolvimento", 2010, "Deitel", "FTD", 2, null, idioma, categoria);
		repository.salvar(livro);
		Assert.assertNotNull(livro.getId());
	}
	
	@Test
	public void atualizarNomeDeUmLivro(){
		String titulo="TDD para iniciantes(2ª edição)";
		//Persiste o livro
		Livro livroAntigo = new Livro("109Se8.8", "TDD para iniciantes(1ª edição)", "Livro que apresenta a famosa técnica de desenvolvimento", 2010, "Deitel", "FTD", 2, null, idioma, categoria);
		livroAntigo = repository.salvar(livroAntigo);
		//Recupera o livro salvo
		Livro livroAAtualizar = repository.recuperar(livroAntigo.getId());
		livroAAtualizar.setTitulo(titulo);
		livroAAtualizar = repository.atualizar(livroAAtualizar);
		//Verifica os asserts
		Assert.assertEquals(livroAntigo.getId(), livroAAtualizar.getId());
		Assert.assertNotEquals(livroAntigo.getTitulo(), livroAAtualizar.getTitulo());
		Assert.assertEquals(livroAAtualizar.getTitulo(),titulo);
	}
	
	@Test
	public void deveListar3Livros(){

		Assert.assertTrue(!repository.listarTodos().isEmpty());
		Assert.assertEquals(3, repository.listarTodos().size());

	}
	
	@Test
	public void deveRecuperar1Livro(){
		livro = new Livro("109Se8.8", "TDD para iniciantes", "Livro que apresenta a famosa técnica de desenvolvimento", 2010, "Deitel", "FTD", 2, null, idioma, categoria);
		livro = repository.salvar(livro);
		Assert.assertEquals(livro.getAutor(), repository.recuperar(livro.getId()).getAutor());
		Assert.assertEquals(livro.getAnoLancamento(), repository.recuperar(livro.getId()).getAnoLancamento());
	}
	
	@Test
	public void deveDeletarUmLivro(){
		repository.deletar(1);
		Assert.assertEquals(2, repository.listarTodos().size());
	}
	
}