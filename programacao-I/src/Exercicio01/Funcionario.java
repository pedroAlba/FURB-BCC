package Exercicio01;

public class Funcionario extends Pessoa{
		
	private String cargo;
	
	private Float valorHora;

	public Funcionario(String nome, String dataNascimento, char genero, String cargo, Float f1) {
		super(nome, dataNascimento, genero);
		this.cargo = cargo;
		this.valorHora = f1;
		
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Float getValorHora() {
		return valorHora;
	}

	public void setValorHora(Float valorHora) {
		this.valorHora = valorHora;
	}
	
	public Float calculaPagamento(int horas){
		return this.valorHora * horas;
	}
	//adicionei um comentario inutil
}
