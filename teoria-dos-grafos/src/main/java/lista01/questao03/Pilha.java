package lista01.questao03;

/**
 * Pedro Henrique Pereira Alba,
 * Renato Constancion Filho
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class Pilha {
	
//	https://docs.oracle.com/javase/8/docs/api/java/util/Stack.html
	
	private Deque<Character> pilhaX;
	private Deque<Character> pilhaY;
	

	public Pilha() {
		super();
		pilhaX = new ArrayDeque<Character>();
		pilhaY = new ArrayDeque<Character>();
	}
	
	public boolean comparar(String lista) throws Exception {
		
		if ( (lista.length() & 1) == 0 )
			throw new Exception("String com número incorreto de caracteres");
		
		String[] stringPartida = lista.split("C");
		char[] parte1 = stringPartida[0].toCharArray();
		char[] parte2 = stringPartida[1].toCharArray();
		
//		Já é realizado o controle de par ou impar no início do método
//		if(parte1.length != parte2.length)
//			return false;
		
		for (int i = 0; i < parte1.length; i++) {
			pilhaX.push(parte1[i]);
		}
		for (int i = parte2.length - 1; i >= 0; i--) {
			pilhaY.push(parte2[i]);
		}
		
		return comparar(lista.length() / 2);
	}
	
	private boolean comparar(int t) {
		for (int i = 0; i < t; i++) {
			if(! (pilhaX.pop().equals(pilhaY.pop())))
				return false;
		}
		return true;
	}
}
