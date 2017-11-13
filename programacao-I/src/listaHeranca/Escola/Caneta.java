package listaHeranca.Escola;

public class Caneta extends Material {
	
	private COR cor;
	
	public Caneta(FINALIDADE finalidade, COR cor) {
		super(finalidade);
		this.cor = cor;
	}

	public String toString(){
		return "Caneta";
	}

	public COR getCor() {
		return cor;
	}

	public void setCor(COR cor) {
		this.cor = cor;
	}

	
	
}
