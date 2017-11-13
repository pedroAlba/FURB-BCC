package trabalho02.comportamento;

import trabalho02.modelo.Oponente;

public class DefinirComportamentoNormal implements DefinirComportamentoOponente {

	@Override
	public String definirComportamento(Oponente o) {
		if(o.getArmado())
			return "Estou armado e estou atacando!.";
		return "Estou desarmado, então vou me defender!";
	}

}