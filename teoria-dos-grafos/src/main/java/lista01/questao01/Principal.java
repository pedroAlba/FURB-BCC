package lista01.questao01;

/**
 * Pedro Henrique Pereira Alba,
 * Renato Constancion Filho
 */

import javax.swing.JOptionPane;

public class Principal {

	public static void main(String[] args) {
	
		
		System.out.println("lista antes\n" + ListagemDeSites.imprime());
		
		String txt = JOptionPane.showInputDialog("");
		System.out.println("input " + ListagemDeSites.procura(txt));
	
		System.out.println("lista depois\n" + ListagemDeSites.imprime());
			  
	}
	


}
