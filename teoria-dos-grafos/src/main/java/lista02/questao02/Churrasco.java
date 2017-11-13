package lista02.questao02;
/**
 * 
 * @author Pedro Alba
 * @author Renato Constancio
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import lista02.commons.Aresta;
import lista02.commons.Grafo;
import lista02.commons.No;

/**
 * https://www.urionlinejudge.com.br/judge/pt/problems/view/1923
 */
public class Churrasco {

	private Integer qtdRelacoes;
	private Integer grauMaximo;
	private ArrayList<No> listaAdjacencia;
	private LinkedList<No> filaBFS;

	public Churrasco(String txt) {
		listaAdjacencia = new ArrayList<>();
		filaBFS = new LinkedList<>();
		separaTexto(txt);
		
	}

	public String bfs() {
//		Seto a pessoa inicial, seu pai como nulo, tempo de descobrimento inicial
		No inicial = listaAdjacencia.get(0);
		inicial.setPai(null);
		inicial.setTempoDescobrimento(0);
//		Adiciono a pessoa inicial na fila para busca em profundidade até a lista estar vazia
		filaBFS.add(inicial);
		while (!filaBFS.isEmpty()) {
//			Removo a pessoa da lista e e adiciono num temporário para facilitar e pego a lista de relacionamentos dela
			No pessoaDaVez = filaBFS.poll();
			for (int i = 0; i < pessoaDaVez.getAdjacentes().size(); i++) {
//				para cada relacionamento da pessoa
//				se for branca adiciono na fila, mudo sua cor cinza, seto seu pai como o da vez e o tempo igual do seu pai mais um
				No temp = pessoaDaVez.getAdjacentes().get(i);
				if(temp.getCor()<1) {
					filaBFS.add(temp);
					temp.setCor(1);
					temp.setPai(pessoaDaVez);
					temp.setTempoDescobrimento(pessoaDaVez.getTempoDescobrimento()+1);
				}
//				se ja foi visitada anteriormente só seto sua cor para preto
				pessoaDaVez.setCor(2);
			}
		}

//		Percorro o vetor verificando quem tem tempo de descobrimento inferior ao grau máximo para convite
		Integer qtd = 0;
		
		List<String> response = new ArrayList<>();
		
		for (No no : listaAdjacencia) {
			if(no.getTempoDescobrimento() <= grauMaximo && no.getTempoDescobrimento() > 0) {
				response.add(no.getNome());
				qtd++;
			}
		}
		
		Collections.sort(response);
		
		StringBuilder retorno = new StringBuilder();		
		
		retorno.append(qtd + "\n");
		for (String string : response) {
			retorno.append(string);
			retorno.append("\n");
		}
		
		return retorno.toString();
	}


	private void separaTexto(String txt) {

		String[] linhas = txt.split("\n");

		ArrayList<Pair<String, String>> lista = new ArrayList<>();

		setQtdRelacoes(Integer.valueOf(linhas[0].split(" ")[0]));

		setGrauMaximo(Integer.valueOf(linhas[0].split(" ")[1]));

		for (int i = 1; i < linhas.length; i++) {
			Pair<String, String> p = new Pair<>(linhas[i].split(" ")[0].trim(), linhas[i].split(" ")[1].trim());
			lista.add(p);
		}

		Grafo grafo = new Grafo();
		List<No> listaDeNos = new ArrayList<>();

		for (Pair<String, String> pair : lista) {

			No left = new No(pair.getLeft());
			No right = new No(pair.getRight());

			if (!listaDeNos.contains(left)) {
				listaDeNos.add(left);
			}

			if (!listaDeNos.contains(right)) {
				listaDeNos.add(right);
			}
		}

		for (Pair<String, String> pair : lista) {
			grafo.adicionaAresta(new Aresta(listaDeNos.get(getIndex(pair.getLeft(), listaDeNos)),
					listaDeNos.get(getIndex(pair.getRight(), listaDeNos))));
		}

		listaAdjacencia = grafo.listaAdjacencias();

	}

	private int getIndex(String n, List<No> listaDeNos) {
		for (int i = 0; i < listaDeNos.size(); i++) {
			if (listaDeNos.get(i).getNome().equals(n)) {
				return i;
			}
		}
		return -1;
	}
	
	public Integer getQtdRelacoes() {
		return qtdRelacoes;
	}

	public void setQtdRelacoes(Integer qtdRelacoes) {
		this.qtdRelacoes = qtdRelacoes;
	}

	public Integer getGrauMaximo() {
		return grauMaximo;
	}

	public void setGrauMaximo(Integer grauMaximo) {
		this.grauMaximo = grauMaximo;
	}

}

