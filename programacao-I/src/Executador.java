
public class Executador {
	public static void main(String[] args) {
		
		Pessoa pessoaF = new PessoaFisica("Pedro", "123", "Brasil", "123");
		
		Pessoa pessoaJ = new PessoaJuridica("Joao", "456", "Gaspar", "456");
		
		
		if(pessoaJ instanceof PessoaFisica){
			System.out.println(1);
		}
		//o
		if(pessoaJ instanceof PessoaFisica){
			System.out.println(2);
		}
		
	}
}
