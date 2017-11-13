package listas.lista03.exercicio02;

import java.util.Comparator;

public class AnoPlacaComparator implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {

		Veiculo v1 = (Veiculo) o1;
		Veiculo v2 = (Veiculo) o2;

		Integer ano1 = v1.getAno();
		Integer ano2 = v2.getAno();
		int comparacaoAno = ano2.compareTo(ano1);

		if (comparacaoAno != 0) {
			return comparacaoAno;
		} else {
			return v1.compareTo(v2);
		}

	}

}
