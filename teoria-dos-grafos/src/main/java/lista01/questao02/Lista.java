package lista01.questao02;

/**
 * Pedro Henrique Pereira Alba,
 * Renato Constancion Filho
 */

import java.util.LinkedList;

public class Lista<T> {
	
	public LinkedList<Elemento<T>> lista;
	private int deletados;
	
	public Lista() {
		super();
		lista = new LinkedList<Elemento<T>>();
		setDeletados(0);
	}

	public LinkedList<Elemento<T>> getLista() {
		return lista;
	}

	public void setLista(LinkedList<Elemento<T>> lista) {
		this.lista = lista;
	}

	public int getDeletados() {
		return deletados;
	}

	public void setDeletados(int deletados) {
		this.deletados = deletados;
	}
	
	public boolean adicionar(T e) {
		Elemento<T> elemento = new Elemento<T>(e);
		return lista.add(elemento);
	}
	
	public boolean remover(T e) {
		Elemento<T> elemento = new Elemento<T>(e);
		Elemento<?> elementoNaLista = buscaElemento(elemento);
		if(getDeletados() >= (lista.size()/2)) { //Necessário dividir por 2 para termos a metade
			lista.forEach((elementoTemp) -> {
	            if(!elementoTemp.isAtivo())
	            	lista.remove(elementoTemp);
	        });
		}
		else if(lista.contains(elemento)) {
			elementoNaLista.setAtivo(false);
			setDeletados(getDeletados()+1);
		}
		else
			return false;
		return true;
	}
	
	private Elemento<T> buscaElemento(Elemento<T> e) {
		for(int i = 0; i < lista.size(); i++){
			if(lista.get(i).equals(e)){
				return lista.get(i);
			}
		}
		return null;
	}

	public String toString(){
		//TODO: Refatorar para lambda
		StringBuilder ret = new StringBuilder();
		for (Elemento<T> elemento : lista) {
			if(elemento.isAtivo())
				ret.append(elemento);
		}
		return ret.toString();
	}

}
