package listas.listaRevisao.questao01.resolucao;


/**
 *
 * @author Marcel
 */
public class Familia {
    private String nome;
    private String origem;
    private Pessoa primeiro;

    public String toString(){
        return this.nome+" - "+this.origem;
    }

    public Pessoa getPessoa(String nome){
        try {
            return primeiro.getPessoa(nome);
        } catch (NullPointerException npe){
            throw new SemPrimeiroException();
        }
    }

    public String exibirDados() {
        String str = "Familia "+this.nome
                     +" de origem "+this.origem
                     +" tem como primeiro membro conhecido "
                     + primeiro.exibirDados(0);
        return str;
    }

    public void addPessoa(String nomePaiMae, Pessoa pessoa){
        try {
            Pessoa paiMae = this.getPessoa(nomePaiMae);
            paiMae.addFilho(pessoa);
        } catch (NullPointerException npe){
            throw new IllegalArgumentException("Nome de pai/mãe não localizado");
        }
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
            || nome.length() < 3){
            throw new IllegalArgumentException("Nome da família inválido");
        }
        else {
            this.nome = nome;
        }
    }

    /**
     * @return the origem
     */
    public String getOrigem() {
        return origem;
    }

    /**
     * @param origem the origem to set
     */
    public void setOrigem(String origem) {
        this.origem = origem;
    }

    /**
     * @return the primeiro
     */
    public Pessoa getPrimeiro() {
        return primeiro;
    }

    /**
     * @param primeiro the primeiro to set
     */
    public void setPrimeiro(Pessoa primeiro) {
        this.primeiro = primeiro;
    }


}
