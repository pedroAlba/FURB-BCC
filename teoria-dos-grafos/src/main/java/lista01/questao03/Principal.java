package lista01.questao03;
/**
 * Pedro Henrique Pereira Alba,
 * Renato Constancion Filho
 */

public class Principal {

	public static void main(String[] args) {

		Pilha p = new Pilha();
		try {
			System.out.println(p.comparar("AAAABBABABBACABBABABBAAAB"));
			System.out.println(p.comparar("AAABBABABBACABBABABBAAA"));
			System.out.println(p.comparar("ABABAACAABABA"));
			System.out.println(p.comparar("ABCBA"));
			System.out.println(p.comparar("ABCAB"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
