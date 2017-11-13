package padroesDeProjeto.estrategia.comportamento;

import padroesDeProjeto.estrategia.imovel.Imovel;

public class DefinirValorComEdificacao implements DefinirValorComportamento {

	@Override
	public float definirValor(Imovel i) {
		float valor = 0;
		float valorComodo = 1000;
		if(i.getLocalizacao() == 'A')
			valor = 3000;
		if(i.getLocalizacao() == 'B')
			valor = 1000;
		if(i.getLocalizacao() == 'C')
			valor = 500;
		float resultado = (valor * i.getEspaco()) + valorComodo * i.getComodos();
		return resultado;
	}

}
