package lista01.questao04;
import java.util.Random;

import lista01.utils.GeraNomeRandomico;

/**
 * Pedro Henrique Pereira Alba,
 * Renato Constancion Filho
 */

public class RestaUm {

	private int qtdSoldados;
	private int nMaximoSorteio;
	private Soldado[] soldados;
	private int numeroSorteado;
	private int ultimaPosicao;

	public RestaUm(int qtdSoldados, int nMaximoSorteio) {
		setQtdSoldados(qtdSoldados);
		setnMaximoSorteio(nMaximoSorteio);
		setNumeroSorteado(0);
		setUltimaPosicao(0);
		gerarSoldado(qtdSoldados);
		System.out.println(imprimeVetor());
	}

	public int getQtdSoldados() {
		return qtdSoldados;
	}

	public void setQtdSoldados(int qtdSoldados) {
		this.qtdSoldados = qtdSoldados;
	}

	public int getnMaximoSorteio() {
		return nMaximoSorteio;
	}

	public void setnMaximoSorteio(int nMaximoSorteio) {
		this.nMaximoSorteio = nMaximoSorteio;
	}

	private Soldado[] getSoldados() {
		return soldados;
	}

	private void setSoldados(Soldado[] soldados) {
		this.soldados = soldados;
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
		soldados = new Soldado[qtd];
		for (int i = 0; i < qtd; i++) {
			soldados[i] = new Soldado(new GeraNomeRandomico().geraNome('m'), (i + 1));
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
		String retorno = "Número sorteado: " + getNumeroSorteado() + " Array: [ ";
		for (int i = 0; i < soldados.length; i++) {
			if (soldados[i].isAtivo())
				retorno += "(" + soldados[i].getNumero() + ") " + soldados[i].getNome() + " | ";
			else
				retorno += "(" + soldados[i].getNumero() + ")" + " Eliminado |";
		}
		retorno = retorno.substring(0, retorno.length() - 2);
		return retorno + " ]";
	}

	public String sorteio() {
		// TODO FAZER FUNCIONAR!!!
		int numero = gerarNumero();
		int passos = 0;
		int ativos = 0;
		int ultimoAtivo = -1;
		for (int i = 0; i < soldados.length; i++) {
			if (soldados[i].isAtivo()) {
				ativos++;
				ultimoAtivo = i;
			}
		}
		if (ativos < 2)
			return "Soldado escolhido: " + soldados[ultimoAtivo];
		// Verifica se o numero é positivo para girar a direito
		if (numero > 0) {
			while (passos != (numero-1)) {
				if (soldados[getUltimaPosicao()].isAtivo()) {
					setUltimaPosicao(getUltimaPosicao() + 1);
					passos++;
				} else
					setUltimaPosicao(getUltimaPosicao() + 1);
				if (getUltimaPosicao() > (soldados.length - 1))
					setUltimaPosicao(0);
			}

		}
		// Se o número for negativo gira a esquerda
		else {
			while (passos != (numero+1)) {
				if (soldados[getUltimaPosicao()].isAtivo()) {
					setUltimaPosicao(getUltimaPosicao() - 1);
					passos--;
				} else 
					setUltimaPosicao(getUltimaPosicao() - 1);
				if (getUltimaPosicao() < 0)
					setUltimaPosicao(soldados.length - 1);
			}
		}

		soldados[getUltimaPosicao()].setAtivo(false);
		atualizaUltimaPosicao(numero);

		// Imprime retorna o vetor com os soldados e eliminados
		return imprimeVetor();
	}

	private void atualizaUltimaPosicao(int numero) {
//		TODO acredito que precise verificar se o numero q vai ser setado não está inativo, este parece ser o problema.
		if(numero>0) {
			setUltimaPosicao(getUltimaPosicao()+1);
			if (getUltimaPosicao() > (soldados.length - 1))
				setUltimaPosicao(0);
		}
		else {
			setUltimaPosicao(getUltimaPosicao()-1);
			if (getUltimaPosicao() < 0)
				setUltimaPosicao(soldados.length - 1);
		}
		
	}

}
