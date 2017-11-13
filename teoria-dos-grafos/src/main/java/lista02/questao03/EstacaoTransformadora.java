package lista02.questao03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class EstacaoTransformadora {

	public static String verificaIntegridade(String lines) throws NumberFormatException, IOException {
		return takeInput();
	}

	private static String takeInput() throws NumberFormatException, IOException {
		String line = "";
		StringTokenizer tokenizer = null;
		StringBuilder retorno = new StringBuilder();

		int contador = 0;

		int qteEstacoes;
		int qteLigacoes;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while ((line = br.readLine()) != null) {
			contador++; // para informar qual é o teste
			tokenizer = new StringTokenizer(line);

			qteEstacoes = Integer.valueOf(tokenizer.nextToken());
			qteLigacoes = Integer.valueOf(tokenizer.nextToken());

			if (qteEstacoes == 0 && qteLigacoes == 0) {
				return "";
			}

			int[][] matriz = new int[qteEstacoes][qteEstacoes]; // matriz para guardar as ligacoes

			for (int i = 0; i < qteEstacoes; i++) {
				for (int j = 0; j < qteEstacoes; j++) {
					matriz[i][j] = 0;
				}
			}

			for (int i = 0; i < qteLigacoes; i++) {
				line = br.readLine();
				tokenizer = new StringTokenizer(line);
				int a = Integer.valueOf(tokenizer.nextToken());
				int b = Integer.valueOf(tokenizer.nextToken());
				matriz[a - 1][b - 1] = 1;
				matriz[b - 1][a - 1] = 1;
			}

			ArrayList<Integer> visitados = new ArrayList<Integer>();

			for (int i = 0; i < qteEstacoes; i++) {
				visitados.add(0);
			}

			buscaProf(matriz, visitados, qteEstacoes, 0);

			int soma = 0;
			for (int i = 0; i < qteEstacoes; i++) {
				soma += visitados.get(i);
			}

			/* Imprimindo resultado */
			retorno.append("Teste " + contador);
			System.out.println("Teste " + contador);

			if (soma != qteEstacoes) {
				System.out.println("falha");
				retorno.append("falha");
			} else {
				System.out.println("normal");
				retorno.append("normal");
			}

			System.out.println();
		}
		return retorno.toString();

	}

	public static void buscaProf(int[][] matriz, ArrayList<Integer> visitados, int qteEstacoes, int ponto) {
		visitados.set(ponto, 1);
		for (int i = 0; i < qteEstacoes; i++) {
			if (matriz[ponto][i] == 1) {
				if (visitados.get(i) == 0) {
					buscaProf(matriz, visitados, qteEstacoes, i);
				}
			}
		}

	}

}
