package listas.lista2_factory_adapter.exercicio01;

public class XTudo extends XEggBacon{

	public XTudo() {
		super.addIngrediente("Milho");
	}
	
	@Override
	public double getPreco() {
		return 10;
	}
}
