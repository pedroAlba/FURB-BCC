package fila;

public interface Fila <T>{

	void insere(T v) throws Exception;
	T retira() throws Exception;
	boolean vazia();
	void libera();
	Fila concatenar(Fila outraFila) throws Exception;
	
}
