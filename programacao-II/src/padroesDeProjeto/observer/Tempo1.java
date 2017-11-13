package padroesDeProjeto.observer;

import java.util.Observable;
import java.util.Observer;

public class Tempo1 implements Observer {

	private double temperatura;
	private String mensagem;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Tempo1(Observable clima) {
		clima.addObserver(this);
	}

	@Override
	public void update(Observable climaSubject, Object arg1) {
		if (climaSubject instanceof Clima) {
			Clima clima = (Clima) climaSubject;

			temperatura = clima.getTemperatura();

			// trabalhar com a temperatura
			if (temperatura <= 15)
				mensagem = "O tempo está frio";
			if (temperatura > 15 && temperatura <= 27)
				mensagem = "A temperatura está moderada";
			if (temperatura > 27)
				mensagem = "A temperatura está quente";
			
		}
	}

}
