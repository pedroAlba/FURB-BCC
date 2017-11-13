package listas.listaPadroesProjeto.lista2b.comportamento;

public class DefinirValorPAC implements DefinirValorEnvio {

    private static DefinirValorPAC dv = new DefinirValorPAC();
	
	private DefinirValorPAC() {    };
	
	public static DefinirValorPAC getInstance(){
	    return dv;
	}
	
	public float definirValor(double peso) throws Exception {
		if (peso < 1)
			return 10f;

		if (peso > 1 && peso <= 2)
			return 15f;

		if (peso > 2 && peso <= 3)
			return 20f;

		if (peso > 3 && peso <= 5)
			return 30f;

		if (peso > 5){
		    throw new TipoEntregaInvalido();
		}

		throw new IllegalArgumentException("Peso inválido");
	}

}
