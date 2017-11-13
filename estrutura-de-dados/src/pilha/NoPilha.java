package pilha;

public class NoPilha<T> {

	private T valor;
	private NoPilha proximo;

	public NoPilha() {
	};

	public NoPilha(T valor, NoPilha<T> prox) {
		this.valor = valor;
		this.proximo = prox;
	}

	public T getValor() {
		return valor;
	}

	public void setValor(T valor) {
		this.valor = valor;
	}

	public NoPilha getProximo() {
		return proximo;
	}

	public void setProximo(NoPilha proximo) {
		this.proximo = proximo;
	}

}
