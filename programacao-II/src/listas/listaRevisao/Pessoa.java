package listas.listaRevisao;

import java.util.ArrayList;

public class Pessoa {

	private String nome;
	private String sobrenome;
	private String data;
	private String cidade;
	private String origem;
	private ArrayList<Pessoa> lista;

	public Pessoa(String nome, String sobrenome, String data, String cidade) {
		setNome(nome);
		setSobrenome(sobrenome);
		setData(data);
		setCidade(cidade);
		this.lista = new ArrayList<Pessoa>();
	}
	
	public Pessoa(String nome, String sobrenome, String data, String cidade, String origem) {
		setNome(nome);
		setSobrenome(sobrenome);
		setData(data);
		setCidade(cidade);
		this.lista = new ArrayList<Pessoa>();
		this.origem = origem;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getNome() {
		return nome;
	}

	public ArrayList<Pessoa> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Pessoa> lista) {
		this.lista = lista;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public void addFilho(Pessoa filho) {

		this.lista.add(filho);

	}
	public void addFilhos(Pessoa f1, Pessoa f2) {

		this.lista.add(f1);
		this.lista.add(f2);
	}

	public String toString() {
		String r;
		r = nome + " " + sobrenome + " " + data + " " + cidade;
		return r;
	}

	public String imprimirLista() {
		String listaa = "";

		for (Pessoa f : lista) {
			listaa += f.toString() + "\n";
		}

		return listaa;
	}

}
