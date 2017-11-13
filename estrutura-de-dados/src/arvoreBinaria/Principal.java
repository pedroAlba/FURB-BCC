package arvoreBinaria;

public class Principal {

	public static void main(String[] args) {
		NoArvoreBinaria<Integer> no4 = new NoArvoreBinaria<Integer>(4);
		NoArvoreBinaria<Integer> no5 = new NoArvoreBinaria<Integer>(5);
		NoArvoreBinaria<Integer> no2 = new NoArvoreBinaria<Integer>(2, no4, no5);
		NoArvoreBinaria<Integer> no7 = new NoArvoreBinaria<Integer>(7);
		NoArvoreBinaria<Integer> no6 = new NoArvoreBinaria<Integer>(6, no7, null);
		NoArvoreBinaria<Integer> no3 = new NoArvoreBinaria<Integer>(3, null, no6);
		NoArvoreBinaria<Integer> no1 = new NoArvoreBinaria<Integer>(1, no2, no3);
		ArvoreBinaria<Integer> arvore = new ArvoreBinaria<>();
		arvore.setRaiz(no1);

		//    
		//
		//          1
		//                3
		//            2        6
		//          4   5    7   
		
		arvore.pertence(50); 
		//System.out.println(arvore.toString());
		
		//arvore.imprimeDiagrama();
		
		
		ArvoreBST<Integer> arvoreBST = new ArvoreBST<>();
		arvoreBST.inserir(50);
		arvoreBST.inserir(25);
		arvoreBST.inserir(15);
		arvoreBST.inserir(5);
		arvoreBST.inserir(20);
		arvoreBST.inserir(35);
		arvoreBST.inserir(30);
		arvoreBST.inserir(40);
		arvoreBST.retirar(25);
        arvoreBST.imprimeDiagrama();
        arvoreBST.retirar(15);
        arvoreBST.imprimeDiagrama();
	}
}
