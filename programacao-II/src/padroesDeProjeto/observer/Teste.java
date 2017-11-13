package padroesDeProjeto.observer;

public class Teste {

	static Tempo1 tempo1;
	static Tempo2 tempo2;
	static Tempo3 tempo3;
	static Tempo4 tempo4;
	
	public static void main(String[] args) {
		
		Clima clima = new Clima();
		tempo1 = new Tempo1(clima);
		tempo2 = new Tempo2(clima);
		tempo3 = new Tempo3(clima);
		tempo4 = new Tempo4(clima);
		
		
		clima.setPressao(10);
		clima.setTemperatura(100);
		clima.setUmidade(20);
		
		getInfo();
		
		clima.setTemperatura(20);
		
		
		getInfo();
		
	}
	
	public static void getInfo(){
		
		System.out.println("-----------------");
		System.out.println(tempo1.getMensagem());
		System.out.println(tempo2.getMensagem());
		System.out.println(tempo3.getMensagem());
		System.out.println(tempo4.getMensagem());
	}
}
