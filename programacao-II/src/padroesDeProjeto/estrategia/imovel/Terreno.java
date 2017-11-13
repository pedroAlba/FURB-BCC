package padroesDeProjeto.estrategia.imovel;

import padroesDeProjeto.estrategia.DefinirValorSemEdificacao;

public class Terreno extends Imovel {

	public Terreno(char localizacao, float espaco) {
		super(localizacao, espaco, 0, DefinirValorSemEdificacao.getInstance());
	}
}
