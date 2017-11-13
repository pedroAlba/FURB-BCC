package slideListasLineares;

public class ClasseTeste {

	public static void main(String[] args) {
		
		ListaEncadeada<String> lista = new ListaEncadeada<>();
		lista.insere("L");
		lista.insere("M");
		lista.insere("A");
		lista.insere("B");
		lista.insere("M");
		lista.insere("N");
		lista.insere("O");
		lista.insere("P");
		lista.insere("P");
		
		System.out.println(lista.ultimoIndiceDe("M"));
		System.out.println(lista.ultimoIndiceDe("P"));
		System.out.println(lista.ultimoIndiceDe("K"));
		
		
		System.out.println(lista.imprimeInverso());
	}
}
