package listaHeranca.Escola;

public class Giz extends Material{
	

	private COR_DA_CAIXA corDaCaixa;
	
	public Giz(FINALIDADE finalidade, COR_DA_CAIXA cor) {
		super(finalidade);
		this.corDaCaixa = cor;
	}

	public String toString(){
		return "Giz";
	}

	public COR_DA_CAIXA getCorDaCaixa() {
		return corDaCaixa;
	}

	public void setCorDaCaixa(COR_DA_CAIXA corDaCaixa) {
		this.corDaCaixa = corDaCaixa;
	}
	
	
}
