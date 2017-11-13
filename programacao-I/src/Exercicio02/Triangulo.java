package Exercicio02;

import java.math.BigDecimal;

public class Triangulo extends FormaGeometrica {

	private BigDecimal base;
	private BigDecimal altura;
	private BigDecimal hipotenusa;

	public Triangulo(Double base, Double altura, Double hipotenusa) {
		this.base = new BigDecimal(base);
		this.altura = new BigDecimal(altura);
		this.hipotenusa = new BigDecimal(hipotenusa);
	}

	@Override
	public BigDecimal calculaArea() {
		return base.multiply(altura)
				   .divide(new BigDecimal("2"));
	}

	@Override
	public BigDecimal calculaPerimetro() {
		return base.add(altura)
				   .add(hipotenusa);
	}

}
