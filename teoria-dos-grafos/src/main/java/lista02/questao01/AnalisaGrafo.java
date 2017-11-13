package lista02.questao01;
/**
 * 
 * @author Pedro Alba
 * @author Renato Constancio
 */
import java.util.ArrayList;

import lista02.commons.Aresta;
import lista02.commons.No;

public class AnalisaGrafo {

	public String tipoDoGrafo(Integer[][] matrizAdjacencia) {

		StringBuilder retorno = new StringBuilder();

		//Simples ou multigrafo
		retorno.append(verificaMultigrafo(matrizAdjacencia));

		//Nulo
		retorno.append(verificaNulo(matrizAdjacencia));

		//Bipartido
		retorno.append(verificaBipartido(matrizAdjacencia));

		//Dirigido ou não dirigido
		retorno.append(verificaDirigido(matrizAdjacencia));

		//Regular
		retorno.append(verificaRegular(matrizAdjacencia));

		//Completo


		return retorno.toString();

	}

	private TipoGrafo verificaRegular(Integer[][] matrizAdjacencia) {

		for (int i = 0; i < matrizAdjacencia.length; i++) {
			for (int j = 0; j < matrizAdjacencia[0].length; j++) {
				if(i==j && matrizAdjacencia[i][j] != 0)
					return TipoGrafo.EMPTY;
				else
					if( matrizAdjacencia[i][j]!= 1)
						return TipoGrafo.EMPTY;
			}
		}
		return TipoGrafo.REGULAR;
	}

	private TipoGrafo verificaBipartido(Integer[][] matrizAdjacencia) {
		if(contaArestas(matrizAdjacencia) % 2 == 0) {
			return TipoGrafo.BIPARTIDO;
		}
		return TipoGrafo.EMPTY;
	}

	public String arestasDoGrafo(Integer[][] matrizAdjacencia) {
		ArrayList<No> nos = new ArrayList<>();
		ArrayList<Aresta> arestas = new ArrayList<>();
		for (int i = 0; i < matrizAdjacencia.length; i++) {
			nos.add(new No(String.valueOf(i)));
		}

		for (int i = 0; i < nos.size(); i++) {
			for (int j = 0; j < matrizAdjacencia[i].length; j++) {
				if(matrizAdjacencia[i][j] > 0)
					arestas.add(new Aresta(nos.get(i), nos.get(j)));
			}
		}

		String retorno = null;
		for (int i = 0; i < arestas.size(); i++) {
			retorno+= arestas.get(i).toString() + "\n";
		}
		return retorno;
	}

	public String grausDoVertice(Integer[][] matrizAdjacencia) {
		String retorno = null;
		if (verificaDirigido(matrizAdjacencia).equals(TipoGrafo.DIRIGIDO)) {
			retorno = contaGrauDirigido(matrizAdjacencia);
		} else {
			retorno = contaGrauNaoDirigido(matrizAdjacencia);
		}
		return retorno;
	}

	private String contaGrauDirigido(Integer[][] matrizAdjacencia) {
		String retorno = null;
		retorno = "GS " + contaGrauNaoDirigido(matrizAdjacencia) + " | GE ";
		for (int i = 0; i < matrizAdjacencia.length; i++) {
			retorno += "[" + i + "] :";
			int soma = 0;
			for (int j = 0; j < matrizAdjacencia[0].length; j++) {
				soma += matrizAdjacencia[j][i];
			}
			retorno += soma + "/n";
		}
		return retorno;
	}

	private String contaGrauNaoDirigido(Integer[][] matrizAdjacencia) {
		String retorno = null;
		for (int i = 0; i < matrizAdjacencia.length; i++) {
			retorno += "[" + i + "] :";
			int soma = 0;
			for (int j = 0; j < matrizAdjacencia[0].length; j++) {
				soma += matrizAdjacencia[i][j];
			}
			retorno += soma + "/n";
		}
		return retorno;
	}

	private TipoGrafo verificaNulo(Integer[][] matrizAdjacencia) {
		for (int i = 0; i < matrizAdjacencia.length; i++) {
			for (int j = 0; j < matrizAdjacencia[0].length; j++) {
				if (matrizAdjacencia[i][j] > 0)
					return TipoGrafo.BIPARTIDO;
			}
		}
		return TipoGrafo.NULO;
	}

	private TipoGrafo verificaMultigrafo(Integer[][] matrizAdjacencia) {
		boolean multigrafo = false;
		for (int i = 0; i < matrizAdjacencia.length; i++) {
			if (matrizAdjacencia[i][i] != 0) {
				multigrafo = true;
				break;
			}
		}

		if (!multigrafo) {
			boolean paralelas = false;
			for (int i = 0; i < matrizAdjacencia.length; i++) {
				if (!paralelas) {
					for (int j = 0; j < matrizAdjacencia[0].length; j++) {
						if (matrizAdjacencia[i][j] > 1)
							paralelas = true;
					}
				} else
					break;
			}
		}
		return multigrafo ? TipoGrafo.MULTIGRAFO : TipoGrafo.SIMPLES;
	}

	private TipoGrafo verificaDirigido(Integer[][] matrizAdjacencia) {
		for (int i = 0; i < matrizAdjacencia.length; i++) {
			for (int j = 0; j < matrizAdjacencia[0].length; j++) {
				if (matrizAdjacencia[i][j] != matrizAdjacencia[j][i])
					return TipoGrafo.NAO_DIRIGIDO;
			}
		}
		return TipoGrafo.DIRIGIDO;
	}

	private Float contaArestas(Integer[][] matrizAdjacencia) {
		Float arestas = 0f;
		for (int i = 0; i < matrizAdjacencia.length; i++) {
			for (int j = 0; j < matrizAdjacencia[0].length; j++) {
				arestas += matrizAdjacencia[i][j];
			}
		}
		return arestas / 2;
	}


}
