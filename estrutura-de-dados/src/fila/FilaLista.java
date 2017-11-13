package fila;

public class FilaLista<T> implements Fila<T> {

	private ElementoLista<T> ini;
	private ElementoLista<T> fim;
	private int qtdElementos = 0;

	@Override
	public void insere(T v) throws Exception {
		ElementoLista<T> novo = new ElementoLista<>();
		novo.setInformacao(v);
		if (vazia()) {
			ini = novo;
			fim = novo;
			qtdElementos++;
		} else {
			fim.setProximo(novo);
			fim = novo;
			qtdElementos++;
		}

	}

	@Override
	public T retira() throws Exception {
		if (vazia())
			throw new Exception("Fila está vazia");
		else {
			ElementoLista<T> temp = new ElementoLista<>();
			temp = ini;
			ini = null;
			ini = temp.getProximo();
			qtdElementos--;
			return temp.getInformacao();
		}
	}

	@Override
	public boolean vazia() {
		return (qtdElementos == 0);
	}

	@Override
	public void libera() {
		if (!vazia()) {
			ElementoLista<T> temp = new ElementoLista<>();
			for (int i = 0; i == qtdElementos; i++) {
				temp = ini.getProximo();
				ini = null;
				ini = temp;
			}
			qtdElementos = 0;
		}
	}

	public String toString() {

		StringBuilder sb = new StringBuilder();
		if (vazia()) {
			return "Pilha está vazia";
		}
		
		ElementoLista<T> elemento = ini;
		while (elemento != null) {
			sb.append(elemento.getInformacao() + ",");
			elemento = elemento.getProximo();
		}

		return sb.toString().substring(0, sb.toString().length() - 1); // remove
																		// ultima
																		// virgula
	}

	private class ElementoLista<T> {

		private T informacao;

		private ElementoLista proximo;

		public T getInformacao() {
			return informacao;
		}

		public void setInformacao(T informacao) {
			this.informacao = informacao;
		}

		public ElementoLista getProximo() {
			return proximo;
		}

		public void setProximo(ElementoLista proximo) {
			this.proximo = proximo;
		}

	}

	@Override
	public Fila concatenar(Fila outraFila) {
		// TODO Auto-generated method stub
		return null;
	}


}