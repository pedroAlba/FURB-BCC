package model;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
@SuppressWarnings("restriction")
public class Palavra {

	private final SimpleIntegerProperty linha;
	private final SimpleStringProperty resultado;
	private final SimpleStringProperty sequencia;
	private final SimpleStringProperty reconhecimento;

	public Palavra(int linha, String resultado, String sequencia, String reconhecimento) {
		this.linha = new SimpleIntegerProperty(linha);
		this.resultado = new SimpleStringProperty(resultado);
		this.sequencia = new SimpleStringProperty(sequencia);
		this.reconhecimento = new SimpleStringProperty(reconhecimento);
	}

	public int getLinha() {
		return linha.get();
	}

	public String getResultado() {
		return resultado.get();
	}

	public String getSequencia() {
		return sequencia.get();
	}

	public String getReconhecimento() {
		return reconhecimento.get();
	}
}