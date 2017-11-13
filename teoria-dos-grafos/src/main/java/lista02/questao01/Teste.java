package lista02.questao01;
/**
 * 
 * @author Pedro Alba
 * @author Renato Constancio
 */
public class Teste {

	public static void main(String[] args) {
		AnalisaGrafo g = new AnalisaGrafo();
		
		Integer[][] matrizAdj = { 
				{ 0,1,1,1} , 
				{ 1,0,1,1},
				{ 1,1,0,1},
				{ 1,1,1,0},
		};
		
		System.out.println(g.arestasDoGrafo(matrizAdj));
		System.out.println(g.tipoDoGrafo(matrizAdj));		
	}
}
