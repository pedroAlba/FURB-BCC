package slideListasLineares;
/**
 *
 * @author Marcel
 */
public class TesteLista {
    public static void main(String[] args){
        ListaComArray<String> lista = new ListaComArray();
        System.out.println(lista.imprime());
        lista.insere("A");
        lista.insere("B");
        System.out.println("Esperado A,B = "+lista.imprime());
        lista.insere("C",0);
        System.out.println("Esperado C,A,B = "+lista.imprime());
        lista.insere("NAO",10);
        System.out.println("Esperado C,A,B = "+lista.imprime());
        int pos = lista.localiza("B");
        System.out.println("Esperado 2 = "+pos);
        pos = lista.localiza("BA");
        System.out.println("Esperado -1 = "+pos);
        String elem = lista.retira(0);
        System.out.println("Esperado C = "+elem);
        System.out.println("Esperado A,B = "+lista.imprime());
        
        ListaComArray<String> lista2 = new ListaComArray();
        lista2.insere("X");
        lista2.insere("Y");
        lista2.insere("Z");
        System.out.println("Lista 2 = "+lista2.imprime());
        
        ListaComArray<String> lista3;
        lista3 = (ListaComArray<String>) lista.concatena(lista2);
        System.out.println("Lista 3 (concatenação de 1 e 2)= "+lista3.imprime());
        
        ListaComArray<String> lista4, lista5;
        lista4 = (ListaComArray<String>) lista3.divide();
        System.out.println("Lista 3 (dividida) = "+lista3.imprime());
        System.out.println("Lista 4 (divisão de 3) = "+lista4.imprime());
        lista4.insere("AA",0);
        System.out.println("Lista 4 com AA na primeira posição = "+lista4.imprime());
        
        lista5 = (ListaComArray<String>) lista4.copia();
        System.out.println("Lista 5 (cópia de 4) = "+lista5.imprime());
        
        
    }
}

