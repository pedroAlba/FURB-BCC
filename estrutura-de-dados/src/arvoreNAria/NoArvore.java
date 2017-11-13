package arvoreNAria;
/**
 * @author pedro alba
 */

public class NoArvore<T> {

	private T info;
	private NoArvore<T> filho;
	private NoArvore<T> irmao;

	public NoArvore(T info) {
		this.info = info;
		this.filho = null;
		this.irmao = null;
	}

	protected T getInfo() {
		return info;
	}

	protected void setInfo(T info) {
		this.info = info;
	}

	protected NoArvore<T> getFilho() {
		return filho;
	}

	protected void setFilho(NoArvore<T> filho) {
		this.filho = filho;
	}

	protected NoArvore<T> getIrmao() {
		return irmao;
	}

	protected void setIrmao(NoArvore<T> irmao) {
		this.irmao = irmao;
	}

	public void inserirFilho(NoArvore<T> no) {
		no.irmao = filho;
		filho = no;
	}

	public NoArvore pertence(T info) {
		if (this.info == info)
			return this;

		NoArvore retFilho = null, retIrmao = null;

		if (filho != null) {
			retFilho = filho.pertence(info);
			if (retFilho != null)
				return retFilho;
		}

		if (irmao != null) {
			retIrmao = irmao.pertence(info);
			if (retIrmao != null)
				return retIrmao;
		}

		return null;
	}

	public String imprimePre() {
		String retorno = "" + this.info;
		if (filho != null)
			retorno += "(" + filho.imprimePre() + ")";
		if (irmao != null)
			retorno += "(" + irmao.imprimePre() + ")";
		return retorno;
	}

	public int getAltura() {
		return altura(this);
	}

	public int altura(NoArvore n) {

		int alturaMaxima = -1;
		for (NoArvore p = n.getFilho(); p != null; p = p.getIrmao()) {
			int altura = altura(p);

			if (altura > alturaMaxima)
				alturaMaxima = altura;
		}

		return alturaMaxima + 1;
	}

	// http://www.inf.ufpr.br/carmem/ci057/apostila.pdf
	public int getNivel(NoArvore n, T info) {
		return nivel(n, info, 0);
	}

	private int nivel(NoArvore no, T info, int nivelBase) {

		int nivel;

		if (no == null)
			return -1;
		else if (no.getInfo().equals(info)) {
			return nivelBase;
		} else {
			nivel = nivel(no.getIrmao(), info, nivelBase);
			if (nivel >= 0)
				return nivel;
			else
				return nivel(no.getFilho(), info, nivelBase + 1);
		}

	}

	public boolean isDegenerada(NoArvore no, int altura, int nivelAux) {
		if (no.getFilho() == null) {// folha
			int nivel = no.nivel(no, no.getInfo(), nivelAux);
			if (nivel == --altura) // -1 tolerancia
				return false;
			if (nivel == altura)
				return false;
			else
				return true;
		}
		if (no.getFilho() != null) {// nao é folha, recursivo até achar folha
			nivelAux++;
			return isDegenerada(no.getFilho(), altura, nivelAux);
		} else if (no.getIrmao() == null)
			return false;

		return isDegenerada(no.getIrmao(), altura, nivelAux);
	}

	public int getGrau(NoArvore<T> no, int n) {

		if (no.getFilho() != null) {
			no = no.getFilho();
			n++;
			return getGrau(no, n);
		}

		return n;
	}

	public String imprimePos(NoArvore<T> no) {
		return "";
	}



}
