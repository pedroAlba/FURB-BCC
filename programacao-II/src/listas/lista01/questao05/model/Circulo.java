package listas.lista01.questao05.model;

import java.io.Serializable;

public class Circulo extends Forma {

	private double raio;
	
	

	public Circulo(double raio) {
		super();
		this.raio = raio;
	}

	public double getRaio() {
		return raio;
	}

	public void setRaio(double raio) {
		this.raio = raio;
	}
	
	@Override
	public String toString(){
		return "Circulo " + String.valueOf(raio);
	}
}
