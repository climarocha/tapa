package br.com.atlantico.bdd;

import static org.junit.Assert.assertEquals;

import java.io.Serializable;

import br.com.atlantico.domain.CalculoDeHoras;
import br.com.atlantico.domain.CalculoDeHorasDiaUtil;
import br.com.atlantico.domain.Funcionario;
import br.com.atlantico.domain.FuncionarioBuilder;
import br.com.atlantico.domain.HorarioNucleo;
import br.com.atlantico.util.PeriodoUtil;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class CalculoDoBancoDeHoras implements Serializable{
	
	private static final long serialVersionUID = -4335130561363624216L;
	private Funcionario funcionario;
	private CalculoDeHoras calculadorDeHoras;
	
	@Given("^Um funcionario for trabalhar e entrar as \"([^\"]*)\", sair para o almoco as \"([^\"]*)\", retornar do almoco as \"([^\"]*)\" e sair as \"([^\"]*)\"$")
	public void Um_funcionario_for_trabalhar_e_entrar_as_sair_para_o_almoco_as_retornar_do_almoco_as_e_sair_as(String entrada, String saidaAlmoco, String retornoAlmoco, String saida) {
		try {
			FuncionarioBuilder builder = new FuncionarioBuilder();
			builder.paraFuncionario("Cleilton")
			.comMatricula(1)
			.comHorario(new HorarioNucleo())
			.entrouNaEmpresaAs(entrada)
			.foiAlmocarAs(saidaAlmoco)
			.retornouDoAlmocoAs(retornoAlmoco)
			.foiEmboraAs(saida);
			
			funcionario = builder.constroi();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Given("^Um funcionario for trabalhar e entrar as \"([^\"]*)\" e sair as \"([^\"]*)\"$")
	public void Um_funcionario_for_trabalhar_e_entrar_as_e_sair_as(String entrada, String saida) {
		try {
			FuncionarioBuilder builder = new FuncionarioBuilder();
			builder.paraFuncionario("Cleilton")
			.comMatricula(1)
			.comHorario(new HorarioNucleo())
			.entrouNaEmpresaAs(entrada)
			.foiEmboraAs(saida);
			funcionario = builder.constroi();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("^O funcionario trabalhou \"([^\"]*)\" horas, tem saldo de horas do banco de \"([^\"]*)\", tem saldo de horas de atraso do banco de \"([^\"]*)\", tem \"([^\"]*)\" horas extras e atrasou: \"([^\"]*)\"$")
	public void O_funcionario_trabalhou_horas_tem_saldo_de_horas_do_banco_de_tem_saldo_de_horas_de_atraso_do_banco_de_tem_horas_extras_e_atrasou(String horasTrabalhada, String horasExtraBanco, String horasAtrasoBanco, String horasExtra, String horasAtraso){
		assertEquals(new PeriodoUtil(calculadorDeHoras.getHorasTrabalhada()).periodoFormatado(), horasTrabalhada);
		assertEquals(new PeriodoUtil(calculadorDeHoras.getHorasExtraBanco()).periodoFormatado(), horasExtraBanco);
		assertEquals(new PeriodoUtil(calculadorDeHoras.getHorasAtrasoBanco()).periodoFormatado(), horasAtrasoBanco);
		assertEquals(new PeriodoUtil(calculadorDeHoras.getHorasExtra()).periodoFormatado(), horasExtra);
		assertEquals(new PeriodoUtil(calculadorDeHoras.getHorasAtraso()).periodoFormatado(), horasAtraso);
		
	}
	
	@When("^em um dia util da semana$")
	public void em_um_dia_util_da_semana() {
		calculadorDeHoras = new CalculoDeHorasDiaUtil(funcionario);
	}
	
	@Given("^Um funcionario for trabalhar em um dia util e entrar as \"([^\"]*)\", sair para o almoco as \"([^\"]*)\", retornar do almoco as \"([^\"]*)\" e sair as \"([^\"]*)\"$")
	public void Um_funcionario_for_trabalhar_em_um_dia_util_e_entrar_as_sair_para_o_almoco_as_retornar_do_almoco_as_e_sair_as(String entrada, String saidaAlmoco, String retornoAlmoco, String saida) throws Throwable {
		try {
			FuncionarioBuilder builder = new FuncionarioBuilder();
			builder.paraFuncionario("Cleilton")
			.comMatricula(1)
			.comHorario(new HorarioNucleo())
			.entrouNaEmpresaAs(entrada)
			.foiAlmocarAs(saidaAlmoco)
			.retornouDoAlmocoAs(retornoAlmoco)
			.foiEmboraAs(saida);
			funcionario = builder.constroi();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("^O saldo de horas do banco ? \"([^\"]*)\"$")
	public void O_saldo_de_horas_do_banco_(String horasBanco) throws Throwable {
		try {
			assertEquals(new PeriodoUtil(calculadorDeHoras.getHorasExtraBanco()), horasBanco);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
