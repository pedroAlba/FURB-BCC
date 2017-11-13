package listas.listaRevisao;


public class Familia {
	
	private String origem;
	private String sobrenome;
	private Pessoa primeiro;

	public Pessoa getPrimeiro() {
		return primeiro;
	}
	public void setPrimeiro(Pessoa primeiro) {
		this.primeiro = primeiro;
	}
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

}
