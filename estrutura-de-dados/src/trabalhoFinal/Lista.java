package trabalhoFinal;

public interface Lista<T> {
	
	Lista<T> concatena(Lista<T> outro);
	
	T consulta(int p);
	
	Lista<T> copia();
	
	Lista<T> divide();
	
	boolean estaCheia();
	
	boolean estaVazia();
	
	int getTamanho();
	
	String imprime();
	
	void insere(T x);
	
	void insere(T x, int p);
	
	int localiza(T x);
	
	T retira(int p);

}
