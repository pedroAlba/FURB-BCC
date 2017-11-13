package lista01.questao02;

/**
 * Pedro Henrique Pereira Alba,
 * Renato Constancion Filho
 */

public class Elemento<T> {
	
	public T elemento;
	private boolean ativo = true;
	
	public Elemento(T elemento) {
		super();
		setElemento(elemento);
	}
	
	public T getElemento() {
		return elemento;
	}
	public void setElemento(T elemento) {
		this.elemento = elemento;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	@Override
	public String toString() {
		return getElemento().toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ativo ? 1231 : 1237);
		result = prime * result + ((elemento == null) ? 0 : elemento.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Elemento other = (Elemento) obj;
		if (ativo != other.ativo)
			return false;
		if (elemento == null) {
			if (other.elemento != null)
				return false;
		} else if (!elemento.equals(other.elemento))
			return false;
		return true;
	}

	
}
