package trabalho02.comportamento;
import trabalho02.modelo.Oponente;

public class DefinirComportamentoAgressivo implements DefinirComportamentoOponente {

	@Override
	public String definirComportamento(Oponente o) {
		return "Estou " + (o.getArmado()? "armado" : "desarmado" ) + " e estou atacando com agressividade!";
	}

}