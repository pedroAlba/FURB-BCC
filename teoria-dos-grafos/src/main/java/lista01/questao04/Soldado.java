package lista01.questao04;

/**
 * Pedro Henrique Pereira Alba,
 * Renato Constancion Filho
 */

public class Soldado {
	
	private String nome;
	private int numero;
	private boolean ativo;
	
	Soldado(String nome, int numero) {
		setNome(nome);
		setNumero(numero);
		setAtivo(true);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
}
