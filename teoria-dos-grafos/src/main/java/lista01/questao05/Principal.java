package lista01.questao05;
/**
 * Pedro Henrique Pereira Alba,
 * Renato Constancion Filho
 */

public class Principal {

	public static void main(String[] args) {
		
		Estacionamento e = new Estacionamento(5);
		
		e.entrar(new Veiculo("ABC", "MODELO1", 0));
		e.entrar(new Veiculo("DEF", "MODELO2", 0));
		e.entrar(new Veiculo("GHI", "MODELO3", 0));
		e.entrar(new Veiculo("JKL", "MODELO4", 0));
		e.entrar(new Veiculo("MNO", "MODELO5", 0));
		
		System.out.println(e.sair("ABC"));

	}

}
