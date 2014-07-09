package br.com.atlantico.unitario;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.atlantico.model.Assunto;
import br.com.atlantico.model.Categoria;
import br.com.atlantico.model.Idioma;
import br.com.atlantico.model.Livro;
import br.com.atlantico.model.LivroRepository;

public class LivroTest {
	
	private Livro livro;
	private Idioma idioma;
	private Assunto assunto;
	private Categoria categoria;

	@Mock
	private LivroRepository repository;
	
	@Before
	public void antesDoTeste(){
		//Cria os mocks das classes anotadas com @Mock
		MockitoAnnotations.initMocks(this);
		
		idioma = new Idioma(1,"Ingles");
		assunto = new Assunto(1,"Desenvolvimento de Software");
		categoria = new Categoria(1,"categoria",assunto);
		livro = new Livro("109Se8.8", "TDD para iniciantes", "Livro que apresenta a famosa técnica de desenvolvimento", 2010, "Deitel", "FTD", 2, null, idioma, categoria);
	}
	
	@Test
	public void salvarUmLivroComTodosOsDados(){
		livro = new Livro("109Se8.8", "TDD para iniciantes", "Livro que apresenta a famosa técnica de desenvolvimento", 2010, "Deitel", "FTD", 2, null, idioma, categoria);
		Livro livroMock = Mockito.mock(Livro.class);
		//Verifica o retorno do método quando este for invocado
		Mockito.when(repository.salvar(livro)).thenReturn(livroMock);
		repository.salvar(livro);
		//Verifica se o método do repository foi invocado
		Mockito.verify(repository).salvar(livro);
		
		Assert.assertNotNull(livroMock.getId());

	}
	
	@Test
	public void atualizarNomeDeUmLivro(){
		Livro livroAtualizado = new Livro("109Se8.8", "TDD para iniciantes(2ª edição)", "Livro que apresenta a famosa técnica de desenvolvimento", 2010, "Deitel", "FTD", 2, null, idioma, categoria);
		
		Mockito.when(repository.atualizar(livroAtualizado)).thenReturn(livroAtualizado);
		repository.atualizar(livroAtualizado);
		Mockito.verify(repository).atualizar(livroAtualizado);

	}
	
	@Test
	public void deveListar2Livros(){
		List<Livro> livros = new ArrayList<Livro>();
		livros.add(new Livro("109Se8.8", "TDD para iniciantes", "Livro que apresenta a famosa técnica de desenvolvimento", 2010, "Deitel", "FTD", 2, null, idioma, categoria));
		livros.add(new Livro("109Se8.8", "TDD para iniciantes", "Livro que apresenta a famosa técnica de desenvolvimento", 2010, "Deitel", "FTD", 2, null, idioma, categoria));
		
		Mockito.when(repository.listarTodos()).thenReturn(livros);
		repository.listarTodos();
		Mockito.verify(repository).listarTodos();
		
		Assert.assertEquals(2, repository.listarTodos().size());

	}
	
	@Test
	public void deveRecuperar1Livro(){
		Livro livro = Mockito.mock(Livro.class);
		
		Mockito.when(repository.recuperar(1)).thenReturn(livro);
		repository.recuperar(1);
		Mockito.verify(repository).recuperar(1);

	}
	
	@Test
	public void deveDeletarUmLivro(){
		Mockito.doNothing().when(repository).deletar(1);
		repository.deletar(1);
		Mockito.verify(repository).deletar(1);

	}

}