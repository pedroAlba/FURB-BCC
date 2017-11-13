package listas.lista2_factory_adapter.exercicio01;

public class SanduicheFactory {

	public Sanduiche criarSanduiche(String tipo) throws Exception {

		switch (tipo) {
		case "XSalada":
			return new XSalada();
		case "XBurguer":
			return new XBurguer();
		case "Americano":
			return new Americano();
		case "XBacon":
			return new XBacon();
		case "XEggBacon":
			return new XEggBacon();
		case "XTudo":
			return new XTudo();
		default:
			throw new Exception("Tipo de sanduiche inválido");
		}

	}
}
