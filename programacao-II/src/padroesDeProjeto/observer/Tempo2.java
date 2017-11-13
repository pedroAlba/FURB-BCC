package padroesDeProjeto.observer;

import java.util.Observable;
import java.util.Observer;

public class Tempo2 implements Observer{

	

	public Tempo2(Observable clima) {
		clima.addObserver(this);

	}
	
	private String mensagem;
	private double maiorTemp, menorTemp;
	
	public String getMensagem(){
		return mensagem;
	}

	@Override
	public void update(Observable climaSubject, Object arg1) {
	
		if (climaSubject instanceof Clima) {
			Clima clima = (Clima) climaSubject;

			double temp = clima.getTemperatura();
			
			
			if(temp < this.menorTemp)
				this.menorTemp = temp;
			
			if(temp > maiorTemp)
				this.maiorTemp = temp;
			}
			mensagem = "A maior temperatura foi de " + maiorTemp; 
			mensagem += "\n A menor temperatura foi de " + menorTemp;
			
		}
		
	}
	
