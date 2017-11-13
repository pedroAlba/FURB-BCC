package lista01.exercicio01;

import java.io.IOException;

public class ClasseTeste {

	public static void main(String[] args) throws IOException {

		int[][] matriz = populaMatriz();

		int numero = 45;

		long startTimeNormal = System.nanoTime();

		int[] resultado = normalSearch(matriz, numero);

		long endTimeNormal = System.nanoTime();

		long duration = (endTimeNormal - startTimeNormal); // divide by 1000000
															// to get
															// milliseconds.

		if (resultado[0] != -1 && resultado[1] != -1) {
			System.out.print("Numero " + numero + " - ");
			System.out.println("Linha " + resultado[0] + " Coluna " + resultado[1]);

		}

		System.out.print("tempo procura normal: ");
		System.out.println(duration);

		System.out.println("\n");

		long startTimeBinary = System.nanoTime();

		int[] resultado2 = linearSearch(matriz, numero);

		long endTimeBinary = System.nanoTime();

		long durationBinary = (endTimeBinary - startTimeBinary); // divide by
																	// 1000000
																	// to get
																	// milliseconds.

		if (resultado2[0] != -1 && resultado2[1] != -1) {
			System.out.print("Numero " + numero + " - ");

			System.out.println("Linha " + resultado2[0] + " Coluna " + resultado2[1]);

		}

		System.out.print("tempo procura binaria: ");
		System.out.print(durationBinary);

	}

	public static int[] normalSearch(int[][] matriz, int numero) {

		for (int linha = 0; linha < matriz[0].length; linha++) {

			for (int coluna = 0; coluna < matriz[0].length; coluna++) {

				if (matriz[linha][coluna] == numero) {
					return new int[] { linha, coluna };
				}
			}

		}

		return new int[] { -1, -1 };
	}

	// O(m + n)
	public static int[] linearSearch(int[][] matriz, int numero) {

		int linha = 0;
		int coluna = matriz[0].length - 1;

		while (linha < matriz.length && coluna >= 0) {

			if (matriz[linha][coluna] == numero) {
				return new int[] { linha, coluna };
			} else if (matriz[linha][coluna] > numero) {
				coluna--;
			} else {
				linha++;
			}
		}

		return new int[] { -1, -1 };

	}

	private static int[][] populaMatriz() {

		int[][] matriz = new int[6][6];

		matriz[0][0] = 7;
		matriz[0][1] = 45;
		matriz[0][2] = 56;
		matriz[0][3] = 58;
		matriz[0][4] = 456;
		matriz[0][5] = 476;
		matriz[1][0] = 489;
		matriz[1][1] = 490;
		matriz[1][2] = 762;
		matriz[1][3] = 922;
		matriz[1][4] = 980;
		matriz[1][5] = 1023;
		matriz[2][0] = 1024;
		matriz[2][1] = 1872;
		matriz[2][2] = 1987;
		matriz[2][3] = 2012;
		matriz[2][4] = 2013;
		matriz[2][5] = 2014;
		matriz[3][0] = 2015;
		matriz[3][1] = 3498;
		matriz[3][2] = 3987;
		matriz[3][3] = 8722;
		matriz[3][4] = 8900;
		matriz[3][5] = 9000;
		matriz[4][0] = 9500;
		matriz[4][1] = 9600;
		matriz[4][2] = 9872;
		matriz[4][3] = 9900;
		matriz[4][4] = 9999;
		matriz[4][5] = 10000;
		matriz[5][0] = 10001;
		matriz[5][1] = 35522;
		matriz[5][2] = 72822;
		matriz[5][3] = 92882;
		matriz[5][4] = 97652;
		matriz[5][5] = 100012;

		return matriz;
	}

	private static int[][] populaMatrizFull() {

		try {
			int[][] matriz = new int[10000][10000];

			int valor = 0;

			for (int linha = 0; linha < matriz.length; linha++) {

				for (int coluna = 0; coluna < matriz.length; coluna++) {

					matriz[linha][coluna] = valor;
					valor++;
				}
			}
			return matriz;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}
