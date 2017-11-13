package padroesDeProjeto.estrategia.imovel;

import padroesDeProjeto.estrategia.DefinirValorComEdificacao;

public class Casa extends Imovel {

	public Casa(char localizacao, float espaco, int comodos) {
		super(localizacao, espaco, comodos, DefinirValorComEdificacao.getInstance());
	}
}
