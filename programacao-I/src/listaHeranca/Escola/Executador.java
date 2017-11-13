package listaHeranca.Escola;

public class Executador {
	
	public static void main(String[] args) {
		
		
		Inventario inv = new Inventario();
		
		Material giz = new Giz(FINALIDADE.SALA_DE_AULA, COR_DA_CAIXA.COLORIDA);
		
		Material apagador = new Apagador(FINALIDADE.LIMPEZA);
		
		Material caneta = new Caneta(FINALIDADE.SALA_DE_AULA, COR.AZUL);
		

		inv.salvar(caneta, "caneta");
		inv.salvar(apagador, "apagador");
		inv.salvar(giz, "giz");
		
		
		System.out.println(inv.ler());
		
	}
}
