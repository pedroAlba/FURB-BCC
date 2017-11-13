package listas.listaRevisao.questao01.resolucao;


import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author Marcel
 */
public class Pessoa {
    private String nome;
    private String localNasc;
    private Date dataNasc;
    private Pessoa paiOuMae;
    private ArrayList<Pessoa> filhos = new ArrayList<Pessoa>();

    // atributos de classe
    private static DateFormat formatador = DateFormat.getDateInstance();
    private static String[] parentesco = {"","Filho ","   Neto ","    Bisneto ","       Tataraneto ","        Tetraneto","          Pentaneto"};

    public String exibirDados(int nivel){
        String str = parentesco[nivel]+" - "
                     + this.nome + " - "
                     + formatador.format(this.dataNasc) + " - "
                     + this.localNasc+"\n";
        
        for (Pessoa f: filhos){
            str += f.exibirDados(nivel+1);
        }
        return str;
    }


    public Pessoa getPessoa(String nome){
        if (nome.equals(this.nome)){ // parâmetro igual ao atributo
            return this;
        }

        for (Pessoa f: filhos){
            Pessoa buscada = f.getPessoa(nome);
            if (buscada != null)
                return buscada;
        }

        return null;
    }

    public void addFilho(Pessoa filho){
        if (filho == null){
            throw new IllegalArgumentException("Filho inválido");
        }
        filhos.add(filho);
        filho.setPaiOuMae(this);
    }
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }



    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        if (nome == null
            || nome.isEmpty()){
            throw new IllegalArgumentException("Nome da pessoa inválido");
        }
        else {
            this.nome = nome;
        }
    }

    /**
     * @return the localNasc
     */
    public String getLocalNasc() {
        return localNasc;
    }

    /**
     * @param localNasc the localNasc to set
     */
    public void setLocalNasc(String localNasc) {
        this.localNasc = localNasc;
    }

    /**
     * @return the dataNasc
     */
    public Date getDataNasc() {
        return dataNasc;
    }

    /**
     * @param dataNasc the dataNasc to set
     */
    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    /**
     * @return the paiOuMae
     */
    public Pessoa getPaiOuMae() {
        return paiOuMae;
    }

    /**
     * @param paiOuMae the paiOuMae to set
     */
    public void setPaiOuMae(Pessoa paiOuMae) {
        this.paiOuMae = paiOuMae;
    }


}
