package lista01.questao01;

/**
 * Pedro Henrique Pereira Alba,
 * Renato Constancion Filho
 */

import java.util.Arrays;
import java.util.LinkedList;

public class ListagemDeSites {
	
	private static LinkedList<String> sites = new LinkedList<String>(Arrays.asList(
											"www.google.com",
											"www.facebook.com",
											"www.g1.globo.com",
											"www.terra.com", 
											"www.hotmail.com",
											"www.ava.furb.br"));
	


	public static String procura(String site){
		for(int i = 0; i < sites.size(); i++){
			String txt = sites.get(i);
			
			if(txt.contains(site)){
				sites.remove(i);
				sites.addFirst(txt);
				return txt;
			}
		}
		return "Site não encontrado";
	}
	
	public static String imprime(){
		return sites.toString();
	}

}
