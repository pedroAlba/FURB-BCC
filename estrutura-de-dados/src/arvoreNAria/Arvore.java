package arvoreNAria;
/**
 * @author pedro alba
 */

public class Arvore<T> {
	
	private NoArvore<T> raiz;
	
	public Arvore(){
		
	}
	
	public void setRaiz(NoArvore<T> no){
		this.raiz = no;
	}
	
	public boolean vazia() {
		return (this.raiz == null);
	}
	
	public NoArvore<T> pertence(T info){
		return raiz.pertence(info);
	}
	
	public String toString(){
		return "(" + raiz.imprimePre() + ")";
	}
	
	public int getAltura(){
		if (this.raiz == null)
			return -1;
		else
			return ((NoArvore<T>) raiz).getAltura();
	}
	
	public int getNivel(T info){
		return ((NoArvore<T>) raiz).getNivel(raiz, info);
	}
	
	public boolean isDegenerada(){
        return raiz.isDegenerada(raiz, getAltura(), 0);
	}

	public int getGrau(){
		if(raiz == null)
			return 0;
		return raiz.getGrau(((NoArvore<T>)raiz),1);
	}
	
	public String imprimePosOrdem(){
		if(raiz == null)
			return "";
		return raiz.imprimePos(((NoArvore<T>)raiz));
	}
}
