package arvoreNAria;


public class Teste {

	public static void main(String[] args) {
//		NoArvore<Integer> n9 = new NoArvore<Integer>(9);
//		NoArvore<Integer> n10 = new NoArvore<Integer>(10);
//		NoArvore<Integer> n4 = new NoArvore<Integer>(4);
//		n4.inserirFilho(n10);
//		n4.inserirFilho(n9);
//		NoArvore<Integer> n8 = new NoArvore<Integer>(8);
//		NoArvore<Integer> n3 = new NoArvore<Integer>(3);
//		NoArvore<Integer> n11 = new NoArvore<Integer>(11);
//		NoArvore<Integer> n20 = new NoArvore<Integer>(20);
//		NoArvore<Integer> n21 = new NoArvore<Integer>(21);
//		NoArvore<Integer> n22 = new NoArvore<Integer>(22);
//		
//		n21.inserirFilho(n22);
//		n20.inserirFilho(n21);
//		n11.inserirFilho(n20);
//		n8.inserirFilho(n11);
//		
//		n3.inserirFilho(n8);
//		NoArvore<Integer> n5 = new NoArvore<Integer>(5);
//		NoArvore<Integer> n6 = new NoArvore<Integer>(6);
//		NoArvore<Integer> n7 = new NoArvore<Integer>(7);
//		NoArvore<Integer> n2 = new NoArvore<Integer>(2);
//		n2.inserirFilho(n7);
//		n2.inserirFilho(n6);
//		n2.inserirFilho(n5);
//		NoArvore<Integer> n1 = new NoArvore<Integer>(1);
//		n1.inserirFilho(n4);
//		n1.inserirFilho(n3);
//		n1.inserirFilho(n2);
//		Arvore<Integer> a = new Arvore<>();
//		a.setRaiz(n1);
//		
//		
////		System.out.println(a.toString());
////		System.out.println(a.pertence(11));
////		System.out.println(a.pertence(10));
////		System.out.println(a.getAltura())
//		
//		
//		System.out.println(a.getAltura());
//		System.out.println(a.isDegenerada());
//		System.out.println("Nivel do 8 -> " + a.getNivel(8));
//		System.out.println("Nivel do 3 -> " + a.getNivel(3));
//		System.out.println("Nivel do 1 -> " + a.getNivel(1));
//		System.out.println("Nivel inexistente -> " + a.getNivel(3182));
		
		
		NoArvore<Integer> n4 = new NoArvore<Integer>(4);
		NoArvore<Integer> n3 = new NoArvore<Integer>(3);
		n3.inserirFilho(n3);
		
		NoArvore<Integer> n5 = new NoArvore<Integer>(5);
		NoArvore<Integer> n2 = new NoArvore<Integer>(5);
		
		n2.inserirFilho(n3);
		n2.inserirFilho(n5);
		
		NoArvore<Integer> n6 = new NoArvore<Integer>(6);
		
		NoArvore<Integer> n10 = new NoArvore<Integer>(10);
		NoArvore<Integer> n9 = new NoArvore<Integer>(9);
		
		n9.inserirFilho(n10);
		
		NoArvore<Integer> n8 = new NoArvore<Integer>(8);
		NoArvore<Integer> n7 = new NoArvore<Integer>(7);
		
		n7.inserirFilho(n8);
		
		
		NoArvore<Integer> raiz = new NoArvore<Integer>(1);
		
		raiz.inserirFilho(n2);
		raiz.inserirFilho(n5);
		raiz.inserirFilho(n7);
		
		Arvore<Integer> a = new Arvore<>();
		a.setRaiz(raiz);
		
		System.out.println(a.getGrau());
		
		Arvore<Integer> b = new Arvore<>();
		System.out.println(b.getGrau());
		
		System.out.println(a.imprimePosOrdem());
	}

}
