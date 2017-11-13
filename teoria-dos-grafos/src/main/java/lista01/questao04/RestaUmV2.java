package lista01.questao04;
import java.util.ArrayList;
import java.util.Random;

import lista01.utils.GeraNomeRandomico;

/**
 * Pedro Henrique Pereira Alba,
 * Renato Constancion Filho
 */

public class RestaUmV2 {

	private int nMaximoSorteio;
	private ArrayList<Soldado> soldadosAtivos;
	private ArrayList<Soldado> soldadosTodos;
	private int numeroSorteado;
	private int ultimaPosicao;

	public RestaUmV2(int qtdSoldados, int nMaximoSorteio) {
		super();
		setnMaximoSorteio(nMaximoSorteio);
		soldadosAtivos = new ArrayList<>();
		soldadosTodos = new ArrayList<>();
		setNumeroSorteado(0);
		setUltimaPosicao(0);
		gerarSoldado(qtdSoldados);
		System.out.println(imprimeVetor());
	}

	private int getnMaximoSorteio() {
		return nMaximoSorteio;
	}

	private void setnMaximoSorteio(int nMaximoSorteio) {
		this.nMaximoSorteio = nMaximoSorteio;
	}

	private int getNumeroSorteado() {
		return numeroSorteado;
	}

	private void setNumeroSorteado(int numeroSorteado) {
		this.numeroSorteado = numeroSorteado;
	}

	private int getUltimaPosicao() {
		return ultimaPosicao;
	}

	private void setUltimaPosicao(int ultimaPosicao) {
		this.ultimaPosicao = ultimaPosicao;
	}

	private void gerarSoldado(int qtd) {
		for (int i = 0; i < qtd; i++) {
			Soldado soldadoTemp = new Soldado(GeraNomeRandomico.geraNome('m'), (i + 1));
			soldadosAtivos.add(soldadoTemp);
			soldadosTodos.add(soldadoTemp);
		}
	}

	private int gerarNumero() {
		int max = getnMaximoSorteio();
		setNumeroSorteado(0);
		Random random = new Random();
		while (getNumeroSorteado() == 0) {
			setNumeroSorteado(random.nextInt(max + 1) * (random.nextBoolean() ? -1 : 1));
		}
		return getNumeroSorteado();
	}

	private String imprimeVetor() {
		String retorno = "Número sorteado: " + getNumeroSorteado() + "\nArray: [ ";
		for (int i = 0; i < soldadosTodos.size(); i++) {
			if (soldadosTodos.get(i).isAtivo())
				retorno += "(" + soldadosTodos.get(i).getNumero() + ") " + soldadosTodos.get(i).getNome() + " | ";
			else
				retorno += "(" + soldadosTodos.get(i).getNumero() + ")" + " Eliminado |";
		}
		retorno = retorno.substring(0, retorno.length() - 2);
		return retorno + " ]";
	}

	public String sorteio() {
		int numero = gerarNumero();
		int passos = 0;
		if (soldadosAtivos.size() < 2)
			return "Soldado escolhido: (" + soldadosAtivos.get(0).getNumero() + ") " + soldadosAtivos.get(0).getNome();
		else {
			if (numero > 0) {
				while (passos != (numero - 1)) {
					setUltimaPosicao(getUltimaPosicao() + 1);
					passos++;
					if (getUltimaPosicao() > (soldadosAtivos.size() - 1))
						setUltimaPosicao(0);
				}
			} else {
				while (passos != (numero + 1)) {
					setUltimaPosicao(getUltimaPosicao() - 1);
					passos--;
					if (getUltimaPosicao() < 0)
						setUltimaPosicao(soldadosAtivos.size() - 1);
				}
			}
		}

		Soldado soldadoTemp = soldadosAtivos.remove(getUltimaPosicao());
		int posicao = soldadosTodos.indexOf(soldadoTemp);
		soldadosTodos.get(posicao).setAtivo(false);

		return imprimeVetor();
	}
}
