package listas.lista01.questao02;

import java.io.File;
import java.util.ArrayList;

public class Utils {

    public static boolean validaArquivoTexto(File file) {

        if (file.isDirectory())
            return false;

        if (!file.toString().endsWith(".txt"))
            return false;

        return true;

    }

    public static ArrayList<String> criptografaLinhas(ArrayList<String> linhas, int parNumero) {

        ArrayList<String> linhasCriptografadas = new ArrayList<String>();
        for (String linha : linhas) {

        	String novaLinha = "";
            for (int i = 0; i < linha.length(); i++) {

                char c = linha.charAt(i);

                int codigoASCII = c + parNumero;

                if (codigoASCII > 255) {
                    int aux = codigoASCII - 255;
                    codigoASCII = aux;
                }

                novaLinha += codigoASCII;                
            }
            linhasCriptografadas.add(novaLinha); // comentario teste
        }

        return linhasCriptografadas;

    }
}
