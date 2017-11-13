package listas.lista2_factory_adapter.exercicio01;

public class XSalada extends Sanduiche{

	public XSalada() {
		super.addIngrediente("Pão");
		super.addIngrediente("Alface");
		super.addIngrediente("Tomate");
		super.addIngrediente("Queijo");
	}
	
	@Override
	public double getPreco() {
		return 6.5;
	}
	
	@Override
	public String toString() {	    
	    return "XSalada\n" + super.toString();
	}
}
