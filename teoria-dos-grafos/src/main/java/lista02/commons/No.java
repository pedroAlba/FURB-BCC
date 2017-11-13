package lista02.commons;
/**
 * 
 * @author Pedro Alba
 * @author Renato Constancio
 */
import java.util.ArrayList;

public class No {

	private String nome;
	private ArrayList<No> adjacentes;
	private int cor;
	private No pai;
	private int tempoDescobrimento;

	public No(String nome) {
		this.nome = nome;
		adjacentes = new ArrayList<>();
	}

	public void addAdjacentes(No n) {
		if(!adjacentes.contains(n))
			adjacentes.add(n);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<No> getAdjacentes() {
		return adjacentes;
	}

	public void setAdjacentes(ArrayList<No> adjacentes) {
		this.adjacentes = adjacentes;
	}



	public int getCor() {
		return cor;
	}

	public void setCor(int cor) {
		this.cor = cor;
	}

	public No getPai() {
		return pai;
	}

	public void setPai(No pai) {
		this.pai = pai;
	}

	public int getTempoDescobrimento() {
		return tempoDescobrimento;
	}

	public void setTempoDescobrimento(int tempoDescobrimento) {
		this.tempoDescobrimento = tempoDescobrimento;
	}

	@Override
	public String toString() {
		return this.getNome() + this.getAdjacentes().toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		No other = (No) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}


}
