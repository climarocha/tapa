package br.com.atlantico.unitario;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.atlantico.model.Funcionario;

public class FuncionarioTest {
	
	private Funcionario funcionario;
	
	@Before
	public void instaciaFuncionario(){
		funcionario = new Funcionario("Victor", true);
	}

	@Test
	public void testCriaFuncionarioComCamposValidos() {
		Assert.assertNotNull(funcionario.getNome());
		Assert.assertNotNull(funcionario.getMatricula());
		Assert.assertTrue(funcionario.isAtivo());
	}
	
	@Test
	public void testInativarFuncionario(){
		funcionario.inativar();
		Assert.assertFalse(funcionario.isAtivo());
	}
	
	@Test
	public void testEditarFuncionario(){
		funcionario.setNome("rondy");
		Assert.assertEquals(funcionario.getNome(), "rondy");
	}
}

