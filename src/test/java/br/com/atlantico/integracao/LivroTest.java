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
		Livro livroAntigo = new Livro("109Se8.8", "TDD para iniciantes(1ª edição)", "Livro que apresenta a famosa técnica de desenvolvimento", 2010, "Deitel", "FTD", 2, null, idioma, categoria);
		Livro livroAtualizado = new Livro("109Se8.8", "TDD para iniciantes(2ª edição)", "Livro que apresenta a famosa técnica de desenvolvimento", 2010, "Deitel", "FTD", 2, null, idioma, categoria);
		
		livroAtualizado = repository.atualizar(livroAtualizado);
		
		Assert.assertEquals(livroAntigo.getId(), livroAtualizado.getId());
		Assert.assertEquals(livroAntigo.getAutor(), livroAtualizado.getAutor());

	}
	
	@Test
	public void deveListar3Livros(){

		Assert.assertTrue(!repository.listarTodos().isEmpty());
		Assert.assertEquals(3, repository.listarTodos().size());

	}
	
	@Test
	public void deveRecuperar1Livro(){
		livro = new Livro("109Se8.8", "TDD para iniciantes", "Livro que apresenta a famosa técnica de desenvolvimento", 2010, "Deitel", "FTD", 2, null, idioma, categoria);
		repository.salvar(livro);
		
		Assert.assertEquals(livro.getAutor(), repository.recuperar(livro.getId()).getAutor());
		Assert.assertEquals(livro.getAnoLancamento(), repository.recuperar(livro.getId()).getAnoLancamento());

	}
	
	@Test
	public void deveDeletarUmLivro(){
		repository.deletar(1);
		Assert.assertEquals(2, repository.listarTodos().size());

	}
	
}