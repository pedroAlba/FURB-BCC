package Exercicio01;

public abstract class Pessoa {

	public Pessoa(String nome, String dataNascimento, char genero) {
		super();
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.genero = genero;
	}
	
	
	private String nome;
	private String dataNascimento;
	private char genero;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public char getGenero() {
		return genero;
	}
	public void setGenero(char genero) {
		this.genero = genero;
	}
	
	public int idade(){
		
		int ano = Integer.parseInt(this.dataNascimento.substring(4, 8));
		
		return 2016 - ano;
	}
}
