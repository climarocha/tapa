package br.com.atlantico.bdd;

import org.junit.runner.RunWith;
import cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@Cucumber.Options(format = { "pretty", "html:target/cucumber" }, tags="@important", features="src\\test\\resources\\br\\com\\atlantico\\bdd\\CalculoDoBancoDeHoras.feature", monochrome=false, strict=true)
/** TAGS - ao anotar os scenarios e/ou features com tags voc� define quais testes devem ou n�o ser executados
 * features - executa apenas os arquivos definidos em features
 * monochorme - define o layout da saida
 * strict - restringe a execu��o apenas dos scenarios implementados, caso um scenario n�o esteja implementado e o valor do strict for true ocorrer� um erro na falha
 * */
public class RunTestsImportant {
}