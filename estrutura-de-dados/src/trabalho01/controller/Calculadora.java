package trabalho01.controller;

import trabalho01.model.*;

public class Calculadora {

	public static double calculaFixo(String expressao) throws Exception {

		PilhaVetor<Double> p = new PilhaVetor<>(expressao.length() - 1);

		String[] valores = expressao.split(" ");
		for (int j = 0; j < valores.length; j++) {
			if (!ehOperador(valores[j])) {
				Double d = Double.valueOf(valores[j]);
				p.push(d);
			} else {
				Double valor1, valor2, resultado;
				valor1 = p.pop();
				valor2 = p.pop();
				resultado = calcular(valor1, valor2, valores[j]);
				p.push(resultado);
			}
		}
		return p.pop();
	}

	public static double calculaDinamico(String expressao) throws Exception {

		PilhaDinamica<Double> p = new PilhaDinamica<>();
		String[] valores = expressao.split(" ");
		for (int j = 0; j < valores.length; j++) {
			if (!ehOperador(valores[j])) {
				Double d = Double.valueOf(valores[j]);
				p.push(d);
			} else {
				Double valor1, valor2, resultado;
				valor1 = p.pop();
				valor2 = p.pop();
				resultado = calcular(valor1, valor2, valores[j]);
				p.push(resultado);
			}
		}
		return p.pop();
	}

	private static boolean ehOperador(String valor) {
		String valorSemEspaco = valor.trim();
		if (valorSemEspaco.equals("+") || valorSemEspaco.equals("-") || valorSemEspaco.equals("/")
				|| valorSemEspaco.equals("*"))
			return true;
		return false;
	}

	private static Double calcular(Double valor1, Double valor2, String operador) throws Exception {

		switch (operador) {
		case "-":
			return valor2 - valor1;
		case "+":
			return valor2 + valor1;
		case "*":
			return valor2 * valor1;
		case "/":
			if (valor1 == 0 || valor2 == 0)
				throw new Exception("Impossivel dividir por 0");
			return valor2 / valor1;
		default:
			throw new Exception("Operador não reconhecido");
		}
	}
}
