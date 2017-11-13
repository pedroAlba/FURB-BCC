package trabalho02.comportamento;
import trabalho02.modelo.Oponente;

public class DefinirComportamentoDefensivo implements DefinirComportamentoOponente{

	@Override
	public String definirComportamento(Oponente o) {
		return "Estou me defendendo!";
	}

}