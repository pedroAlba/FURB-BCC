package listas.listaApoio;

public class Teste {

	public static void main(String[] args) {
		Validador v = new Validador("(a+10)+()/(23*(10.5-b)-2.59/(b*a))");
		System.out.println(v.validarFormacao());
	}
}
