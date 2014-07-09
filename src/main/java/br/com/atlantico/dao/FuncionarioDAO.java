package br.com.atlantico.dao;

import java.util.HashMap;

import br.com.atlantico.model.Funcionario;

public class FuncionarioDAO {

private static HashMap<Long, Funcionario> funcionariosBD = new HashMap<Long, Funcionario>();
	
	public static Long salvar(Funcionario funcionario) {
		
		funcionariosBD.put(funcionario.getMatricula(), funcionario);
		return funcionario.getMatricula();
	}
	
	public static Funcionario recuperar(Long matricula) {
		return funcionariosBD.get(matricula);
				
	}
	
	public static Funcionario editar(Funcionario func){
		funcionariosBD.put(func.getMatricula(),func);
		
		return func;
		
	}

	public static Funcionario remover(Long matricula) {
		// TODO Auto-generated method stub
		return funcionariosBD.remove(matricula);
	}

}
