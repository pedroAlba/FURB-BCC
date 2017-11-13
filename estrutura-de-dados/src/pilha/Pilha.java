package pilha;

public interface Pilha<T> {

	void push(T v) throws Exception;

	T pop() throws Exception;

	T peek() throws Exception;

	boolean vazia();

	void libera();
	
}
