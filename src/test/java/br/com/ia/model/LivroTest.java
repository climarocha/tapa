package br.com.ia.model;

import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LivroTest {
	
	Livro livro = null;
	
	@Before
	public void antesDoTeste(){
		livro = null;
	}
	
	@Test
	public void cadastrarLivro(){
		Idioma idioma = new Idioma(1,"Ingles");
		Assunto assunto = new Assunto(1,"Desenvolvimento de Software");
		Categoria categoria = new Categoria(1,"categoria",assunto);
		livro = new Livro("109Se8.8", "TDD para iniciantes", "Livro que apresenta a famosa técnica de desenvolvimento", 2010, "Deitel", "FTD", 2, null, idioma, categoria);
		livro.salvar();
		Assert.assertNotEquals(0,livro.getId());
		Assert.assertNotNull(livro);
	}
	
	@Test
	public void atualizarLivroExistente(){
		long identificador = 1;
		String nomeAutor = "autor"+new Random().nextLong();
		//Atualiza o livro
		livro = new Livro(identificador).recuperar();
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
	public void recuperarUmLivroCadastradoApartirDeUmIdentificador(){
		long identificador = 1;
		livro = new Livro(identificador).recuperar();
		Assert.assertEquals(identificador,livro.getId());
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