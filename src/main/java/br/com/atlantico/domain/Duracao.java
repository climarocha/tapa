package br.com.atlantico.domain;

import java.io.Serializable;

import org.joda.time.Duration;
import org.joda.time.Period;

/**
 * Classe que representa a dura��o entre uma marca�ao e outra
 * */
public class Duracao implements Serializable {
	
	private static final long serialVersionUID = -8321139465416971448L;
	private Marcacao entrada;
    private Marcacao saida;
    private Period horasTrabalhadasNoPeriodo;
    
	public Duracao(Marcacao entrada, Marcacao saida, boolean flagPermiteSaidaMaiorQueEntrada)  {
		try {
			this.entrada = entrada;
			this.saida = saida;
			if(!flagPermiteSaidaMaiorQueEntrada && saida.getDatetime().isBefore(entrada.getDatetime())){
				throw new Exception("A sa�da n�o pode ser superior a entrada");
			}else{
				Duration d = new Duration(entrada.getDatetime(), saida.getDatetime());
				this.horasTrabalhadasNoPeriodo = d.toPeriod();  
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Marcacao getEntrada() {
		return entrada;
	}
	public void setEntrada(Marcacao entrada) {
		this.entrada = entrada;
	}
	public Marcacao getSaida() {
		return saida;
	}
	public void setSaida(Marcacao saida) {
		this.saida = saida;
	}

	public Period getHorasTrabalhadasNoPeriodo() {
		return horasTrabalhadasNoPeriodo;
	}
	
}