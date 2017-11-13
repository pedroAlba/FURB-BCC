package trabalho01.teste;

import static org.junit.Assert.*;

import org.junit.Test;

import trabalho01.model.PilhaDinamica;
import trabalho01.model.PilhaVetor;
import trabalho01.controller.Calculadora;

public class Teste {

	@Test
	public void testaCalculoPilhaFixa() throws Exception {
		String expressao = "1 2 - 4 5 + *";
		Double d = Calculadora.calculaFixo(expressao);
		assertEquals(-9, d, 0);
		
		expressao = "90 50 * 1000 50 / * 10 -";
		
		d = Calculadora.calculaFixo(expressao);
		assertEquals(89990, d, 0);
		
	}
	
	@Test
	public void testaCalculoPilhaDinamica() throws Exception{
		
		String expressao = "1 2 - 4 5 + *";
		Double d = Calculadora.calculaDinamico(expressao);
		assertEquals(-9, d, 0);
		
		expressao = "90 50 * 1000 50 / * 10 -";
		
		d = Calculadora.calculaDinamico(expressao);
		assertEquals(89990, d, 0);
	}

	@Test
	public void testaTamanhoPilhaFixa(){
		
		PilhaVetor<String> p = new PilhaVetor<String>(2);
		try {
			p.push("1");
			p.push("2");
			p.push("3");
		} catch (Exception e) {
			assertEquals(Exception.class, e.getClass());
			assertEquals("Pilha está cheia", e.getMessage());
		}
		
	}
	
	@Test
	public void testaPushPeekPopPilhaFixa() throws Exception{
		PilhaVetor<String> p = new PilhaVetor<>(5);
		
		p.push("A");
		p.push("B");
		
		assertEquals("B", p.pop());
		assertEquals("A", p.pop());
		
		p.push("C");
		p.push("D");
		
		assertEquals("D", p.peek());
		p.pop();
		assertEquals("C", p.peek());
		
	}
	
	@Test
	public void testaVazioPilhaFixa(){
		PilhaVetor<String> p = new PilhaVetor<>(5);
		
		try {
			p.pop();
		} catch (Exception e) {
			assertEquals(Exception.class, e.getClass());
			assertEquals("Pilha está vazia", e.getMessage());
		}
	}
	@Test
	public void testaPilhaDinamica() throws Exception{
		PilhaDinamica<String> p = new PilhaDinamica<>();
		p.push("A");
		p.push("B");
		
		assertEquals("B", p.peek());
		p.pop();
		assertEquals("A", p.peek());
	}
	
	@Test
	public void testaVazioPilhaDinamica(){
		PilhaDinamica<String> p = new PilhaDinamica<>();
		
		try {
			p.pop();
		} catch (Exception e) {
			assertEquals(Exception.class, e.getClass());
			assertEquals("A pilha está vazia", e.getMessage());
		}
	}
}
