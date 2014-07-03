package br.com.atlantico.model;

import java.util.List;

import br.com.atlantico.dao.BancoDeDadosLivros;

public class LivroRepository implements Repository<Livro> {
	
	public LivroRepository() {}

	@Override
	public Livro salvar(Livro t) {
		return BancoDeDadosLivros.salvar(t);
	}

	@Override
	public Livro atualizar(Livro t) {
		return BancoDeDadosLivros.update(t);
	}

	@Override
	public Livro recuperar(long id) {
		return BancoDeDadosLivros.recupera(id);
	}

	@Override
	public List<Livro> listarTodos() {
		return BancoDeDadosLivros.listaTodos();
	}

	@Override
	public void deletar(long id) {
		BancoDeDadosLivros.deletar(id);
	}

}
