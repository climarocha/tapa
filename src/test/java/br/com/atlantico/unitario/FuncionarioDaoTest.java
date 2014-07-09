package br.com.atlantico.unitario;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.atlantico.dao.FuncionarioDAO;
import br.com.atlantico.model.Funcionario;

public class FuncionarioDaoTest {
	
	private Funcionario funcionario;
	
	@Before
	public void instaciaFuncionario(){
		funcionario = new Funcionario("Victor", true);
	}
		
	@Test
	public void testSalvarFuncionario(){
		Long matricula = FuncionarioDAO.salvar(funcionario);
		Assert.assertNotNull(matricula);
	}
	
	@Test
	public void testRecuperarFuncionario(){
		Long matricula = FuncionarioDAO.salvar(funcionario);
		Funcionario funcionario = FuncionarioDAO.recuperar(matricula);
		Assert.assertNotNull(funcionario.getNome());
		Assert.assertNotNull(funcionario.getMatricula());
		Assert.assertEquals("Victor", funcionario.getNome());
	}
	
	@Test
	public void testEditarFuncionario(){
		Long matricula = FuncionarioDAO.salvar(funcionario);
		Funcionario funcionario = FuncionarioDAO.recuperar(matricula);
		
		funcionario.setNome("Zé");
		
		Funcionario func = FuncionarioDAO.editar(funcionario);
		
		Assert.assertEquals("Zé", func.getNome());
		
	}
	
	@Test
	public void testRemoverFuncionario(){
		Long matricula = FuncionarioDAO.salvar(funcionario);
		funcionario = FuncionarioDAO.recuperar(matricula);
		
		Funcionario funcionario = FuncionarioDAO.remover(funcionario.getMatricula());
		
		Assert.assertEquals("Zé", func.getNome());
		
	}
	
}
