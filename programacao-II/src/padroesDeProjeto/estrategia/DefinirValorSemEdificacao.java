package padroesDeProjeto.estrategia;

import padroesDeProjeto.estrategia.imovel.Imovel;

public class DefinirValorSemEdificacao implements DefinirValorComportamento {

	private static DefinirValorSemEdificacao uniqueInstance;

	private DefinirValorSemEdificacao() {
	}

	public static DefinirValorSemEdificacao getInstance() {
		return uniqueInstance == null ? new DefinirValorSemEdificacao() : uniqueInstance;
	}

	@Override
	public float definirValor(Imovel i) {
		float valor = 0;
		if (i.getLocalizacao() == 'A')
			valor = 1500;
		if (i.getLocalizacao() == 'B')
			valor = 750;
		if (i.getLocalizacao() == 'C')
			valor = 200;
		return valor * i.getEspaco();
	}

}
