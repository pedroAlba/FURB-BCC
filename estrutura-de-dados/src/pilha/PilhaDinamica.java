package pilha;

public class PilhaDinamica<T> implements Pilha<T> {
	private NoPilha top;

	public void push(T nome) {

		NoPilha novo = new NoPilha<T>();

		novo.setValor(nome);
		novo.setProximo(top);
		top = novo;
	}

	public T pop() throws Exception {
		if (!vazia()) {
			T elemento = (T) top.getValor();
			top = top.getProximo();
			return elemento;
				
		} else {
			throw new Exception("Pilha Vazia !!!");
		}
	}

	public boolean vazia() {
		return top == null;
	}

	public int size() {
		NoPilha<T> p;
		p = top;
		int cont = 0;
		while (p != null) {
			p = p.getProximo();
			cont++;
		}
		return cont;
	}

	public NoPilha top() throws Exception {
		if (vazia()) {
			throw new Exception("Pilha Vazia !!!");
		} else {
			return top;
		}
	}


	@Override
	public T peek() throws Exception {
		if(!vazia()){
			T elemento = (T) top.getValor();
			return elemento;
		}
		return null;
	}

	@Override
	public void libera() {
		top = null;
	}
}
