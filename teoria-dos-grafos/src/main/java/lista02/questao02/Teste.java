package lista02.questao02;
/**
 * 
 * @author Pedro Alba
 * @author Renato Constancio
 */
public class Teste {

	public static void main(String[] args) {
		
		String input = "6 1\n";
		input +=    "Rerisson Lucas\r\n" + 
					"Rerisson Jonathan\r\n" + 
					"Lucas Jonathan\r\n" + 
					"Jonathan Pedro\r\n" + 
					"Pedro Juan\r\n" + 
					"Lucas Juan";
		
		Churrasco c = new Churrasco(input);
		System.out.println(c.bfs());
		 
		
		input = "4 3\n" + 
				"Rerisson Ordan\r\n" + 
				"Ordan Gustavo\r\n" + 
				"Rerisson Yean\r\n" + 
				"Gustavo Yean";
		
		c = new Churrasco(input);
		System.out.println(c.bfs());
	}
}
