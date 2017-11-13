package trabalho02.modelo;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import trabalho02.comportamento.Comportamento;

public class Jogo implements Observer {

	// private Jogador jogador;
	private int qtdJogadores;
	private int qtdOponentes;
	private Nivel nivel;
	private ArrayList<Oponente> oponentes;
	private ArrayList<Jogador> jogadores;

	private static Jogo uniqueInstance;

	private Jogo(int qtdJogadores, Nivel nivel) {
		setQtdJogadores(qtdJogadores);
		setNivel(nivel);
		jogadores = new ArrayList<>();
		for (int i = 1; i <= getQtdJogadores(); i++) {
			Jogador j = new Jogador("Jogador " + i);
			j.addObserver(this);
			jogadores.add(j);
		}
		criarOponentes();
	}

	public ArrayList<Oponente> getOponentes() {
		return oponentes;
	}

	public static Jogo getInstance(int qtdJogadores, Nivel n) {
		if (uniqueInstance == null)
			uniqueInstance = new Jogo(qtdJogadores, n);
		return uniqueInstance;
	}

	protected Nivel getNivel() {
		return nivel;
	}

	protected void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	protected int getQtdJogadores() {
		return qtdJogadores;
	}

	protected void setQtdJogadores(int qtdJogadores) {
		this.qtdJogadores = qtdJogadores;
	}

	private void criarOponentes() {
		oponentes = new ArrayList<>();
		if (nivel.name().equals("FACIL")) {
			setQtdOponentes(getQtdJogadores() % 3);
			for (int i = 0; i < getQtdOponentes(); i++)
				oponentes.add(new Oponente(retornaArmado(), retornaComportamento(), "Oponente " + i + 1));
		}
		if (nivel.name().equals("MEDIO")) {
			setQtdOponentes(getQtdJogadores());
			for (int i = 0; i < getQtdOponentes(); i++)
				oponentes.add(new Oponente(retornaArmado(), retornaComportamento(), "Oponente " + i + 1));
		}
		if (nivel.name().equals("DIFICIL")) {
			setQtdOponentes(getQtdJogadores() * 2);
			for (int i = 0; i < getQtdOponentes(); i++)
				oponentes.add(new Oponente(retornaArmado(), retornaComportamento(), "Oponente " + i + 1));
		}
	}

	public int getQtdOponentes() {
		return qtdOponentes;
	}

	protected void setQtdOponentes(int qtdOponentes) {
		if (qtdOponentes < 1)
			this.qtdOponentes = 1;
		else
			this.qtdOponentes = qtdOponentes;
	}

	private Boolean retornaArmado() {
		return Math.random() < 0.5;
	}

	private Comportamento retornaComportamento() {
		int numero = new Random().nextInt(Comportamento.values().length);
		return Comportamento.values()[numero];
	}

	@Override
	public void update(Observable o, Object arg) {

		if (o instanceof Jogador) {
			Jogador j = (Jogador) o;
			String mensagem = j.getMensagem();
			System.out.println("Mensagem " + mensagem);

			for (Jogador jogador : jogadores) {
				jogador.setMensagem(mensagem);
			}
			exibeLog(j);
		}
	}

	private void exibeLog(Jogador j) {
		System.out.println(j.getMensagem());
	}

	public ArrayList<Jogador> getJogadores() {
		return jogadores;
	}

	public void setJogadores(ArrayList<Jogador> jogadores) {
		this.jogadores = jogadores;
	}

}
