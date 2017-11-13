package trabalhoFinal;

public class MapaDispersao <K,T>{

	private ListaDuplamenteEncadeada<ElementoMapa<K,T>>[] mapa;
	
	public MapaDispersao(int quantidade){
		int tamanho = NumeroPrimo.nextPrime(quantidade^2);
		mapa = new ListaDuplamenteEncadeada[tamanho];
		
		for(int i = 0; i< mapa.length; i++){
			mapa[i] = new ListaDuplamenteEncadeada<>();
		}
	}
	
	public Integer calcularHash(K key){
		int ret = Math.abs(key.hashCode() % mapa.length);
		return ret;
	}
	
	public void inserir(K key, T dado){
		int hash = calcularHash(key);
		mapa[hash].insere(new ElementoMapa<K,T>(key,dado));
	}
	
	public T remover(K key){
		ListaDuplamenteEncadeada<ElementoMapa<K, T>> lista = mapa[calcularHash(key)];
		for(int i = 0; i < lista.getTamanho(); i++){
			if(lista.consulta(i).getKey().equals(key)){
				ElementoMapa<K,T> e = lista.retira(i); 
				return e.getValue();
			}
		}
		return null;
	}
	
	public T buscar(K key){
		ListaDuplamenteEncadeada<ElementoMapa<K, T>> lista = mapa[calcularHash(key)];
		for(int i = 0; i < lista.getTamanho(); i++){
			if(lista.consulta(i).getKey().equals(key)){
				ElementoMapa<K,T> e =lista.consulta(i); 
				return e.getValue();
			}
		}
		return null;
	}
}