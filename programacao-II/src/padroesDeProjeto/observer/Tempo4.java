package padroesDeProjeto.observer;

import java.util.Observable;
import java.util.Observer;

public class Tempo4 implements Observer{

	public Tempo4(Observable clima) {
		clima.addObserver(this);
	}
	
	private double umidade;
	private String mensagem;
	
	public String getMensagem() {
		return mensagem;
	}
	
	@Override
	public void update(Observable climaSubject, Object arg1) {
	
		if (climaSubject instanceof Clima) {
			Clima clima = (Clima) climaSubject;

			double umidade = clima.getUmidade();
			
			if(umidade <= 30){
				mensagem = "O ar está seco";
			}else
				mensagem = "O ar está úmido";
			
			
		}}

}
