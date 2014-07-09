package br.com.atlantico.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import br.com.atlantico.model.Assunto;
import br.com.atlantico.model.Categoria;
import br.com.atlantico.model.Idioma;
import br.com.atlantico.model.Livro;

/*Classe que representa a tabela de livros*/
public class BancoDeDadosLivros implements Cloneable{

	private static HashMap<Long, Livro> livrosBD = new HashMap<Long, Livro>();
	
	public static void iniciaBanco(){
		livrosBD = new HashMap<Long, Livro>();
		Idioma idioma=new Idioma(1, "Ingles");
		Categoria categoria=new Categoria(1, "Desenvolvimento de software", new Assunto(1,"Tecnologia da informação"));
		//Instanciando os livros
		Livro livro1 = new Livro("cdd - L1", "titulo - L1", "descricao - L1", 2010, "autor - L1", "editora - L1",1, "observacao - L1", idioma, categoria);
		Livro livro2 = new Livro("cdd - L2", "titulo - L2", "descricao - L2", 2010, "autor - L2", "editora - L2",2, "observacao - L2", idioma, categoria);
		Livro livro3 = new Livro("cdd - L3", "titulo - L3", "descricao - L3", 2010, "autor - L3", "editora - L3",3, "observacao - L3", idioma, categoria);
		//Atribuindo os ID´s
		livro1.setId(1);
		livro2.setId(2);
		livro3.setId(3);
		//Adicionando os livros no Map
		livrosBD.put(livro1.getId(), livro1);
		livrosBD.put(livro2.getId(), livro2);
		livrosBD.put(livro3.getId(), livro3);
	}

	public static Livro salvar(Livro livro) {
		livro.setId(new Random().nextLong());
		livrosBD.put(livro.getId(), livro);
		return livro;
	}

	public static Livro recupera(long id) {
		if(livrosBD.containsKey(id)){
			return livrosBD.get(id).clone();
		}
		return null;
	}

	public static Livro update(Livro livro) {
		livrosBD.put(livro.getId(), livro);
		return livro;
	}
	
	public static List<Livro> listaTodos() {
		return new ArrayList<>(livrosBD.values());
	}
	
	public static void deletar(long id) {
		livrosBD.remove(id);
	}
	
}
