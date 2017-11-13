package listas.listaPadroesProjeto.lista2b.comportamento;

public class DefinirValorSedex implements DefinirValorEnvio {

    private static DefinirValorSedex dv = new DefinirValorSedex();
    
    private DefinirValorSedex() {    };
    
    public static DefinirValorSedex getInstance(){
        return dv;
    }
    
    @Override
    public float definirValor(double peso) {

        if (peso < 0.5)
            return 12.5f;
        if (peso > 0.500 && peso <= 0.750)
            return 20.0f;
        if (peso > 0.750 && peso <= 1.2)
            return 30f;
        if (peso > 1.2 && peso <= 2)
            return 45f;
        if (peso > 2) {
            float ret = 46.5f;
            double gramasAdicionais = peso - 2;
            ret += gramasAdicionais * 1.5f;
            return ret;
        }

        throw new IllegalArgumentException("Peso inválido");
    }

}
