package fila;

public class FilaVetor<T> implements Fila<T> {

	private T[] vet;
	private int tam;
	private int n;
	private int ini;

	public FilaVetor(int tamanho) {
		tam = tamanho;
		vet = (T[]) new Object[tamanho];
	}

	private int fim() {
		return ((ini + n) % tam);
	}

	@Override
	public void insere(T v) throws Exception {
		if (cheia())
			throw new Exception("Fila está cheia");
		else {
			vet[fim()] = v;
			n++;
		}
	}

	@Override
	public T retira() throws Exception {
		if (vazia())
			throw new Exception("Fila está vazia");
		else {
			n--;
			return vet[ini++];
		}
	}

	@Override
	public boolean vazia() {
		return (n == 0);
	}

	private boolean cheia() {
		return n > tam;
	}

	@Override
	public void libera() {
		n = 0;
		ini = 0;
	}

	@Override
	public Fila concatenar(Fila outraFila) throws Exception {
		
		Fila<T> novaFila;
		Fila<T> copiaOutraFila;
		
		int tamanhoOutraFila = 0;
		
		T o = (T) outraFila.retira();
		
		while(o != null){
			tamanhoOutraFila++;
			//copiaOutraFila.insere(o);
		}
		
		int indiceVetor = 0;
		novaFila = new FilaVetor<>(tamanhoOutraFila + this.getTamanho());
		
		//T[] vetor = (T []) new Object[tamanho];
		

		
		return novaFila;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		int indice = ini;
		for (int i = 0; i < n; i++) {
			indice = (ini + i) % getTamanho();
			sb.append(this.vet[indice] + ",");
			//if (indice++ >= getTamanho()) {
			//	indice = 0;
			//}
		}
		return sb.toString().substring(0, sb.toString().length() - 1);
	}

	private int getTamanho() {
		return tam;
	}

}
