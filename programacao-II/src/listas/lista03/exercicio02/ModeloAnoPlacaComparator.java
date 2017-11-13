package listas.lista03.exercicio02;

import java.util.Comparator;

public class ModeloAnoPlacaComparator implements Comparator {
	private Comparator comparador = new AnoPlacaComparator();
	@Override
	public int compare(Object o1, Object o2) {
		Veiculo v1 = (Veiculo) o1;
		Veiculo v2 = (Veiculo) o2;

		String modelo1 = v1.getModelo();
		String modelo2 = v2.getModelo();

		int comparacaoModelo = modelo1.compareTo(modelo2);

		if (comparacaoModelo != 0)
			return comparacaoModelo;
		else {
			return comparador.compare(o1, o2);
		}
	}
}
