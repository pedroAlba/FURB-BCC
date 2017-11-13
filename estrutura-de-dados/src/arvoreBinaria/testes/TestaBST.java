package arvoreBinaria.testes;

import static org.junit.Assert.*;

import org.junit.Test;

import arvoreBinaria.ArvoreBST;

public class TestaBST {

    @Test
    public void testaInsercao() {
        
        ArvoreBST<Integer> arvoreBST = new ArvoreBST<>();
        arvoreBST.inserir(1);
        arvoreBST.inserir(3);
        arvoreBST.inserir(2);
        arvoreBST.inserir(4);
        arvoreBST.inserir(6);
        arvoreBST.inserir(5);
        assertEquals("(1()( 3( 2()() )( 4()( 6( 5()() )() ) ) ))".trim(), arvoreBST.toString().trim());
        
    }

    @Test
    public void testaRemocao() {
        ArvoreBST<Integer> arvoreBST = new ArvoreBST<>();
        arvoreBST.inserir(1);
        arvoreBST.inserir(3);
        arvoreBST.inserir(2);
        arvoreBST.inserir(4);
        arvoreBST.inserir(6);
        arvoreBST.inserir(5);
        
        arvoreBST.retirar(4);    
        assertEquals("(1()( 3( 2()() )( 6( 5()() )() ) ))".trim() ,arvoreBST.toString().trim());
        
        arvoreBST.retirar(6);
        assertEquals("(1()( 3( 2()() )( 5()() ) ))".trim(), arvoreBST.toString().trim());
        
        arvoreBST.inserir(4);
        arvoreBST.inserir(6);
        arvoreBST.inserir(0);
        
        assertEquals("(1( 0()() )( 3( 2()() )( 5( 4()() )( 6()() ) ) ))".trim(), arvoreBST.toString().trim());
    }
}
