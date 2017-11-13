package trabalhoFinal;

public class ElementoMapa<K,T> {

	private K key;
	private T value;

	public ElementoMapa(K k, T t){
		this.key = k;
		this.value = t;
	}
	
	public ElementoMapa(){
		
	}
	
	public K getKey() {
		return key;
	}
	public void setKey(K key) {
		this.key = key;
	}
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	
	
}
