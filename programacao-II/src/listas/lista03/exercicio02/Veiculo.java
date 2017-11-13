package listas.lista03.exercicio02;

import java.util.Comparator;

import listas.lista03.exercicio01.ExecutionTime;

public class Veiculo implements Comparable {

	private String placa;
	private String modelo;
	private int ano;
	private String proprietario;

	public Veiculo(String placa, String modelo, int ano, String proprietario) {
		super();
		this.placa = placa;
		this.modelo = modelo;
		this.ano = ano;
		this.proprietario = proprietario;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getProprietario() {
		return proprietario;
	}

	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}

	@Override
	public String toString() {
		return this.modelo + " - " + this.placa + " - " + this.getAno() + " - " + this.getProprietario();
	}

	@Override
	public int compareTo(Object o) {
		return placa.compareTo(((Veiculo) o).getPlaca());
	}


}
