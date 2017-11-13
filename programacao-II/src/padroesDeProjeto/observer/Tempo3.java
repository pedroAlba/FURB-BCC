package padroesDeProjeto.observer;

import java.util.Observable;
import java.util.Observer;

public class Tempo3 implements Observer{

	

	
	private int pressao;
	private String mensagem;
	
	public String getMensagem(){
		return this.mensagem;
	}
	
	public Tempo3(Observable clima) {
		clima.addObserver(this);
	}
	
	@Override
	public void update(Observable climaSubject, Object arg1) {
		
		if (climaSubject instanceof Clima) {
			Clima clima = (Clima) climaSubject;
			
			this.pressao = clima.getPressao();
			
			if(pressao < 1013){
				mensagem = "Há maior probabilidade de chuva";
			}else
				mensagem = "Há pouca probabilidade de chuva";
		}
		
	}
}
