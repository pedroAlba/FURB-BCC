package lista01.questao05.teste;

/**
 * Pedro Henrique Pereira Alba,
 * Renato Constancion Filho
 */

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import lista01.questao05.Estacionamento;
import lista01.questao05.Veiculo;

public class EstacionamentoTest {

	@Test
	public void test() {
		
		Estacionamento e = new Estacionamento(5);
		e.entrar(new Veiculo("A", "MODELO1", 0));
		e.entrar(new Veiculo("B", "MODELO2", 0));
		e.entrar(new Veiculo("C", "MODELO3", 0));
		e.entrar(new Veiculo("D", "MODELO4", 0));
		e.entrar(new Veiculo("E", "MODELO5", 0));
		
		assertEquals(0, e.sair("B").getManobras());
		assertEquals(1, e.sair("A").getManobras());
		assertEquals(0, e.sair("D").getManobras());
		assertEquals(0, e.sair("E").getManobras());
		assertEquals(2, e.sair("C").getManobras());
	}

}
