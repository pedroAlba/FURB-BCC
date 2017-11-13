package lista02.questao01;
/**
 * 
 * @author Pedro Alba
 * @author Renato Constancio
 */
public enum TipoGrafo {

	DIRIGIDO("Dirigido\n"),
	NAO_DIRIGIDO("Não dirigido\n"),
	SIMPLES("Simples\n"),
	MULTIGRAFO("Multigrafo\n"),
	REGULAR("Regular\n"),
	COMPLETO("Completo\n"), 
	NULO("Nulo\n"), 
	BIPARTIDO("Bipartido\n"),
	EMPTY("");

	private final String text;

	private TipoGrafo(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}
