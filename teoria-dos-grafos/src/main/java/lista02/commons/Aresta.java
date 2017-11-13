package lista02.commons;
/**
 * 
 * @author Pedro Alba
 * @author Renato Constancio
 */
public class Aresta {

	private No origem;
	private No destino;

	public Aresta(No origem, No destino) {
		this.origem = origem;
		this.destino = destino;
	}

	public No getOrigem() {
		return origem;
	}

	public void setOrigem(No origem) {
		this.origem = origem;
	}

	public No getDestino() {
		return destino;
	}

	public void setDestino(No destino) {
		this.destino = destino;
	}

	public String toString() {
		return "[" + getOrigem() + "," + getDestino() + "]";
	}

}
