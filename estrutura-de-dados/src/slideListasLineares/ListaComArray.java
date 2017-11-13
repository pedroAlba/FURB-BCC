package slideListasLineares;
public class ListaComArray<T> implements Lista<T> {
	private T[] valores;
	private static final int MAXTAM = 100;
	private int ultimo = 0;

	public ListaComArray() {
		valores = (T[]) new Object[MAXTAM];
	}

	public void insere(T x) {
		/*
		 * if (ultimo < MAXTAM){ valores[ultimo] = x; ultimo++; }
		 */
		try {
			valores[ultimo] = x;
			ultimo++;
		} catch (ArrayIndexOutOfBoundsException aiobe) {
			// n�o � feito nada, ou seja, o elemento n�o � inclu�do por n�o ter mais espa�o
		}
	}

	public boolean estaCheia() {
		return (ultimo == MAXTAM);
	}

	public boolean estaVazia() {
		return (ultimo == 0);
	}

	public void insere(T x, int p) {
		if (!this.estaCheia() && p <= ultimo) {
			T temp;
		for (int i = p; i <= ultimo; i++) {
			temp = valores[i];
			valores[i] = x;
			x = temp;
		}
		ultimo++;
		}
	}

	public T retira(int p) {
		T retorna = null;
		if (!this.estaVazia() && p >= 0 && p < ultimo) {
			retorna = valores[p];
		for (int i = p; i < ultimo - 1; i++) {
			valores[i] = valores[i + 1];
		}
		ultimo--;
		valores[ultimo] = null; // opcional
	}
	return retorna;
	}

	public int localiza(T x) {
		for (int i = 0; i < this.ultimo; i++) {
			if (valores[i].equals(x)) {
				return i;
			}
		}
		return -1;
	}

	public int getTamanho() {
		return this.ultimo;
	}

	public String imprime() {
		String retorna = "[";

		for (int i = 0; i < this.ultimo; i++) {
			retorna += valores[i] + "; ";
		}
		
		try {
			// para retirar a �ltima v�rgula e o espa�o
			retorna = retorna.substring(0, retorna.length() - 2);
			return retorna + "]";
		} catch (StringIndexOutOfBoundsException strExc) {
			return "[]";
		}
	}

	public T consulta(int p) {
		try {
			return this.valores[p];
		} catch (IndexOutOfBoundsException iae) {
			return null;
		}
	}

	public Lista<T> concatena(Lista<T> outra) {
		if (this.getTamanho() + outra.getTamanho() > MAXTAM)
			return null;

		ListaComArray<T> nova = new ListaComArray();
		for (int i = 0; i < this.getTamanho(); i++) {
			nova.insere(this.consulta(i));
		}
		for (int i = 0; i < outra.getTamanho(); i++) {
			nova.insere(outra.consulta(i));
		}
		return nova;
	}

	public Lista<T> divide() {
		if (this.estaVazia())
			return null;

		ListaComArray<T> nova = new ListaComArray();
		int meio = this.getTamanho() / 2;

		for (int i = meio; i < this.getTamanho(); i++) {
			nova.insere(valores[i]);
			valores[i] = null;
		}
		this.ultimo = meio;
		return nova;
	}

	public Lista<T> copia() {
		ListaComArray<T> nova = new ListaComArray();

		for (int i = 0; i < this.getTamanho(); i++) {
			nova.insere(valores[i]);
		}
		return nova;
	}

}
