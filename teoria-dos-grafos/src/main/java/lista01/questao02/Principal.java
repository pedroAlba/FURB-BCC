package lista01.questao02;

/**
 * Pedro Henrique Pereira Alba,
 * Renato Constancion Filho
 */

public class Principal {

	public static void main(String[] args) {
		Lista<String> lista = new Lista<String>();
		
		lista.adicionar("1");
		lista.adicionar("2");
		lista.remover("2");
		lista.remover("1");
		lista.remover("0");
		lista.adicionar("1");
		System.out.println(lista.toString());
	}

}
