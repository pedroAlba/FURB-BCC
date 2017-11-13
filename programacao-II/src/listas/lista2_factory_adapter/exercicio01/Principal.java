package listas.lista2_factory_adapter.exercicio01;

public class Principal {

	public static void main(String[] args) throws Exception {
		
		Sanduiche x = Sanduiche.comprarSanduiche("XSalada");
		System.out.println(x.getPreco());
		System.out.println(x);
		
		Sanduiche y = Sanduiche.comprarSanduiche("Americano");
		System.out.println(y.getPreco());
		System.out.println(y);
	}
}
