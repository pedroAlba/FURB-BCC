package listas.lista2_factory_adapter.exercicio01;

public class XBacon extends XBurguer{

	public XBacon() {
		super.addIngrediente("Bacon");
	}
	
	@Override
	public double getPreco() {
		return 8.5;
	}
}
