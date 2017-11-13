package Exercicio02;

import java.math.RoundingMode;

public class Executador {
	
	public static void main(String[] args) {
		
		FormaGeometrica quadrado = new Quadrilatero(10l,5l);
		FormaGeometrica circulo = new Circulo(10l);
		FormaGeometrica triangulo = new Triangulo(10d, 20d, 5d);
		
		
		
		System.out.println(quadrado.calculaPerimetro());
		System.out.println(quadrado.calculaArea());
		
		
		System.out.println(circulo.calculaPerimetro().setScale(2, RoundingMode.CEILING));
		System.out.println(circulo.calculaArea().setScale(2, RoundingMode.CEILING));
		
		System.out.println(triangulo.calculaPerimetro());
		System.out.println(triangulo.calculaArea());

		
	}
}
