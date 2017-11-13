package arvoreBinaria;

abstract class ArvoreBinariaAbstract<T> {

	public NoArvoreBinaria<T> raiz;
	
	public ArvoreBinariaAbstract(){
		
	}
	
	protected void setRaiz(NoArvoreBinaria<T> no){
		this.raiz = no;
	}
	
	public boolean vazia() {
		return (this.raiz == null);
	}
	
	public NoArvoreBinaria<T> pertence(T item){
		return raiz.pertence(item);
	}
	
	@Override
	public String toString(){
		return "(" + raiz.imprimePre() + ")";
	}
	
	public void imprimeDiagrama(){
		BTreePrinter.printNode(raiz);
	}
}
