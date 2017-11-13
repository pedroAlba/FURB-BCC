package listas.listaRevisao.questao01;

public class Principal {

	public static void main(String[] args) {

		Pessoa silvio = new Pessoa("Silvio", "Santos", "12/12/1830", "S�o Vicente", "Portuguesa");
		Pessoa maria = new Pessoa("Maria", "dos Santos", "12/12/1860", "S�o Vicente");
		Pessoa antonio = new Pessoa("Antonio", "dos Santos", "12/12/1885", "S�o Vicente");
		Pessoa jose = new Pessoa("Jos�", "dos Santos", "12/12/1860", "S�o Vicente");
		Pessoa carlos = new Pessoa("Carlos", "Pereira dos Santos", "23/05/1887", "S�o Paulo");
		Pessoa abravanel = new Pessoa("Abravanel", "dos Santos", "18/07/1920", "S�o Paulo");
		Pessoa aparecida = new Pessoa("Aparecida", "Silveira dos Santos", "14/12/1886", "Curitiba");

		silvio.addFilhos(maria, jose);
		maria.addFilhos(antonio, carlos);
		antonio.addFilho(abravanel);
		jose.addFilho(aparecida);

		LeFilhos(silvio, 0);// 0 == pai

	}

	public static void LeFilhos(Pessoa p, int tipo) {

		String tipoPessoa = "";
		String baseFamilia = "Familia %s, de origem %s, tem como primeiro membro conhecido %s, nascido em %s em %s";

		if (tipo == 0) {

			baseFamilia = String.format(baseFamilia, p.getSobrenome(), p.getOrigem(), p.getNome(), p.getSobrenome(),
					p.getData(), p.getCidade());

			System.out.println(baseFamilia);

		} else if (tipo == 1) {
			tipoPessoa = "  Filho: ";
		} else if (tipo == 2) {
			tipoPessoa = "    Neto: ";
		} else if (tipo == 3) {
			tipoPessoa = "      Bisneto: ";
		}
 
		System.out.println(tipoPessoa + p.getNome() + " - " + p.getData() + " - " + p.getCidade());

		for (int i = 0; i < p.getLista().size(); i++) {
			LeFilhos(p.getLista().get(i), tipo + 1);
		}
	}

}
