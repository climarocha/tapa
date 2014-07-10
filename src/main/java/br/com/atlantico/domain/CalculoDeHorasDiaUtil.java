package br.com.atlantico.domain;

import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

/**
 * Classe responsável por realizar os cálculos da hora padrão
 * */
public class CalculoDeHorasDiaUtil extends CalculoDeHoras {

	private static final long serialVersionUID = -79800458483859740L;
	
	public CalculoDeHorasDiaUtil(Funcionario funcionario) {
		super(funcionario);
	}

	@Override
	public String calculaHorasExtras() {
		
		try {
			PeriodFormatter formatter = new PeriodFormatterBuilder().minimumPrintedDigits(2).printZeroAlways()
			.appendHours()		
			.appendSeparator(":")
			.appendMinutes()
            .toFormatter();
			 System.out.println("[HORAS TRABALHADAS - TODAS] - "+formatter.print(horasTrabalhada));
			 System.out.println("[HORAS TRABALHADAS - HORÁRIO NÚCLEO] - "+formatter.print(horasTrabalhadasNoHorarioDoFuncionario));
			 System.out.println("[HORAS TRABALHADAS - HORAS EXTRAS] - "+formatter.print(horasExtra));
			 System.out.println("[HORAS TRABALHADAS - HORAS EXTRAS BANCO] - "+formatter.print(horasExtraBanco));
			 System.out.println("[HORAS ALMOÇO] - "+formatter.print(horaAlmoco));
			 System.out.println("[HORAS ATRASO - DESCONTO] - "+formatter.print(horasAtraso));
			 System.out.println("[HORAS ATRASO - BANCO] - "+formatter.print(horasAtrasoBanco));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
