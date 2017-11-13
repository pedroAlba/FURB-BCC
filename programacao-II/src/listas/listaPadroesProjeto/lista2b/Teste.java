package listas.listaPadroesProjeto.lista2b;

import java.util.Date;
import listas.listaPadroesProjeto.lista2b.comportamento.DefinirValorSedex;
import listas.listaPadroesProjeto.lista2b.model.Pedido;
import listas.listaPadroesProjeto.lista2b.model.Produto;

public class Teste {
	public static void main(String[] args) throws Exception {

		Produto chocolate = new Produto("Chocolate", 5, 0.5);
		Produto arroz = new Produto("Arroz", 10, 5);

		Pedido pedido1 = new Pedido(DefinirValorSedex.getInstance());
		pedido1.setData(new Date());
		pedido1.setNomecliente("Teste");
		pedido1.setNumero(746545);
		pedido1.setEndereco("Brasil");

		pedido1.incluirItem(arroz, 10); // 100 reais, 50 kg
		pedido1.incluirItem(chocolate, 5);// 25 reais, 2,5 kg

		// pedido1.setDv(DefinirValorPAC.getInstance()); // invalido, não aceita
		// > 5kg;

		System.out.println(pedido1.toString());

	}
}
