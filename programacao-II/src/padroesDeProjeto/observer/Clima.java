package padroesDeProjeto.observer;

import java.util.Observable;

public class Clima extends Observable {
 
	private double temperatura;
	private double umidade;
	private int pressao;

	public double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;

		setChanged();
		notifyObservers();

	}

	public double getUmidade() {
		return umidade;
	}

	public void setUmidade(double umidade) {
		this.umidade = umidade;

		setChanged();
		notifyObservers();
	}

	public int getPressao() {
		return pressao;
	}

	public void setPressao(int pressao) {
		this.pressao = pressao;

		setChanged();
		notifyObservers();
	}

}
