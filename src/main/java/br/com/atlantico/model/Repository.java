package br.com.atlantico.model;

import java.io.Serializable;
import java.util.List;

public interface Repository<T extends Serializable> {
	
	T salvar(T t);
	T atualizar(T t);
	T recuperar(long id);
	void deletar(long id);
	List<T> listarTodos();

}