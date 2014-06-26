package br.com.ia.dao;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import br.com.ia.model.Assunto;
import br.com.ia.model.Categoria;
import br.com.ia.model.Idioma;
import br.com.ia.model.Livro;

/*Classe que representa a tabela de livros*/
public class BancoDeDadosLivros implements Cloneable{

	private static Set<Livro> livrosBD = new HashSet<Livro>();
	
	static {
		iniciaBanco();
	}
	
	private static void iniciaBanco(){
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
		//Adiciona os livros na lista
		livrosBD.add(livro1);
		livrosBD.add(livro2);
		livrosBD.add(livro3);
	}

	public static Livro perist(Livro t) {
		t.setId(new Random().nextLong());
		livrosBD.add(t);
		return t;
	}

	public static Livro retrieve(long id) {
		for (Livro livro : livrosBD) {
			if(livro.getId()==id){
				return livro.clone();
			}
		}
		return null;
	}

	public static Livro update(Livro t) {
		livrosBD.add(t);
		return t;
	}
	

	
}
