package br.com.ia.model;

import java.util.List;

import br.com.ia.dao.BancoDeDadosLivros;

public class LivroRepository implements Repository<Livro> {
	
	public LivroRepository() {}

	@Override
	public Livro salvar(Livro t) {
		return BancoDeDadosLivros.perist(t);
	}

	@Override
	public Livro atualizar(Livro t) {
		return BancoDeDadosLivros.update(t);
	}

	@Override
	public Livro recuperar(long id) {
		return BancoDeDadosLivros.retrieve(id);
	}

	@Override
	public List<Livro> listarTodos() {
		return null;
	}

	@Override
	public Livro deletar(long id) {
		return null;
	}

}
