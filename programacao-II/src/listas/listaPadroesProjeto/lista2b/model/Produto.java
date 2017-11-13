package listas.listaPadroesProjeto.lista2b.model;

public class Produto {

	private String descricao;
	private double valor;
	private double peso;
	
	public Produto(String descricao, double valor, double peso) {
		this.descricao = descricao;
		this.valor = valor;
		this.peso = peso;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	public double getPeso() {
		return peso;
	}
	
}
