package trabalho01.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import trabalho01.model.SRTErrorException;
import trabalho01.model.SRTWarningException;

public class ArquivoController {

	private final static String PATTERN_LINHA_TEMPO = "\\d{2}:\\d{2}:\\d{2},\\d{3} [-][-][>] \\d{2}:\\d{2}:\\d{2},\\d{3}";
	private final static String PATTERN_LINHA_TEMPO_COORDENADAS = PATTERN_LINHA_TEMPO
			+ " [X]\\d:\\d{2,} [X]\\d\\:\\d{2,} [Y]\\d\\:\\d{2,} [Y]\\d\\:\\d{2,}";
	private final static String PATTERN_NUMERO = "\\d";

	/**
	 * 
	 * Metodo utilizado para analisar um arquivo srt de acordo com as
	 * especificacoes do trabalho.
	 * 
	 * @param arquivo
	 *            arquivo a ser analisdo
	 * @return true se o arquivo for válido, false se for inválido.
	 * @throws SRTErrorException
	 *             quando o arquivo tiver problemas com o formato de dados
	 * @throws SRTWarningException
	 *             quando o arquivo tiver imperfeicoes
	 * @throws IOException
	 */
	public static void analisaArquivo(File arquivo) throws SRTErrorException, SRTWarningException, IOException {

		String nomeDoArquivo = arquivo.getName();
		String ultimaLinhaAnalisada = "-";
		double ultimoTempoAnalisado = 0, ultimoNumeroAnalisado = 0;
		ArrayList<String> listaDeLinhas = new ArrayList<String>();
		FileReader fileReader = new FileReader(arquivo);
		BufferedReader leitorTextobuffer = new BufferedReader(fileReader);

		while (leitorTextobuffer.ready()) {
			listaDeLinhas.add(leitorTextobuffer.readLine());
		}

		leitorTextobuffer.close();

		for (int i = 0; i < listaDeLinhas.size(); i++) {

			String linhaAtual = listaDeLinhas.get(i);

			int valorNumericoDaLinhaAtual = i + 1;
			Pattern patternLinhaTempo = Pattern.compile(PATTERN_LINHA_TEMPO);
			Pattern patternLinhaTempoComCoordenadas = Pattern.compile(PATTERN_LINHA_TEMPO_COORDENADAS);
			Pattern patternNumero = Pattern.compile(PATTERN_NUMERO);

			boolean linhaAtualEhTemporal = patternLinhaTempo.matcher(linhaAtual).matches();
			boolean linhaAtualEhTemporalComCoordenadas = patternLinhaTempoComCoordenadas.matcher(linhaAtual).matches();
			boolean linhaAtualEhNumero = patternNumero.matcher(linhaAtual).matches();
			boolean linhaAtualEhEspaco = linhaAtual.equals("");

			if (linhaAtualEhEspaco) {
				if (ultimaLinhaAnalisada.equals(""))
					throw new SRTErrorException(nomeDoArquivo + " - Erro na linha:  " + valorNumericoDaLinhaAtual
							+ " - Multiplas linhas em branco entre as legendas!");

				ultimaLinhaAnalisada = linhaAtual;

			} else if (linhaAtualEhNumero) {

				int valorDaLinhaAtual = Integer.valueOf(linhaAtual);

				if (valorDaLinhaAtual < ultimoNumeroAnalisado)
					throw new SRTWarningException(nomeDoArquivo + " - Erro na linha: " + valorNumericoDaLinhaAtual
							+ "Os numeros não estão em ordem!");

				ultimoNumeroAnalisado = valorDaLinhaAtual;
				ultimaLinhaAnalisada = linhaAtual;
				continue;
			} else if (linhaAtualEhTemporal || linhaAtualEhTemporalComCoordenadas) {

				String primeiro = "", segundo = "";
				double primeiroValor = 0, segundoValor = 0;
				if (linhaAtualEhTemporal) {
					primeiro = linhaAtual.substring(0, 12);
					segundo = linhaAtual.substring(linhaAtual.length() - 12, linhaAtual.length());

					primeiro = primeiro.replace(":", "");
					primeiro = primeiro.replaceAll(",", "");

					segundo = segundo.replace(":", "");
					segundo = segundo.replaceAll(",", "");

					primeiroValor = Double.valueOf(primeiro);
					segundoValor = Double.valueOf(segundo);
				} else {
					primeiro = linhaAtual.substring(0, 12);
					segundo = linhaAtual.substring(17, 29);

					primeiro = primeiro.replace(":", "");
					primeiro = primeiro.replaceAll(",", "");

					segundo = segundo.replace(":", "");
					segundo = segundo.replaceAll(",", "");

					primeiroValor = Double.valueOf(primeiro);
					segundoValor = Double.valueOf(segundo);

				}

				if (primeiroValor >= segundoValor)
					throw new SRTWarningException(nomeDoArquivo + " - Erro na linha: " + i
							+ " Segundo valor de tempo é maior que o primeiro");

				if (primeiroValor < ultimoTempoAnalisado)
					throw new SRTWarningException(nomeDoArquivo + " - Erro na linha:  " + i
							+ " O tempo de exibição da próxima legenda é anterior ao término da legenda atual.");

				ultimoTempoAnalisado = segundoValor;
				ultimaLinhaAnalisada = linhaAtual;

			} else {

				if (ehNumero(ultimaLinhaAnalisada)) {
					if (!linhaAtualEhTemporal) {
						throw new SRTErrorException(
								nomeDoArquivo + " - Erro na linha:  " + i + " Linha temporal mal formada");
					}
				}
			}
		}
	}

	private static boolean ehNumero(String s) {
		return Pattern.matches("-?\\d+", s);
	}
}
