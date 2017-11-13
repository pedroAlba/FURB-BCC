package slideListasLineares;
public class TesteDuasListas {
    public static void main(String[] args){
        System.out.println("TESTE COM LISTAS DE AMBAS AS CLASSES");
        System.out.println("\nJo�o e Maria v�o ao supermercado, com uma �nica lista de compras.");
        Lista<String> lista = new ListaEncadeada();
        lista.insere("arroz");
        lista.insere("batata");
        lista.insere("caf�");
        lista.insere("detergente");
        System.out.println("Esperado arroz, batata, caf�, detergente = "+lista.imprime());
        System.out.println("\nMaria lembrou que tamb�m precisava de f�sforos e anotou no in�cio da lista.");
        lista.insere("f�sforos",0);
        System.out.println("Esperado f�sforos, arroz, batata, caf�, detergente = "+lista.imprime());
        lista.insere("N�O",10);
        System.out.println("Esperado f�sforos, arroz, batata, caf�, detergente = "+lista.imprime());
        System.out.println("Jo�o passou na frente da g�ndola dos cereais e verificou se arroz e feij�o estavam na lista");    
        int pos = lista.localiza("arroz");
        System.out.println("Esperado 1 = "+pos);
        pos = lista.localiza("feij�o");
        System.out.println("Esperado -1 = "+pos);
        System.out.println("Como arroz estava na lista, pegou arroz e riscou da lista.");    
        String elem = lista.retira(1);
        System.out.println("Esperado arroz = "+elem);
        System.out.println("Agora a lista tem (Esperado f�sforos, batata, caf�, detergente) = "+lista.imprime());
        
        System.out.println("\nMaria lembrou que j� havia outra lista de supermercado, que estava em seu bolso:");
        Lista<String> lista2 = new ListaComArray();
        lista2.insere("tomate");
        lista2.insere("cebola");
        lista2.insere("cenoura");
        System.out.println("Lista 2(Array) Esperado tomate,cebola,cenoura = "+lista2.imprime());
        System.out.println("Como estava na frente da g�ndola da cebola, j� pegou e tirou da lista, mas lembrou que tamb�m precisava de ovos:");
        elem = lista2.retira(1);
        lista2.insere("ovos",0);
        System.out.println("Esperado ovos,tomate,cenoura = "+lista2.imprime());
        

        System.out.println("\nComo estavam com pressa, resolveram juntar as duas listas");
        Lista lista3, lista3A;
        lista3 = lista.concatena(lista2);
        System.out.println("Lista Jo�o Esperado f�sforos,batata,caf�,detergente,ovos,tomate,cenoura = "+lista3.imprime());
        lista3A = lista2.concatena(lista);
        System.out.println("Lista Maria Esperado ovos,tomate,cenoura,f�sforos,batata,caf�,detergente = "+lista3A.imprime());

        System.out.println("E at� fizeram uma confus�o, juntando tudo de novo... \nEsperado f�sforos; batata; caf�; detergente; ovos; tomate; cenoura; ovos; tomate; cenoura; f�sforos; batata; caf�; detergente");
        lista3 = lista3.concatena(lista3A);
        System.out.println("Nova Lista = "+lista3.imprime());
        
        System.out.println("\nPara resolver o problema, dividiram a lista ");
        Lista lista4, lista5;
        lista4 = lista3.divide();
        System.out.println("Jo�o (dividida) Esperado = f�sforos; batata; caf�; detergente; ovos; tomate; cenoura\n"+lista3.imprime());
        System.out.println("Maria (divis�o da Nova lista) Esperado = ovos; tomate; cenoura; f�sforos; batata; caf�; detergente\n"+lista4.imprime());
        System.out.println("E Maria anotou mais uma coisa: chá");
        lista4.insere("chá",0);
        System.out.println("Lista Maria Esperado = ch�; ovos; tomate; cenoura; f�sforos; batata; caf�; detergente\n"+lista4.imprime());

        System.out.println("E Maria entregou uma c�pia da lista para Jo�o Esperado = ch�; ovos; tomate; cenoura; f�sforos; batata; caf�; detergente");
        lista5 = lista4.copia();
        System.out.println("Jo�o copiada de Maria = "+lista5.imprime());

        System.out.println("Por fim, Maria ainda acrescenta em sua lista mais um item: esponja\nEsperado = ch�; ovos; tomate; cenoura; f�sforos; batata; caf�; detergente; esponja");
        lista4.insere("esponja");
        System.out.println("Lista Maria = "+lista4.imprime());
        System.out.println("Mas do Jo�o permanece a mesma\nEsperado = ch�; ovos; tomate; cenoura; f�sforos; batata; caf�; detergente\n\t\t "+lista5.imprime());
        
    }
}