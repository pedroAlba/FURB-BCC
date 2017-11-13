package trabalho01.model;

public class PilhaVetor<T> implements Pilha<T> {

	private int n;
	private int tam;
	private T[] vet;

	/**
	 * @param n
	 * @param tam
	 * @param vet
	 */
	public PilhaVetor(int tamanho) {
		tam = tamanho;
		vet = (T[]) new Object[tamanho];
	}

	/**
	 * @return the tam
	 */
	protected int getTam() {
		return tam;
	}

	@Override
	public void push(T v) throws Exception {
		if (n >= tam)
			throw new Exception("Pilha está cheia");
		vet[n] = v;
		n++;
		// return vet[n++] -> primeiro usa o n e depois soma;
	}

	@Override
	public T pop() throws Exception {
		if (this.vazia())
			throw new Exception("Pilha está vazia");
		n--;
		return vet[n];
		// return vet[--n] -> primeiro diminui e depois usa o n;

	}

	@Override
	public T peek() throws Exception {
		if (this.vazia())
			throw new Exception("Pilha está vazia");
		return vet[n - 1];
	}

	@Override
	public boolean vazia() {
		return (n <= 0);
	}

	@Override
	public void libera() {
		n = 0;

	}

}