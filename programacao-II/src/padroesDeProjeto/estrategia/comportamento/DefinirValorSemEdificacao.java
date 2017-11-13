package padroesDeProjeto.estrategia.comportamento;

import padroesDeProjeto.estrategia.imovel.Imovel;

public class DefinirValorSemEdificacao implements DefinirValorComportamento {

	@Override
	public float definirValor(Imovel i) {
		float valor = 0;
		float valorComodo = 1000;
		if(i.getLocalizacao() == 'A')
			valor = 1500;
		if(i.getLocalizacao() == 'B')
			valor = 750;
		if(i.getLocalizacao() == 'C')
			valor = 200;
		float resultado = valor * i.getEspaco();
		return resultado;
	}
	
}
