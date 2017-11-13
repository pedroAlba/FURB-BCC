package lista01.questao05;

/**
 * Pedro Henrique Pereira Alba,
 * Renato Constancion Filho
 */

public class VeiculoFactory {
	
	public Veiculo createVeiculo(){
		return new Veiculo(geraPlaca(), geraModelo(), 0);
	}

	private String geraModelo() {
//		http://exame.abril.com.br/negocios/os-50-carros-mais-vendidos-em-2016/
		String modelo[] = {
				"GM Onix",
				"Hyundai HB20",
				"Ford Ka",
				"VW Gol",
				"Renault Sandero",
				"Honda HR-V",
				"Toyota Corolla",
				"GM Prisma",
				"VW Fox/Cross Fox",
				"Fiat Palio",
				"Fiat Mobi",
				"Jeep Renegade",
				"Jeep Compass",
				"VW Up",
				"Toyota Etios HB",
				"VW Voyage",
				"Honda Civic",
				"Hyundai Creta",
				"Fiat Uno",
				"Hyundai HB20s",
				"Honda Fit",
				"Nissan Kicks",
				"Toyota Etios Sedan",
				"Fiat Siena",
				"GM Cobalt",
				"Ford Ecosport",
				"Renault Duster",
				"Nissan March",
				"GM Spin",
				"Renault Logan",
				"Ford Fiesta",
				"Honda City",
				"Ford Ka Sedan",
				"Nissan Versa",
				"GM Cruze Sedan",
				"Toyota Hilux SW4",
				"Peugeot 208",
				"Peugeot 2008",
				"Citroen C3",
				"Hyundai IX35",
				"GM Tracker",
				"Citroen Aircross",
				"GM Cruze HB",
				"Ford Focus",
				"VW Golf",
				"Ford Focus Sedan",
				"M.Benz Classe Gla",
				"Fiat Weekend",
				"Mitsubishi Outlander",
				"VW Jetta"
		};
		return modelo[(int) (Math.random() * (modelo.length))];
	}

	private String geraPlaca() {
		String letras[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		return letras[(int) (Math.random() * letras.length)] + letras[(int) (Math.random() * letras.length)] + letras[(int) (Math.random() * letras.length)] + "-" + (int) (Math.random() * (10)) + (int) (Math.random() * (10)) + (int) (Math.random() * (10)) + (int) (Math.random() * (10));
	}

}
