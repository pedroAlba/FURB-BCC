package slideListasLineares;
public class ListaDuplamenteEncadeada<T> implements Lista<T> {
	private Nodo<T> primeiro;
	private Nodo<T> ultimo;
	private int tamanho = 0;

	public ListaDuplamenteEncadeada() {
	}

	public void insere(T x) {
		Nodo novo = new Nodo();
		novo.setInfo(x);

		if (this.estaVazia()) {
			primeiro = novo;
		} else {
			ultimo.setProx(novo);
		}
		ultimo = novo;
		tamanho++;
	}

	public boolean estaCheia() {
		return false;
	}

	public boolean estaVazia() {
		return (primeiro == null);
	}

	public void insere(T x, int p) {
		if (p >= 0 && p <= this.tamanho) {
			if (p == this.tamanho) { //�ltima posi��o
				this.insere(x);
			} else {
				Nodo novo = new Nodo();
				novo.setInfo(x);
				Nodo temp;
				if (p == 0) { // primeira posi��o
					novo.setProx(primeiro);
					primeiro = novo;
				} else { // posi��o intermedi�ria
					temp = this.Nodo(p - 1);
					novo.setProx(temp.getProx());
					temp.setProx(novo);
				}
				tamanho++;
			}
		}
	}

	private Nodo Nodo(int i) {
        // TODO Auto-generated method stub
        return null;
    }

    private Nodo<T> consultaInterna(int p) {
		if (p >= 0 && p < this.tamanho) { // posi��o procurada inv�lida
			Nodo proximo = primeiro;
			for (int i = 0; i < p; i++) {
				proximo = proximo.getProx();
			}
			return proximo;
		} else {
			return null;
		}
	}

	public String imprime() {
		String retorno = "[";
		Nodo proximo = primeiro;
		while (proximo != null) {
			retorno += proximo.getInfo() + "; ";
			proximo = proximo.getProx();
		}

		// para retirar a �ltima v�rgula e espa�o
		try {
			retorno = retorno.substring(0, retorno.length() - 2);
			return retorno + "]";
		} catch (StringIndexOutOfBoundsException strExc) {
			return "[]";
		}
	}

	public T retira(int p) {
		if (p == 0) {
			T retorno = this.primeiro.getInfo();
			this.primeiro = primeiro.getProx();
			tamanho--;
			return retorno;
		} else if (p > 0 && p < tamanho) {
			Nodo<T> elementoLista = this.consultaInterna(p - 1);
			Nodo<T> elementoRemovido = elementoLista.getProx();
			elementoLista.setProx(elementoRemovido.getProx());
			tamanho--;
			return elementoRemovido.getInfo();
		} else if (p == tamanho) {
			T retorno = this.ultimo.getInfo();
			Nodo novoUltimo = this.consultaInterna(p - 1);
			this.ultimo = novoUltimo;
			novoUltimo.setProx(null);
			tamanho--;
			return retorno;
		}
		return null;

	}

	public int localiza(T x) {
		Nodo elementoLista = primeiro;
		int i = 0;
		while ((elementoLista != null)) {
			if (elementoLista.getInfo().equals(x)) {
				return i;
			}
			i++;
			elementoLista = elementoLista.getProx();
		}

		return -1;
	}

	public int getTamanho() {
		return tamanho;
	}

	public T consulta(int p) {
		return this.consultaInterna(p).getInfo();
	}

	public Lista concatena(Lista outra) {
		Lista novalista = new ListaEncadeada();
		Nodo elementoLista = this.consultaInterna(0);
		while ((elementoLista != null)) {
			novalista.insere(elementoLista.getInfo());
			elementoLista = elementoLista.getProx();
		}

		for (int i = 0; i < outra.getTamanho(); i++) {
			novalista.insere(outra.consulta(i));
		}
		return novalista;
	}

	public Lista divide() {
		if (this.estaVazia()) {
			return null;
		}
		int tamanhoOriginal = this.getTamanho();
		Lista nova = new ListaEncadeada();
		int meio = tamanhoOriginal / 2;

		for (int i = meio; i < tamanhoOriginal; i++) {
			nova.insere(this.consulta(meio));
			this.retira(meio);
		}
		return nova;
	}

	public Lista copia() {
		Nodo elementoLista = primeiro;
		Lista lista = new ListaEncadeada();
		while ((elementoLista != null)) {
			lista.insere(elementoLista.getInfo());
			elementoLista = elementoLista.getProx();
		}
		return lista;
	}

	private class Nodo<T> {
		private T info;
		private Nodo prox;
		private Nodo prev;
		
		public T getInfo() {
			return info;
		}

		public void setInfo(T info) {
			this.info = info;
		}

		public Nodo getProx() {
			return prox;
		}

		public void setProx(Nodo prox) {
			this.prox = prox;
		}
		
		public Nodo getPrev() {
			return prev;
		}
		
		public void setPrev() {
			this.prev = prev;
	}
}
}

