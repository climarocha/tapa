package br.com.ia.model;

import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class LivroTestMock {
	
	private Livro livro;
	private Idioma idioma;
	private Assunto assunto;
	private Categoria categoria;
	private LivroRepository repository;
	
	@Before
	public void antesDoTeste(){
		idioma = new Idioma(1,"Ingles");
		assunto = new Assunto(1,"Desenvolvimento de Software");
		categoria = new Categoria(1,"categoria",assunto);
		repository=Mockito.mock(LivroRepository.class);
		livro = new Livro(repository,"109Se8.8", "TDD para iniciantes", "Livro que apresenta a famosa técnica de desenvolvimento", 2010, "Deitel", "FTD", 2, null, idioma, categoria);
	}
	
	@Test
	public void deveCadastrarUmLivroComIdValido(){
		livro = new Livro("109Se8.8", "TDD para iniciantes", "Livro que apresenta a famosa técnica de desenvolvimento", 2010, "Deitel", "FTD", 2, null, idioma, categoria);
		livro.salvar();
		Assert.assertNotEquals(0,livro.getId());
		Assert.assertNotNull(livro);
	}
	
	@Test
	public void verificarAcaoExecutadaAoSalvarUmLivro(){
		livro.salvar();
		Mockito.verify(repository).salvar(livro);
	}
	
	@Test
	public void verificarAcaoExecutadaAoAtualizarUmLivroExistente(){
		populaIdLivro(1);
		livro.atualizar();
		Mockito.verify(repository).atualizar(livro);
	}
	
	@Test 
	public void verificarAcaoExecutadaAoRecuperarUmLivroExistente(){
		populaIdLivro(1);
		livro.recuperar();
		Mockito.verify(repository).recuperar(livro.getId());
	}
	
	@Test
	public void verificarAcaoExecutadaAoInativarUmLivroExistente(){
		populaIdLivro(1);
		livro.inativarLivro();
		Assert.assertFalse(livro.isFlagAtivo());
		Mockito.verify(repository).atualizar(livro);
	}
	
	private void populaIdLivro(long id) {
		livro.setId(id);
	}
	
	//TODO Acrescentar esses testes no BDD
	@Test
	public void atualizarLivroExistente(){
		long identificador = 1;
		String nomeAutor = "autor"+new Random().nextLong();
		//Recupera o livro
		livro = new Livro(1).recuperar();
		//Altera nome
		livro.setAutor(nomeAutor);
		livro.atualizar();
		//Recupera o livro para verificar o resultado
		livro = new Livro(identificador).recuperar();
		//Asserts
		Assert.assertEquals(identificador,livro.getId());
		Assert.assertEquals(nomeAutor,livro.getAutor());
		Assert.assertNotNull(livro);
	}
	
	@Test
	public void inativarUmLivroExistente(){
		long identificador = 1;
		//Inativa o registro no banco
		livro = new Livro(identificador).inativarLivro();
		livro = new Livro(identificador).recuperar();
		Assert.assertEquals(identificador,livro.getId());
		Assert.assertFalse(livro.isFlagAtivo());
		Assert.assertNotNull(livro);
	}
	
}