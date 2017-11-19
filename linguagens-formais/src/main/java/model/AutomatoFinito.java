package model;

public class AutomatoFinito {

	private AutomatoFinito(){};
	
	private static String[][] tabelaTransicao = 
	{      
			{"",  "",    "0",     "1",    "#"},
			{"-", "Q0",   "Q1Q6",  "Q0Q7", ""},
			{"",  "Q1Q6", "Q5Q7",  "Q1Q6", "Q2"},
			{"", "Q0Q7", "Q1Q6",  "Q0Q7", "Q8"},
			{"", "Q5Q7", "Q1Q6",  "Q5Q7", "Q8"},
			{"", "Q2",   "Q3",    "",    ""},
			{"", "Q3",   "Q4",    "",    ""},
			{"*","Q4",   "Q3",    "",    ""},
			{"", "Q8",   "Q4",    "",    ""},
	};
	
	public static Palavra validaPalavra(String palavra, int numeroLinha) {
		
		StringBuilder result = new StringBuilder();
		StringBuilder reconhecimento = new StringBuilder();
        boolean erro = false;
		boolean encontrou = false;        
        int linhaAtual = getIndex();
        for (int linha = 0; linha < palavra.length() && !erro; linha++) {
        	encontrou = false;
            String c = String.valueOf(palavra.charAt(linha));
            reconhecimento.append(tabelaTransicao[linhaAtual][1]);
            reconhecimento.append(",");
            
            for (int coluna = 2; coluna < tabelaTransicao[0].length && !erro && !encontrou; coluna++) {
                if (c.equals(tabelaTransicao[0][coluna])) {
                    linhaAtual = getLine(tabelaTransicao[linhaAtual][coluna]);
                    encontrou = true;
                }
                if (linhaAtual == -1) {
                    erro = true;
                    result.append("erro: palavra inválida");
                    reconhecimento.append(" - ERRO");
                }
                if (coluna == tabelaTransicao[0].length - 1 && !encontrou) {
                    erro = true;
                    if(c.contains("+") || c.contains(";") || c.contains(".")){
                    	result.append("simbolo especial");
                    }else {
                        result.append("erro: símbolo(s) inválido(s)");                    	
                    }
                    reconhecimento.append(" - ERRO");
                }
            }
        }
        if (!erro) {
            if (tabelaTransicao[linhaAtual][0].equals("*")) {
                result.append("palavra válida");
                reconhecimento.append(tabelaTransicao[linhaAtual][1]);
            } else {
                result.append("erro: palavra inválida");
                reconhecimento.append(tabelaTransicao[linhaAtual][1] + ", - ERRO");

            }
        }
        return new Palavra(numeroLinha, result.toString(), palavra, reconhecimento.toString());
	}
	
    private static int getLine(String estado) {
        for (int i = 1; i < tabelaTransicao.length; i++) {
            if (tabelaTransicao[i][1].equals(estado)) {
                return i;
            }
        }
        return -1;
    }
    
    private static int getIndex() {
        for (int i = 1; i < tabelaTransicao.length; i++) {
            if (tabelaTransicao[i][0].equals("-")) {
                return i;
            }
        }
        return -1;
    }
}
