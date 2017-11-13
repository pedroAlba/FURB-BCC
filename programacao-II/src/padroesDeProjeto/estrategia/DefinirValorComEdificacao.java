package padroesDeProjeto.estrategia;

import padroesDeProjeto.estrategia.imovel.Imovel;

public class DefinirValorComEdificacao implements DefinirValorComportamento {

	private static DefinirValorComEdificacao uniqueInstance;

	private DefinirValorComEdificacao() {
	}

	public static DefinirValorComEdificacao getInstance() {
		return uniqueInstance == null ? new DefinirValorComEdificacao() : uniqueInstance;
	}

	@Override
	public float definirValor(Imovel i) {
		float valor = 0;
		float valorComodo = 1000;
		if (i.getLocalizacao() == 'A')
			valor = 3000;
		if (i.getLocalizacao() == 'B')
			valor = 1000;
		if (i.getLocalizacao() == 'C')
			valor = 500;
		return (valor * i.getEspaco()) + valorComodo * i.getComodos();
	}

}
