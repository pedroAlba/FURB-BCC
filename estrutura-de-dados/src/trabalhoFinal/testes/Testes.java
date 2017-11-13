package trabalhoFinal.testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import trabalhoFinal.MapaDispersao;
import trabalhoFinal.NumeroPrimo;
import trabalhoFinal.Veiculo;

public class Testes {

	@Test
	public void testaMapa() {
		
		MapaDispersao<String, Veiculo> mapa = new MapaDispersao<>(20);
		
		Veiculo a = new Veiculo("ABC-1234", "Palio", 2017, "PEDRO");
		Veiculo b = new Veiculo("MJO-5249", "Cruise", 2012, "FRONZA");
		Veiculo c = new Veiculo("MIW-1548", "Gol", 2012, "ANTENOR");
		
		mapa.inserir(a.getPlaca(), a);
		mapa.inserir(b.getPlaca(), b);
		mapa.inserir(c.getPlaca(), c);
		
		Veiculo aRetorno = mapa.buscar("ABC-1234");
		assertEquals(a, aRetorno);
		
		Veiculo cRetorno = mapa.buscar("MIW-1548");
		assertEquals(c, cRetorno);
		
		Veiculo inexistente = mapa.buscar("ABC-1233");
		assertEquals(inexistente, null);
		
		Veiculo retornoRemocao = mapa.remover("MJO-5249");
		assertEquals(b, retornoRemocao);
		
		Veiculo retornoNuloRemocao = mapa.remover("AAA-9876");
		assertEquals(null, retornoNuloRemocao);
		
		MapaDispersao<String, Veiculo> mapa2 = new MapaDispersao<>(10);
		assertEquals(null, mapa2.buscar("123"));
		
	}
	
	@Test
	public void testaColisao(){
		
		MapaDispersao<String, Veiculo> mapa = new MapaDispersao<>(1);
		
		Veiculo a = new Veiculo("ABC-1234", "Palio", 2017, "PEDRO");
		Veiculo b = new Veiculo("MJO-5249", "Cruise", 2012, "FRONZA");
		Veiculo c = new Veiculo("MIW-1548", "Gol", 2012, "ANTENOR");
		
		mapa.inserir(a.getPlaca(), a);
		mapa.inserir(b.getPlaca(), b);
		mapa.inserir(c.getPlaca(), c);
		
		Veiculo aRetorno = mapa.buscar("ABC-1234");
		assertEquals(a, aRetorno);
		
		Veiculo cRetorno = mapa.buscar("MIW-1548");
		assertEquals(c, cRetorno);
		
	}
	
	
	@Test
	public void testaOverFlow(){
		
		try{
			List<Veiculo> veiculos = VeiculoFactory.instance.geraVeiculo(2000);
			MapaDispersao<String, Veiculo> map = new MapaDispersao<>(5000);
			for (Veiculo veiculo : veiculos) {
				map.inserir(veiculo.getPlaca(), veiculo);
			}
			
			
		}catch (Exception e) {
			fail("Não pode gerar exceção");
		}
		
	}
	
	@Test
	public void testaProximoNumeroPrimo(){
	
		assertEquals(1,  NumeroPrimo.nextPrime(0));
		assertEquals(7,  NumeroPrimo.nextPrime(6));
		assertEquals(29, NumeroPrimo.nextPrime(25));
		assertEquals(43, NumeroPrimo.nextPrime(42));
		assertEquals(61, NumeroPrimo.nextPrime(60));
		assertEquals(67, NumeroPrimo.nextPrime(61));
	}
}
