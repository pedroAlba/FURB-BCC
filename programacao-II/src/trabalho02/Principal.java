package trabalho02;

import java.util.ArrayList;

import trabalho02.comportamento.Comportamento;
import trabalho02.modelo.Jogador;
import trabalho02.modelo.Jogo;
import trabalho02.modelo.Nivel;
import trabalho02.modelo.Oponente;

public class Principal {
	public static void main(String[] args) {
	
		Jogo jogo= Jogo.getInstance(3, Nivel.FACIL);		
		ArrayList<Jogador> jogadores = jogo.getJogadores();
		ArrayList<Oponente> oponentes = jogo.getOponentes(); 
		Jogador j1 = jogadores.get(0);	
		// Exibe a mensagem, e define ela nos outros jogadores 
		j1.setVida(4);
		
		Oponente o1 = oponentes.get(0);
		
		// Exibe o comportamento inicial		
		System.out.println("\n-----Comportamento inicial-----");
		System.out.println(o1.toString());
		
		// Altera o comportamento de um oponente
		System.out.println("\n-----Definindo comportamento agressivo! -----");
		o1.setComportamento(Comportamento.AGRESSIVO);
		System.out.println(o1.toString());
		
		// Altera o comportamento de um oponente
		System.out.println("\n-----Definindo comportamento defensivo! -----");
		o1.setComportamento(Comportamento.DEFENSIVO);
		System.out.println(o1.toString());
		
		// Altera o comportamento de um oponente
		System.out.println("\n-----Definindo comportamento normal! -----");
		o1.setComportamento(Comportamento.NORMAL);			
		System.out.println(o1.toString());
		
	}
}
