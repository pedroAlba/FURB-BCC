package listas.lista03.exercicio01;

public class ExecutionTime implements Comparable{

	private String nome;
	private Long tempoTotal;
	private int qtdItens;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getTempoTotal() {
		return tempoTotal;
	}
	public void setTempoTotal(Long tempoTotal) {
		this.tempoTotal = tempoTotal;
	}
	public ExecutionTime(String nome, Long tempoTotal, int qtd) {
		super();
		this.qtdItens = qtd;
		this.nome = nome;
		this.tempoTotal = tempoTotal;
	}
	
	public int getQtdItens() {
		return qtdItens;
	}
		
	@Override
	public int compareTo(Object o) {
		return tempoTotal.compareTo(((ExecutionTime) o).getTempoTotal());
	}
	
	
	
}
