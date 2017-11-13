package listas.lista01.questao05.model;

public class Retangulo extends Forma {

	private double lado, altura;

	
	public Retangulo(double lado, double altura) {
		super();
		this.lado = lado;
		this.altura = altura;
	}

	public double getLado() {
		return lado;
	}

	public void setLado(double lado) {
		this.lado = lado;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}
	
	@Override
	public String toString(){
		String s = "";
		
		s += " Retangulo - ";
		s += " lado = " + lado;
		s += " altura = " + altura;
		return s;
	}
}
