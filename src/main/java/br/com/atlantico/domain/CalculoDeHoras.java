package br.com.atlantico.domain;

import java.io.Serializable;

import org.joda.time.Period;

/**
 * Classe respons�vel por calcular as horas de trabalho de um funcion�rio
 * **/
public abstract class CalculoDeHoras implements Serializable{

	private static final long serialVersionUID = -943903863347027227L;
	protected Period horaAlmoco=new Period();
	protected Period horasTrabalhada=new Period();
	protected Period horasTrabalhadasNoHorarioDoFuncionario=new Period();
	protected Period horasExtraBanco=new Period();
	protected Period horasAtraso=new Period();
	protected Period horasAtrasoBanco=new Period();
	protected Period horasExtra=new Period();
	private Marcacoes marcacoes;
	private Horario horario;
	
	private Marcacao marcacaoSaidaAlmoco;
	private Marcacao marcacaoRetornoAlmoco;
	
	public CalculoDeHoras(){}
	
	public CalculoDeHoras(Funcionario funcionario){
		this.horario = funcionario.getHorario();
		this.marcacoes = funcionario.getMarcacoes();
		try {
			marcacaoSaidaAlmoco = marcacoes.getMarcacaoPorTipo(TipoDeMarcacao.SAIDA_ALMOCO);
			marcacaoRetornoAlmoco = marcacoes.getMarcacaoPorTipo(TipoDeMarcacao.RETORNO_ALMOCO);
			calculoDeHorasAtraso();
			calculoDeHorasAlmoco();
			calculoDeHorasTrabalhadas();
			calculoDeHorasExtra();
			calculoDeHorasTrabalhadasNohorario();
			calculoDehorasExtraBanco();
			calculoDeHorasAtrasoBanco();

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	/**
	 * Se o funcion�rio tiver chegado ap�s o hor�rio limite de entrada ou saido antes do hor�rio base de sa�da essas horas ser�o consideradas como [ATRASO]
	 * */
	public void calculoDeHorasAtraso(){
		
		//Calcula o atraso na entrada
		Duracao atrasoEntrada = new Duracao(marcacoes.getMarcacaoPorTipo(TipoDeMarcacao.ENTRADA), horario.getEntradaLimite(), true);
		if(atrasoEntrada.getHorasTrabalhadasNoPeriodo().getHours()<0){
			horasAtraso = horasAtraso.plusHours(atrasoEntrada.getHorasTrabalhadasNoPeriodo().getHours());
		}
		if(atrasoEntrada.getHorasTrabalhadasNoPeriodo().getMinutes()<0){
			horasAtraso = horasAtraso.plusMinutes(atrasoEntrada.getHorasTrabalhadasNoPeriodo().getMinutes());
		}
		
		//Calcula o atraso na sa�da para o almo�o, se houver marca��o
		if(marcacaoSaidaAlmoco!=null){
			Duracao atrasoSaidaAlmoco = new Duracao(horario.getSaidaAlmoco(),marcacaoSaidaAlmoco, true);
			if(atrasoSaidaAlmoco.getHorasTrabalhadasNoPeriodo().getHours()<0){
				horasAtraso = horasAtraso.plusHours(atrasoSaidaAlmoco.getHorasTrabalhadasNoPeriodo().getHours());
			}
			if(atrasoSaidaAlmoco.getHorasTrabalhadasNoPeriodo().getMinutes()<0){
				horasAtraso = horasAtraso.plusMinutes(atrasoSaidaAlmoco.getHorasTrabalhadasNoPeriodo().getMinutes());
			}
		}
		
		//Calcula o atraso no retorno do almo�o sa�da para o almo�o
		if(marcacaoRetornoAlmoco!=null){
			Duracao atrasoRetornoAlmoco = new Duracao(marcacaoRetornoAlmoco, horario.getRetornoAlmoco(), true);
			if(atrasoRetornoAlmoco.getHorasTrabalhadasNoPeriodo().getHours()<0){
				horasAtraso = horasAtraso.plusHours(atrasoRetornoAlmoco.getHorasTrabalhadasNoPeriodo().getHours());
			}
			if(atrasoRetornoAlmoco.getHorasTrabalhadasNoPeriodo().getMinutes()<0){
				horasAtraso = horasAtraso.plusMinutes(atrasoRetornoAlmoco.getHorasTrabalhadasNoPeriodo().getMinutes());
			}
		}
		
		//Calcula o atraso na sa�da
		Duracao atrasoSaida = new Duracao(horario.getSaida(), marcacoes.getMarcacaoPorTipo(TipoDeMarcacao.SAIDA), true);
		if(atrasoSaida.getHorasTrabalhadasNoPeriodo().getHours()<0){
			horasAtraso = horasAtraso.plusHours(atrasoSaida.getHorasTrabalhadasNoPeriodo().getHours());
		}
		if(atrasoSaida.getHorasTrabalhadasNoPeriodo().getMinutes()<0){
			horasAtraso = horasAtraso.plusMinutes(atrasoSaida.getHorasTrabalhadasNoPeriodo().getMinutes());
		}
		
		//Tornando as horas positivas
		horasAtraso = horasAtraso.negated().normalizedStandard();
	}
	
	/**
	 * C�lculo do per�odo de almo�o. Caso o funcion�rio n�o tenha demorado no m�nimo 1 hora mesmo assim esse valor ser� debitado.
	 * */
	public void calculoDeHorasAlmoco(){
		if(marcacaoSaidaAlmoco!=null && marcacaoRetornoAlmoco!=null){
			Duracao duracao = new Duracao(marcacoes.getMarcacaoPorTipo(TipoDeMarcacao.SAIDA_ALMOCO), marcacoes.getMarcacaoPorTipo(TipoDeMarcacao.RETORNO_ALMOCO), false);
			if(duracao.getHorasTrabalhadasNoPeriodo().getHours()<1){
				horaAlmoco=new Period().plusHours(1);
			}
			else{
				horaAlmoco=duracao.getHorasTrabalhadasNoPeriodo();
			}
			horaAlmoco = horaAlmoco.normalizedStandard();
		}
	}
	
	/**
	 * C�lculo simples considerando a entrada e sa�da do funcion�rio menos o per�odo de almo�o
	 * */
	public void calculoDeHorasTrabalhadas(){
		Duracao duracaoPeriodoTotal = new Duracao(marcacoes.getMarcacaoPorTipo(TipoDeMarcacao.ENTRADA), marcacoes.getMarcacaoPorTipo(TipoDeMarcacao.SAIDA), false);
		//Adiciona as horas 
		horasTrabalhada = horasTrabalhada.plusHours(duracaoPeriodoTotal.getHorasTrabalhadasNoPeriodo().getHours()).plusMinutes(duracaoPeriodoTotal.getHorasTrabalhadasNoPeriodo().getMinutes());
		//Menos a hora do almo�o
		horasTrabalhada = horasTrabalhada.minusHours(horaAlmoco.getHours()).minusMinutes(horaAlmoco.getMinutes());
		//Menos a saida extra
		if(marcacoes.getMarcacaoPorTipo(TipoDeMarcacao.SAIDA_EXTRA) != null && marcacoes.getMarcacaoPorTipo(TipoDeMarcacao.ENTRADA_EXTRA)!=null){
			Duracao duracao = new Duracao(marcacoes.getMarcacaoPorTipo(TipoDeMarcacao.SAIDA_EXTRA), marcacoes.getMarcacaoPorTipo(TipoDeMarcacao.ENTRADA_EXTRA), false);
			horasTrabalhada = horasTrabalhada.minusHours(duracao.getHorasTrabalhadasNoPeriodo().getHours()).minusHours(duracao.getHorasTrabalhadasNoPeriodo().getMinutes());
		}
		horasTrabalhada = horasTrabalhada.normalizedStandard();
	}
	
	/**
	 * O c�lculo das horas extras simples considera se o funcion�rio entrou antes do in�cio da jornada de trabalho e se se trabalhou ap�s o hor�rio limite do t�rmino da jornada de trabalho 
	 * */
	public void calculoDeHorasExtra(){
		Duracao horaExtraManha = new Duracao(marcacoes.getMarcacaoPorTipo(TipoDeMarcacao.ENTRADA), horario.getInicioHorario(), true);
		if(horaExtraManha.getHorasTrabalhadasNoPeriodo().getHours()>0){
			horasExtra = horasExtra.plusHours(horaExtraManha.getHorasTrabalhadasNoPeriodo().getHours());
		}
		if(horaExtraManha.getHorasTrabalhadasNoPeriodo().getMinutes()>0){
			horasExtra = horasExtra.plusMinutes(horaExtraManha.getHorasTrabalhadasNoPeriodo().getMinutes());
		}
		
		Duracao horaExtraTarde = new Duracao(horario.getTerminoHorario(), marcacoes.getMarcacaoPorTipo(TipoDeMarcacao.SAIDA), true);
		if(horaExtraTarde.getHorasTrabalhadasNoPeriodo().getHours()>0){
			horasExtra = horasExtra.plusHours(horaExtraTarde.getHorasTrabalhadasNoPeriodo().getHours());
		}
		if(horaExtraTarde.getHorasTrabalhadasNoPeriodo().getMinutes()>0){
			horasExtra = horasExtra.plusMinutes(horaExtraTarde.getHorasTrabalhadasNoPeriodo().getMinutes());
		}
		horasExtra = horasExtra.normalizedStandard();
	}
	
	/**
	 * Considera apenas as horas trabalhadas no hor�rio n�cleo
	 * */
	private void calculoDeHorasTrabalhadasNohorario() {
		horasTrabalhadasNoHorarioDoFuncionario = new Period().plusHours(horasTrabalhada.getHours()-horasExtra.getHours()).plusMinutes(horasTrabalhada.getMinutes()-horasExtra.getMinutes()).normalizedStandard();
	}
	
	/**
	 * O c�lculo das horas de banco considera as horas trabalhadas - menos as horas extras 
	 * */
	public void calculoDehorasExtraBanco(){
		if(this.horasTrabalhadasNoHorarioDoFuncionario.getHours()>horario.getCargaHorariaPadrao() || (this.horasTrabalhadasNoHorarioDoFuncionario.getHours()==horario.getCargaHorariaPadrao() && this.horasTrabalhadasNoHorarioDoFuncionario.getMinutes()>0)){
			int numhorasExtraBanco = this.horasTrabalhada.getHours() - this.horasExtra.getHours() - horario.getCargaHorariaPadrao();
			int numMinutosBanco = this.horasTrabalhada.getMinutes() - this.horasExtra.getMinutes();
			horasExtraBanco = horasExtraBanco.plusHours(numhorasExtraBanco);
			horasExtraBanco = horasExtraBanco.plusMinutes(numMinutosBanco);
		}
		horasExtraBanco = horasExtraBanco.normalizedStandard();
	}
	
	/**
	 * As horas de atraso no banco consideram as horas trabalhadas menos as horas de atraso
	 * */
	public void calculoDeHorasAtrasoBanco(){
		Period periodo = new Period().plusHours(horasTrabalhada.getHours()+horasAtraso.getHours()).plusMinutes(horasTrabalhada.getMinutes()+horasAtraso.getMinutes()).normalizedStandard();
		if(periodo.getHours() < horario.getCargaHorariaPadrao()){
			int numMinutosBanco = (horario.getCargaHorariaPadrao() - periodo.getHours())*60 - periodo.getMinutes();
			horasAtrasoBanco = horasAtrasoBanco.plusMinutes(numMinutosBanco);
		}
		horasAtrasoBanco = horasAtrasoBanco.normalizedStandard();
	}
	
    public abstract String calculaHorasExtras();

	public Period getHoraAlmoco() {
		return horaAlmoco;
	}

	public Period getHorasTrabalhada() {
		return horasTrabalhada;
	}

	public Period getHorasTrabalhadasNoHorarioDoFuncionario() {
		return horasTrabalhadasNoHorarioDoFuncionario;
	}

	public Period getHorasExtraBanco() {
		return horasExtraBanco;
	}

	public Period getHorasAtraso() {
		return horasAtraso;
	}

	public Period getHorasAtrasoBanco() {
		return horasAtrasoBanco;
	}

	public Period getHorasExtra() {
		return horasExtra;
	}

	public Marcacoes getMarcacoes() {
		return marcacoes;
	}

	public Horario getHorario() {
		return horario;
	}

}