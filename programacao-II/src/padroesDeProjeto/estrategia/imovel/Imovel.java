package padroesDeProjeto.estrategia.imovel;

import padroesDeProjeto.estrategia.DefinirValorComportamento;

public abstract class Imovel {
	
	private int comodos;
	private float espaco;
	private char localizacao;
	private DefinirValorComportamento dv;

	public Imovel(char localizacao,float espaco,int comodos, DefinirValorComportamento dv) {
		this.comodos = comodos;
		this.espaco = espaco;
		this.localizacao = localizacao;
		this.dv = dv;
	}
	public DefinirValorComportamento getDv() {
		return dv;
	}
	
	public void setDv(DefinirValorComportamento dv) {
		this.dv = dv;
	}
	
	public float getValor(){
		return this.getDv().definirValor(this); 
	}

	public int getComodos() {
		return comodos;
	}

	public void setComodos(int comodos) {
		this.comodos = comodos;
	}

	public float getEspaco() {
		return espaco;
	}

	public void setEspaco(float espaco) {
		this.espaco = espaco;
	}

	public char getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(char localizacao) {
		this.localizacao = localizacao;
	}
}
