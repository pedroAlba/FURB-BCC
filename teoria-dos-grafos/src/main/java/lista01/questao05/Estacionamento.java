package lista01.questao05;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Pedro Henrique Pereira Alba,
 * Renato Constancion Filho
 */

public class Estacionamento {
	
	private Deque<Veiculo> estacionamento;
	private Deque<Veiculo> estacionamentoTemp;
	private int vagasOcupadas;
	private int vagasTotais;
	
	public Estacionamento(int vagasTotais) {
		super();
		estacionamento = new ArrayDeque<>();
		estacionamentoTemp = new ArrayDeque<>();
		setVagasOcupadas(0);
		setVagasTotais(vagasTotais);
	}

	private int getVagasOcupadas() {
		return vagasOcupadas;
	}

	private void setVagasOcupadas(int vagasOcupadas) {
		this.vagasOcupadas = vagasOcupadas;
	}

	private int getVagasTotais() {
		return vagasTotais;
	}

	private void setVagasTotais(int vagasTotais) {
		this.vagasTotais = vagasTotais;
	}
	
	public boolean entrar(Veiculo veiculo) {
		
		if(existemVagas()) {
			estacionamento.push(veiculo);
			aumentaLotacao();
			return true;
		}
		else
			return false;
	}

	public Veiculo sair(String placa) {
		
		Veiculo retorno = null;
		
//		Verifica se a placa � igual a solicitada;
		
		while(!estacionamento.isEmpty()){
			Veiculo veiculoTemp = estacionamento.pop();
			
//			Se for coloca os dados do ve�culo na string de retorno
			if(placa.equalsIgnoreCase(veiculoTemp.getPlaca())) {
				retorno = veiculoTemp;
				diminuiLotacao();
				break;
			}
//			Se n�o for empilha no estacionamento tempor�rio;
			else {
				estacionamentoTemp.push(veiculoTemp);
			}
		}
		
//		Verifica se h� algum ve�culo no estacionamento tempor�rio
//		Se houve desempilha do estacionamento tempor�rio
		
		if(!estacionamentoTemp.isEmpty()) {
			for(int i = 0 ; i< estacionamentoTemp.size(); i++) {
				Veiculo veiculoTemp = estacionamentoTemp.pop();
				veiculoTemp.adicionaManobra();
				estacionamento.push(veiculoTemp);
			}
		}
		return retorno;
	}

	private void diminuiLotacao() {
		setVagasOcupadas(getVagasOcupadas()-1);
	}
	
	private void aumentaLotacao() {
		setVagasOcupadas(getVagasOcupadas()+1);
	}

	private boolean existemVagas() {
		return getVagasOcupadas() < getVagasTotais();
	}
}
