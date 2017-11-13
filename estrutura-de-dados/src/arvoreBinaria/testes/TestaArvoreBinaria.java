package arvoreBinaria.testes;

import static org.junit.Assert.*;

import org.junit.Test;

import arvoreBinaria.ArvoreBinaria;
import arvoreBinaria.NoArvoreBinaria;

public class TestaArvoreBinaria {

	@Test
	public void testSearch() {
		NoArvoreBinaria<Integer> no4 = new NoArvoreBinaria<Integer>(4);
		NoArvoreBinaria<Integer> no5 = new NoArvoreBinaria<Integer>(5);
		NoArvoreBinaria<Integer> no2 = new NoArvoreBinaria<Integer>(2, no4, no5);
		NoArvoreBinaria<Integer> no7 = new NoArvoreBinaria<Integer>(7);
		NoArvoreBinaria<Integer> no6 = new NoArvoreBinaria<Integer>(6, no7, null);
		NoArvoreBinaria<Integer> no3 = new NoArvoreBinaria<Integer>(3, null, no6);
		NoArvoreBinaria<Integer> no1 = new NoArvoreBinaria<Integer>(1, no2, no3);
		ArvoreBinaria<Integer> arvore = new ArvoreBinaria<>();
		arvore.setRaiz(no1);
		
		assertEquals(null, arvore.pertence(50));
		assertEquals(no4.getInfo(), arvore.pertence(4).getInfo());
		
	}
	
	@Test
	public void testToString(){
		NoArvoreBinaria<Integer> no4 = new NoArvoreBinaria<Integer>(4);
		NoArvoreBinaria<Integer> no5 = new NoArvoreBinaria<Integer>(5);
		NoArvoreBinaria<Integer> no2 = new NoArvoreBinaria<Integer>(2, no4, no5);
		NoArvoreBinaria<Integer> no7 = new NoArvoreBinaria<Integer>(7);
		NoArvoreBinaria<Integer> no6 = new NoArvoreBinaria<Integer>(6, no7, null);
		NoArvoreBinaria<Integer> no3 = new NoArvoreBinaria<Integer>(3, null, no6);
		NoArvoreBinaria<Integer> no1 = new NoArvoreBinaria<Integer>(1, no2, no3);
		ArvoreBinaria<Integer> arvore = new ArvoreBinaria<>();
		arvore.setRaiz(no1);
		
		assertEquals("1( 2( 4()() )( 5()() ) )( 3()( 6( 7()() )() ) )", arvore.toString());
				
		
	}

}
