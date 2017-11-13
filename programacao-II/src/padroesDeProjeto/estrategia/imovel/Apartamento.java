package padroesDeProjeto.estrategia.imovel;

import padroesDeProjeto.estrategia.DefinirValorComEdificacao;

public class Apartamento extends Imovel {

	public Apartamento(char localizacao, float espaco, int comodos) {
		super(localizacao, espaco, comodos, DefinirValorComEdificacao.getInstance());
	}
}
