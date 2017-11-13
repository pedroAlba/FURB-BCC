package listas.lista2_factory_adapter.exercicio01;

import java.util.ArrayList;

public class Sanduiche {

    private ArrayList<Ingrediente> ingredientes;

    protected Sanduiche() {
        ingredientes = new ArrayList<Ingrediente>();
    }

    public double getPreco() {
        return -1;
    }

    public static Sanduiche comprarSanduiche(String tipo) throws Exception {
        return new SanduicheFactory().criarSanduiche(tipo);
    }

    public void addIngrediente(String ingrediente) {
        ingredientes.add(new Ingrediente(ingrediente));
    }

    public String toString() {
        String s = "";
        for (Ingrediente ing : ingredientes) {
            s += "\t-" + ing.getNome() + "\n";
        }
        return s;
    }

}
