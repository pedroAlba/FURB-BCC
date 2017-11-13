package listas.lista2_factory_adapter.exercicio01;

public class XEggBacon extends XBacon{

	public XEggBacon() {
		super.addIngrediente("Ovo");
	}
	
	@Override
	public double getPreco() {
		return 9;
	}
}
