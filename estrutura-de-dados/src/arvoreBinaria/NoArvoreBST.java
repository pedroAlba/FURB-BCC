package arvoreBinaria;

public class NoArvoreBST<T extends Comparable> extends NoArvoreBinaria {

	public NoArvoreBST(T info) {
		super(info);
	}

	public void inserir(T info){
		if(info.compareTo(this.getInfo()) >= 0){
			if(dir == null){
				setDir(new NoArvoreBST(info));
			}
			else
				((NoArvoreBST) dir).inserir(info);
		}
		if(info.compareTo(this.getInfo()) < 0){
			if(esq == null){
				setEsq(new NoArvoreBST(info));
			}
			else
				((NoArvoreBST) esq).inserir(info);
		}
		
	}
	
	public NoArvoreBST<T> busca(T info){
		if(info.equals(this.getInfo()))
			return this;
					
		if(info.compareTo(this.getInfo()) >= 0){
			if(dir == null)
				return null;
			else
				return ((NoArvoreBST) dir).busca(info);
		}
		if(info.compareTo(this.getInfo()) < 0){
			if(esq == null)
				return null;
			else
				return ((NoArvoreBST) esq).busca(info);
		}
		return null;
	}
	
	public NoArvoreBST<T> buscaPai(T info, NoArvoreBST<T> pai){
		
		if(info.equals(this.getInfo()))
			return pai;
					
		if(info.compareTo(this.getInfo()) >= 0){
			if(dir == null)
				return null;
			else
				return ((NoArvoreBST) dir).buscaPai(info, this);
		}
		if(info.compareTo(this.getInfo()) < 0){
			if(esq == null)
				return null;
			else
				return ((NoArvoreBST) esq).buscaPai(info,this);
		}
		return null;
	}
}
