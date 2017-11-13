package trabalho02.modelo;
import java.util.Observable;

public class Jogador extends Observable {
	
	private String nome;
	private int vida;
	private String mensagem;
	

	protected Jogador(String nome) {
		setNome(nome);
		setVida(10);
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	protected String getNome() {
		return nome;
	}

	protected void setNome(String nome) {
		this.nome = nome;
	}

	protected int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
		this.setMensagem(this.nome + " tem " + this.getVida() + " de vida");
		setChanged();
		notifyObservers();
	}



}
