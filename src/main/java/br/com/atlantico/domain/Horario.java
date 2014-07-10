package br.com.atlantico.domain;

import java.io.Serializable;

public interface Horario extends Serializable {
	
	Marcacao getInicioHorario();
	Marcacao getEntradaLimite();
	Marcacao getSaida();
	Marcacao getRetornoAlmoco();
	Marcacao getSaidaAlmoco();
	Marcacao getTerminoHorario();
	int getCargaHorariaPadrao();
	
}
