package Exercicio01;

public class Aluno extends Pessoa {

	private Float nota1;
	private Float nota2;
	private Float nota3;

	public Aluno(String nome, String dataNascimento, char genero, Float f1, Float f2, Float f3) {
		super(nome, dataNascimento, genero);
		this.nota1 = f1;
		this.nota2 = f2;
		this.nota3 = f3;

	}

	public Float getNota1() {
		return nota1;
	}

	public void setNota1(Float nota1) {
		this.nota1 = nota1;
	}

	public Float getNota2() {
		return nota2;
	}

	public void setNota2(Float nota2) {
		this.nota2 = nota2;
	}

	public Float getNota3() {
		return nota3;
	}

	public void setNota3(Float nota3) {
		this.nota3 = nota3;
	}

	public Float media() {
		return (this.nota1 + this.nota2 + this.nota3) / 3;
	}

}
