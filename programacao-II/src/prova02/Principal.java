package prova02;
import java.util.ArrayList;

/**
 *
 * @author Pedro Henrique Pereira Alba
 */
public class Principal {
    public static void main(String args[]) {
        // Este ArrayList não pode ser alterado!
        ArrayList<Obra> acervo = new ArrayList();
        
        Autor a1 = new Autor("GAMMA", "Erich",1948);
        Autor a2 = new Autor("Helm","RiCHard", 1952);
        Autor a3 = new Autor("Johnson","RALPH", 1950);
        Autor a4 = new Autor("Vlissides","John", 1954);
        
        // ALTERAÇÕES A PARTIR DESTE PONTO
        
        Obra o1 = new Obra("Design Patterns","Elements of reusable object-Oriented Software",
                            "Addison-Wesley","New Jersey", 1995);
        o1.addAutor(a1);
        o1.addAutor(a2);
        o1.addAutor(a3);
        o1.addAutor(a4);
        
        Obra o2 = new Obra("Use a cabeça!","padrões de projetos",
                            "Alta Books","Rio de Janeiro", 2007);
        o2.addAutor(new Autor("FREEMAN", "Eric",1965));
        o2.addAutor(new Autor("Freeman", "ELISABETH",1968));
        o2.addAutor(new Autor("Kathy", "SieRRa", 1970));
        o2.addAutor(new Autor("Bert", "Bates", 1970));

        
        acervo.add(o1);
        acervo.add(o2);
        
        // PARA ALTERAR ABAIXO
        // definir para a obra 1 que o formato é ISO 690
        // e para a obra 2 que é ABNT NBR 6023:2002
        
        for (Obra o:acervo){
            System.out.println("\nBibliografia:"+o.getBibliografia());
            System.out.println("Citação: "+o.getCitacao());
        }
        
        // definir para a obra 1 que o formato é ABNT NBR 6023:2002
        // e para a obra 2 que é ISO 690
        
        System.out.println("\n\n\n\n\n\n--- Formatações");
        for (Obra o:acervo){
        	String bibliografiaObraABNT = Formatador.getInstance(TipoFormatacao.ABNT_NBR_6023_2002).definirFormatacaoBibliografica(o);
        	System.out.println("\n--------------Formatacao bibliografica ABNT--------------\n");
        	System.out.println(bibliografiaObraABNT);
        	System.out.println("\n--------------Formatacao bibliografica ISO--------------\n");
        	String bibliografiaObraISO = Formatador.getInstance(TipoFormatacao.ISO_690).definirFormatacaoBibliografica(o);
        	System.out.println(bibliografiaObraISO);
        	String referenciaObraABNT = Formatador.getInstance(TipoFormatacao.ABNT_NBR_6023_2002).definirFormatacaoReferencias(o);
        	System.out.println("\n--------------Formatacao referencia ABNT--------------\n");
        	System.out.println(referenciaObraABNT);
        	String referenciaISO = Formatador.getInstance(TipoFormatacao.ISO_690).definirFormatacaoReferencias(o);
        	System.out.println("\n--------------Formatacao referencia ISO--------------\n");
        	System.out.println(referenciaISO);
        }
        

    }
}