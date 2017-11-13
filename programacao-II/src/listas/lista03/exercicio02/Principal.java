package listas.lista03.exercicio02;

import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;

public class Principal {
	
	public static void main(String[] args) {
		
		
		LinkedList<Veiculo> listaDeVeiculos = (LinkedList<Veiculo>) VeiculoFactory.getInstance().geraVeiculo(10);
		
		Veiculo v = new Veiculo("ABC-1234", "Uno", 2000, "Pedro");
		listaDeVeiculos.add(v);
		
		System.out.println("\nRemover por objeto " + listaDeVeiculos.remove(v));
		System.out.println("\nRemover por posicao " + listaDeVeiculos.remove(7));
		
		//Remover antePenultimo
		
		ListIterator i = listaDeVeiculos.listIterator(listaDeVeiculos.size() -2);
		
		listaDeVeiculos.forEach(veiculo -> System.out.println("\n" + veiculo));
		
		if(i.hasPrevious()){
			System.out.println("\nVeiculo a ser removido " + i.previous());
			i.remove();
		}
		
		listaDeVeiculos.forEach(veiculo -> System.out.println(veiculo));
		
		Collections.sort(listaDeVeiculos);
		
		System.out.println("Ordenado por placa");
		listaDeVeiculos.forEach(veiculo -> System.out.println(veiculo));
		
		
		System.out.println("\n====================== Ordenado por ano -> placa ====================== ");
		
		Collections.sort(listaDeVeiculos, new AnoPlacaComparator());		
		
		listaDeVeiculos.forEach(veiculo -> System.out.println(veiculo));
		
		
		Collections.sort(listaDeVeiculos, new ModeloAnoPlacaComparator());		
		
		System.out.println("\n======================= Ordenado por modelo -> ano -> placa");
		
		listaDeVeiculos.forEach(veiculo -> System.out.println(veiculo));
		
		
	}

}
