package lista01.questao05;
/**
 * Pedro Henrique Pereira Alba,
 * Renato Constancion Filho
 */

public class Veiculo {

	private String placa;
	private String modelo;
	private int manobras;

	public Veiculo(String placa, String modelo, int manobras) {
		setPlaca(placa);
		setModelo(modelo);

	}

	protected String getPlaca() {
		return placa;
	}

	protected void setPlaca(String placa) {
		this.placa = placa;
	}

	protected String getModelo() {
		return modelo;
	}

	protected void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public int getManobras() {
		return manobras;
	}

	public void setManobras(int manobras) {
		this.manobras = manobras;
	}
	
	public void adicionaManobra(){
		this.manobras++;
	}

	public String toString() {
		return "Veículo " + this.getModelo() + ", placa " + this.getPlaca() + ", manobras " + this.getManobras();
	}

}
