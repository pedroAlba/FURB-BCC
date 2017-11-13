package listas.lista2_factory_adapter.exercicio01;

public class XBurguer extends XSalada{

	public XBurguer() {
		super.addIngrediente("Hamburguer");
	}
	
	@Override
	public double getPreco() {
		return 7.5;
	}
}
