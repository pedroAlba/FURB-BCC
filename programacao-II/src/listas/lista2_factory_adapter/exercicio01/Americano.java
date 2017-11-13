package listas.lista2_factory_adapter.exercicio01;

public class Americano extends XSalada{

	public Americano() {
		super.addIngrediente("Ovo");
	}
	
	@Override
	public double getPreco() {
		return 7.5;
	}
}
