package lista02.questao02;
/**
 * 
 * @author Pedro Alba
 * @author Renato Constancio
 */
import java.util.ArrayList;
import java.util.List;

public class Pessoa {

	private String nome;
	private List<Pessoa> relacionamentos;
	private int cor;
	private Pessoa pai;
	private int tempoDescobrimento;

	Pessoa(String nome){
		setNome(nome);
		setCor(0); // 0 branco, 1 cinza, 2 preto
		setPai(null);
		setTempoDescobrimento(-1);
	}

	public Pessoa getPai() {
		return pai;
	}

	public void setPai(Pessoa pai) {
		this.pai = pai;
	}

	public int getTempoDescobrimento() {
		return tempoDescobrimento;
	}

	public void setTempoDescobrimento(int tempoDescobrimento) {
		this.tempoDescobrimento = tempoDescobrimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Pessoa> getRelacionamentos() {
		return relacionamentos;
	}

	public int getCor() {
		return cor;
	}

	public void setCor(int cor) {
		this.cor = cor;
	}

	public void addAmigo(Pessoa p) {
		if(this.relacionamentos ==  null)
			this.relacionamentos = new ArrayList<>();

		this.relacionamentos.add(p);
	}

	@Override
	public String toString() {
		return this.nome + " - " + this.relacionamentos.toString();
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
		Pessoa other = (Pessoa) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}