package padroesDeProjeto.singleton;

public class Singleton {

	private static Singleton instanciaUnica = new Singleton();
	private Singleton() {

	}
	public static Singleton getInstance() {		
		return instanciaUnica;
	}
	
}
