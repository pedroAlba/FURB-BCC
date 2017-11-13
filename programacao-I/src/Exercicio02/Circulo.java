package Exercicio02;

import java.math.BigDecimal;

public class Circulo extends FormaGeometrica {

	private BigDecimal raio;

	public Circulo(Long raio) {
		this.raio = new BigDecimal(raio);
	}

	@Override
	public BigDecimal calculaArea() {
		return raio.multiply(new BigDecimal("2")).multiply(new BigDecimal("3.14"));
	}

	@Override
	public BigDecimal calculaPerimetro() {
		return new BigDecimal(2 * 3.14).multiply(raio.multiply(new BigDecimal("2")));
	}

	public BigDecimal getRaio() {
		return raio;
	}

	public void setRaio(BigDecimal raio) {
		this.raio = raio;
	}

	
}
