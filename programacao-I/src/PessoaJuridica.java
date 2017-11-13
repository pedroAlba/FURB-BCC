
public class PessoaJuridica extends Pessoa{

	private String cnpj;

	public PessoaJuridica(String nome, String telefone, String endereco, String cnpj) {
		super(nome, telefone, endereco);
		this.cnpj = cnpj;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	
	
}
