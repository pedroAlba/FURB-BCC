package Exercicio02;

import java.math.BigDecimal;

public class Quadrilatero extends FormaGeometrica {

	private BigDecimal lado1;
	private BigDecimal lado2;

	Quadrilatero(Long l1, Long l2) {
		this.lado1 = new BigDecimal(l1);
		this.lado2 = new BigDecimal(l2);
	}

	@Override
	public BigDecimal calculaArea() {
		return this.lado1.multiply(this.lado2);
	}
	
	@Override
	public BigDecimal calculaPerimetro(){
		return (new BigDecimal("2").multiply(lado1).add(new BigDecimal("2").multiply(lado2)));
	}

	public BigDecimal getLado1() {
		return lado1;
	}

	public void setLado1(BigDecimal lado1) {
		this.lado1 = lado1;
	}

	public BigDecimal getLado2() {
		return lado2;
	}

	public void setLado2(BigDecimal lado2) {
		this.lado2 = lado2;
	}

}
