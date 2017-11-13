package padroesDeProjeto.estrategia;

import padroesDeProjeto.estrategia.imovel.Apartamento;
import padroesDeProjeto.estrategia.imovel.Casa;
import padroesDeProjeto.estrategia.imovel.Imovel;
import padroesDeProjeto.estrategia.imovel.Terreno;

public class Teste {

	public static void main(String[] args) {

		Imovel casa = new Casa('A', 100f, 2);
		Imovel apartamento = new Apartamento('B', 20f, 2);
		Imovel terreno = new Terreno('C', 1000f);

		System.out.println("Casa: " + casa.getValor());
		System.out.println("Apartamento: " + apartamento.getValor());
		System.out.println("Terreno: " + terreno.getValor());
	}

}
