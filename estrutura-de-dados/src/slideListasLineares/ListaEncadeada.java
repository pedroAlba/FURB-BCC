package slideListasLineares;

import javax.swing.text.AbstractDocument.ElementEdit;

public class ListaEncadeada<T> implements Lista<T> {
	private ElementoLista<T> primeiro;
	private ElementoLista<T> ultimo;
	private int tamanho = 0;

	public ListaEncadeada() {
	}

	public void insere(T x) {
		ElementoLista novo = new ElementoLista();
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
			if (p == this.tamanho) { // ultima posi��o
				this.insere(x);
			} else {
				ElementoLista novo = new ElementoLista();
				novo.setInfo(x);
				ElementoLista temp;
				if (p == 0) { // primeira posi��o
					novo.setProx(primeiro);
					primeiro = novo;
				} else { // posi��o intermedi�ria
					temp = this.consultaInterna(p - 1);
					novo.setProx(temp.getProx());
					temp.setProx(novo);
				}
				tamanho++;
			}
		}
	}

	private ElementoLista<T> consultaInterna(int p) {
		if (p >= 0 && p < this.tamanho) { // posi��o procurada inv�lida
			ElementoLista proximo = primeiro;
			for (int i = 0; i < p; i++) {
				proximo = proximo.getProx();
			}
			return proximo;
		} else {
			return null;
		}
	}

	public String imprime() {
		String retorno = "";
		ElementoLista proximo = primeiro;
		while (proximo != null) {
			retorno += proximo.getInfo() + "; ";
			proximo = proximo.getProx();
		}
		try {
			retorno = retorno.substring(0, retorno.length() - 2);
			return retorno;
		} catch (StringIndexOutOfBoundsException strExc) {
			return "";
		}
	}

	public T retira(int p) {
		if (p == 0) {
			T retorno = this.primeiro.getInfo();
			this.primeiro = primeiro.getProx();
			tamanho--;
			return retorno;
		} else if (p > 0 && p < tamanho) {
			ElementoLista<T> elementoLista = this.consultaInterna(p - 1);
			ElementoLista<T> elementoRemovido = elementoLista.getProx();
			elementoLista.setProx(elementoRemovido.getProx());
			tamanho--;
			return elementoRemovido.getInfo();
		} else if (p == tamanho) {
			T retorno = this.ultimo.getInfo();
			ElementoLista novoUltimo = this.consultaInterna(p - 1);
			this.ultimo = novoUltimo;
			novoUltimo.setProx(null);
			tamanho--;
			return retorno;
		}
		return null;

	}

	public int localiza(T x) {
		ElementoLista elementoLista = primeiro;
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
		ElementoLista elementoLista = this.consultaInterna(0);
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
		ElementoLista elementoLista = primeiro;
		Lista lista = new ListaEncadeada();
		while ((elementoLista != null)) {
			lista.insere(elementoLista.getInfo());
			elementoLista = elementoLista.getProx();
		}
		return lista;
	}

	public int ultimoIndiceDe(T x) {

		int indice = -1;
		int contador = 0;
		ElementoLista<T> atual = this.primeiro;
		
		while (atual != null){
			if(atual.getInfo().equals(x)){
				indice = contador;
			}
			contador++;
			atual = atual.getProx();
		}
		return indice;
	}

	public String imprimeInverso() {
		String retorno = "";
		for (int i = (this.getTamanho() - 1); i >= 0; i--){
			ElementoLista<T> atual = this.consultaInterna(i);
			retorno += atual.getInfo() + "; ";
		}
		
		try{
			retorno = retorno.substring(0,  retorno.length() - 2);
			return retorno + ";";
			
		}catch (StringIndexOutOfBoundsException e) {
			return "[]";
		}
		
	}

	private class ElementoLista<T> {
		private T informacao;
		private ElementoLista prox;

		public T getInfo() {
			return informacao;
		}

		public void setInfo(T informacao) {
			this.informacao = informacao;
		}

		public ElementoLista getProx() {
			return prox;
		}

		public void setProx(ElementoLista prox) {
			this.prox = prox;
		}
	}
}
