package fila;

public class Teste {

	public static void main(String[] args) throws Exception {

		Fila<String> f = new FilaVetor<String>(10);

		f.insere("PRIMEIRO DA FILA");
		f.insere("SEGUNDO DA FILA");
		f.insere("TERCEIRO DA FILA");

		System.out.println(f.toString());
	}
}
